(ns aoc.a1506-2
  (:require [aoc.a1506 :refer [read pts]])
  (:refer-clojure :exclude [read eval]))

(defn cmd [board op]
  (let [action
        (case (first op)
          "turn on" inc
          "turn off" #(max (- % 1) 0)
          "toggle" (partial + 2))]
    (assert false (str "Unknown op " (first op)))))

(defn eval [expr]
  (->> (into (hash-map) (map #(% 0) (pts [nil 0 0 999 999])))
       (reduce cmd #{})
       (vals)
       (reduce +)))
