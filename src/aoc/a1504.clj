(ns aoc.a1504
  (:require [clojure.string :as str]
            [digest])
  (:refer-clojure :exclude [read eval]))

(defn read [input] input)

(defn- is-coin? [key n]
  (let [digest (digest/md5 (str key n))]
    (if (str/starts-with? digest "00000")
      n
      nil)))

(defn eval [expr]
  (->> 1
       (iterate inc)
       (map (partial is-coin? expr))
       (filter identity)
       (first)))
