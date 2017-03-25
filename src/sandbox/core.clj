(ns sandbox.core
  (:require [clojure.java.io :as io]
            [loom.graph :refer :all]
            [loom.io :refer :all]
            [loom.label :refer :all]
            [loom.attr :refer :all]
            [loom.alg :refer :all]
            [clojure.data.json :as json]
            [clojure.string :as str])
  (:gen-class)) 

(def city "sp") 

(def subway-schema (json/read-json (-> "res/sp.json"
                                       (io/file)
                                       (slurp)))) 

(defn -main []
  (def g (apply digraph (map #(apply digraph (partition 2 1 (map :name (:stations %)))) subway-schema)))) 

(comment
(map #(partition 2 1 (map :name (:stations %))) subway-schema) 
(attr g "Sé" [1 2] :lines) 
(defn a [] digraph ["A" "B"]) 
(attrs g "Sé") 
(attrs (add-attr g "Sé" :lines [1, 2]) "Sé") 
(defn dummy
  [n]
  (map (partial + n)
       (range (int (* (/ (float (* 2 n))
                        n)
                      10))))) 
(dummy 4) 
(+ 1 2)
(.toUpperCase "a")
((-> "first" symbol resolve) [1 2 3]))
