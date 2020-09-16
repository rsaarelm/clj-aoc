(ns aoc.a1507
  (:require [aoc.util :refer [re-read]])
  (:require [clojure.core.match :refer [match]])
  (:require [clojure.string :as str]))

(defn parse [input]
  (->> input
       (str/split-lines)
       (map (partial re-read
                     [#"(.+) (.+) (.+) -> (.+)"
                      #"(.+) (.+) -> (.+)"
                      #"(.+) -> (.+)"]))
       (map (fn [x] (match [x]
                      [([a v] :seq)] [v (fn [get-f] (get-f a))]
                      [(["NOT" a v] :seq)] [v (fn [get-f] (bit-and-not 0xffff (get-f a)))]
                      [([a "AND" b v] :seq)] [v (fn [get-f] (bit-and (get-f a) (get-f b)))]
                      [([a "OR" b v] :seq)] [v (fn [get-f] (bit-or (get-f a) (get-f b)))]
                      [([a "LSHIFT" b v] :seq)] [v (fn [get-f] (bit-shift-left (get-f a) (get-f b)))]
                      [([a "RSHIFT" b v] :seq)] [v (fn [get-f] (bit-shift-right (get-f a) (get-f b)))])))
       (into (hash-map))))

(defn p1 [input]
  (let [expr (parse input)
        ; Local-memoizing-recursive trick from
        ; https://stackoverflow.com/a/3908020
        lookup (with-local-vars
                [lut (memoize
                      (fn [k]
                        (if (number? k) k ((expr k) lut))))]
                 (.bindRoot lut @lut)
                 @lut)]
    ((expr "a") lookup)))

(def answer-to-p1 46065)

(defn p2 [input]
  (let [expr (parse input)
        lookup (with-local-vars
                [lut (memoize
                      (fn [k]
                        (cond
                          (number? k) k
                          (= k "b") answer-to-p1
                          :else ((expr k) lut))))]
                 (.bindRoot lut @lut)
                 @lut)]
    ((expr "a") lookup)))
