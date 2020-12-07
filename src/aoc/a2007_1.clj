(ns aoc.a2007-1
  (:require [aoc.util :refer [re-read sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn parse [input]
  (let [color (fn [s] (re-find #"^[a-z]+ [a-z]+" s))
        contents (fn [s]
                   (->>
                    (re-seq #"(\d+) ([a-z]+ [a-z]+)" s)
                    (mapv (fn [[_ n c]] [c (read-string n)]))
                    (into {})))]
    (->> input
         (str/split-lines)
         (map (fn [line] [(color line) (contents line)]))
         (into {}))))

(defn- can-contain? [bags content-color color]
  (let [local-contents (keys (bags color))]
    (or (some #{content-color} local-contents) (some (partial can-contain? bags content-color) local-contents))))

(defn- run [input]
  (let [bags (parse input)]
    (->> (keys bags)
         (filter (partial can-contain? bags "shiny gold"))
         (count))))

(defn- r [input] (run (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "light red bags contain 1 bright white bag, 2 muted yellow bags.
       dark orange bags contain 3 bright white bags, 4 muted yellow bags.
       bright white bags contain 1 shiny gold bag.
       muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
       shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
       dark olive bags contain 3 faded blue bags, 4 dotted black bags.
       vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
       faded blue bags contain no other bags.
       dotted black bags contain no other bags.")
   4]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
