(ns aoc.a1501-2
  (:refer-clojure :exclude [read eval]))

(defn read [input] (map {\( 1 \) -1} input))

(defn- running-sum [acc elt]
  (conj acc (+ (peek acc) elt)))

(defn eval [expr]
  (->> expr
       (reduce running-sum [0])
       (take-while #(>= % 0))
       (count)))
