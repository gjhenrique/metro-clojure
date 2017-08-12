(ns metro.seq
  (:require [metro.algorithm]))

#?(:clj
   (deftype MetroGraph [state]
     clojure.lang.ISeq
     (first [self] (seq-first state))

     (more [self] (seq-rest self))

     (next [self] (seq-next state))

     (seq [self] self)))

#?(:cljs
   (deftype MetroGraph [state]
     ISeq
     (-first [self] (seq-first state))

     (-rest [self] (seq-rest self))

     INext
     (-next [self] (seq-next state))

     ISeqable
     (-seq [self] self)))

(defn seq-first
  [state]
  {:station (:current-node state)
   :line (:current-line state)})

(defn seq-rest
  [self]
  (or (next self) '()))

(defn seq-next
  [state]
  (let [new-state (metro.algorithm/traverse-subway-graph state)]
    (when-not (nil? new-state)
      (MetroGraph. new-state))))

(defn seq-config
  [config]
  (-> config
      (metro.algorithm/initial-subway-config)
      (metro.algorithm/traverse-subway-graph)
      (MetroGraph.)))

(defn seq-graph
  [graph]
  (-> graph
      (metro.algorithm/initial-subway-graph)
      (metro.algorithm/traverse-subway-graph)
      (MetroGraph.)))



