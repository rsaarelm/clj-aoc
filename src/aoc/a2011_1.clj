(ns aoc.a2011-1
  (:require [aoc.util :refer [sl str->grid]]
            [aoc.vec :as v]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn parse [input]
  (->> input
       (str->grid)))

(defn- neighbors [chart pos]
  (->> (map (partial v/+ pos)
            [[-1 -1]
             [-1  0]
             [-1  1]
             [0 -1]
             [0  1]
             [1 -1]
             [1  0]
             [1  1]])
       (filter #(#{\#} (chart %)))
       (count)))

(defn- update-cell [s n]
  (cond
    (and (= s \L) (= n 0)) \#
    (and (= s \#) (>= n 4)) \L
    :else s))

(defn- find-fixed [neighbors update-cell chart]
  (loop [chart chart]
    (let [new-chart
          (->> chart
               (map (fn [[p s]] [p (update-cell s (neighbors chart p))]))
               (into {}))]
      (if (= new-chart chart) new-chart (recur new-chart)))))

(defn run [neighbors update-cell input]
  (->> (parse input)
       (find-fixed neighbors update-cell)
       (filter (fn [[_ c]] (#{\#} c)))
       (count)))

(defn- r [input] (run neighbors update-cell (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "L.LL.LL.LL
      LLLLLLL.LL
      L.L.L..L..
      LLLL.LL.LL
      L.LL.LL.LL
      L.LLLLL.LL
      ..L.L.....
      LLLLLLLLLL
      L.LLLLLL.L
      L.LLLLL.LL")
   37]))

(defn -main [& args] (->> (slurp *in*)
                          (str/trim) (run neighbors update-cell) (println)))
