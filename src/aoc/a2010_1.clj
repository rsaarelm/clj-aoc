(ns aoc.a2010-1
  (:require [aoc.util :refer [re-read sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn parse [input]
  (->> input
       (str/split-lines)
       (mapv re-read)))

(defn- run [input]
  (let [elts (sort (parse input))
        elts (concat [0] elts (vector (+ (last elts) 3)))
        diffs (mapv - (rest elts) elts)]
    (* (count (filter #{3} diffs)) (count (filter #{1} diffs)))))

(defn- r [input] (run (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "16
       10
       15
       5
       1
       11
       7
       19
       6
       12
       4")
   35
   (r "28
       33
       18
       42
       31
       14
       46
       20
       48
       47
       24
       23
       49
       45
       19
       38
       39
       11
       1
       32
       25
       35
       8
       17
       7
       9
       4
       2
       34
       10
       3")
   220]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
