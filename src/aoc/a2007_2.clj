(ns aoc.a2007-2
  (:require [aoc.a2007-1 :refer [parse]]
            [aoc.util :refer [sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn- contents [bags color]
  (->> (bags color)
       (map (fn [[c n]] (+ n (* n (contents bags c)))))
       (reduce +)))

(defn- run [input]
  (contents (parse input) "shiny gold"))

(defn- r [input] (run (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "shiny gold bags contain 2 dark red bags.
       dark red bags contain 2 dark orange bags.
       dark orange bags contain 2 dark yellow bags.
       dark yellow bags contain 2 dark green bags.
       dark green bags contain 2 dark blue bags.
       dark blue bags contain 2 dark violet bags.
       dark violet bags contain no other bags.")
   126]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
