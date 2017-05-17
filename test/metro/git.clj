(ns metro.git
  (:require [metro.git :as sut]
            [me.raynes.fs :as file]
            [clojure.test :as t]))

(def base_folder (str (.getParent (java.io.File. *file*)) "/test_repo/")) 
(def linear-g [{:name "Blue" :stations ["A" "B" "C"]}])

(def seqa (metro.algorithm/seq-graph linear-g)) 

(first (first (sut/create-repo base_folder seqa)))

(file/delete-dir base_folder)
