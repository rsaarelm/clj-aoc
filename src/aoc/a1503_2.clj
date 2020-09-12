(ns aoc.a1503-2
  (:require [aoc.a1503 :refer [read vec-add]])
  (:require [clojure.set :refer [union]])
  (:refer-clojure :exclude [read eval]))

(defn- index-filter [p ss]
  (->> ss
       (map-indexed (fn [i e] (if (p i) e nil)))
       (filter (complement nil?))))

(defn eval [expr]
  (let
   [santa (index-filter even? expr)
    robot (index-filter odd? expr)
    path (partial reductions vec-add [0 0])]
    (count (union (set (path santa)) (set (path robot))))))
