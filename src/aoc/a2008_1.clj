(ns aoc.a2008-1
  (:require [aoc.util :refer [re-read sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn parse [input]
  (->> input
       (str/split-lines)
       (mapv (partial re-read #"([^ ]+) (\+\d+|-\d+)"))))

; {mem pc seen acc}
(defn vm [mem pc seen acc]
  (let
   [[op n] (mem pc)]
    (cond
      (seen pc) acc
      (= op "nop") (vm mem (inc pc) (conj seen pc) acc)
      (= op "acc") (vm mem (inc pc) (conj seen pc) (+ acc n))
      (= op "jmp") (vm mem (+ pc n) (conj seen pc) acc))))

(defn- run [input]
  (vm (parse input) 0 #{} 0))

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
       jmp -4
       acc +6")
   5]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
