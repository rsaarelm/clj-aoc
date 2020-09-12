(ns aoc.a0000
  (:refer-clojure :exclude [read eval]))

(defn read [text] text)

(defn eval [input] (str input ", World!"))
