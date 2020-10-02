; New style, standalone main with inline unit tests

(ns aoc.a0001-2
  (:require [clojure.test :refer [is]]
            [aoc.a0001-1 :refer [parse]]
            [aoc.util :refer [sl]]))

(defn run [input]
  (apply * input))

(is (=
     (->> (sl "1
               2
               3
               4")
          (parse)
          (run))
     24))

(defn -main [& args]
  (println (run (parse (slurp *in*)))))
