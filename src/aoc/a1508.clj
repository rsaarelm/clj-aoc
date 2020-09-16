(ns aoc.a1508
  (:require [clojure.string :as str]))

(defn- parse [input] (str/split-lines input))

(defn cleanup [s]
  (-> s
      (subs 1 (dec (count s)))
      (str/replace "\\\\" "@")
      (str/replace #"\\x.." "@")
      (str/replace "\\" "")))

(defn p1 [input]
  (->> (parse input)
       (map #(- (count %) (count (cleanup %))))
       (reduce +)))

(defn dirt-count [s]
  (->> s
       (map #(if (#{\" \\} %) 2 1))
       (reduce +)
       (+ 2)))

(defn p2 [input]
  (->> (parse input)
       (map #(- (dirt-count %) (count %)))
       (reduce +)))

