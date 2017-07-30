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
