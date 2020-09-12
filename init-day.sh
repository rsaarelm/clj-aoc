#!/usr/bin/env bash

cat > src/aoc/a$1.clj << EOF
(ns aoc.a$1
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
