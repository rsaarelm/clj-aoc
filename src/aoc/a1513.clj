(ns aoc.a1513
  (:require [clojure.math.combinatorics :refer [permutations]])
  (:require [aoc.util :refer [re-read]])
  (:require [clojure.string :as str]))

(defn- parse [input]
  (->> input
       (str/split-lines)
       (map (partial re-read
                     #"(.+) would (.+) (.+) happiness units by sitting next to (.+)."))
       (map (fn [[a s n b]] (if (= s "lose") [[a b] (- n)] [[a b] n])))
       (into (hash-map))))

(defn score [m s]
  (let [looped (concat s [(first s)])]
    (->> (map #(+ (m [%1 %2]) (m [%2 %1])) looped (rest looped))
         (reduce +))))

(defn p1 [input]
  (let [happy-map (parse input)
        ; Use (into (set)) to make sure you only get one of each name.
        names (->> (keys happy-map)
                   (map first) (set) (into (vector)))]
    (->> (permutations names)
         (map (partial score happy-map))
         (reduce max))))

(defn p2 [input]
  (let [happy-map (parse input)
        ; Use (into (set)) to make sure you only get one of each name.
        names (->> (keys happy-map)
                   (map first) (set) (into (vector)) (#(conj % "player")))
        lookup (fn [[a b]] (if (or (= a "player") (= b "player")) 0
                               (happy-map [a b])))]
    (->> (permutations names)
         (map (partial score lookup))
         (reduce max))))
