(ns metro.git
  (:require [metro.git :as sut]
            [me.raynes.fs :as file]
            [clojure.test :as t]
            [clj-jgit.porcelain :as git]))

(def base_folder (str (.getParent (java.io.File. *file*)) "/test_repo/")) 
(def linear-g [{:name "Blue" :stations ["A" "B" "C"]}])

(def seqa (metro.seq/seq-graph linear-g)) 

;; (first (sut/build-repo base_folder seqa))

;; (def repo (git/git-init base_folder))

;; (git/git-commit repo "Initial commit") 

;; (git/git-branch-delete repo ["Blue"] true) 
;; (git/git-branch-create repo "Blue") 


;; (def commit 
;; (->
;;  repo
;;  (.commit)
;;  (.setMessage "Oi")
;;  )) 

;; (-> repo
;;     (.checkout)
;;     (.setName "Blue")
;;     ;; (.setCreateBranch true)
;;     (.setOrphan true)
;;     (.call)) 

;; (file/delete-dir base_folder)
