(ns aoc.util-test
  (:require [clojure.test :refer :all]
            [aoc.util :refer :all]))

(deftest unfortune-test
  (is (= (unfortune "") []))
  (is (= (unfortune "a\nb") ["a\nb"]))
  (is (= (unfortune "a\nb\n") ["a\nb"]))
  (is (= (unfortune "a\n\n%\nb\n") ["a\n" "b"]))
  (is (= (unfortune "a\n%\nb\n\n") ["a" "b\n"]))
  (is (= (unfortune "a\n%\nb\n%\n") ["a" "b"]))
  (is (= (unfortune "a\n%\nb\n") ["a" "b"])))
