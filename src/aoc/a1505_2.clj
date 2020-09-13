(ns aoc.a1505-2
  (:require [aoc.a1505 :refer [read]])
  (:refer-clojure :exclude [read eval]))

(defn- pairs2? [str]
  (if (>= (count str) 4)
    (or (re-find (re-pattern (subs str 0 2)) (subs str 2))
        (recur (subs str 1)))
    nil))

(defn- dubs3? [str]
  (->> (map vector str (rest str) (nthrest str 2))
       (filter (fn [[a _ c]] (= a c)))
       (first)))

(defn- nice? [str]
  (and (pairs2? str) (dubs3? str)))

(defn eval [expr] (count (filter nice? expr)))
