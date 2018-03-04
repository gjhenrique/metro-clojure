(ns metro.algorithm-steps
  (:require [loom.graph :as graph]
            [loom.attr :as attr]
            [metro.graph]
            [metro.algorithm]))

;; Omit all the nil in the source code
(defn traverse-step1
  [state]
  (let [{:keys [graph current-node current-line]} state]
    (println (metro.algorithm/visited? graph current-node))
    (cond
      (metro.algorithm/visited? graph current-node)
      nil

      :else
      (assoc state
             :current-line (metro.graph/lines graph current-node)
             :graph (attr/add-attr graph current-node :visited true)))))

(defn traverse-step2
  [state]
  (let [{:keys [graph current-node current-line]} state
        predecessor (metro.algorithm/find-predecessor graph current-node)]
    (cond
      (not (nil? predecessor))
      (traverse-step2 (assoc state :current-node predecessor))

      (metro.algorithm/visited? graph current-node)
      nil

      :else
      (assoc state
             :current-line (metro.graph/lines graph current-node)
             :graph (attr/add-attr graph current-node :visited true)))))

;; Surprinsigly when there's a merge everything is well
(defn traverse-step3
  [state]
  (let [{:keys [graph current-node current-line]} state
        predecessor (metro.algorithm/find-predecessor graph current-node)
        successors (metro.algorithm/find-successors graph current-node)]
    (cond
      (not (nil? predecessor))
      (traverse-step3 (assoc state :current-node predecessor))

      (and (metro.algorithm/visited? graph current-node) (seq successors))
      (traverse-step3 (assoc state :current-node (first successors)))

      (and (metro.algorithm/visited? graph current-node) (empty? successors))
      nil

      :else
      (assoc state
             :current-line (metro.graph/lines graph current-node)
             :graph (attr/add-attr graph current-node :visited true)))))

(defn traverse-step4
  [state]
  (let [{:keys [graph current-node current-line end]} state
        predecessor (metro.algorithm/find-predecessor graph current-node)
        successors (metro.algorithm/find-successors graph current-node)]
    (cond
      end nil

      (not (nil? predecessor))
      (traverse-step4 (assoc state :current-node predecessor))

      (and (metro.algorithm/visited? graph current-node) (seq successors))
      (traverse-step4 (assoc state :current-node (first successors)))

      (and (metro.algorithm/visited? graph current-node) (empty? successors))
      (traverse-step4 (assoc state :end true))

      :else
      (assoc state
             :current-line (metro.graph/lines graph current-node)
             :graph (attr/add-attr graph current-node :visited true)))))
