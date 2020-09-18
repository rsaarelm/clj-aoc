(ns aoc.a1511
  (:require [clojure.math.numeric-tower :as math])
  (:require [clojure.test :refer [is]]))

(defn str->num
  "Parse lowercase-alpha-only string as base-26 decimal."
  [str]
  (->> str
       (reverse)
       (map #(- (int %) (int \a)))
       (map-indexed #(* %2 (math/expt 26 %1)))
       (reduce +)))

(defn a-pad
  "Trim password string to 8 characters, pad with a."
  [s]
  (cond
    (> (count s) 8) (subs str (- (count s) 8))
    (< (count s) 8) (recur (cons \a s))
    :else (apply str s)))

(defn num->str
  "Convert number to 8 character lowercase-alpha string in base-26."
  [num]
  (let
   [digits (fn [acc num] (if (= num 0) (reverse acc)
                             (recur (conj acc (mod num 26)) (quot num 26))))]
    (->> (digits [] num)
         (map #(char (+ (int \a) %)))
         (apply str)
         (a-pad))))

(defn inc-pw [pw] (->> pw (str->num) (inc) (num->str)))

(is (= (str->num "xyzzy") 10949846))
(is (= (str->num "aaxyzzy") 10949846))
(is (= (num->str 0) "aaaaaaaa"))
(is (= (num->str 26) "aaaaaaba"))
(is (= (inc-pw "aaaaaaxz") "aaaaaaya"))
(is (= (inc-pw "aaaaaaya") "aaaaaayb"))

(defn has-straight? [pw]
  (->> pw
       (map-indexed #(- (int %2) %1)) ; Flatten rising ramps
       (partition-by identity)        ; Turn flats into chunks
       (map count)
       (reduce max)
       (<= 3)))

(defn count-pairs [pw]
  (->> pw
       (partition-by identity)
       (filter #(> (count %) 1))
       (count)))

(defn valid-pw? [pw]
  ; XXX Assume 8-char lowercase alpha as given invariant instead of checking
  (cond
    (not (has-straight? pw)) false
    (< (count-pairs pw) 2) false
    (some #{\i \o \l} pw) false
    :else true))

(defn next-pw [pw]
  (let [pw2 (inc-pw pw)]
    (if (valid-pw? pw2) pw2 (recur pw2))))

(is (not (valid-pw? "hijklmmn")))
(is (not (valid-pw? "abbceffg")))
(is (not (valid-pw? "abbcegjk ")))
(is (valid-pw? "abcdffaa"))

(defn p1 [input] (next-pw input))

(defn p2 [input] (->> input (next-pw) (next-pw)))
