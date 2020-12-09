(ns aoc.a2009-2
  (:require [aoc.a2009-1 :refer [parse]]
            [aoc.util :refer [sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn- blackjack [target v n acc]
  (cond
    (not v) nil
    (> n target) false
    (= n target) acc
    :else (recur target (rest v) (+ n (first v)) (conj acc (first v)))))

(defn- find-blackjack [target v]
  (let [result (blackjack target v 0 [])]
    (cond
      (not v) nil
      result (+ (reduce min result) (reduce max result))
      :else (recur target (rest v)))))

(defn- run [target input]
  (find-blackjack target (parse input)))

(defn- r [input] (run 127 (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "35
       20
       15
       25
       47
       40
       62
       55
       65
       95
       102
       117
       150
       182
       127
       219
       299
       277
       309
       576")
   62]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run 15353384) (println)))
