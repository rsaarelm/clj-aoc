#!/usr/bin/env bash

cat > src/aoc/a$1_1.clj << EOF
(ns aoc.a$1-1
  (:require [aoc.util :refer [re-read sl]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn parse [input]
  (->> input
       str/split-lines
       (map re-read)))

(defn run [input]
  (apply + input))

(is (=
     (->> (sl "1
               2
               3")
          (parse)
          (run))
     6))

(defn -main [& args]
  (println (run (parse (slurp *in*)))))
EOF

cat > src/aoc/a$1_2.clj << EOF
(ns aoc.a$1-2
  (:require [clojure.test :refer [is]]
            [aoc.a$1-1 :refer [parse]]
            [aoc.util :refer [sl]]))

(defn run [input]
  (apply * input))

(is (=
     (->> (sl "1
               2
               3
               4")
          (parse)
          (run))
     24))

(defn -main [& args]
  (println (run (parse (slurp *in*)))))
EOF

# Make git see them so they show up in FZF
git add -N src/aoc/a$1_1.clj
git add -N src/aoc/a$1_2.clj
