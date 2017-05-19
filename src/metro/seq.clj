(ns metro.seq)

;; Postponde the implementation of this
(deftype MetroGraph [state]
  clojure.lang.ISeq
  (more [self]
    (let [new-state (next self)]
    (if new-state
      new-state
      '())))

  (next [self]
    ;; (:current-node (rest state)))
    ;; (when-let [state (.state (rest self))]
    ;;   (list (:current-node state))))
      (let [new-state (metro.algorithm/traverse-subway-graph state)]
        (when-not (:end new-state)
          (MetroGraph. new-state))))
;; (when-let [new-state (.state (rest self))] ;;   (MetroGraph. new-state)))

  ;; Only this is realized
  (first [self] (:current-node state))

  (seq [self] (when self self))) 


(defmethod print-method MetroGraph [o, ^java.io.Writer w]
  (.write w (str (.state o)))) 

(def graph (metro.graph/build-subway-graph
             [{:name "Blue" :stations ["A" "B" "C" "D"]}])) 

(filter #(= % "A") (MetroGraph. (metro.algorithm/traverse-subway-graph graph))) 

(class 
(-> (MetroGraph. (metro.algorithm/traverse-subway-graph graph))
    rest rest) 
) 

(map identity (MetroGraph. (metro.algorithm/traverse-subway-graph graph))) 
(count (MetroGraph. (metro.algorithm/traverse-subway-graph graph))) 

