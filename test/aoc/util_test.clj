(ns aoc.util-test
  (:require [clojure.test :refer :all]
            [aoc.util :refer :all]))

(deftest chunks-test
  (is (= (text->chunks "") [""]))
  (is (= (text->chunks "%\n%\n%\n") ["" "" ""]))
  (is (= (text->chunks "a\nb") ["a" "b"]))
  (is (= (text->chunks "a\nb\n") ["a" "b"]))
  (is (= (text->chunks "a\nb\n%") ["a\nb"]))
  (is (= (text->chunks "a\n\n%\nb\n") ["a\n" "b"]))
  (is (= (text->chunks "a\n%\nb\n\n") ["a" "b\n"]))
  (is (= (text->chunks "a\n%\nb\n%\n") ["a" "b"]))
  (is (= (text->chunks "a\n%\nb\n") ["a" "b"])))

(deftest re-read-test
  (is (= (re-read #"(.+);(.+)" "xyzzy;123") ["xyzzy" 123])))
