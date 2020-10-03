(ns aoc.a1514-2
  (:require [aoc.a1514-1 :refer [parse dist]]
            [aoc.util :refer [sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn- lead
  "Return set of names at lead at time t."
  [pack t]
  (let [maxdist (reduce max (map (partial dist t) pack))]
    (->> pack (filter #(= (dist t %) maxdist)) (map :name) (set))))

(defn- update-score
  "Update scores with a new set of leads."
  [score leads]
  (reduce #(update %1 %2 (fnil inc 0)) score leads))

(defn- scores
  "Return scores for reindeer pack after t seconds."
  [pack t]
  (->> (range 1 (inc t))
       (map (partial lead pack))
       (reduce update-score {})))

(defn run [input]
  (let [pack (parse input)]
    (->> (scores pack 2503)
         (vals)
         (reduce max))))

(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  (let [pack [{:name "comet",  :speed 14, :burst 10, :interval (+ 10 127)}
              {:name "dancer", :speed 16, :burst 11, :interval (+ 11 162)}]]
    [(lead pack 0) #{"comet" "dancer"},
     (lead pack 1) #{"dancer"},
     (lead pack 139) #{"dancer"},
     (lead pack 140) #{"comet"},
     (update-score {} ["xyzzy"]) {"xyzzy" 1},
     (update-score {"xyzzy" 1} ["xyzzy" "plugh"]) {"xyzzy" 2, "plugh" 1}
     (scores pack 1000) {"dancer" 689, "comet" 312}])))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
