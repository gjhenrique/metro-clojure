(ns metro.git
  (:require [metro.algorithm :as algo]
            [clj-jgit.porcelain :as git]))


(defn create-git [repo commit]
  ;; check if branch exists
  (let [message (first commit)
        branch (second commit)]
    (git/git-branch-list repo)
    ;; (git/git-commit repo commit)
    )
  ) 

;; TODO: Document this
(defn create-repo
  [path stations]
  ;; if folder already has git, load this repo, does not create it
  (let [repo (git/git-init path)]
    (map (partial create-git repo) stations) 
  )) 

;; git-branch-create
;; git-commit
;; git-merge
;; git-checkout
