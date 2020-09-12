(ns aoc.a1501-2
  (:require [aoc.a1501 :refer [read]])
  (:refer-clojure :exclude [read eval]))

(defn- running-sum [acc elt]
  (conj acc (+ (peek acc) elt)))

(defn eval [expr]
  (->> expr
       (reduce running-sum [0])
       (take-while #(>= % 0))
       (count)))
