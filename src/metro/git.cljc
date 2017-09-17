(ns metro.git
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn git-checkout
  [branch repo]
  (if (contains? (set (keys repo)) branch)
    (str "git checkout \"" branch "\"")
    (str "git checkout --orphan \"" branch "\"")))

(defn git-commit
  [commit-name]
  (str "git commit --allow-empty -m \"" commit-name "\""))

(defn git-force-branch
  [branches]
  (map (fn [branch] (str "git branch -f \"" branch "\" HEAD")) branches))

(defn git-merge
  [commit-name branches]
  (str "git merge --strategy=ours --allow-unrelated-histories --no-ff --commit -m \""
       commit-name
       "\" "
       (str/join " " branches)))

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

(defn create-git-commands
  ([commit-name branches]
   (create-git-commands {} commit-name branches))

  ([state commit-name branches]
   (let [repo (or (:repo state) {})
         head (:head state)
         commands (atom [])
         new-head (pick-head head repo branches)]

     ;; checkout to the branch
     (if-not (= head new-head)
       (swap! commands conj (git-checkout new-head repo)))

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

(defn git-commands
  [stations]
  (let [commands (atom [])]
    (reduce
     (fn [state station-info]
       (let [new-state (create-git-commands state (:station station-info) (:line station-info))]
         (swap! commands conj (:commands new-state))
         new-state))
     {}
     stations)
    (deref commands)))
