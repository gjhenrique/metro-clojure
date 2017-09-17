(ns metro.file
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [loom.io])
  (:gen-class))

(def regex-line-separator #"\*")

(defn create-image
  [graph]
  (loom.io/view graph))

(defn read-json-file
  [file-name]
  (json/read-json (-> file-name
                      (io/file)
                      (slurp))))

(defn- file-contents
  [file-name]
  (-> (io/resource (str file-name ".txt"))
      (io/file)
      (slurp)
      (str/split-lines)))

(defn- loop-lines
  [file-name]
  (partition 2
             (partition-by
              #(re-find (re-pattern (str regex-line-separator #"\w*")) %)
              (file-contents file-name))))

(defn- filter-stations
  [line-info]
  (let [name (ffirst line-info)
        stations (second line-info)]
    (assoc {} :name (str/replace name regex-line-separator "") :stations stations)))

(defn git-commands
  [metro-seq]
  (str/join "\n"
            (mapcat
             (fn [station-info]
               (flatten [(str "# " (:station station-info)
                              " "
                              (str/join " " (:line station-info)))
                         (:commands station-info)]))
             metro-seq)))

(defn format-stations
  [file-name]
  (map
   filter-stations
   (loop-lines file-name)))
