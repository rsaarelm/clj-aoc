(ns aoc.a2003-2
  (:require [aoc.a2003-1 :refer [parse]]
            [aoc.util :refer [sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn- run [input]
  (->>
   (for [slope
         [[1 1]
          [3 1]
          [5 1]
          [7 1]
          [1 2]]]
     (apply aoc.a2003-1/run (conj slope input)))
   (reduce *)))

(defn- r [input] (run (sl input)))
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
   336]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
