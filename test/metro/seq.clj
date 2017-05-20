(ns metro.seq
  (:require [metro.seq :as sut]
            [clojure.test :as t]))

(def config [{:name "Blue" :stations ["A" "B" "C"]}
             {:name "Red" :stations ["D" "B" "E"]}])

(def g (sut/seq-graph config))

(t/deftest count-seq
  (t/is (= (count g) 5)))

(t/deftest first-seq
  (t/is (= (:station (first g)) "A"))
  (t/is (= (:line (first g)) '("Blue"))))


(t/deftest map-seq
  (t/is (= (map :station g) '("A" "D" "B" "E" "C"))))

(t/deftest filter-seq
  (t/is
   (= (filter #(> (count (:line %)) 1) g)
      [{:station "B" :line '("Blue" "Red")}])))

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
