(ns aoc.a2006-2
  (:require [aoc.a2006-1 :refer [parse]]
            [aoc.util :refer [sl]]
            [clojure.set :as set]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn- run [input]
  (->> (parse input)
       (map (partial apply set/intersection))
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
   6]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
