(ns aoc.a1504
  (:require [clojure.string :as str] [digest]))

(defn- is-coin? [prefix key n]
  (let [digest (digest/md5 (str key n))]
    (if (str/starts-with? digest prefix)
      n
      nil)))

(defn p1 [input]
  (->> 1
       (iterate inc)
       (map (partial is-coin? "00000" input))
       (filter identity)
       (first)))

(defn p2 [input]
  (->> 1
       (iterate inc)
       (map (partial is-coin? "000000" input))
       (filter identity)
       (first)))
