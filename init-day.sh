#!/usr/bin/env bash

cat > src/aoc/a$1.clj << EOF
(ns aoc.a$1
  ;(:require [aoc.util :refer [re-read]])
  ;(:require [clojure.string :as str])
  )

(defn- parse [input] input)

(defn p1 [input] (parse input))

(defn p2 [input] (parse input))
EOF

# Make git see them so they show up in FZF
git add -N src/aoc/a$1.clj
touch resources/$1-1.test resources/$1-2.test
git add -N resources/$1-1.test resources/$1-2.test
