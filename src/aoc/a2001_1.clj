(ns aoc.a2001-1
  (:require [aoc.util :refer [re-read sl]]
            [clojure.string :as str]
            [clojure.math.combinatorics :refer [combinations]]
            [clojure.test :refer [is]]))

(defn parse [input]
  (->> input
       str/split-lines
       (map re-read)))

(defn run [n input]
  (let [vals (parse input)
        tuples (combinations vals n)]
    (->> tuples
         (filter #(= (reduce + %) 2020))
         (first)
         (reduce *))))

(defn- r [input] (run 2 (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "1721
       979
       366
       299
       675
       1456")
   514579]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run 2) (println)))
