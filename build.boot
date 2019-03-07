(set-env!
 :source-paths #{"src"}
 :resource-paths #{"res"}
 :dependencies '[[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.520" :scope "test"]

                 [aysylu/loom "1.0.2"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/test.check "0.9.0" :scope "test"]
                 [adzerk/boot-test "1.2.0" :scope "test"]
                 [tolitius/boot-check "0.1.4"]

                 ;; cljs
                 [adzerk/boot-cljs "2.1.5"]
                 [pandeiro/boot-http "0.8.3"]
                 [org.clojure/tools.nrepl "0.2.12"]
                 [adzerk/boot-reload "0.6.0"]
                 [adzerk/boot-cljs-repl "0.4.0"]
                 [com.cemerick/piggieback "0.2.2"]
                 [cider/piggieback "0.3.9" :scope "test"]
                 [nrepl "0.4.5" :scope "test"]
                 [weasel "0.7.0"]

                 [cljsjs/cytoscape "3.1.4-0"]])

(require '[clojure.java.io :as io]
         '[metro.file]
         '[metro.seq]
         '[clojure.data.json :as json]
         '[tolitius.boot-check :as check]
         '[adzerk.boot-test :as boot-test]

         '[adzerk.boot-cljs :refer [cljs]]
         '[pandeiro.boot-http :refer [serve]]
         '[adzerk.boot-reload :refer [reload]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]])


(deftask dev
  []
  (comp
   (serve :dir "target")
   (watch)
   (reload)
   (cljs-repl)
   (cljs)
   (target :dir #{"target"})))

(deftask prod
  [f folder FOLDER str "The folder to save the optimized js"]
  (comp
   (cljs :optimizations :advanced
         :source-map true
         :compiler-options {:externs ["src/metro/cytoscape_externs.js"]})
   (target :dir #{(or folder "prod")})))

(deftask raw-file-to-json
  [c city CITY str "The city with the subways"]
  (with-pre-wrap fileset
    (with-open [w (io/writer (str "res/" city ".json"))]
      (.write w (json/json-str
                 (metro.file/format-stations city))))
    fileset))

(deftask generate-git-commands
  [c city CITY str "The city with the subways"
   f file FILE str "File to be written"]
  (->>
   (metro.file/read-json-file (str "res/" city ".json"))
   (metro.seq/seq-config)
   (metro.file/git-commands)
   (spit file)))

(deftask view-graph
  [c city CITY str "The city with the subways"]
  (comp
    (->>
     (metro.file/read-json-file (str "res/" city ".json"))
     (metro.graph/build-optimized-graph)
     (metro.file/create-image))
    ;; Argh. This is ugly
    (Thread/sleep 10)))

(deftask check-sources []
  (set-env! :source-paths #{"src"})
  (comp
    (check/with-yagni)
    (check/with-eastwood)
    (check/with-kibit)
    (check/with-bikeshed)))

(deftask test []
  (set-env! :source-paths #{"src" "test"})
  (boot-test/test))

(boot.core/load-data-readers!)
