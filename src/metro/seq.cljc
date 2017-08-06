(ns metro.seq
  (:require [metro.algorithm]))

;; TODO: Make clj and cljs work from here
(deftype MetroGraph [state]
  ISeq
  (-first [self] {:station (:current-node state)
                 :line (:current-line state)})
  (-rest [self]
    (or (next self) '()))

  INext
  (-next [self]
      (let [new-state (metro.algorithm/traverse-subway-graph state)]
        (when-not (nil? new-state)
          (MetroGraph. new-state))))

  ISeqable
  (-seq [self] self))

(defn seq-graph
  [config]
  (-> config
      (metro.algorithm/initial-subway-graph)
      (metro.algorithm/traverse-subway-graph)
      (MetroGraph.)))
