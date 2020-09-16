(ns aoc.a1501)

(defn- parse [input] (map {\( 1 \) -1} input))

(defn p1 [input] (reduce (partial +) 0 (parse input)))

(defn p2 [input]
  (let [running-sum (fn [acc elt]
                      (conj acc (+ (peek acc) elt)))]
    (->> (parse input)
         (reduce running-sum [0])
         (take-while #(>= % 0))
         (count))))
