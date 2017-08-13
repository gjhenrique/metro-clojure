(ns metro.animations
  (:require [metro.graph]
            [metro.seq]
            [clojure.string]
            [cljsjs.cytoscape]))

(enable-console-print!)

(def graph-layout {:name "breadthfirst" :directed true})

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
(defn graph-attrs
  [graph]
  {:nodes (map
           (fn [n] {:data {:id n}
                    :classes (clojure.string/join " " (metro.graph/lines graph n))})
           (metro.graph/stations graph))
   :edges (map (fn [e] {:data {:id (clojure.string/join e) :source (first e) :target (second e)}})
               (metro.graph/connections graph))})

(defn find-element
  [container id]
  (.getElementById container id))

(defn create-cy
  [container graph]
  (js/cytoscape (clj->js {:container (find-element js/document container)
                          :elements (graph-attrs graph)
                          :layout graph-layout
                          :style (concat [node-style edge-style highlighted-style] colors-style) })))

(defn find-predecessor-edges
  [cy graph station]
  (let [preds (metro.graph/predecessors graph station)]
    (when preds
      (map #(find-element cy (str % station)) preds))))

(defn cy-nodes-and-edges
  [cy stations-seq graph]
  ;; Removing nodes that don't have successors
  (remove nil?
          ;; flattening only one level
          (apply concat
                 (map (fn [station]
                        [
                         {
                          :type :edge
                          :elems (find-predecessor-edges cy graph (:station station))
                          }

                         {
                          :type :node
                          :elems (find-element cy (:station station))
                          :git-commands (:commands station)
                          }
                         ])
                      stations-seq))))

(defn add-git-commands
  [git-container git-commands]
  (let [fragment (.createDocumentFragment js/document)]
    (run! (fn [command]
           (let [element (.createElement js/document "div")]
             (set! (. element -innerHTML) command)
             (.appendChild fragment element)))
          git-commands)
    (.appendChild git-container fragment)))

(defn remove-git-commands
  [git-container]
  (set! (. git-container -innerHTML) ""))

(defn iterate-animation
  [cy git-container original-seq algorithm-seq]
  (if (empty? algorithm-seq)
    (do
      (when git-container
        (remove-git-commands git-container))

      (.batch cy (fn []
                   (.removeClass (.nodes cy) "highlighted")
                   (.removeClass (.edges cy) "highlighted")))

      (js/setTimeout #(iterate-animation cy git-container original-seq original-seq) 500))

    (do
      (let [element (first algorithm-seq)]
        (if (= (:type element) :node)
            (do
              (when git-container
                (add-git-commands git-container
                                 (:git-commands element)))

              (.addClass (:elems element) "highlighted"))

            (run! #(.addClass % "highlighted") (:elems element))))

      (js/setTimeout #(iterate-animation cy git-container original-seq (rest algorithm-seq)) 500))))

(defn ^:export start-animation
  [containers config]
  (let [{graph-container :graph_container, git-container :git_container} (js->clj containers :keywordize-keys true)
        graph (metro.graph/build-subway-graph (js->clj config :keywordize-keys true))
        metro-seq (metro.seq/seq-graph graph)
        cy (create-cy graph-container graph)
        nodes-edges (cy-nodes-and-edges cy metro-seq graph)]
    (iterate-animation cy (find-element js/document git-container) nodes-edges nodes-edges)))
