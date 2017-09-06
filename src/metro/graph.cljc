(ns metro.graph
  (:require [loom.graph :as graph]
            [loom.attr :as attr]))

(defn lines-inside
  [station subway-info]
  (map :name
       (filter
        (fn [line]
          (some #(= station %) (:stations line)))
        subway-info)))

(defn all-stations
  [subway-info]
  (set
   (flatten
    (map :stations subway-info))))

(defn lines
  [graph node]
  (attr/attr graph node :lines))

(defn stations
  [graph]
  (graph/nodes graph))

(defn build-attributes
  [graph subway-info]
  (reduce
   (fn [g station]
     (attr/add-attr g station :lines (lines-inside station subway-info)))
   graph
   (all-stations subway-info)))

(defn connections
  [graph]
  (graph/edges graph))

(defn predecessors
  [graph station]
  (graph/predecessors graph station))

(defn successors
  [graph station]
  (graph/successors graph station))

(defn build-raw-nodes
  [subway-info]
  (map
   #(apply graph/digraph (partition 2 1 (:stations %)))
   subway-info))


;; (defn build-raw-graph
;;   [subway-info]
;;   (apply graph/digraph (map
;;                         #(apply graph/digraph (partition 2 1 (:stations %)))
;;                         subway-info)))
(defn build-raw-graph
  [subway-info]
  (reduce
   (fn [graph station-graph]
     (let [new-graph (graph/digraph graph station-graph)]
       (if (loom.alg/dag? new-graph)
         (do
           (println (str "Dag!"))
           new-graph)
         (do
           (let [b (graph/digraph (loom.graph/transpose graph) (loom.graph/transpose station-graph))]
             (if (loom.alg/dag? b)
               (do
                 (println "Yes. Dag")
                 b)
               (do
                 (println "Not Dag")
                 (println station-graph)
                 new-graph
                 )))))))
   (build-raw-nodes subway-info)))


(defn build-subway-graph
  [subway-info]
  (-> subway-info
      (build-raw-graph)
      (build-attributes subway-info)))
