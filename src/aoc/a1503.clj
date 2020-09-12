(ns aoc.a1503
  (:require [clojure.string :as str])
  (:refer-clojure :exclude [read eval]))

(defn vec-add [[x1 y1] [x2 y2]] [(+ x1 x2) (+ y1 y2)])

(defn read [input]
  (map {\< [-1 0] \^ [0 -1] \> [1 0] \v [0 1]} input))

(defn eval [expr]
  (->> expr
       (reductions vec-add [0 0])
       (set)
       (count)))

; 2081
