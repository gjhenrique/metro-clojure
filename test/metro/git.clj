(ns metro.git
  (:require [metro.git :as sut]
            [me.raynes.fs :as file]
            [clojure.test :as t]
            [clj-jgit.porcelain :as git]))


(def base-folder (str (.getParent (java.io.File. *file*)) "/test_repo/"))

(defn call-git-commands [metro-seq]
  (file/delete-dir base_folder)
  (build-git-operations (metro.seq/seq-graph metro-seq) base-folder))

(def linear-g [{:name "Blue" :stations ["A" "B" "C"]}])

(t/deftest simple-git-operations
  (let [repo (call-git-commands linear-g)]
    (t/is (instance? org.eclipse.jgit.api.Git repo))

    (t/is (= (sut/list-branches repo) '("Blue")))

    (t/is (= (count (git/git-log repo)) 3))

    (t/is (= (map #(.getShortMessage %) (git/git-log repo)  '("C" "B" "A"))))))

(def merge-g [{:name "Blue" :stations ["A" "B"]}
              {:name "Red" :stations ["C" "B"]}])

(t/deftest merge-git-operations
  (let [repo (call-git-commands merge-g)]
    (t/is (= (sut/list-branches repo) '("Blue" "Red")))))
