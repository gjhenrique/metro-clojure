(ns metro.blog)

(defn- add-line-information
  [graph stations line-name]
  "Auxiliary function to add the line name to stations"
  (reduce
   (fn [g station]
     (->>
      ;; To not override other lines
      (conj (or (loom.attr/attr g station :lines) []) line-name)
      ;; Add the attribute to the graph
      (loom.attr/add-attr g station :lines)))
   graph
   stations))

(defn build-graph
  [config]
  "Build a graph without worrying about cycles"
  (reduce
   (fn [graph line]
     ;; partition transforms [A B C] into (("C" "B") ("B" "A"))
     (let [connections (partition 2 1 (:stations line))
           ;; add these edges into the graph
           new-graph (apply loom.graph/digraph graph connections)]
       ;; add the line as attributes in the nodes, it works even with connections
       (add-line-information new-graph (:stations line) (:name line))))
   ;; Start with an empty digraph
   (loom.graph/digraph)
   config))

(def config1 [{:name "Red", :stations ["A", "C"]},
             {:name "Blue", :stations ["B", "C"]}])
(def g1 (build-graph config1))


(defn- reverse-stations
  [connections]
  (map
   (fn [info] [(second info) (first info)])
   (reverse connections)))

(defn- add-connections
  [graph connections]
  (let [new-graph (apply loom.graph/digraph graph connections)]
    (when (loom.alg/dag? new-graph) connections)))

(defn- valid-connection
  [graph line-config]
  (let [line-name (:name line-config)
        connections (partition 2 1 (:stations line-config))]
    (or (add-connections graph connections)
        (add-connections graph (reverse-stations connections)))))

(defn build-graph
  [config]
  "Build a graph reversing cycles"
  (reduce
   (fn [graph line-config]
     (let [connections (valid-connection graph line-config)
           new-graph (apply loom.graph/digraph graph connections)]
       (add-line-information new-graph (:stations line-config) (:name line-config))))
   (loom.graph/digraph)
   config))

(def config [{:name "Red" :stations ["B" "C" "D"]}
              {:name "Blue" :stations ["A" "D" "B"]}])

(def g (build-graph config))

(loom.graph/edges g)
;; (["C" "D"] ["B" "C"] ["B" "D"] ["D" "A"])

(loom.graph/predecessors g "D")
;; #{"C" "B"}


(loom.alg/dag? g)
;; (def g2 (build-reversing-lines config2))
;; (loom.alg/dag? g2)

(defn- connections-without-cycle
  [graph stations line-name]
  (loop [g graph
         final-stations [(first stations)]
         iteration-stations (rest stations)]

    (if (empty? iteration-stations)
      (partition 2 1 final-stations)

      (let [new-graph
            (loom.graph/digraph g [(last final-stations)
                                   (first iteration-stations)])]
        (if (loom.alg/dag? new-graph)
          (recur new-graph
                 (conj final-stations (first iteration-stations))
                 (rest iteration-stations))

            (recur graph final-stations (rest iteration-stations)))))))

(defn- valid-connection
  [graph line-config]
  (let [line-name (:name line-config)
        connections (partition 2 1 (:stations line-config))]
    (or (add-connections graph connections)
        (add-connections graph (reverse-stations connections))
        (connections-without-cycle graph (:stations line-config) line-name))))

(defn build-graph
  [config]
  "Build a graph and remove cycles"
  (reduce
   (fn [graph line]
     ;; partition transforms [A B C] into (("C" "B") ("B" "A"))
     (let [connections (valid-connection graph line)
           ;; add these edges into the graph
           new-graph (apply loom.graph/digraph graph connections)]
       (println connections)
       ;; add the line as attributes in the nodes, it works even with connections
       (add-line-information new-graph (:stations config) (:name config))))
   ;; Start with an empty digraph
   (loom.graph/digraph)
   config))


(def config [{:name "Red" :stations ["A" "B" "C" "A"]}])
(def g (build-graph config))
;; => (loom.graph/nodes g2)
;; #{"C" "B" "A"}
;; => (loom.graph/edges g2)
;; (["B" "C"] ["A" "B"])
;; =>
;; => (loom.alg/dag? g2)
;; true
