(ns aoc.a2011-2
  (:require [aoc.a2011-1 :refer [parse run]]
            [aoc.util :refer [sl]]
            [aoc.vec :as v]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn- raycast [chart pos dir n]
  (let [cell (chart (v/+ pos (v/* n dir)))]
    (case cell
      \. (recur chart pos dir (inc n))
      nil nil
      cell)))

(defn- neighbors [chart pos]
  (->> [[-1 -1]
        [-1  0]
        [-1  1]
        [0 -1]
        [0  1]
        [1 -1]
        [1  0]
        [1  1]]
       (filter #(#{\#} (raycast chart pos % 1)))
       (count)))

(defn- update-cell [s n]
  (cond
    (and (= s \L) (= n 0)) \#
    (and (= s \#) (>= n 5)) \L
    :else s))

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
   26]))

(defn -main [& args] (->> (slurp *in*)
                          (str/trim) (run neighbors update-cell) (println)))
