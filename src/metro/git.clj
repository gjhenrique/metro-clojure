(ns metro.git
  (:require [metro.algorithm :as algo]
            [metro.seq :as seq]
            [clojure.string :as str]
            [me.raynes.fs :as file]
            [clj-jgit.porcelain :as porcelain]
            [clj-jgit.querying :as querying])
  (:import [org.eclipse.jgit.api Git InitCommand]))

(defn filter-branch-name
  [branch]
  (str/replace (.getName branch) #"refs/heads/" ""))

(defn list-branches
  [repo]
  (map filter-branch-name (porcelain/git-branch-list repo)))

(defn branch-exists?
  [repo branch-name]
  (some #(= % branch-name) (list-branches repo)))

(defn my-git-checkout
  [^Git repo branch-name orphan]
  (-> repo
      (.checkout)
      (.setName branch-name)
      (.setOrphan orphan)
      (.call)))

(defn branch-information
  [repo]
  (map
   (fn [info]
     {:name (filter-branch-name (first info))
      :message (.getShortMessage (second info))})
   (querying/branch-list-with-heads repo)))

(defn branches-with-same-commit?
  [repo branches]
  (let [commits (map :message 
                     (filter #(contains? branches (:name %))
                             (branch-information repo)))]
    (not= (count (distinct commits)) (count commits))))

;; private
(defn create-git-commands
  [state station]
  (let [commit-name (:station station)
        branches (:line station)]
    (cond
      ;; If there is only one branch and we already in it
      (and (= (count branches) 1 ) (= (porcelain/git-branch-current repo) (first branches)))
      (porcelain/git-commit repo commit-name)

      ;; If there is only one branch and we are not in it
      (= (count branches) 1)
      (let [branch-name (first branches)
            orphan (not (branch-exists? repo branch-name))]
        (my-git-checkout repo branch-name orphan)
        (porcelain/git-commit repo commit-name))

      ;; (not= (branches-not-pointing? repo branches))
      ;; (porcelain/git-merge repo )

      )


    repo))

(defn build-git-operations
  [subway-seq target-path]
  (let [repo (porcelain/git-init target-path)]
    (reduce create-git-commands repo subway-seq)
    repo))

;; get all the branches that are not pointing to the same SHA
;; if branch is none, pick the first and create a commit
;; if branch is only one, commit it
;; if branch is more than 2, merge them
;; get the remaining branches and git branch -f in them to the current HEAD
