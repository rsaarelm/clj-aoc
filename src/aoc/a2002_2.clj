(ns aoc.a2002-2
  (:require [aoc.a2002-1 :refer [parse]]
            [aoc.util :refer [sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn- validate [p1 p2 c pw]
  (let [c (first c)
        a (if (= (get pw (dec p1)) c) 1 0)
        b (if (= (get pw (dec p2)) c) 1 0)]
    (= (+ a b) 1)))

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
   1]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
