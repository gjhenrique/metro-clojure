(ns metro.animations
  (:require [metro.algorithm]
            [metro.graph]
            [metro.seq]
            [clojure.string]
            [cljsjs.cytoscape]))

(enable-console-print!)

(def config [{:name "Blue" :stations ["A" "B" "G"]}
             {:name "Green" :stations ["D" "B" "F" "H"]}
             {:name "Red" :stations ["C" "B" "E" "H"]}])

(def g (metro.graph/build-subway-graph config))

(defn graph-attrs
  [graph]
  {:nodes (map
           (fn [n] {:data {:id n}
                    :classes (clojure.string/join " " (metro.graph/lines graph n))})
           (metro.graph/stations graph))
   :edges (map (fn [e] {:data {:id (clojure.string/join e) :source (first e) :target (second e)}})
               (loom.graph/edges graph))})

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
                                        {:selector ".Red.Blue.Green" :style {"background-color" "orange"}}
                                        {:selector ".highlighted" :style {"background-color" "olive" "line-color" "olive"
                                                                          "target-arrow-color" "olive"
                                                                          "transition-property" "background-color, line-color, target-arrow-color"
                                                                          "transition-duration" "0.5s"}}]})))

(defn search-algorithm
  [cy seqa config]
  (if (empty? seqa)

    (do
      (.removeClass (.nodes cy) "highlighted")
      (js/setTimeout #(search-algorithm cy (metro.seq/seq-graph config) config) 1000))

    (do
      (.addClass (.getElementById cy (:station (first seqa))) "highlighted")
      (js/setTimeout #(search-algorithm cy (rest seqa) config) 1000))))

(search-algorithm cy (metro.seq/seq-graph config) config)
