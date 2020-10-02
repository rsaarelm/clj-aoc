(ns aoc.a1501-2
  (:require [clojure.test :refer [is]]
            [aoc.a1501-1 :refer [parse]]
            [aoc.util :refer [sl]]))

(defn run [input]
  (let [running-sum (fn [acc elt]
                      (conj acc (+ (peek acc) elt)))]
    (->> (parse input)
         (reduce running-sum [0])
         (take-while #(>= % 0))
         (count))))

(run! #(is (= (run (first %)) (second %))) (partition 2
  [")" 1,
   "()())" 5]))

(defn -main [& args]
  (println (run (slurp *in*))))
