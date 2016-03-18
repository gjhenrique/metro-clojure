; boot -r src -d me.raynes/conch:0.8.0 -- pom -p my-project -v 0.1.0 -- jar -M Foo=bar -- install
(set-env!
 :source-paths #{"src"}
 :resource-paths #{"res"})

(require '[sandbox.core :refer :all]
         '[clojure.java.io :as io]
         '[clojure.string :as str])

(deftask to-json
  [c city CITY str "The city with the subways"]
  (with-pre-wrap fileset
    (let [file-contents (-> (io/resource (str city ".txt"))
                            (io/file)
                            (slurp))]
      (println
       (for [[station-info lines] (partition 2
                              (partition-by #(re-find #"\d+ - \w*" %) (str/split-lines file-contents)))]
         (let [info (map str/trim (str/split (first station-info) #"-"))]
                    (assoc {} :position (first info) :name (second info) :lines lines)))))
    fileset))

(deftask run []
  (with-pre-wrap fileset
    (sandbox.core/-main)
    fileset))


