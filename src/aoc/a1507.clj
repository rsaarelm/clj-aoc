(ns aoc.a1507
  (:require [aoc.util :refer [re-read]])
  (:require [clojure.core.match :refer [match]])
  (:require [clojure.string :as str]))

(def graph-get
  "Memoizing function that uses externally provided function to get a key.

  Used to resolve the parsed connection diagram with get-f accessing the data
  structure. Graph does not change so results can be memoized."
  (memoize (fn [get-f k] (get-f k))))

(defn getter
  "Create getter functions for the parsed graph data structure.

  Takes the logical function f and arguments to it and wraps the arguments
  with graph-get."
  [f & args]
  (fn [get-f] (apply f (map (partial graph-get get-f) args))))

(defn parse [input]
  (->> input
       (str/split-lines)
       (map (partial re-read
                     [#"(.+) (.+) (.+) -> (.+)"
                      #"(.+) (.+) -> (.+)"
                      #"(.+) -> (.+)"]))
       (map (fn [x] (match [x]
                      [([a v] :seq)] [v (getter identity a)]
                      [(["NOT" a v] :seq)] [v (getter (partial bit-and-not 0xffff) a)]
                      [([a "AND" b v] :seq)] [v (getter bit-and a b)]
                      [([a "OR" b v] :seq)] [v (getter bit-or a b)]
                      [([a "LSHIFT" b v] :seq)] [v (getter bit-shift-left a b)]
                      [([a "RSHIFT" b v] :seq)] [v (getter bit-shift-right a b)])))
       (into (hash-map))))

(defn p1 [input]
  (let [expr (parse input)
        lookup (fn lookup [k]
                 (if (number? k) k ((expr k) lookup)))]
    ((expr "a") lookup)))

(def answer-to-p1 46065)

(defn p2 [input]
  (let [expr (parse input)
        lookup (fn lookup [k]
                 (cond
                   (number? k) k
                   (= k "b") answer-to-p1
                   :else ((expr k) lookup)))]
    ((expr "a") lookup)))
