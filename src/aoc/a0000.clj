(ns aoc.a0000)

(defn- parse [input] input)

(defn p1 [input]
  (let [expr (parse input)]
    expr))

(defn p2 [input]
  (let [expr (parse input)]
    (str expr ", World!")))
