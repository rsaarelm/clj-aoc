(ns aoc.a1501-1
  (:require [aoc.util :refer [re-read sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn parse [input]
  (->> input
       (map {\( 1 \) -1})))

(defn run [input] (reduce (partial +) 0 (parse input)))

(run! #(is (= (run (first %)) (second %))) (partition 2
  ["(())"
   0,
   "()()"
   0,
   "((("
   3,
   "(()(()("
   3,
   "))((((("
   3,
   "())"
   -1,
   "))("
   -1,
   ")))"
   -3,
   ")())())"
   -3]))

(defn -main [& args]
  (println (run (slurp *in*))))
