(ns aoc.a1510)

(defn looksee [string]
  (->> string
       (partition-by identity)
       (mapcat #(vector (char (+ (int \0) (count %))) (first %)))
       (apply str)))

(defn p1 [input]
  (count (reduce (fn [s _] (looksee s)) input (range 40))))

(defn p2 [input]
  (count (reduce (fn [s _] (looksee s)) input (range 50))))
