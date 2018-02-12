(ns metro.graph
  (:require [metro.graph :as sut]
            #?(:clj [clojure.test :as t]
               :cljs [cljs.test :as t :include-macros true])))


(def raw-config [{:name "Blue" :stations ["A" "B" "C"]}])

(t/deftest simple-graph
  (let [g (sut/build-optimized-graph raw-config)]
    (t/is (= (sut/stations g) (set '("A" "B" "C"))))
    (t/is (= (set [["A" "B"] ["B" "C"]]) (set (sut/connections g))))))

(def revert-config [{:name "Blue" :stations ["A" "B" "C"]}
                    {:name "Red" :stations ["C" "B"]}])

(t/deftest revert-stations-if-needed
  (let [g (sut/build-optimized-graph revert-config)]
    (t/is (= (set [["A" "B"] ["B" "C"]]) (set (sut/connections g))))))


(def attr-config [{:name "Blue" :stations ["A" "B"]}
                  {:name "Red" :stations ["A" "B"]}])

(t/deftest test-attribute
  (let [g (sut/build-optimized-graph attr-config)]
    (t/is (= (loom.attr/attr g "B" :lines) ["Blue" "Red"]))
    (t/is (= (loom.attr/attr g "A" :lines) ["Blue" "Red"]))))

(def cycle-config [{:name "Blue" :stations ["A" "B" "C"]}
                   {:name "Red" :stations ["C" "A" "B"]}])
 
(t/deftest remove-connection-if-needed
  (let [g (sut/build-optimized-graph cycle-config)]
    (t/is (loom.alg/dag? g))))
