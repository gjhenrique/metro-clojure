(ns metro.git
  (:require [metro.git :as sut]
            [clojure.test :as t]
            [clojure.string :as str]))


(defn call-git-commands [metro-seq]
  (let [commands (atom [])]
    (sut/build-git-operations
     (metro.seq/seq-config metro-seq)
     (fn [command] (swap! commands conj command)))
    (deref commands)))

(def linear-g [{:name "Blue" :stations ["A" "B" "C"]}])

(t/deftest simple-git-operations
  (let [commands (call-git-commands linear-g)]
    (t/is (= commands ["git checkout --orphan Blue"
                       (git-commit "A")
                       (git-commit "B")
                       (git-commit "C")]))))

(def merge-g [{:name "Blue" :stations ["A" "B"]}
              {:name "Red" :stations ["C" "B"]}])

(t/deftest merge-git-operations
  (let [commands (call-git-commands merge-g)]
    (t/is (= commands ["git checkout --orphan Red"
                       (git-commit "C")
                       "git checkout --orphan Blue"
                       (git-commit "A")
                       (git-merge "B" '("Red"))
                       "git branch -f Red HEAD"]))))

(def existing-g [{:name "Red" :stations ["C" "B"]}
                 {:name "Yellow" :stations ["B" "D"]}])

(t/deftest merge-git-operations
  (let [commands (call-git-commands existing-g)]
    (t/is (= commands ["git checkout --orphan Red"
                       (git-commit "C")
                       (git-commit "B")
                       "git branch -f Yellow HEAD"
                       "git checkout Yellow"
                       (git-commit "D")]))))
