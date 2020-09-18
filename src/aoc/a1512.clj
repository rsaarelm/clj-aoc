(ns aoc.a1512
  (:require [aoc.util :refer [re-read]]))

(defn- parse [input] input)

(defn p1 [input] (->> input
                      (re-seq #"-?\d+")
                      (map re-read)
                      (reduce +)))

(defn p2 [input]
  (with-local-vars
   [tail (re-seq #"-?\d+|\{|\}|:\"red\"" input)]
    (let
      ; m: multiplier for result, set to 0 to wipe out red items
     [sum (fn sum [m acc seq]
            (case (first seq)
              nil (* m acc)
              "}" (do (var-set tail (rest seq)) (* m acc))
              "{" (let
                   [subsum (sum m 0 (rest seq))] ; Eval early, updates tail
                    (recur m (+ acc subsum) @tail))
              ":\"red\"" (recur 0 0 (rest seq))
              (recur m (+ acc (read-string (first seq))) (rest seq))))]
      (sum 1 0 @tail))))
