(ns metro.seq)

(deftype MetroGraph [state]
  clojure.lang.ISeq
  (more [self]
    (or (next self) '()))

  (next [self]
      (let [new-state (metro.algorithm/traverse-subway-graph state)]
        (when-not (nil? new-state)
          (MetroGraph. new-state))))

  (first [self] {:station (:current-node state)
                 :line (:current-line state)})

  (seq [self] self))


(defmethod print-method MetroGraph [o, ^java.io.Writer w]
  (.write w (str (:current-node (.state o)))))

(defn seq-graph
  [config]
  (-> config
      (metro.algorithm/initial-subway-graph)
      (metro.algorithm/traverse-subway-graph)
      (MetroGraph.)))
