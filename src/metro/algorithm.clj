(ns metro.algorithm
  (:require [loom.alg :as alg]
            [loom.graph :as graph]
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

(defn really-traverse-graph
  [state]
  (let [{:keys [graph current-node current-line pending-nodes]} state
        predecessor (find-predecessor graph current-node)
        successors (find-successors graph current-node)]
    (cond
      (and (not (nil? predecessor)))
      (really-traverse-graph (assoc state :current-node predecessor))

      (and (visited? graph current-node) (seq successors))
      (really-traverse-graph (assoc state
                                    :current-node (first successors)
                                    :pending-nodes (concat pending-nodes (rest successors))))

      (and (empty? successors) (empty? pending-nodes))
      (assoc state
             :current-line (lines graph current-node)
             :graph (attr/add-attr graph current-node :visited true)
             :end true)

      (and (visited? graph current-node) (empty? successors))
      (really-traverse-graph (assoc state
                                    :current-node (first pending-nodes)
                                    :pending-nodes (rest pending-nodes)))

      :else
      (assoc state
             :current-line (lines graph current-node)
             :graph (attr/add-attr graph current-node :visited true)))))

(defn traverse-subway-graph
  ([params]
   (if (instance? loom.graph.BasicEditableDigraph params)
     (traverse-subway-graph (assoc (random-initial-station params) :graph params))
     (really-traverse-graph params))))

;; TODO: test this
(defn seq-graph
  ([info]
  (seq-graph [] (g/build-subway-graph info)))
  ([result state]
  (if (:end state)
    result
    (let [new-state (traverse-subway-graph state)]
      (seq-graph (conj result [(:current-node new-state) (:current-line new-state)]) new-state)))))
