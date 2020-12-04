(ns aoc.a2004-1
  (:require [aoc.util :refer [re-read sl]]
            [clojure.set :as set]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn parse [input]
  (->> input
       (str/split-lines)
       (partition-by empty?)
       (filter #(not-empty (first %)))
       (map #(str/split (str/join " " %) #" "))
       (map #(map (fn [x] (str/split x #":")) %))
       (map (partial into {}))))

(defn all-fields? [data]
  (let [required #{"byr" "iyr" "eyr" "hgt" "hcl" "ecl" "pid"}]
    (= (set/intersection required (set (keys data))) required)))

(defn- run [input]
  (->> (parse input)
       (filter all-fields?)
       (count)))

(defn- r [input] (run (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
       byr:1937 iyr:2017 cid:147 hgt:183cm

       iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
       hcl:#cfa07d byr:1929

       hcl:#ae17e1 iyr:2013
       eyr:2024
       ecl:brn pid:760753108 byr:1931
       hgt:179cm

       hcl:#cfa07d eyr:2025 pid:166559648
       iyr:2011 ecl:brn hgt:59in")
   2]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
