(ns aoc.a1506
  (:require [aoc.util :refer [re-read]]
            [clojure.string :as str]
            [clojure.set :refer [union difference]]))

(defn- parse [input]
  (->> input
       (str/split-lines)
       (map (partial re-read #"(.+) (\d+),(\d+) through (\d+),(\d+)"))))

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

(defn p1 [input]
  (->> (parse input)
       (reduce cmd #{})
       (count)))

(defn cmd2 [board op]
  (let [action
        (case (first op)
          "turn on" inc
          "turn off" #(max (- % 1) 0)
          "toggle" (partial + 2)
          (assert false (str "Unknown op " (first op))))]
    (reduce #(update-in %1 [%2] action) board (pts op))))

(defn p2 [input]
  (let [board (into (hash-map) (map #(vector % 0) (pts [nil 0 0 999 999])))]
    (->> (parse input)
         (reduce cmd2 board)
         (vals)
         (reduce +))))
