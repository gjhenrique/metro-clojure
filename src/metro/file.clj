(ns metro.file
  (:require [sandbox.core :refer :all]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(defn file-contents
  [file-name]
  (-> (io/resource (str file-name ".txt"))
      (io/file)
      (slurp)))


(defn find-connections
  [line-name station lines]
  (map :name (filter #(and (some #{station} (:stations %))
                           (not (= line-name (:name %))))
                      lines)))

(defn association-between-lines
  [line lines]
  (assoc line :stations
         (map #(assoc {}
                      :name %
                      :connections (find-connections (:name line) % lines))
              (:stations line))))


(defn loop-lines
  [file-name]
  (partition 2 (partition-by
                #(re-find #"\d+ - \w*" %)
                (str/split-lines (file-contents file-name)))))

(defn build-stations
  [file-name]
  (for [[station-info stations] (loop-lines file-name)]
    (let [info (map str/trim (str/split (first station-info) #"-"))]
      (assoc {} :position (read-string (first info)) :name (second info) :stations stations))))

;; TODO: put functions under the methods
(defn format-stations
  [file-name]
   (let [stations (build-stations file-name)]
     (map #(association-between-lines % stations) stations)))
