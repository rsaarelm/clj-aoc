(ns aoc.a1505
  (:require [clojure.string :as str]))

(defn- parse [input] (str/split-lines input))

(defn- vowels [str] (filter (set "aeiou") str))

(defn- dubs? [str]
  (->> (map vector str (rest str))
       (filter (fn [[a b]] (= a b)))
       (first)))

(defn- nastygram? [str]
  (let [nasties #{#"ab" #"cd" #"pq" #"xy"}]
    (some #(re-find % str) nasties)))

(defn nice? [str]
  (cond
    (< (count (vowels str)) 3) false
    (not (dubs? str)) false
    (nastygram? str) false
    :else true))

(defn p1 [input]
  (->> (parse input)
       (filter nice?)
       (count)))

(defn- pairs2? [str]
  (if (>= (count str) 4)
    (or (re-find (re-pattern (subs str 0 2)) (subs str 2))
        (recur (subs str 1)))
    nil))

(defn- dubs3? [str]
  (->> (map vector str (rest str) (nthrest str 2))
       (filter (fn [[a _ c]] (= a c)))
       (first)))

(defn- nice2? [str]
  (and (pairs2? str) (dubs3? str)))

(defn p2 [input]
  (->> (parse input)
       (filter nice2?)
       (count)))
