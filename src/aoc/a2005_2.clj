(ns aoc.a2005-2
  (:require [aoc.a2005-1 :refer [parse]]
            [aoc.util :refer [sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn- run [input]
  (let
   [seats (sort (parse input))
    gap (map (fn [a b] (if (= b (+ a 2)) (inc a) nil)) seats (next seats))]
    (first (filter some? gap))))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
