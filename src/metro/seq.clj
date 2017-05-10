(ns metro.seq)

(deftype MetroGraph [state]
  clojure.lang.ISeq
  ;; (equiv [self a] true)
  ;; (empty [self] state)
  ;; (more [self] '(1 2))
  (more [self]
    (let [s (next self)]
      (if (nil? (first s))
        '()
        (MetroGraph. (first s)))))
  (next [self] (list
                (let [new-state (metro.algorithm/traverse-subway-graph state)]
                  (if (:end new-state)
                    nil
                    new-state))))
  (first [self] (:current-node state))
  (seq [self] (if (seq? self) self nil)))

(defmethod print-method MetroGraph [o, ^java.io.Writer w]
  (.write w (str "MetroGraph")))

(let [graph (metro.graph/build-subway-graph
             [{:name "Blue" :stations ["A" "B" "C" "D"]}])]
  (filter (fn [a] (= a "D")) (MetroGraph. (metro.algorithm/traverse-subway-graph graph))))

;; (deftype ReverseGraph [state]
;;   clojure.lang.ISeq
;;   ;; (equiv [self a] true)
;;   ;; (empty [self] state)
;;   ;; (more [self] '(1 2))
;;   (more [self]
;;     (let [s (next self)]
;;       (if (nil? s)
;;         []
;;         (MetroGraph. s))))
;;   (next [self] (list (metro.algorithm/traverse-subway-graph state)))
;;   (first [self] (:name state))
;;   (seq [self] (if (seq? self) self nil)))

;; (defmethod print-method ReverseGraph [o, ^java.io.Writer w]
;;   (.write w (str "[RedBlackTree] ")))

;; (ReverseGraph. {:name "Olá" :list [1 2 3]})
