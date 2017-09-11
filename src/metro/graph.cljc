(ns metro.graph
  (:require [loom.graph :as graph]
            [loom.attr :as attr]))

(defn lines-inside
  [station subway-info]
  (map :name
       (filter
        (fn [line]
          (some #(= station %) (:stations line)))
        subway-info)))

;; TODO: Check if remove this?
(defn all-stations
  [subway-info]
  (set
   (flatten
    (map :stations subway-info))))

(defn lines
  [graph node]
  (attr/attr graph node :lines))

(defn stations
  [graph]
  (graph/nodes graph))

(defn build-attributes
  [graph subway-info]
  (reduce
   (fn [g station]
     (println station)
     (let [lines (lines-inside station subway-info)]
       (println lines)
       (if lines
         (attr/add-attr g station :lines lines)
         g)))
   graph
   (all-stations subway-info)))

(defn connections
  [graph]
  (graph/edges graph))

(defn predecessors
  [graph station]
  (graph/predecessors graph station))

(defn successors
  [graph station]
  (graph/successors graph station))

(defn remove-connection
  [station-graph station-connection]
  (let [fn-with-element (fn [connection] (not= connection station-connection))
        first-elements (take-while fn-with-element station-graph)
        last-elements (rest (drop-while fn-with-element station-graph))]
    ;; Check if element is not first or last one
    (if (or (empty? first-elements) (empty? last-elements))
      (concat first-elements last-elements)
      (concat (butlast first-elements)
              [[(first (last first-elements)) (ffirst last-elements)]]
              last-elements))))

(defn graph-without-cycle
  [graph station-graph]
  (reduce
   (fn [g station-connection]
     (let [new-graph (graph/digraph g station-connection)]
       (if (loom.alg/dag? new-graph)
         (do
           ;; (println (str "Its dag " (first station-connection) "   -   " (second station-connection)))
           new-graph
           )
         (do 
           (println (str "Removing " (first station-connection) "  -  " (second station-connection)))
           (reduced (graph-without-cycle graph (remove-connection station-graph station-connection))))
         )))
   graph
   station-graph))

(defn build-raw-nodes
  [subway-info]
  (map #(partition 2 1 (:stations %)) subway-info))
   ;; #(apply graph/digraph (partition 2 1 (:stations %)))

;; (DEFN build-raw-graph
;;   [subway-info]
;;   (apply graph/digraph (map
;;                         #(apply graph/digraph (partition 2 1 (:stations %)))
;;                         subway-info)))

(defn reverse-graph
  [graph-info]
  (map 
   (fn [info] [(second info) (first info)])
   (reverse graph-info)))

(defn build-raw-graph
  [subway-info]
  (let [graph-info (build-raw-nodes subway-info)]
    (reduce
     (fn [graph station-graph]
       (let [new-graph (apply graph/digraph graph station-graph)]
         (if (loom.alg/dag? new-graph)
           (do
             (println (str "Dag!"))
             new-graph)
           (do
             (let [b (apply graph/digraph graph (reverse-graph station-graph))]
               (if (loom.alg/dag? b)
                 (do
                   (println "Yes. Dag")
                   b)
                 (do
                   (println "Not Dag")
                   (graph-without-cycle graph station-graph)
                   )))))))
     (apply graph/digraph (first graph-info))
     (rest graph-info))))


(defn build-subway-graph
  [subway-info]
  (-> subway-info
      (build-raw-graph)
      (loom.alg/dag?)
      (println)
      ;; (build-attributes subway-info)))
      ))
