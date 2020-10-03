(ns aoc.a1514-1
  (:require [aoc.util :refer [re-read sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn parse [input]
  (->> input
       (str/split-lines)
       (map (partial re-read
                     #"(.+) can fly (.+) km/s for (.+) seconds, but then must rest for (.+) seconds."))
       (map (fn [[n s t1 t2]] {:name n, :speed s, :burst t1 :interval (+ t1 t2)}))))

(defn dist [t {:keys [speed burst interval]}]
  (let
   [complete-cycles (quot t interval)
    current-burst (min burst (mod t interval))
    flight-time (+ (* complete-cycles burst) current-burst)]
    (* speed flight-time)))

(defn run [input]
  (->> (parse input)
       (map (partial dist 2503))
       (reduce max)))

(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  (let
   [comet  {:speed 14, :burst 10, :interval (+ 10 127)}
    dancer {:speed 16, :burst 11, :interval (+ 11 162)}]
    [(dist 1 comet) 14,
     (dist 1 dancer) 16,
     (dist 10 comet) 140,
     (dist 10 dancer) 160,
     (dist 11 comet) 140,
     (dist 11 dancer) 176,
     (dist 12 comet) 140,
     (dist 12 dancer) 176,
     (dist 174 dancer) 192])))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
