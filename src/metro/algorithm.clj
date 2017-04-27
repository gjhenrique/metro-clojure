(ns metro.algorithm
  (:require [loom.alg :as alg]
            [loom.graph :as graph]
            [loom.attr :as attr]))

;; TODO: Give it a better name
;; Find initial station
;; Predecessors
;; Find initial 
;;   Begin algorithm
;;   Paint as visited if all the predecessors are visited or if it does not have predecessor

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

;; (defn find-predecessor)
;; (defn previous-station
;;   [g node line]
;;   (let [predecessor (find-predecessor g node line)]
;;     (if (nil? predecessor)
;;       node
;;       (find-first-station g predecessor line))))

(defn visited?
  [g node]
  (attr/attr g node :visited))

(defn random-initial-station
  "Pick a random station to begin the algorithm"
  [g]
  (let [node (first (graph/nodes g))]
    {:current-node node :current-line (first (attr/attr g node :lines))})) 

(defn really-traverse-graph
  [state]
  (let [{:keys [graph current-node current-line]} state
        predecessor (find-predecessor graph current-node current-line)
        successor (find-successor graph current-node current-line)]
    (cond
      ;; Predecessor is not visited yet
      (and (not (nil? predecessor)) (not (visited? graph predecessor)))
      (really-traverse-graph (assoc state :current-node predecessor))

      ;; Finding next successor
      (and (visited? graph current-node) (not (nil? successor)))
      (really-traverse-graph (assoc state :current-node successor))

      ;; Return the same
      :else
      (assoc state :graph (attr/add-attr graph current-node :visited true))
  )))

(defn traverse-subway-graph
  ([params]
  ;;  (traverse-subway-graph (assoc (random-initial-station g) :graph g )))
   (if (instance? loom.graph.BasicEditableDigraph params) 
     (traverse-subway-graph (assoc (random-initial-station params) :graph g))
     (really-traverse-graph params))))
