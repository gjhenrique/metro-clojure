(ns metro.git
  (:require [metro.git :as sut]
            [clojure.test :as t]
            [clojure.string :as str]))


(defn call-git-commands [metro-seq]
  (build-git-operations (metro.seq/seq-graph metro-seq) base-folder))

(def linear-g [{:name "Blue" :stations ["A" "B" "C"]}])

(t/deftest simple-git-operations
  (let [repo (call-git-commands linear-g) ]))

(def merge-g [{:name "Blue" :stations ["A" "B"]}
              {:name "Red" :stations ["C" "B"]}
              {:name "Yellow" :stations ["D" "B"]}])

(t/deftest merge-git-operations
  (let [repo (call-git-commands merge-g)]))

