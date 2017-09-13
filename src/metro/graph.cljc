(ns metro.graph
  (:require [loom.graph :as graph]
            [loom.attr :as attr]))

(defn lines
  [graph node]
  (attr/attr graph node :lines))

(defn stations
  [graph]
  (graph/nodes graph))

(defn connections
  [graph]
  (graph/edges graph))

(defn predecessors
  [graph station]
  (graph/predecessors graph station))

(defn successors
  [graph station]
  (graph/successors graph station))

(defn connections-without-cycle
  [graph stations]
  (loop [g graph 
         final-stations [(first stations)]
         iter-stations (rest stations)]

    (if (empty? iter-stations)
      (partition 2 1 final-stations)

      (let [new-graph (graph/digraph g [(last final-stations) (first iter-stations)])]
        (if (loom.alg/dag? new-graph)
          (recur new-graph
                 (conj final-stations (first iter-stations))
                 (rest iter-stations))

          (do
            (println (str "Removing station " [(last final-stations) (first iter-stations)]))
            (recur graph final-stations (rest iter-stations))))))))

(defn reverse-stations
  [connections]
  (map 
   (fn [info] [(second info) (first info)])
   (reverse connections)))

(defn build-attributes
  [graph connections line]
  (reduce
   (fn [g station]
     (attr/add-attr g station :lines
                    (conj (or (attr/attr g station :lines) [])
                          line)))
   graph
   (set (flatten connections))))

(defn build-graph
  [graph connections]
  (let [new-graph (apply graph/digraph graph connections)]
    (when (loom.alg/dag? new-graph) connections)))
  
(defn valid-connection
  [graph subway-info]
  (let [connections (partition 2 1 (:stations subway-info))]
    (or (build-graph graph connections)
        (build-graph graph (reverse-stations connections))
        (connections-without-cycle graph (:stations subway-info)))))

(defn build-subway-graph
  [subway-info]
  (reduce
   (fn [graph station-info]
     (let [connections (valid-connection graph station-info)
           new-graph (apply graph/digraph graph connections)]
        (build-attributes new-graph connections (:name station-info))))
     ;; Empty graph
     (graph/digraph)
     subway-info))
