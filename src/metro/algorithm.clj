(ns metro.algorithm
  (:require [loom.alg :as alg]
            [loom.graph :as graph]
            [loom.attr :as attr]))

(defn find-predecessor
  [g node line]
  (first (filter
          (fn [p] (some #(= line %) (attr/attr g p :lines)))
          (graph/predecessors g node))))

(defn find-successor
  [g node line]
  (first (filter
          (fn [p] (some #(= line %) (attr/attr g p :lines)))
          (graph/successors g node))))

(defn visited?
  [g node]
  (attr/attr g node :visited))

;; TODO: Move this to graph file
(defn lines
  [g node]
  (attr/attr g node :lines))

(defn random-initial-station
  "Pick a random station to begin the algorithm"
  [g]
  (let [node (first (graph/nodes g))]
    {:current-node node :current-line (lines g node)})) 

(defn really-traverse-graph
  [state]
  (let [{:keys [graph current-node current-line]} state
        line (first current-line)
        predecessor (find-predecessor graph current-node line)
        successor (find-successor graph current-node line)]
    (cond
      ;; Predecessor is not visited yet
      (and (not (nil? predecessor)) (not (visited? graph predecessor)))
      (really-traverse-graph (assoc state :current-node predecessor))

      ;; Checking if there are other successors

      ;; Finding next successor
      (and (visited? graph current-node) (not (nil? successor)))
      (really-traverse-graph (assoc state :current-node successor))

      ;; If there are no more successors, come back until you find one that has

      ;; If the node is visited and it does not have any successors, mark it as the end

      ;; Return the current-node
      :else
      (assoc state :current-line (lines graph current-node) :graph (attr/add-attr graph current-node :visited true))
  )))

(defn traverse-subway-graph
  ([params]
   (if (instance? loom.graph.BasicEditableDigraph params) 
     (traverse-subway-graph (assoc (random-initial-station params) :graph params))
     (really-traverse-graph params))))
