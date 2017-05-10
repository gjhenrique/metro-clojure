(set-env!
 :source-paths #{"src"}
 :resource-paths #{"res"}
 :dependencies '[[org.clojure/clojure "1.8.0"]
                 [aysylu/loom "0.5.4"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/test.check "0.9.0" :scope "test"]
                 [tolitius/boot-check "0.1.4"]])


(require '[clojure.java.io :as io]
         '[metro.file]
         '[clojure.data.json :as json]
         '[tolitius.boot-check :as check])

(deftask raw-file-to-json
  [c city CITY str "The city with the subways"]
  (with-pre-wrap fileset
    (with-open [w (io/writer (str "res/" city ".json"))]
      (.write w (json/json-str
                 (metro.file/format-stations city))))
    fileset))

(deftask check-sources []
  (set-env! :source-paths #{"src"})
  (comp
    (check/with-yagni)
    (check/with-eastwood)
    (check/with-kibit)
    (check/with-bikeshed)))

(boot.core/load-data-readers!) 
