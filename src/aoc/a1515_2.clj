(ns aoc.a1515-2
  (:require [aoc.a1515-1 :refer [parse score hill-climb simplex-neighbors]]
            [aoc.util :refer [sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn run [input]
  (parse input))

(defn- calorie-score [ingredients simplex]
  (let
   [weights (conj simplex (- 100 (reduce + simplex)))
    calories (->> (map last ingredients) (map * weights) (reduce +))
    calorie-delta (Math/abs (- 500 calories))
    dakka-numba 1000000]
    (- (score ingredients simplex) (* dakka-numba calorie-delta))))

; Fuck it just brute force it.
; Stepwise simplex, first item can range 0 to 100
; Subsequent items get range from expended value...
; subseq needs to be flattened...

; [nil nil nil] ...

; FIXME Doesn't work on input.
(defn- run [input]
  (let [ingredients (parse input)
        pt (vec (repeat (dec (count ingredients)) 0))
        optimum (hill-climb simplex-neighbors (partial calorie-score ingredients) pt)]
    (score ingredients optimum)))

(defn- r [input] (run (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
      Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3")
   57600000]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
