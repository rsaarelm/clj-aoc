(ns aoc.a2005-1
  (:require [aoc.util :refer [re-read sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

; ROW position is low bits, 0-7, 3 bits, R=1, L=0
; COLUMN position is 7 bits, 0-127, B=1, F=0

(defn parse-pass [s]
  (let [bits (reduce
              (fn [acc [a b]] (str/replace acc a b))
              s
              (partition 2 "L0R1F0B1"))]
    (read-string (str "2r" bits))))

(defn parse [input]
  (->> input
       (str/split-lines)
       (mapv parse-pass)))

(defn- run [input]
  (reduce max (parse input)))

(is (= (parse-pass "BFFFBBFRRR") 567))
(is (= (parse-pass "FFFBBBFRRR") 119))
(is (= (parse-pass "BBFFBBFRLL") 820))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
