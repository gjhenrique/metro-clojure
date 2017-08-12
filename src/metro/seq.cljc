(ns metro.seq
  (:require [metro.algorithm]
            [metro.git]))

(declare seq-first seq-rest seq-next)

#?(:clj
   (deftype MetroGraph [algorithm-state git-state]
     clojure.lang.ISeq
     (first [self] (seq-first algorithm-state git-state))

     (more [self] (seq-rest self))

     (next [self] (seq-next algorithm-state git-state))

     (seq [self] self)))

#?(:cljs
   (deftype Metro [algorithm-state git-state]
     ISeq
     (-first [self] (seq-first algorithm-state git-state))

     (-rest [self] (seq-rest self))

     INext
     (-next [self] (seq-next algorithm-state git-state))

     ISeqable
     (-seq [self] self)))

(defn seq-first
  [algorithm-state git-state]
  {:station (:current-node algorithm-state)
   :line (:current-line algorithm-state)
   :commands (:commands git-state)})

(defn seq-rest
  [self]
  (or (next self) '()))

(defn seq-next
  [algorithm-state git-state]
  (let [new-state (metro.algorithm/traverse-subway-graph algorithm-state)]
    (when-not (nil? new-state)
      (let [new-git-state (metro.git/create-git-commands git-state
                                                         (:current-node new-state)
                                                         (:current-line new-state))]
        (MetroGraph. new-state new-git-state)))))

(defn build-seq
  [config initial-subway-fn]
  (let [initial-state (-> config
                          (initial-subway-fn)
                          (metro.algorithm/traverse-subway-graph))]
    (MetroGraph. initial-state
                 (metro.git/create-git-commands {}
                                                (:current-node initial-state)
                                                (:current-line initial-state)))))

(defn seq-config
  [config]
  (build-seq config metro.algorithm/initial-subway-config))

(defn seq-graph
  [graph]
  (build-seq config metro.algorithm/initial-subway-graph))
