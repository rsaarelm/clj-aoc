(ns aoc.a2003-1
  (:require [aoc.util :refer [re-read sl str->grid]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn parse [input]
  (->> input
       str/split-lines
       (map re-read)))

(defn run [dx dy input]
  (let [grid (str->grid input)
        w (->> grid (map #(first (first %))) (reduce max) (inc))
        h (->> grid (map #(second (first %))) (reduce max) (inc))
        track (for [y (range 0 h dy)
                    :let [x (mod (* dx (/ y dy)) w)]]
                (grid [x y]))]
    (count (filter #{\#} track))))

(defn- r [input] (run 3 1 (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "..##.......
       #...#...#..
       .#....#..#.
       ..#.#...#.#
       .#...##..#.
       ..#.##.....
       .#.#.#....#
       .#........#
       #.##...#...
       #...##....#
       .#..#...#.#")
   7]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run 3 1) (println)))
