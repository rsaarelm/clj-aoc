(ns aoc.a1506
  (:require [clojure.string :as str])
  (:require [aoc.util :refer [smart-read]])
  (:require [clojure.set :refer [union difference]])
  (:refer-clojure :exclude [read eval]))

(defn read [input]
  (->> input
       (str/split-lines)
       (map (partial re-find #"(.+) (\d+),(\d+) through (\d+),(\d+)"))
       (map rest)
       (map (partial map smart-read))))

(defn toggle [board cell]
  (if (board cell) (disj board cell) (conj board cell)))

(defn pts [[_ x1 y1 x2 y2]]
  (set (for [x (range x1 (inc x2)) y (range y1 (inc y2))] [x y])))

(defn cmd [board op]
  (case (first op)
    "turn on" (union board (pts op))
    "turn off" (difference board (pts op))
    "toggle" (reduce toggle board (pts op))
    (assert false (str "Unknown op " (first op)))))

(defn eval [expr]
  (->> expr
       (reduce cmd #{})
       (count)))
