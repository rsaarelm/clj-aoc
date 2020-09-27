(ns aoc.a1514
  (:require [aoc.util :refer [re-read]])
  (:require [clojure.string :as str]))

; Reindeer a has cycle of c+d seconds
; Reindeer spends first c seconds flying at speed b

(defn- parse [input]
  (->> input
       (str/split-lines)
       (map (partial re-read
#"(.+) can fly (.+) km/s for (.+) seconds, but then must rest for (.+) seconds."))
       (map (fn [[a s n b]] (if (= s "lose") [[a b] (- n)] [[a b] n])))
       (into (hash-map))))

(defn p1 [input] (parse input))

(defn p2 [input] (parse input))
