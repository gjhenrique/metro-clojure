How to test it
How to build to production (prod)
How to see clojurescript (dev)

https://github.com/clojure-emacs/cider/blob/master/doc/up_and_running.md

```
(setq cider-boot-parameters "dev")
```

```
(first
(rest 
(let [current-node "B"
      graph (metro.graph/build-optimized-graph [{:name "Red" :stations ["A", "C", "B"]}])]
  (metro.seq/build-seq
   {:current-node current-node :graph graph}
   metro.algorithm/traverse-subway-graph
   ))))
``` 
