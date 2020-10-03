(ns aoc.a1515-1
  (:require [aoc.util :refer [re-read sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn parse [input]
  (->>
   input
   str/split-lines
   (map
    (partial
     re-read
     #"(.+): capacity (.+), durability (.+), flavor (.+), texture (.+), calories (.+)"))
   (map vec)
   (vec)))

(defn score [ingredients simplex]
  (let
   [weights (conj simplex (- 100 (reduce + simplex)))]
    (->>
     ; clip to relevant parameters and weight with ingredient amounts
     (map (fn [i w] (map (partial * w) (subvec i 1 5))) ingredients weights)
     (reduce (partial map +))  ; sum matrix columns
     (map (partial max 0))     ; snap negatives to zero
     (reduce *))))             ; calculate score

(defn hill-climb [neighbors score pt]
  (let [s (score pt)
        pt2 (->>
             (neighbors pt)
             (map #(vector % (score %)))      ; compute neighbor scores
             ; filter out worse scores, approach same so you can maybe glide
             ; out when stuck in zero space (or get stuck in a loop)
             (filter (fn [[p ps]] (<= s ps)))
             ; if we're coasting, make it random walk
             (shuffle)
             (sort-by (fn [[_ ps]] (- ps)))   ; grab biggest improvement
             (first) (first))]
    (if pt2
      (recur neighbors score pt2)
      pt)))

(defn- valid-simplex? [simplex]
  (and (<= (reduce + simplex) 100) (every? #(<= 0 %) simplex)))

(defn simplex-neighbors [simplex]
  (let [raw-neighbors
        (for [i (range (count simplex)) op [inc dec]]
          (update simplex i op))]
    (filter valid-simplex? raw-neighbors)))

; XXX: Randomly give suboptimal results
(defn- run [input]
  (let [ingredients (parse input)
        pt (vec (repeat (dec (count ingredients)) 0))
        optimum (hill-climb simplex-neighbors (partial score ingredients) pt)]
    (score ingredients optimum)))

(defn- r [input] (run (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(score
    (parse
     (sl "Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
          Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3"))
    [44])
   62842880,

   (simplex-neighbors [0 2 98])
   '([0 1 98] [0 2 97]),

   (r "Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8
      Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3")
   62842880]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
