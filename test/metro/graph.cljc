(ns metro.graph
  (:require [metro.graph :as sut]
            #?(:clj [clojure.test :as t]
               :cljs [cljs.test :as t :include-macros true])))


(def raw-config [{:name "Blue" :stations ["A" "B" "C"]}])

(t/deftest simple-graph
  (let [g (sut/build-subway-graph raw-config)]
    (t/is (= (sut/stations g) (set '("A" "B" "C"))))
    (t/is (= (set [["A" "B"] ["B" "C"]]) (set (sut/connections g))))))

(def revert-config [{:name "Blue" :stations ["A" "B" "C"]}
                    {:name "Red" :stations ["C" "B"]}])

(t/deftest revert-stations-if-needed
  (let [g (sut/build-subway-graph revert-config)]
    (t/is (= (set [["A" "B"] ["B" "C"]]) (set (sut/connections g))))))


(def cycle-config [{:name "Blue" :stations ["A" "B" "C"]}
                   {:name "Red" :stations ["C" "A" "B"]}])
                   ;; {:name "Yellow" :stations ["B" "E" "D"]}])
 
(t/deftest remove-connection-if-needed
  (let [g (sut/build-subway-graph cycle-config)]
    ;; (println (sut/connections g))
    (println (sut/connections g))
    (t/is (loom.alg/dag? g))))
