(ns metro.animations
  (:require [metro.algorithm :as alg]
            [metro.graph]
            [clojure.string]
            [cljsjs.cytoscape]))

(enable-console-print!)

(def g (metro.graph/build-subway-graph
        [{:name "Blue" :stations ["A" "B" "E"]}
         {:name "Red" :stations ["C" "B" "G"]}]))

(defn graph-attrs [graph]
  {:nodes (map (fn [n] {:data {:id n} :classes (clojure.string/join " " (alg/lines g n))}) (loom.graph/nodes graph))
   :edges (map (fn [e] {:data {:id (clojure.string/join e) :source (first e) :target (second e)}})
        (loom.graph/edges graph))})

(js/cytoscape (clj->js {:container (.getElementById js/document "graph")
                        :elements (graph-attrs g)
                        :boxSelectionEnabled true
                        :layout {:name "breadthfirst" :directed true}
                        :style [{:selector "node" :style {"label" "data(id)"}}
                                {:selector "edge" :style {"curve-style" "bezier" "target-arrow-shape" "triangle"
                                                          "width" 4 "line-color" "#888" "target-arrow-color" "#333"}}
                                {:selector ".Blue" :style {"background-color" "blue"}}
                                {:selector ".Red" :style {"background-color" "red"}}
                                {:selector ".Red.Blue" :style {"background-color" "#101"}}]}))
