; boot -r src -d me.raynes/conch:0.8.0 -- pom -p my-project -v 0.1.0 -- jar -M Foo=bar -- install
(set-env!
 :source-paths #{"src"}
 :resource-paths #{"res"}
 :dependencies '[[aysylu/loom "0.5.4"]
                 [org.clojure/test.check "0.9.0" :scope "test"]])


(comment 
(deftask run []
  (with-pre-wrap fileset
    (sandbox.core/-main)
    fileset))
)

(boot.core/load-data-readers!) 
