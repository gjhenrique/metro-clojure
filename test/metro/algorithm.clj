(ns metro.algorithm
  (:require  [clojure.test :as t]
             [metro.graph :refer [build-subway-graph]]))

(def g (build-subway-graph [{:name "Blue" :stations ["A" "B" "C"]}]))
(def expected-g (map (fn [st] {:name "Blue" :station st}) ["A" "B" "C"])) 

(loom.graph/successors g "A") 

(t/deftest initial-simple-traverse
  (let [state (traverse-subway-graph g)]
    (t/are [x y] (= x y)
              "A" (:current-node state)
              "Blue" (:current-line state))))

(t/deftest second-simple-traverse
  (let [state
        (-> g
            (traverse-subway-graph)
            (traverse-subway-graph))]
    (t/are [x y] (= x y)
              "B" (:current-node state)
              "Blue" (:current-line state))))


(t/deftest third-simple-traverse
  (let [state
        (-> g
            (traverse-subway-graph)
            (traverse-subway-graph)
            (traverse-subway-graph))]
    (t/are [x y] (= x y)
              "C" (:current-node state)
              "Blue" (:current-line state))))
