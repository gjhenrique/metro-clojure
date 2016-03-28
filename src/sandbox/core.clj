(ns sandbox.core
  (:require [clojure.java.io :as io]
            [loom.graph :refer :all]
            [loom.io :refer :all]
            [loom.label :refer :all]
            [loom.attr :refer :all]
            [loom.io :refer :all]
            [loom.alg :refer :all]
            [clojure.data.json :as json]
            [clojure.string :as str])
  (:gen-class)) 

(def city "sp") 

(def subway-schema (json/read-json (-> "res/sp.json"
                                       (io/file)
                                       (slurp)))) 

(defn -main []
  (view (apply digraph (map #(apply digraph (partition 2 1 (map :name (:stations %)))) subway-schema))))

