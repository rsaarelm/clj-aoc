(ns aoc.a0000-1
  (:require [aoc.util :refer [re-read sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn parse [input]
  input)

(defn run [input]
  (parse input))

(run! #(is (= (run (first %)) (second %))) (partition 2
  ["A"
   "A"]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
