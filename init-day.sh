#!/usr/bin/env bash

cat > src/aoc/a$1_1.clj << EOF
(ns aoc.a$1-1
  (:require [aoc.util :refer [re-read sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn parse [input]
  (->> input
       str/split-lines
       (map re-read)))

(defn- run [input]
  (parse input))

(defn- r [input] (run (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "put
       example
       input
       here")
   "put example answer here"]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
EOF

cat > src/aoc/a$1_2.clj << EOF
(ns aoc.a$1-2
  (:require [aoc.a$1-1 :refer [parse]]
            [aoc.util :refer [sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn- run [input]
  (parse input))

(defn- r [input] (run (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "put
       example
       input
       here")
   "put example answer here"]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run) (println)))
EOF

# Make git see them so they show up in FZF
git add -N src/aoc/a$1_1.clj
git add -N src/aoc/a$1_2.clj
