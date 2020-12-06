(ns aoc.a2006-1
  (:require [aoc.util :refer [re-read sl]]
            [clojure.set :as set]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn parse [input]
  (->> input
       (str/split-lines)
       (partition-by empty?)
       (filter #(not-empty (first %)))
       (map (partial map set))))

(defn- run [input]
  (->> (parse input)
       (map (partial apply set/union))
       (map count)
       (reduce +)))

(defn- r [input] (run (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "abc

       a
       b
       c

       ab
       ac

       a
       a
       a
       a

       b")
   11]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
