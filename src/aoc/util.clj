(ns aoc.util
  (:require [clojure.string :as str]))

(defn- my-split-lines
  "Makes sure lines ending with \\n\\n get an empty line at the end."
  [text]
  (let [lines (str/split-lines text)]
    (if (str/ends-with? text "\n\n") (conj lines "") lines)))

(defn unfortune
  "Convert text in unix fortune format into separated text chunks.

  The separator is a line with a single '%' character.

  The last newline is omitted to allow newline-less single line inputs. To add
  a newline, leave an empty line before the separating '%'.

  A '%' after the last item is optional. This will show up in the parser as
  the last element of the sequence being an empty string, which will then be
  ignored.

  BUGS
  - Consecutive %-lines do not emit an empty string between them.
  - Any number of trailing newlines will be collapsed to a single newline at
    end.
  "
  [text]
  (loop [chunks (partition-by #{"%"} (my-split-lines text))
         result []]
    (cond
      (or (not (seq chunks)) (= chunks [[""]])) result
      ; Ignore terminal %
      (and (= (count chunks) 1) (= (ffirst chunks) "%")) result
      (= (ffirst chunks) "%") (recur (rest chunks) (conj result ""))
      :else (recur
              (nnext chunks)
              (conj result (str/join "\n" (first chunks)))))))
