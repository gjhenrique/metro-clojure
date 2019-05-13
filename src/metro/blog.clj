(ns metro.blog
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn- add-line-information
  "Store the lines as attributes of each station"
  [graph stations line-name]
  (reduce
   (fn [g station]
     (let [current-line (or (loom.attr/attr g station :lines) [])]
       (loom.attr/add-attr g station :lines (conj current-line line-name))))
   graph
   (set (flatten stations))))

(defn build-graph-without-cycles
  "Build a new loom graph with the stations
  as nodes and lines as attributes"
  [config]
  (reduce
   (fn [graph line-config]
     ;; Break the stations ["A" "B" "C"]
     ;; to [["A" "B"] ["B" "C"]]
     (let [connections
           (partition 2 1 (:stations line-config))
           ;; Add the connections as edges
           new-graph (apply loom.graph/digraph graph connections)]
       (add-line-information new-graph connections (:name line-config))))
   ;; Initializing reduce function with an empty directed graph
   (loom.graph/digraph)
   config)) 

(def config1 [{:name "Red", :stations ["A", "C"]},
             {:name "Blue", :stations ["B", "C"]}]) 
(build-graph-without-cycles config1) 


(defn- reverse-stations
  "Reversing [[A B] [B C]] to [[C B] [B C]]"
  [connections]
  (map
   (fn [info] [(second info) (first info)])
   (reverse connections)))

(defn- connections-without-cycle
  "Check if adding the connections to a graph introduces cycle
   If the graph is a dag, returns the connection, otherwise returns nil"
  [graph connections]
  (let [new-graph (apply loom.graph/digraph graph connections)]
    (when (loom.alg/dag? new-graph) connections)))

(defn- valid-connection
  "Return the connections that has a cycle"
  [graph line-config]
  (let [line-name (:name line-config)
        connections (partition 2 1 (:stations line-config))]
    (or (connections-without-cycle graph connections)
        (connections-without-cycle graph (reverse-stations connections)))))

(defn build-graph-reversal
  "Build a graph and try to reverse cycles if necessary"
  [config]
  (reduce
   (fn [graph line-config]
     (let [connections (valid-connection graph line-config)
           new-graph (apply loom.graph/digraph graph connections)]
       (add-line-information new-graph connections (:name line-config))))
   (loom.graph/digraph)
   config))

(def config2 [{:name "Red" :stations ["B" "C" "D"]}
              {:name "Blue" :stations ["A" "D" "B"]}]) 

(def g (build-graph-reversal config2)) 

(loom.graph/edges g) 
;; (["C" "D"] ["B" "C"] ["B" "D"] ["D" "A"])

;; (loom.graph/predecessors g "D")
;; #{"C" "B"}


;; (loom.alg/dag? g)
;; (def g2 (build-reversing-lines config2))
;; (loom.alg/dag? g2)

(defn- connections-removing-cycles
  "For each new connection, check if we're introducing a cycle.
   If there is a cycle,
   try to connect the origin station to the next destination"
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
    (or (connections-without-cycle graph connections)
        (connections-without-cycle graph (reverse-stations connections))
        (connections-removing-cycles graph (:stations line-config) line-name))))

(defn build-graph-with-cycles
  "Build a graph and remove cycles"
  [config]
  (reduce
   (fn [graph line-config]
     (let [connections (valid-connection graph line-config)
           new-graph (apply loom.graph/digraph graph connections)]
       (add-line-information new-graph connections (:name line-config))))
   (loom.graph/digraph)
   config))

(def config2 [{:name "Red" :stations ["B" "C" "D" "B"]}]) 

(def g (build-graph-reversal config2)) 

(loom.graph/edges g) 

(defn- lines
  "Auxiliary function to return the lines of a station"
  [graph station]
  (loom.attr/attr graph station :lines))

(defn traverse-graph-single-station
  "Only traverse a single station"
  [state]
  (let [{:keys [graph current-node]} state]
      (assoc state
             :current-line (lines graph current-node)
             :graph (loom.attr/add-attr graph current-node :visited true))))


(def config [{:name "Green" :stations ["A" "B" "C"]}]) 
(def g (build-graph-with-cycles config))

;; => (def state1 (traverse-graph-single-station {:graph g :current-node "B"})) 
;; state1 
;; => (def state2 (traverse-graph-single-station state1)) 
;; state2 

;; (def config [{:name "Green" :stations ["A" "B" "C"]}])
;; (def g1 (build-graph3 config)) 
;; (def state1 (traverse-graph1 {:graph g1 :current-node "B"})) 
;; (def state2 (traverse-graph1 {:graph g1 :current-node "B"})) 

(defn- visited?
  [graph station]
  (loom.attr/attr graph station :visited))

(defn- find-unvisited-predecessor
  [graph station]
  (first (filter
          (fn [p] (not (visited? graph p)))
          (loom.graph/predecessors graph station))))

(defn traverse-graph-with-predecessors
  "Traverse and don't visit if there are unvisited predecessors"
  [state]
  (let [{:keys [graph current-node current-line]} state
        predecessor (find-unvisited-predecessor graph current-node)]
    (cond
      (not (nil? predecessor))
      (traverse-graph-with-predecessors
       (assoc state :current-node predecessor))

      :else
      (assoc state
             :current-line (metro.graph/lines graph current-node)
             :graph (loom.attr/add-attr graph current-node :visited true)))))

(def config [{:name "Green" :stations ["A" "B" "C"]}]) 
(def g (build-graph-without-cycles config)) 

(def state1 (traverse-graph-with-predecessors {:graph g :current-node "B"})) 
(def state2 (traverse-graph-with-predecessors state1)) 
(def state3 (traverse-graph-with-predecessors state2)) 

;; (defn traverse-graph-with-pending-stations
;;   "When picking a station from multiple successors, add the remaining stations in the pending-nodes list"
;;   [state]
;;   (let [{:keys [graph current-node current-line pending-nodes end]} state
;;         predecessor (find-unvisited-predecessor graph current-node)
;;         successors (find-unvisited-successors graph current-node)]
;;     (cond
;;       (not (nil? predecessor))
;;       (traverse-graph-with-pending-stations (assoc state :current-node predecessor))

;;       (and (visited? graph current-node) (seq successors))
;;       (traverse-graph-with-pending-stations (assoc state
;;                                     :current-node (first successors)
;;                                     :pending-nodes (concat pending-nodes (rest successors))))

;;       (and (visited? graph current-node) (empty? successors))
;;       (traverse-graph-with-pending-stations (assoc state
;;                                     :current-node (first pending-nodes)
;;                                     :pending-nodes (rest pending-nodes)))

;;       :else
;;       (assoc state
;;              :pending-nodes (remove #{current-node} pending-nodes)
;;              :current-line (lines graph current-node)
;;              :graph (loom.attr/add-attr graph current-node :visited true)))))

;; (def config [{:name "Green" :stations ["A", "B", "C"]},
;;              {:name "Red" :stations ["D", "B", "E"]}])

;; (def g (build-graph config)) 

;; (def state1 (traverse-graph-with-pending-stations {:graph g :current-node "B"})) 
;; (def state2 (traverse-graph-with-pending-stations state1)) 
;; (def state3 (traverse-graph-with-pending-stations state2)) 
;; (def state4 (traverse-graph-with-pending-stations state3)) 
;; (def state5 (traverse-graph-with-pending-stations state4)) 

;; (defn traverse-graph
;;   "In the final station, pass a key called end. If this key is present, nil is returned"
;;   [state]
;;   (let [{:keys [graph current-node current-line pending-nodes end]} state
;;         predecessor (find-predecessor graph current-node)
;;         successors (find-successors graph current-node)]
;;     (cond
;;       end nil

;;       (not (nil? predecessor))
;;       (traverse-graph (assoc state :current-node predecessor))

;;       (and (visited? graph current-node) (seq successors))
;;       (traverse-graph (assoc state
;;                               :current-node (first successors)
;;                               :pending-nodes (concat pending-nodes (rest successors))))

;;       (and (visited? graph current-node) (empty? successors))
;;       (traverse-graph (assoc state
;;                               :current-node (first pending-nodes)
;;                               :pending-nodes (rest pending-nodes)))
;;       (and (empty? successors) (empty? pending-nodes))
;;       (assoc state
;;              :current-line (metro.graph/lines graph current-node)
;;              :graph (loom.attr/add-attr graph current-node :visited true)
;;              :end true)

;;       :else
;;       (assoc state
;;              :pending-nodes (remove #{current-node} pending-nodes)
;;              :current-line (metro.graph/lines graph current-node)
;;              :graph (loom.attr/add-attr graph current-node :visited true))))) 

;; (def config [{:name "Green" :stations ["A", "B", "C"]},
;;              {:name "Red" :stations ["D", "B", "E"]}]) 

;; (def g (build-graph config)) 

;; (def state1 (traverse-graph {:graph g :current-node "B"})) 
;; (def state2 (traverse-graph state1)) 
;; (def state3 (traverse-graph state2)) 
;; (def state4 (traverse-graph state3)) 
;; (def state5 (traverse-graph state4)) 
;; (def state6 (traverse-graph state5)) 


;; (defn initial-state
;;   [graph]
;;   (let [station (first (loom.graph/nodes graph))]
;;     {:graph graph
;;      :pending-nodes ()
;;      :current-node station
;;      :current-line (lines graph station)}))

;; (initial-state g)


;; (defn git-checkout
;;   [branch current-branches]
;;   ;; current-branches have all the already created branches
;;   (if (contains? (set current-branches) branch)
;;     (str "git checkout \"" branch "\"")
;;     (str "git checkout --orphan \"" branch "\"")))

;; (defn git-commit
;;   [commit-name]
;;   (str "git commit --allow-empty -m \"" commit-name "\""))

;; (defn create-git-commands-single
;;   "Returns an array of commands from a single line/branch"
;;   ([commit-name branch]
;;    (create-git-commands-single {} commit-name branch))

;;   ([state commit-name branch]
;;    (let [current-branch (:current-branch state)
;;          commands (atom [])]

;;      (if (nil? current-branch)
;;        (swap! commands conj (git-checkout commit-name branch)))

;;      (swap! commands conj (git-commit commit-name))

;;      (assoc state
;;             :commands (flatten (deref commands))
;;             :current-branch branch))))

;; ;; (def state1 (metro.blog/create-git-commands-single "A" '("Blue")))
;; ;; (:commands state1) 
;; ;; (def state2 (metro.blog/create-git-commands1 state1 "B" '("Blue")))
;; ;; (:commands state2) 
;; ;; (def state3 (metro.blog/create-git-commands1 state2 "C" '("Blue")))
;; ;; (:commands state3) 


;; (defn pick-head
;;   [current-head repo station-branches]
;;   (if (and
;;        (contains? (set station-branches) current-head)
;;        (contains? (set (keys repo)) current-head))
;;     current-head
;;     (first station-branches)))

;; (def head1 (pick-head nil {} '("Blue")))
;; ;; => "Blue"
;; (def head2 (pick-head head1 {"Blue" '("A")} '("Red" "Blue"))) 
;; ;; => "Blue"
;; (def head3 (pick-head head2 {"Blue" '("A" "B") "Red" '("B")} '("Red"))) 
;; ;; => "Red"

;; (defn find-merge-branches
;;   [head repo branches]
;;   (let [head-station (get repo head)]
;;     (filter
;;      (fn [branch]
;;        (let [branch-station (get repo branch)]
;;          (and
;;           (not (nil? branch-station))
;;           (not= branch-station head-station)
;;           (not= branch head))))
;;      branches)))

;; (find-merge-branches nil {} '("Blue"))
;; ;; => ()
;; (find-merge-branches "Blue" {"Blue" "A"} '("Red"))
;; ;; => ()
;; (find-merge-branches "Red" {"Blue" "A" "Red" "C"} '("Red" "Blue")) 
;; ;; => ("Blue")

;; (defn find-companion-branches
;;   [head merging-branches branches]
;;   (->> (set/difference (set branches) (set merging-branches))
;;        (remove #{head})))

;; (find-companion-branches "Red" '("Blue") '("Red" "Blue")) 
;; (find-companion-branches "Red" '() '("Red" "Blue")) 
;; ;; (find-merge-branches "Blue" {} '("Blue"))

;; (defn git-force-branch
;;   [branches]
;;   (map (fn [branch] (str "git branch -f \"" branch "\" HEAD")) branches))

;; (defn git-merge
;;   [commit-name branches]
;;   (str "git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m \""
;;        commit-name
;;        "\" "
;;        (str/join " " branches)))

;; (defn update-repo
;;   [repo branches commit-name]
;;   (into repo (map (fn [branch] {branch commit-name}) branches)))

;; (defn create-git-commands
;;   ([commit-name branches]
;;    (create-git-commands {} commit-name branches))

;;   ([state commit-name branches]
;;    (let [repo (or (:repo state) {})
;;          head (:head state)
;;          commands (atom [])
;;          new-head (pick-head head repo branches)]

;;      ;; checkout to the branch
;;      (if-not (= head new-head)
;;        (swap! commands conj (git-checkout new-head (keys repo))))

;;      ;; check if branch has more than one pointing to new-head
;;      (let [merging-branches (find-merge-branches new-head repo branches)
;;            companion-branches (find-companion-branches new-head merging-branches branches)]
;;        (if (> (count merging-branches) 0)
;;          (swap! commands conj (git-merge commit-name merging-branches))
;;          (swap! commands conj (git-commit commit-name)))

;;        (let [not-head-branches (concat merging-branches companion-branches)]
;;          (swap! commands conj (git-force-branch not-head-branches))))

;;      (assoc state :commands (flatten (deref commands))
;;             :head new-head
;;             :repo (update-repo repo branches commit-name)))))

;; (def config
;;   [{:name "Green" :stations ["A", "D", "E"]},
;;    {:name "Red" :stations ["B", "D", "F", "G"]},
;;    {:name "Blue" :stations ["C", "D", "F", "H"]}])
;; (def g (build-graph config))

;; (def alg-state1 (traverse-graph (initial-state g)))
;; (def git-state1 (create-git-commands (:current-node alg-state1) (:current-line alg-state1))) 

;; (def alg-state2 (traverse-graph alg-state1)) 
;; (def git-state2 (create-git-commands git-state1 (:current-node alg-state2) (:current-line alg-state2)))

;; (def alg-state3 (traverse-graph alg-state2))
;; (def git-state3 (create-git-commands git-state2 (:current-node alg-state3) (:current-line alg-state3)))

;; (def alg-state4 (traverse-graph alg-state3))
;; (def git-state4 (create-git-commands git-state3 (:current-node alg-state4) (:current-line alg-state4)))
;; (:commands git-state4)
;; ;; => ("git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m \"D\" Red Blue" "git branch -f \"Red\" HEAD" "git branch -f \"Blue\" HEAD")
;; ; => (git merge )
;; (:head git-state4)
;; ;; => "Green"
;; (:repo git-state4)
;; ;; => {"Blue" "D", "Red" "D", "Green" "D"}

;; (declare seq-first seq-rest seq-next)

;; (deftype MetroGraph [algorithm-state git-state]
;;   clojure.lang.ISeq
;;   (first [self] (seq-first algorithm-state git-state))

;;   (more [self] (seq-rest self))

;;   (next [self] (seq-next algorithm-state git-state))

;;   (seq [self] self))

;; (defn- seq-first
;;   [algorithm-state git-state]
;;   {:station (:current-node algorithm-state)
;;    :line (:current-line algorithm-state)
;;    :commands (:commands git-state)
;;    :state algorithm-state})

;; (defn- seq-rest
;;   [self]
;;   (or (next self) '()))

;; (defn- seq-next
;;   [algorithm-state git-state]
;;   (let [new-state (traverse-graph algorithm-state)]
;;     (when-not (nil? new-state)
;;       (let [new-git-state (create-git-commands git-state
;;                                                          (:current-node new-state)
;;                                                          (:current-line new-state))]
;;         (MetroGraph. new-state new-git-state))))) 

;; (defn- build-seq
;;   [initial-state]
;;   (MetroGraph. initial-state
;;                (metro.git/create-git-commands
;;                 (:current-node initial-state)
;;                 (:current-line initial-state))))

;; (defn metro-git-seq
;;   [config]
;;   (build-seq
;;    (-> config
;;        (build-graph)
;;        (initial-state)
;;        (traverse-graph)))) 

;; (def config [{:name "Red", :stations ["A", "C"]},
;;                 {:name "Green", :stations ["B", "C"]}])

;; (def seq1 (metro-git-seq config))
;; (:station (first seq1))
;; (:line (last seq1))


;; (def nyc-config (metro.file/read-json-file (str "res/nyc.json")))
;; (def nyc-seq (metro-git-seq nyc-config))

;; (:command (first nyc-seq))

;; (sort (set (flatten (map :line nyc-seq))))
;; ;; => ("1" "2" "3" "4" "5" "6" "7" "A" "B" "C" "D" "E" "F" "G" "J" "L" "M" "N" "Q" "R" "W" "Z")

;; (map :station (filter #(> (count (:line %)) 6) nyc-seq)) 
;; ;; => ("West 4 Street - Washington Square / 6 Avenue" "Atlantic Avenue / Barclays Center")

;; (count (filter #(str/starts-with? % "git merge") (mapcat :commands nyc-seq)))
;; ;; => 62

;; (spit "a.txt" (str/join "\n" (mapcat :commands nyc-seq)))



