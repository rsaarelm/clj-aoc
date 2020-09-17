(ns aoc.a1509
  (:require [aoc.util :refer [re-read]])
  (:require [clojure.math.combinatorics :refer [permutations]])
  (:require [clojure.string :as str]))

(defn- parse [input]
  (let
   [update (fn [info [p1 p2 dist]]
             (-> info
                 (update-in [:sites] #(conj % p1 p2))
                 (assoc #{p1 p2} dist)))]
    (->> input
         (str/split-lines)
         (map (partial re-read #"(.+) to (.+) = (.+)"))
         (reduce update {:sites #{}}))))

(defn p1 [input]
  (let [info (parse input)
        routes (permutations (:sites info))
        length (fn [route]
                 (->> (map #(info #{%1 %2}) route (rest route))
                      (reduce +)))]
    (->> (map length routes)
         (reduce min))))

(defn p2 [input]
  (let [info (parse input)
        routes (permutations (:sites info))
        length (fn [route]
                 (->> (map #(info #{%1 %2}) route (rest route))
                      (reduce +)))]
    (->> (map length routes)
         (reduce max))))
