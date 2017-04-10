(ns metro.graph
  (:require [clojure.java.io :as io]
            [loom.graph :refer :all]
            [loom.io :refer :all]
            [loom.label :refer :all]
            [loom.attr :refer :all]
            [loom.alg :refer :all]
            [clojure.data.json :as json]
            [clojure.string :as str])
  (:gen-class))

(defn subway-schema
  []
  (json/read-json (-> "res/sp.json"
                      (io/file)
                      (slurp))))

(defn print-chart
  [graph]
  (view graph)) 

(defn build-graph-line
  [line]
  (let [stations (:stations line)
        name (:name line)]
    (add-attr-to-nodes 
     (apply digraph (partition 2 1 stations))
     :line name stations)))

(defn build-subway-graph
  [subway-info]
  (apply digraph (map build-graph-line subway-info)))
