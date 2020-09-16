(ns aoc.a1503
  (:require [clojure.set :refer [union]]))

(defn- parse [input]
  (map {\< [-1 0] \^ [0 -1] \> [1 0] \v [0 1]} input))

(defn vec-add [[x1 y1] [x2 y2]] [(+ x1 x2) (+ y1 y2)])

(defn- index-filter [p ss]
  (->> ss
       (map-indexed (fn [i e] (if (p i) e nil)))
       (filter (complement nil?))))

(defn p1 [input]
  (->> (parse input)
       (reductions vec-add [0 0])
       (set)
       (count)))

(defn p2 [input]
  (let [expr (parse input)
        santa (index-filter even? expr)
        robot (index-filter odd? expr)
        path (partial reductions vec-add [0 0])]
    (count (union (set (path santa)) (set (path robot))))))
