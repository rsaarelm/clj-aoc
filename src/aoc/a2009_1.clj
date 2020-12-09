(ns aoc.a2009-1
  (:require [aoc.util :refer [re-read sl]]
            [clojure.math.combinatorics :refer [combinations]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn parse [input]
  (->> input
       (str/split-lines)
       (mapv re-read)))

(defn- windowed [n v i]
  (let [pos (+ n i)]
    (when (< pos (count v))
      [(subvec v i pos) (v pos)])))

(defn- valid? [[pre x]]
  (when x
    (let [sums
          (->> (combinations pre 2)
               (map (partial reduce +))
               (into #{}))]
      (sums x))))

(defn- run [n input]
  (let [v (parse input)]
    (->> (map (partial windowed n v) (range (count v)))
         (filter (fn [x] (not (valid? x))))
         (first)
         (second))))

(defn- r [input] (run 5 (sl input)))
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
   127]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run 25) (println)))
