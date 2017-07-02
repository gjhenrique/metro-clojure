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

;; if branch does not exist, create it
;; git checkout --orphan branch

;; if branch exists and you are not in it, checkout it
;; git checkout

;; if branches have the same sha, commit the first then git branch -f the second
;; git commit --allow-empty -m "Station"
;; git branch -f yellow HEAD

;; if branch is more than 1, create a merge commit
;; git merge --no-ff --commit -m "Station" other

;; if branch is 1, create a simple commit
;; git commit --allow-empty -m "Station"
