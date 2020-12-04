(ns aoc.a2004-2
  (:require [aoc.a2004-1 :refer [parse all-fields?]]
            [aoc.util :refer [sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn- between
  ([mn mx] #(if (<= mn % mx) % nil))
  ([mn mx x] (if (<= mn x mx) x nil)))

(defn- integer [s] (try (Integer. s) (catch Exception _ nil)))

(defn- coerce
  ; Specifically for regex result, ignores first element
  ; XXX: Does not check for failed coercions...
  ([a x] (a (nth x 1)))
  ([a b x] [(a (nth x 1)) (b (nth x 2))]))

(def validate-field
  {"byr" (fn [s] (some->> s
                          (re-find #"^(\d{4})$")
                          (coerce integer)
                          (between 1920 2002))),
   "iyr" (fn [s] (some->> s
                          (re-find #"^(\d{4})$")
                          (coerce integer)
                          (between 2010 2020))),
   "eyr" (fn [s] (some->> s
                          (re-find #"^(\d{4})$")
                          (coerce integer)
                          (between 2020 2030))),
   "hgt" (fn [s] (some->> s
                          (re-find #"^(\d+)(in|cm)$")
                          (coerce integer keyword)
                          (apply (fn [x unit]
                                   (({:in (between 59 76),
                                      :cm (between 150 193)} unit) x))))),
   "hcl" (fn [s] (re-find #"^#[0-9a-f]{6}$" s)),
   "ecl" #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"},
   "pid" (fn [s] (re-find #"^[0-9]{9}$" s)),
   ; hax: don't validate cid
   "cid" (fn [_] true)})

(defn valid? [record]
  (->> record
       (map (fn [[key val]] ((validate-field key) val)))
       (every? identity)))

(comment (->> (re-find #"^(\d{4})$" "2002") (coerce integer)))
(comment (valid? {"byr" "1980" "hgt" "190cmin"}))
(comment ((validate-field "byr") "2002"))
(comment ((validate-field "hgt") "190cm"))

(defn- run [input]
  (->> (parse input)
       (map #(and (all-fields? %) (valid? %)))
       (filter identity)
       (count)))

(is (valid?      {"byr" "2002"}))
(is (not (valid? {"byr" "2003"})))
(is (valid?      {"hgt" "60in"}))
(is (valid?      {"hgt" "190cm"}))
(is (not (valid? {"hgt" "190in"})))
(is (not (valid? {"hgt" "190"})))
(is (valid?      {"hcl" "#123abc"}))
(is (not (valid? {"hcl" "#123abz"})))
(is (not (valid? {"hcl" "123abc"})))
(is (valid?      {"ecl" "brn"}))
(is (not (valid? {"ecl" "wat"})))
(is (valid?      {"pid" "000000001"}))
(is (not (valid? {"pid" "0123456789"})))

; 203 too high
(defn- r [input] (run (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "eyr:1972 cid:100
       hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926

       iyr:2019
       hcl:#602927 eyr:1967 hgt:170cm
       ecl:grn pid:012533040 byr:1946

       hcl:dab227 iyr:2012
       ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277

       hgt:59cm ecl:zzz
       eyr:2038 hcl:74454a iyr:2023
       pid:3556412378 byr:2007")
   0,
   (r "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
       hcl:#623a2f

       eyr:2029 ecl:blu cid:129 byr:1989
       iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm

       hcl:#888785
       hgt:164cm byr:2001 iyr:2015 cid:88
       pid:545766238 ecl:hzl
       eyr:2022

       iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719")
   4]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
