(ns metro.algorithm
  (:require  [clojure.test :as t]
             [metro.graph :refer [build-subway-graph]]
             [loom.attr :as attr]))

(def linear-g [{:name "Blue" :stations ["A" "B" "C"]}])
(def expected-linear-g [{:node "A" :line '("Blue")} {:node "B" :line '("Blue")} {:node "C" :line '("Blue")}])

(defn test-traversal-values
  [params expected-values]
  (reduce
   (fn [state value]
     (t/is (= (:current-node state) (:node value)))
     (t/is (= (:current-line state) (:line value)))
     (traverse-subway-graph state))
   (traverse-subway-graph (initial-subway-config params))
   expected-values))

(defn test-traversal
  [g expected-values]
  (let [state (test-traversal-values g expected-values)]
    (t/is (nil? state))))

(t/deftest initial-simple-traverse
  (test-traversal linear-g expected-linear-g))



(def merge-g [{:name "Blue" :stations ["A" "B"]}
              {:name "Red" :stations ["C" "B"]}])
(def expected-merge-g [{:node "C" :line '("Red")}
                       {:node "A" :line '("Blue")}
                       {:node "B" :line '("Blue" "Red")}])

(t/deftest merge-traverse
  (test-traversal merge-g expected-merge-g))


(def fallback-g [{:name "Blue" :stations ["A" "B"]}
                 {:name "Red" :stations ["A" "C"]}])

(def expected-fallback-g [{:node "A" :line '("Blue" "Red")}
                          {:node "C" :line '("Red")}
                          {:node "B" :line '("Blue")}])

(t/deftest fallback-traverse
  (test-traversal fallback-g expected-fallback-g))
