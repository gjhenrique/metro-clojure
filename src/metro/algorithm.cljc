(ns metro.algorithm
  (:require [loom.graph :as graph]
            [loom.attr :as attr]
            [metro.graph :as g]))

(defn visited?
  [g node]
  (attr/attr g node :visited))

(defn find-predecessor
  [g node]
  (first (filter
          (fn [p] (not (visited? g p)))
          (graph/predecessors g node))))

(defn find-successors
  [g node]
  (filter (fn [s] (not (visited? g s)))
          (graph/successors g node)))

;; TODO: Move this to graph file
(defn lines
  [g node]
  (attr/attr g node :lines))

(defn random-initial-station
  "Pick a random station to begin the algorithm"
  [g]
  (let [node (first (graph/nodes g))]
    {:pending-nodes () :current-node node :current-line (lines g node)}))

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

      (and (empty? successors) (empty? pending-nodes))
      (assoc state
             :current-line (lines graph current-node)
             :graph (attr/add-attr graph current-node :visited true)
             :end true)

      (and (visited? graph current-node) (empty? successors))
      (traverse-subway-graph (assoc state
                                    :current-node (first pending-nodes)
                                    :pending-nodes (rest pending-nodes)))

      :else
      (assoc state
             :current-line (lines graph current-node)
             :graph (attr/add-attr graph current-node :visited true)))))

(defn initial-subway-graph
  [config]
  (let [graph (g/build-subway-graph config)]
    (assoc (random-initial-station graph) :graph graph)))

