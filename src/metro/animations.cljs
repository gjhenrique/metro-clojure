(ns metro.animations
  (:require [metro.algorithm :as alg]
            [cljsjs.d3]))

(enable-console-print!)

(.. js/d3
    (select "h1")
    (transition)
    (style "background-color" "white"))

