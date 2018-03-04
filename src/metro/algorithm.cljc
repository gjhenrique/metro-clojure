(ns metro.algorithm
  (:require [loom.graph :as graph]
            [loom.attr :as attr]
            [metro.graph]))

(defn visited?
  [graph node]
  (attr/attr graph node :visited))

(defn find-predecessor
  [graph node]
  (first (filter
          (fn [p] (not (visited? graph p)))
          (metro.graph/predecessors graph node))))

(defn find-successors
  [graph node]
  (filter
   (fn [s] (not (visited? graph s)))
          (metro.graph/successors graph node)))

(defn- random-initial-station
  "Pick a random station to begin the algorithm"
  [graph]
  (let [node (first (graph/nodes graph))]
    {:pending-nodes () :current-node node :current-line (metro.graph/lines graph node)}))

(defn traverse-subway-graph
  [state]
  (let [{:keys [graph current-node current-line pending-nodes end]} state
        predecessor (find-predecessor graph current-node)
        successors (find-successors graph current-node)]
    (cond
      end nil

      (and (not (nil? predecessor)))
      (traverse-subway-graph (assoc state :current-node predecessor))

      (and (visited? graph current-node) (seq successors))
      (traverse-subway-graph (assoc state
                                    :current-node (first successors)
                                    :pending-nodes (concat pending-nodes (rest successors))))

      (and (visited? graph current-node) (empty? successors))
      (traverse-subway-graph (assoc state
                                    :current-node (first pending-nodes)
                                    :pending-nodes (rest pending-nodes)))

      (and (empty? successors) (empty? pending-nodes))
      (assoc state
             :current-line (metro.graph/lines graph current-node)
             :graph (attr/add-attr graph current-node :visited true)
             :end true)

      :else
      (assoc state
             :pending-nodes (remove #{current-node} pending-nodes)
             :current-line (metro.graph/lines graph current-node)
             :graph (attr/add-attr graph current-node :visited true)))))

(defn initial-subway-config
  [config]
  (let [graph (metro.graph/build-optimized-graph config)]
    (assoc (random-initial-station graph) :graph graph)))

(defn initial-subway-graph
  [graph]
  (assoc (random-initial-station graph) :graph graph))
