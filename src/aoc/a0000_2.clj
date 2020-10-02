(ns aoc.a0000-2
  (:require [aoc.a0000-1 :refer [parse]]
            [aoc.util :refer [sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn run [input]
  (str (parse input) ", World!"))

(run! #(is (= (run (first %)) (second %))) (partition 2
  ["Hello"
   "Hello, World!"]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
