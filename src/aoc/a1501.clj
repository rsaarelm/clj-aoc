(ns aoc.a1501
  (:refer-clojure :exclude [read eval]))

(defn read [input] (map {\( 1 \) -1} input))

(defn eval [expr] (reduce (partial +) 0 expr))
