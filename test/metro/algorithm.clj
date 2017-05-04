(ns metro.algorithm
  (:require  [clojure.test :as t]
             [metro.graph :refer [build-subway-graph]]))

(def linear-g [{:name "Blue" :stations ["A" "B" "C"]}])
(def expected-linear-g [{:node "A" :line '("Blue")} {:node "B" :line '("Blue")} {:node "C" :line '("Blue")}])

(defn test-graph
  [g expected-values]
  (reduce 
   (fn [state value]
     (t/is (= (:current-node state) (:node value)))
     (t/is (= (:current-line state) (:line value)))
     (traverse-subway-graph state))
   (traverse-subway-graph g)
   expected-values))

(t/deftest initial-simple-traverse
  (test-graph (metro.graph/build-subway-graph linear-g) expected-linear-g))

(def merge-g [{:name "Blue" :stations ["A" "B" "C"]} {:name "Red" :stations ["D" "B" "E"]}])
(def expected-merge-g [{:node "D" :line '("Red")} {:node "A" :line '("Blue")} {:node "B" :line '("Blue" "Red")}])

;; (def g (metro.graph/build-subway-graph merge-g)) 
;; (-> g
;;     (traverse-subway-graph) 
;;     (traverse-subway-graph)) 

;; (lines (metro.graph/build-subway-graph merge-g) )
