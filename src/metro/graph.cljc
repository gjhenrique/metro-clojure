(ns metro.graph
  (:require [loom.graph :as graph]
            [loom.attr :as attr]))

(defn lines-inside
  [station subway-info]
  (map :name
       (filter
        (fn [line]
          (some #(= station %) (:stations line)))
        subway-info)))

(defn all-stations
  [subway-info]
  (set
   (flatten
    (map :stations subway-info))))

(defn build-attributes
  [graph subway-info]
  (reduce
   (fn [g station]
     (attr/add-attr g station :lines (lines-inside station subway-info)))
   graph
   (all-stations subway-info)))

(defn lines
  [graph node]
  (attr/attr graph node :lines))

(defn stations
  [graph]
  (loom.graph/nodes graph))

(defn connections
  [graph]
  (loom.graph/edges graph))

(defn predecessors
  [graph station]
  (graph/predecessors graph station))

(defn successors
  [graph station]
  (graph/successors graph station))

(defn build-raw-graph
  [subway-info]
  (apply graph/digraph (map
                        #(apply graph/digraph (partition 2 1 (:stations %)))
                        subway-info)))

(defn build-subway-graph
  [subway-info]
  (-> subway-info
      (build-raw-graph)
      (build-attributes subway-info)))
