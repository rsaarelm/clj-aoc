(ns aoc.a1505
  (:require [clojure.string :as str])
  (:refer-clojure :exclude [read eval]))

(defn read [input] (str/split-lines input))

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

(defn eval [expr] (count (filter nice? expr)))
