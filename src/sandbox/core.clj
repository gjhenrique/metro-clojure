(ns sandbox.core
  (:require [clojure.java.io :as io])
  (:gen-class))

(defn -main []
  (println (io/resource "sp.txt")))

