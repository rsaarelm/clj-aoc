(ns aoc.a1502-2
  (:require [aoc.a1502 :refer [read]])
  (:refer-clojure :exclude [read eval]))

(defn- volume [[w h d]] (* w h d))

(defn- round [box]
  (let [[x y] (take 2 (sort box))]
    (* 2 (+ x y))))

(defn eval [expr]
  (->> expr
       (map #(+ (volume %) (round %)))
       (reduce +)))
