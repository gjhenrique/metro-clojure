(ns metro.blog
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn- add-line-information
  [graph stations line-name]
  (reduce
   (fn [g station]
     (let [current-line (or (loom.attr/attr g station :lines) [])]
       (loom.attr/add-attr g station :lines (conj current-line line-name))))
   graph
   (set (flatten stations))))

(defn build-graph1
  [config]
  "Build a graph without worrying about cycles"
  (reduce
   (fn [graph line]
     (let [connections (partition 2 1 (:stations line))
           new-graph (apply loom.graph/digraph graph connections)]
       (add-line-information new-graph connections (:name line))))
   (loom.graph/digraph)
   config)) 

;; (def config1 [{:name "Red", :stations ["A", "C"]},
;;              {:name "Blue", :stations ["B", "C"]}]) 
;; (build-graph1 config1)


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

(defn build-graph2
  [config]
  "Build a graph reversing cycles"
  (reduce
   (fn [graph line-config]
     (let [connections (valid-connection graph line-config)
           new-graph (apply loom.graph/digraph graph connections)]
       (add-line-information new-graph connections (:name line-config))))
   (loom.graph/digraph)
   config))

(def config2 [{:name "Red" :stations ["B" "C" "D"]}
              {:name "Blue" :stations ["A" "D" "B"]}]) 

;; (build-graph2 config2) 

;; (loom.graph/edges g)
;; (["C" "D"] ["B" "C"] ["B" "D"] ["D" "A"])

;; (loom.graph/predecessors g "D")
;; #{"C" "B"}


;; (loom.alg/dag? g)
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
   (fn [graph line-config]
     (let [connections (valid-connection graph line-config)
           new-graph (apply loom.graph/digraph graph connections)]
       (add-line-information new-graph connections (:name line-config))))
   (loom.graph/digraph)
   config))


(defn- lines
  [graph node]
  (loom.attr/attr graph node :lines))

(defn traverse-graph1
  [state]
  (let [{:keys [graph current-node]} state]
      (assoc state
             :current-line (lines graph current-node)
             :graph (loom.attr/add-attr graph current-node :visited true))))


;; (def config [{:name "Green" :stations ["A" "B" "C"]}])
;; (def g1 (build-graph3 config)) 
;; (def state1 (traverse-graph1 {:graph g1 :current-node "B"})) 
;; (def state2 (traverse-graph1 {:graph g1 :current-node "B"})) 

(defn visited?
  [graph station]
  (loom.attr/attr graph station :visited))

(defn find-predecessor
  [graph station]
  "Finds the non visited predecessors of station"
  (first (filter
          (fn [p] (not (visited? graph p)))
          (loom.graph/predecessors graph station))))

(defn traverse-graph2
  [state]
  (let [{:keys [graph current-node current-line]} state
        predecessor (metro.algorithm/find-predecessor graph current-node)]
    (cond
      (not (nil? predecessor))
      (traverse-graph2 (assoc state :current-node predecessor))

      :else
      (assoc state
             :current-line (metro.graph/lines graph current-node)
             :graph (loom.attr/add-attr graph current-node :visited true)))))

;; (def config [{:name "Green" :stations ["A" "B" "C"]}]) 
;; (def g (build-graph config))

;; (def state1 (metro.blog/traverse-graph2 {:graph g :current-node "B"})) 
;; (def state2 (metro.blog/traverse-graph2 state1)) 

(defn find-successors
  [graph node]
  (filter
   (fn [s] (not (visited? graph s)))
          (loom.graph/successors graph node)))

(defn traverse-graph3
  [state]
  (let [{:keys [graph current-node current-line]} state
        predecessor (metro.algorithm/find-predecessor graph current-node)
        successors (metro.algorithm/find-successors graph current-node)]
    (cond
      (not (nil? predecessor))
      (traverse-graph3 (assoc state :current-node predecessor))

      (and (metro.algorithm/visited? graph current-node) (seq successors))
      (traverse-graph3 (assoc state :current-node (first successors)))

      :else
      (assoc state
             :current-line (metro.graph/lines graph current-node)
             :graph (loom.attr/add-attr graph current-node :visited true)))))

;; (def config [{:name "Green" :stations ["A" "B" "C"]}]) 
;; (def g (build-graph config)) 

;; (def state1 (metro.blog/traverse-graph3 {:graph g :current-node "B"})) 
;; (def state2 (metro.blog/traverse-graph3 state1)) 
;; (def state3 (metro.blog/traverse-graph3 state2)) 

(defn traverse-graph4
  [state]
  (let [{:keys [graph current-node current-line pending-nodes end]} state
        predecessor (find-predecessor graph current-node)
        successors (find-successors graph current-node)]
    (cond
      (and (not (nil? predecessor)))
      (traverse-graph4 (assoc state :current-node predecessor))

      (and (visited? graph current-node) (seq successors))
      (traverse-graph4 (assoc state
                                    :current-node (first successors)
                                    :pending-nodes (concat pending-nodes (rest successors))))

      (and (visited? graph current-node) (empty? successors))
      (traverse-graph4 (assoc state
                                    :current-node (first pending-nodes)
                                    :pending-nodes (rest pending-nodes)))

      :else
      (assoc state
             :pending-nodes (remove #{current-node} pending-nodes)
             :current-line (metro.graph/lines graph current-node)
             :graph (loom.attr/add-attr graph current-node :visited true)))))

(def config [{:name "Green" :stations ["A", "B", "C"]},
             {:name "Red" :stations ["D", "B", "E"]}])

(def g (metro.blog/build-graph config)) 

(def state1 (metro.blog/traverse-graph4 {:graph g :current-node "B"})) 
(def state2 (metro.blog/traverse-graph4 state1)) 
(def state3 (metro.blog/traverse-graph4 state2)) 
(def state4 (metro.blog/traverse-graph4 state3)) 
(def state5 (metro.blog/traverse-graph4 state4)) 

(defn traverse-graph
  [state]
  (let [{:keys [graph current-node current-line pending-nodes end]} state
        predecessor (find-predecessor graph current-node)
        successors (find-successors graph current-node)]
    (cond
      end nil

      (and (not (nil? predecessor)))
      (traverse-graph (assoc state :current-node predecessor))

      (and (visited? graph current-node) (seq successors))
      (traverse-graph (assoc state
                              :current-node (first successors)
                              :pending-nodes (concat pending-nodes (rest successors))))

      (and (visited? graph current-node) (empty? successors))
      (traverse-graph (assoc state
                              :current-node (first pending-nodes)
                              :pending-nodes (rest pending-nodes)))
      (and (empty? successors) (empty? pending-nodes))
      (assoc state
             :current-line (metro.graph/lines graph current-node)
             :graph (loom.attr/add-attr graph current-node :visited true)
             :end true)

      :else
      (assoc state
             :pending-nodes (remove #{current-node} pending-nodes)
             :current-line (metro.graph/lines graph current-node)
             :graph (loom.attr/add-attr graph current-node :visited true)))))

(def config [{:name "Green" :stations ["A", "B", "C"]},
             {:name "Red" :stations ["D", "B", "E"]}])

(def g (metro.blog/build-graph config)) 

(def state1 (metro.blog/traverse-graph {:graph g :current-node "B"})) 
(def state2 (metro.blog/traverse-graph state1)) 
(def state3 (metro.blog/traverse-graph state2)) 
(def state4 (metro.blog/traverse-graph state3)) 
(def state5 (metro.blog/traverse-graph state4)) 
(def state6 (metro.blog/traverse-graph state5)) 


(defn initial-state
  [graph]
  (let [station (first (loom.graph/nodes graph))]
    {:pending-nodes () :current-node station :current-line (lines graph station)}))

(initial-state g)


(defn git-checkout
  [branch current-branches]
  ;; current-branches have all the already created branches
  (if (contains? (set current-branches) branch)
    (str "git checkout \"" branch "\"")
    (str "git checkout --orphan \"" branch "\"")))

(defn git-commit
  [commit-name]
  (str "git commit --allow-empty -m \"" commit-name "\""))

(defn create-git-commands1
  ([commit-name branch]
   (create-git-commands1 {} commit-name branch))

  ([state commit-name branch]
   (let [current-branch (:current-branch state)
         commands (atom [])]

     ;; checkout to the branch
     (if (nil? current-branch)
       (swap! commands conj (git-checkout commit-name branch)))

     (swap! commands conj (git-commit commit-name))

     (assoc state
            :commands (flatten (deref commands))
            :current-branch branch))))

;; (def state1 (metro.blog/create-git-commands1 "A" '("Blue")))
;; (:commands state1) 
;; (def state2 (metro.blog/create-git-commands1 state1 "B" '("Blue")))
;; (:commands state2) 
;; (def state3 (metro.blog/create-git-commands1 state2 "C" '("Blue")))
;; (:commands state3) 


(defn pick-head
  [head repo branches]
  (if (and
       (contains? (set branches) head)
       (contains? (set (keys repo)) head))
    head
    (first branches)))

(defn find-divergent-branches
  [head repo branches]
  (let [station (get repo head)]
    (filter
     (fn [branch]
       (let [branch-station (get repo branch)]
         (and
          (not (nil? branch-station))
          (not= branch-station station)
          (not= branch head))))
     branches)))

(defn find-remaining-branches
  [head merging-branches branches]
  (->> (set/difference (set branches) (set merging-branches))
       (remove #{head})))

(defn update-repo
  [repo branches commit-name]
  (into repo (map (fn [branch] {branch commit-name}) branches)))

(defn create-git-commands2
  ([commit-name branches]
   (create-git-commands2 {} commit-name branches))

  ([state commit-name branches]
   (let [repo (or (:repo state) {})
         head (:head state)
         commands (atom [])
         new-head (pick-head head repo branches)]

     ;; checkout to the branch
     (if-not (= head new-head)
       (swap! commands conj (git-checkout new-head (keys repo))))

     ;; check if branch has more than one pointing to new-head
     (let [merging-branches (find-divergent-branches new-head repo branches)
           remaining-branches (find-remaining-branches new-head merging-branches branches)]
       (if (> (count merging-branches) 0)
         (swap! commands conj (git-merge commit-name merging-branches))
         (swap! commands conj (git-commit commit-name)))

       (let [not-head-branches (concat merging-branches remaining-branches)]
         (swap! commands conj (git-force-branch not-head-branches))))

     (assoc state :commands (flatten (deref commands))
            :head new-head
            :repo (update-repo repo branches commit-name)))))

