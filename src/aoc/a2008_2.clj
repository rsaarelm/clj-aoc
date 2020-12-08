(ns aoc.a2008-2
  (:require [aoc.a2008-1 :refer [parse]]
            [aoc.util :refer [sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn vm [mem pc seen acc]
  (if (>= pc (count mem))
    acc
    (let
     [[op n] (mem pc)]
      (cond
        (seen pc) nil
        (= op "nop") (vm mem (inc pc) (conj seen pc) acc)
        (= op "acc") (vm mem (inc pc) (conj seen pc) (+ acc n))
        (= op "jmp") (vm mem (+ pc n) (conj seen pc) acc)))))

(defn mutate [mem i]
  (let [[op n] (mem i)
        new-op (case op
                 "nop" "jmp"
                 "jmp" "nop"
                 op)]
    (assoc mem i [new-op n])))

(defn- run [input]
  (let [mem (parse input)
        prns (for [i (range (count mem))] (mutate mem i))]
    (->> prns
         (map #(vm % 0 #{} 0))
         (filter some?)
         (first))))

(defn- r [input] (run (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "nop +0
       acc +1
       jmp +4
       acc +3
       jmp -3
       acc -99
       acc +1
       nop -4
       acc +6")
   8]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
