#!/usr/bin/env bash

cat > src/aoc/a$1.clj << EOF
(ns aoc.a$1
  (:require [clojure.string :as str])
  (:refer-clojure :exclude [read eval]))

(defn read [input] input)

(defn eval [expr] expr)
EOF

cat > src/aoc/a$1_2.clj << EOF
(ns aoc.a$1-2
  (:require [aoc.a$1 :refer [read]])
  (:refer-clojure :exclude [read eval]))

(defn eval [expr] expr)
EOF

# Make git see them so they show up in FZF
git add -N src/aoc/a$1.clj src/aoc/a$1_2.clj
touch resources/$1.test
git add -N resources/$1.test
