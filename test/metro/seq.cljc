(ns metro.seq
  (:require [metro.seq :as sut]
            [metro.git :as git]
            [clojure.test :as t]))

(def config [{:name "Blue" :stations ["A" "B" "C"]}
             {:name "Red" :stations ["D" "B" "E"]}])

(def g (sut/seq-config config))

(t/deftest count-seq
  (t/is (= (count g) 5)))

(t/deftest first-seq
  (t/is (= (:station (first g)) "A"))
  (t/is (= (:line (first g)) '("Blue"))))

(t/deftest map-seq
  (t/is (= (map :station g) '("A" "D" "B" "E" "C"))))

(t/deftest filter-seq
  (t/is
   (= (count (filter #(> (count (:line %)) 1) g)) 1))

  (t/is (= (map :station (filter #(> (count (:line %)) 1) g)) '("B"))))

(t/deftest reverse-seq
  (t/is
   (=
    (map :station (reverse g))
    ["C" "E" "B" "D" "A"])))

(t/deftest reduce-seq
  (t/is
   (=
    (reduce str (map :station g))
    "ADBEC")))

(t/deftest test-commands
  (t/is
   (= (:commands (first g) )
    ['"git checkout --orphan \"Blue\""
     (git/git-commit "A")])))
