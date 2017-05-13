(ns metro.seq)

;; Postponde the implementation of this
(deftype MetroGraph [state]
  clojure.lang.ISeq
  (more [self]
    (let [new-state (metro.algorithm/traverse-subway-graph state)]
      (if (:end new-state)
        '()
        (MetroGraph. new-state))))

  (next [self]
    (:current-node (rest state)))

  (first [self] (:current-node state))

  (seq [self] (when (seq? self) self))) 

(defmethod print-method MetroGraph [o, ^java.io.Writer w]
  (.write w (str "MetroGraph")))

(def graph (metro.graph/build-subway-graph
             [{:name "Blue" :stations ["A" "B" "C" "D"]}])) 

;; (seq-graph [{:name "Blue" :stations ["A" "B" "C" "D"]}])
