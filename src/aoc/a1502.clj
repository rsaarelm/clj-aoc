(ns aoc.a1502
  (:require [clojure.string :as str]))

(defn- parse [input]
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

(defn- volume [[w h d]] (* w h d))

(defn- round [box]
  (let [[x y] (take 2 (sort box))]
    (* 2 (+ x y))))

(defn p1 [input]
  (let [expr (parse input)]
    (->> expr
         (map paper)
         (reduce +))))

(defn p2 [input]
  (let [expr (parse input)]
    (->> expr
         (map #(+ (volume %) (round %)))
         (reduce +))))
