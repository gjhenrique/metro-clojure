(ns metro.seq
  (:require [metro.algorithm]
            [metro.git]))

(declare seq-first seq-rest seq-next)

#?(:clj
   (deftype MetroGraph [algorithm-state git-state traversal-algorithm]
     clojure.lang.ISeq
     (first [self] (seq-first algorithm-state git-state))

     (more [self] (seq-rest self))

     (next [self] (seq-next algorithm-state git-state traversal-algorithm))

     (seq [self] self)))

#?(:cljs
   (deftype MetroGraph [algorithm-state git-state traversal-algorithm]
     ISeq
     (-first [self] (seq-first algorithm-state git-state))

     (-rest [self] (seq-rest self))

     INext
     (-next [self] (seq-next algorithm-state git-state traversal-algorithm))

     ISeqable
     (-seq [self] self)))

(defn- seq-first
  [algorithm-state git-state]
  {:station (:current-node algorithm-state)
   :line (:current-line algorithm-state)
   :commands (:commands git-state)
   :state algorithm-state})

(defn- seq-rest
  [self]
  (or (next self) '()))

(defn- seq-next
  [algorithm-state git-state traversal-algorithm-fn]
  (let [new-state (traversal-algorithm-fn algorithm-state)]
    (when-not (nil? new-state)
      (let [new-git-state (metro.git/create-git-commands git-state
                                                         (:current-node new-state)
                                                         (:current-line new-state))]
        (MetroGraph. new-state new-git-state traversal-algorithm-fn)))))

(defn build-seq
  [initial-state traversal-algorithm]
  (MetroGraph. initial-state
               (metro.git/create-git-commands
                (:current-node initial-state)
                (:current-line initial-state))
               traversal-algorithm))

(defn seq-config
  ([config]
   (seq-config config metro.algorithm/traverse-subway-graph))
  ([config traversal-algorithm]
   (build-seq
    (-> config
        (metro.algorithm/initial-subway-config)
        (metro.algorithm/traverse-subway-graph))
    traversal-algorithm)))

(defn seq-graph
  ([graph]
   (seq-graph graph metro.algorithm/traverse-subway-graph))
  ([graph traversal-algorithm]
   (build-seq
    (-> graph
        (metro.algorithm/initial-subway-graph)
        (metro.algorithm/traverse-subway-graph))
    traversal-algorithm)))
