(ns metro.git
  (:require [metro.algorithm :as algo]
            [metro.seq :as seq]
            [clojure.string :as str]
            [me.raynes.fs :as file]
            [clj-jgit.porcelain :as git])
  (:import [org.eclipse.jgit.api Git InitCommand]))

(defn list-branches
  [repo]
  (map
   #(str/replace (.getName %) #"refs/heads/" "")
   (git/git-branch-list repo)))

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

;; private
(defn create-git-commands
  [repo station]
  (let [commit-name (:station station)
        branches (:line station)]
    (cond
      ;; If there is only one branch and we already in it
      (and (= (count branches) 1 ) (= (git/git-branch-current repo) (first branches)))
      (git/git-commit repo commit-name)

      ;; If there is only one branch and we are not in it
      (= (count branches) 1)
      (let [branch-name (first branches)
            orphan (not (branch-exists? repo branch-name))]
        (my-git-checkout repo branch-name orphan)
        (git/git-commit repo commit-name)))
    repo))

(defn build-git-operations
  [subway-seq target-path]
  (let [repo (git/git-init target-path)]
    (reduce create-git-commands repo subway-seq)
    repo))

;; get all the branches that are not pointing to the same SHA
;; if branch is none, pick the first and create a commit
;; if branch is only one, commit it
;; if branch is more than 2, merge them
;; get the remaining branches and git branch -f in them to the current HEAD
