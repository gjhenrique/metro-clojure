(ns metro.git
  (:require [metro.algorithm :as algo]
            [metro.seq :as seq]
            [clj-jgit.porcelain :as git]))


(clojure.set/difference (set [1 2 3]) #{2 3 5}) 

(clojure.set/union #{1 2 3} '(3 5)) 

(defn create-new-branches [lines]
  (map #(println (str "git checkout --orphan " %)) lines))

(defn create-commit [state station-info]
  (let [{:keys [head created-branches] :or {created-branches #{}}} state
        {:keys [station line]} station-info
        new-branches (clojure.set/difference (set line) created-branches)]
    (create-new-branches new-branches)

    (assoc state
     :created-branches (clojure.set/union created-branches new-branches)
     :head (first line)
    )
  )) 

(defn create-seq
  [subway-seq]
  (reduce
   create-commit
   {:created-branches #{}}
   subway-seq
   )
  ) 

(create-seq seqa) 

;; TODO: Document this
;; (defn build-repo
;;   [path subway-seq]
;;   ;; if folder already has git, load this repo, does not create it
;;   (let [repo (git/git-init path)]
;;     (map (partial create-git repo) subway-seq) 
;;   )) 

;; if branch does not exist, create it
;; git checkout --orphan branch

;; if branch exists and you are not in it, checkout it
;; git checkout

;; if branch is more than 1, create a merge commit
;; git commit --allow-empty -m "Station"

;; if branch is 1, create a simple commit
;; git merge --no-ff --commit -m "Station" other

;; (git/git-branch-list repo)
;; (git/git-commit repo commit)

;; git-branch-create
;; git-commit
;; git-merge
;; git-checkout
