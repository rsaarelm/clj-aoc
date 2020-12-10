(ns aoc.a2010-2
  (:require [aoc.a2010-1 :refer [parse]]
            [aoc.util :refer [sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn- runs [s]
  "Get lengths of contiguous runs."
  (->> (map (fn [a b c] (if (= (inc a) b (dec c)) b nil)) s (next s) (nnext s))
       (filter some?)                        ; Filter to within-run values
       (map-indexed (fn [idx n] (- n idx)))  ; Turn runs to equal values
       (frequencies)
       (vals)))

(def combinations
  "Number of valid combinations in a run of length n."
  (memoize
   (fn [n]
     (let [valid-perm? (fn [k]
                         (-> (Integer/toString (+ (bit-shift-left 1 n) k) 2)
                             (.contains "000")
                             (not)))]
       (->> (range (bit-shift-left 1 n))
            (filter valid-perm?)
            (count))))))

(defn- run [input]
  (let [elts (sort (parse input))
        elts (concat [0] elts [(+ (last elts) 3)])]
    (->> (runs elts)
         (map combinations)
         (reduce *))))

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
   8
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
   19208]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
