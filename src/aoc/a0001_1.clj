; New style, standalone main with inline unit tests

(ns aoc.a0001-1
  (:require [aoc.util :refer [re-read sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

; Public parse fn, since solution 2 will want to reuse it
(defn parse [input]
  (->> input
       str/split-lines
       (map re-read)))

(defn run [input]
  (apply + input))

; Crummy inline unit tests get run every time the module is loaded, don't care
; in context of AoC speedcoding.
(is (=
     (->> (sl "1
               2
               3")
          (parse))
     [1 2 3]))

(is (=
     (->> (sl "1
               2
               3")
          (parse)
          (run))
     6))

(defn -main [& args]
  (println (run (parse (slurp *in*)))))
