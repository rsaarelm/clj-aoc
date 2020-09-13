(ns aoc.a0001
  (:refer-clojure :exclude [read eval]))

; Example with input only as edn.

(defn read [input] (assert false "Not implemented!"))

(defn eval [expr] (reduce + expr))
