; boot -r src -d me.raynes/conch:0.8.0 -- pom -p my-project -v 0.1.0 -- jar -M Foo=bar -- install
(set-env!
 :source-paths #{"src"}
 :resource-paths #{"res"}
 :dependencies '[[org.clojure/data.json "0.2.6"]
                 [aysylu/loom "0.5.4"]])

(require '[sandbox.core :refer :all]
         '[clojure.java.io :as io]
         '[clojure.string :as str]
         '[clojure.data.json :as json]
         '[loom.graph :as graph])

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
                      :connections (find-connections (:name line) % lines)) (:stations line))))

(deftask to-json
  [c city CITY str "The city with the subways"]
  (with-pre-wrap fileset
    (let [file-contents (-> (io/resource (str city ".txt"))
                            (io/file)
                            (slurp))]
      (with-open [w (io/writer (str "res/" city ".json"))]
        (.write w (json/json-str
                   (let [ready
                         (let [semi-ready (for [[station-info stations] (partition 2
                                                                                   (partition-by #(re-find #"\d+ - \w*" %) (str/split-lines file-contents)))]
                                            (let [info (map str/trim (str/split (first station-info) #"-"))]
                                              (assoc {} :position (read-string (first info)) :name (second info) :stations stations)))]
                           semi-ready)]
                     (map #(association-between-lines % ready) ready))))))
    fileset))

(deftask run []
  (with-pre-wrap fileset
    (sandbox.core/-main)
    fileset))

