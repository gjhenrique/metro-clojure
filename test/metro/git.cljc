(ns metro.git
  (:require [metro.git :as sut]
            [clojure.test :as t]
            [clojure.string :as str]))

(def linear-g [{:station "A" :line '("Blue")}
               {:station "B" :line '("Blue")}
               {:station "C" :line '("Blue")}])

(t/deftest simple-git-operations
  (let [commands (sut/git-commands linear-g)]
    (t/is (= commands [["git checkout --orphan Blue"
                        (git-commit "A")]

                       [(git-commit "B")]
                       [(git-commit "C")]]))))

(def merge-g [{:station "C" :line '("Red")}
              {:station "A" :line '("Blue")}
              {:station "B" :line '("Blue" "Red")}])

(t/deftest merge-git-operations
  (let [commands (sut/git-commands merge-g) ]
    (t/is (= commands [["git checkout --orphan Red"
                       (git-commit "C")]
                       ["git checkout --orphan Blue"
                       (git-commit "A")]
                       [(git-merge "B" '("Red"))
                       "git branch -f Red HEAD"]]))))

(def existing-g [{:station "C" :line '("Red")}
                 {:station "B" :line '("Yellow" "Red")}
                 {:station "D" :line '("Yellow")}])

(t/deftest existing-git-operations
  (let [commands (sut/git-commands existing-g)]
    (t/is (= commands [["git checkout --orphan Red"
                        (git-commit "C")]
                       [(git-commit "B")
                        "git branch -f Yellow HEAD"]
                       ["git checkout Yellow"
                        (git-commit "D")]])))) 
