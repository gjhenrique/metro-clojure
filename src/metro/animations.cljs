(ns metro.animations
  (:require [metro.graph]
            [metro.seq]
            [clojure.string]
            [cljsjs.cytoscape]))

(enable-console-print!)

(def node-style {:selector "node"
                 :style {"label" "data(id)" "text-margin-x" "7px"
                         "padding" "10px" "text-halign" "right" "text-valign" "center"}})

(def edge-style {:selector "edge"
                 :style {"curve-style" "bezier" "target-arrow-shape" "triangle"
                         "width" 4 "line-color" "#888" "target-arrow-color" "#333"}})

(def colors-style [{:selector ".Blue" :style {"background-color" "blue"}}
                   {:selector ".Red" :style {"background-color" "red"}}
                   {:selector ".Green" :style {"background-color" "green"}}
                   {:selector ".Red.Blue" :style {"background-color" "#101"}}
                   {:selector ".Red.Green" :style {"background-color" "#101"}}
                   {:selector ".Red.Blue.Green" :style {"background-color" "orange"}}])

(def highlighted-style {:selector ".highlighted"
                        :style {"border-color" "#87CEFA" "border-width" "10"
                                "line-color" "#87CEFA"
                                "target-arrow-color" "#87CEFA"
                                "transition-duration" "500"}})
(defn- graph-attrs
  [graph]
  {:nodes (map
           (fn [n] {:data {:id n}
                    :classes (clojure.string/join " " (metro.graph/lines graph n))})
           (metro.graph/stations graph))
   :edges (map (fn [e] {:data {:id (clojure.string/join e) :source (first e) :target (second e)}})
               (metro.graph/connections graph))})

(defn- find-element
  [container id]
  (.getElementById container id))

(defn- create-cy
  [container graph layout]
  (js/cytoscape (clj->js {:container (find-element js/document container)
                          :elements (graph-attrs graph)

                          :layout {:name (or layout "breadthfirst") :directed true}
                          :style (concat [node-style edge-style highlighted-style] colors-style) })))

(defn- find-predecessor-edges
  [cy graph station]
  (let [preds (metro.graph/predecessors graph station)]
    (when preds
      (map #(find-element cy (str % station)) preds))))

(defn- cy-nodes-and-edges
  [cy stations-seq graph]
  ;; Removing nodes that don't have successors
  (remove nil?
          ;; flattening only one level
          (apply concat
                 (map (fn [station]
                        [{:type :edge
                          :elems (find-predecessor-edges cy graph (:station station))}
                         {:type :node
                          :elems (find-element cy (:station station))
                          :git-commands (:commands station)
                          :state (assoc (:state station) :graph "loom-graph")}])
                      stations-seq))))

(defn- add-git-commands
  [git-container git-commands]
  (let [fragment (.createDocumentFragment js/document)]
    (run! (fn [command]
            (let [element (.createElement js/document "span")]
              (set! (. element -innerHTML) command)
              (.appendChild fragment element)))
          git-commands)
    (.appendChild git-container fragment)))

(defn- remove-git-commands
  [git-container]
  (set! (. git-container -innerHTML) ""))


(defn- ^:export stop-animation
  [state]
  (js/clearTimeout (:timeout-id @state)))

;; this function is too big. sorry ='|
(defn- ^:export start-animation
  [state]
  (if (empty? (:algorithm-seq @state))
    (do
      (.batch (:cy @state) (fn []
                             (.removeClass (.nodes (:cy @state)) "highlighted")
                             (.removeClass (.edges (:cy @state)) "highlighted")))

      (swap! state assoc :algorithm-seq (:original-seq @state))
      (swap! state assoc :timeout-id (js/setTimeout #(start-animation state)
                                                    (:timeout @state))))

    (do
      (let [element (first (:algorithm-seq @state))]
        (if (= (:type element) :node)
          (do
            (when (:git-container @state)
              (remove-git-commands (:git-container @state))
              (add-git-commands (:git-container @state)
                                (:git-commands element)))

              (.addClass (:elems element) "highlighted"))

          (run! #(.addClass % "highlighted") (:elems element))))

      (swap! state assoc :algorithm-seq (rest (:algorithm-seq @state)))
      (swap! state assoc :timeout-id (js/setTimeout #(start-animation state)
                                                    (:timeout @state))))))

(defn ^:export build-raw-animation
  [containers config]
  (let [{layout :layout graph-container :graph_container} (js->clj containers :keywordize-keys true)
        graph (metro.graph/build-raw-graph (js->clj config :keywordize-keys true))
        cy (create-cy graph-container graph layout)]
    cy))

(defn- choose-function-name
  "Clojurescript mungles the function names.
  That's why we cant pass the function names as string"
  [option]
  (cond
    (= option 1) metro.algorithm-steps/traverse-step1
    (= option 2) metro.algorithm-steps/traverse-step2
    (= option 3) metro.algorithm-steps/traverse-step3
    :default metro.algorithm/traverse-subway-graph))

(defn ^:export build-animation
  [containers config]
  (let [{graph-container :graph_container
         timeout :timeout
         git-container :git_container
         current-node :current_node
         layout :layout
         function-option :traversal_function}
        (js->clj containers :keywordize-keys true)

        traversal-function
        (choose-function-name function-option)

         graph (metro.graph/build-optimized-graph (js->clj config :keywordize-keys true))

         metro-seq (metro.seq/build-seq
                    (traversal-function {:current-node current-node :graph graph})
                    traversal-function)

         cy (create-cy graph-container graph layout)

         nodes-edges (cy-nodes-and-edges cy metro-seq graph)]

    (atom {:cy cy
           :git-container (find-element js/document git-container)
           :original-seq nodes-edges
           :timeout (or timeout 500)
           :algorithm-seq nodes-edges})))
 

[
 {:name "Red",
  :stations ["A", "C"]},
 {:name "Green",
  :stations ["B", "C"]}
 ]
