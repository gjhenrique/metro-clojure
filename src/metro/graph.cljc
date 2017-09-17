(ns metro.graph
  (:require [loom.graph :as graph]
            [loom.attr :as attr]
            [loom.alg :as alg]))

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

(defn- connections-without-cycle
  [graph stations line-name]
  (println (format "[%s] We have to remove some nodes. =(" line-name))
  (loop [g graph
         final-stations [(first stations)]
         iteration-stations (rest stations)]

    (if (empty? iteration-stations)
      (partition 2 1 final-stations)

      (let [new-graph (graph/digraph g [(last final-stations) (first iteration-stations)])]
        (if (alg/dag? new-graph)
          (recur new-graph
                 (conj final-stations (first iteration-stations))
                 (rest iteration-stations))

          (do
            (println (str "Removing station " [(last final-stations) (first iteration-stations)]))
            (recur graph final-stations (rest iteration-stations))))))))

(defn- reverse-stations
  [connections]
  (map
   (fn [info] [(second info) (first info)])
   (reverse connections)))

(defn- add-attributes
  [graph connections line]
  (reduce
   (fn [g station]
     (attr/add-attr g station :lines
                    (conj (or (attr/attr g station :lines) [])
                          line)))
   graph
   (set (flatten connections))))

(defn- add-connections
  [graph connections line-name description]
  (println (format "[%s] Trying %s" line-name description))
  (let [new-graph (apply graph/digraph graph connections)]
    (when (alg/dag? new-graph) connections)))

(defn- valid-connection
  [graph line-config]
  (let [line-name (:name line-config)
        connections (partition 2 1 (:stations line-config))]
    (or (add-connections graph connections line-name "Normal")
        (add-connections graph (reverse-stations connections) line-name "Reversed")
        (connections-without-cycle graph (:stations line-config) line-name))))

(defn build-subway-graph
  [subway-config]
  (reduce
   (fn [graph line-config]
     (let [connections (valid-connection graph line-config)
           new-graph (apply graph/digraph graph connections)]
       (add-attributes new-graph connections (:name line-config))))
   ;; Empty graph
   (graph/digraph)
   subway-config))
