(ns metro.git
  (:require [metro.algorithm :as algo]
            [clj-jgit.porcelain :as git]))


(defn create-repo
  [stations folder]
  (git/git-init folder))

;; git-branch-create
;; git-commit
;; merge-ref
