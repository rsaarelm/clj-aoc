(ns aoc.a2002-1
  (:require [aoc.util :refer [re-read sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn parse [input]
  (->> input
       str/split-lines
       (map (partial re-read #"(\d+)-(\d+) (.): (.+)"))))

(defn- validate [mn mx c pw] (<= mn (count (re-seq (re-pattern c) pw)) mx))

(defn- run [input]
  (->> (parse input)
       (filter (partial apply validate))
       count))

(defn- r [input] (run (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "1-3 a: abcde
       1-3 b: cdefg
       2-9 c: ccccccccc")
   2]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
