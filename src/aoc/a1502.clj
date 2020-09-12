(ns aoc.a1502
  (:require [clojure.string :as str])
  (:refer-clojure :exclude [read eval]))

(defn read [input]
  (->> input
       (str/split-lines)
       (map #(str/split % #"x"))
       (map (partial map read-string))))

(defn- sides [[w h d]]
  [(* d w)
   (* w h)
   (* h d)])

(defn- area [box]
  (* 2 (apply + (sides box))))

(defn- paper [box]
  (+ (area box) (apply min (sides box))))

(defn eval [expr]
  (->> expr
       (map paper)
       (reduce +)))


