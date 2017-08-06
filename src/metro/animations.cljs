(ns metro.animations
  (:require [metro.graph]
            [metro.seq]
            [clojure.string]
            [cljsjs.cytoscape]))

(enable-console-print!)

(def config [{:name "Blue" :stations ["Sé" "Liberdade" "São Joaquim"]}
             {:name "Green" :stations ["Sé" "A" "F" "H"]}
             {:name "Red" :stations ["C" "B" "E" "H"]}])

(def g (metro.graph/build-subway-graph config))

(defn graph-attrs
  [graph]
  {:nodes (map
           (fn [n] {:data {:id n}
                    :classes (clojure.string/join " " (metro.graph/lines graph n))})
           (metro.graph/stations graph))
   :edges (map (fn [e] {:data {:id (clojure.string/join e) :source (first e) :target (second e)}})
               (metro.graph/connections graph))})

(def cy (js/cytoscape (clj->js {:container (.getElementById js/document "graph")
                                :elements (graph-attrs g)
                                :boxSelectionEnabled false
                                :layout {:name "breadthfirst" :directed true}
                                :style [{:selector "node" :style {"label" "data(id)" "text-margin-x" "3px"
                                                                  "padding" "10px" "text-halign" "right" "text-valign" "center"}}
                                        {:selector "edge" :style {"curve-style" "bezier" "target-arrow-shape" "triangle"
                                                                  "width" 4 "line-color" "#888" "target-arrow-color" "#333"}}
                                        {:selector ".Blue" :style {"background-color" "blue"}}
                                        {:selector ".Red" :style {"background-color" "red"}}
                                        {:selector ".Green" :style {"background-color" "green"}}
                                        {:selector ".Red.Blue" :style {"background-color" "#101"}}
                                        {:selector ".Red.Green" :style {"background-color" "#101"}}
                                        {:selector ".Red.Blue.Green" :style {"background-color" "orange"}}
                                        {:selector ".highlighted" :style {"border-color" "#87CEFA" "border-width" "10"
                                                                          "line-color" "#87CEFA"
                                                                          "target-arrow-color" "#87CEFA"
                                                                          "transition-property" "background-color, line-color, target-arrow-color"
                                                                          "transition-duration" "0.5s"}}]})))

(defn find-element
  [cy id]
  (.getElementById cy id))

(defn find-predecessor-edges
  [cy graph station]
  (let [preds (metro.graph/predecessors graph station)]
    (when preds
      (map #(find-element cy (str % station)) preds))))

(defn cy-nodes-and-edges
  [cy stations-seq graph]
  (remove nil?
          ;; flattening only one level
          (apply concat
                 (map (fn [station]
                        [(find-predecessor-edges cy graph (:station station))
                         [(find-element cy (:station station))]])
                      stations-seq))))

(defn search-algorithm
  [cy original-seq algorithm-seq]
  (if (empty? algorithm-seq)

    (do
      (.removeClass (.nodes cy) "highlighted")
      (.removeClass (.edges cy) "highlighted")
      (js/setTimeout #(search-algorithm cy original-seq original-seq) 500))

    (do
      (run! #(.addClass % "highlighted") (first algorithm-seq))
      (js/setTimeout #(search-algorithm cy original-seq (rest algorithm-seq)) 500))))

(let [nodes-edges (cy-nodes-and-edges cy (metro.seq/seq-graph g) g)]
  (search-algorithm cy nodes-edges nodes-edges))
