(ns metro.file
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(def regex-line-separator #"\*")

(defn file-contents
  [file-name]
  (-> (io/resource (str file-name ".txt"))
      (io/file)
      (slurp)
      (str/split-lines)))

(defn loop-lines
  [file-name]
  (partition 2
             (partition-by
                #(re-find (re-pattern (str regex-line-separator #"\w*")) %)
                (file-contents file-name))))

(defn filter-stations
  [line-info]
  (let [name (ffirst line-info)
        stations (second line-info)]
    (assoc {} :name (str/replace name regex-line-separator "") :stations stations)))

;; TODO: put functions under the methods
(defn format-stations
  [file-name]
  (map
   filter-stations
   (loop-lines file-name)))
