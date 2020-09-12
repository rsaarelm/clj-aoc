(ns aoc.util
  (:require [clojure.string :as str]))

(defn- my-split-lines
  "Makes sure lines ending with \\n\\n get an empty line at the end."
  [text]
  (let [lines (str/split-lines text)]
    (cond
      (str/ends-with? text "\n\n") (conj lines "")
      :else lines)))

(defn- pop-if
  [p s]
  (if (p (peek s)) (pop s) s))

(defn unfortune
  "Convert text in unix fortune format into separated text chunks.

  The separator is a line with a single '%' character.

  The last newline is omitted to allow newline-less single line inputs. To add
  a newline, leave an empty line before the separating '%'.

  A '%' after the last item is optional. This will show up in the parser as
  the last element of the sequence being an empty string, which will then be
  ignored.

  BUGS
  - Any number of trailing newlines will be collapsed to a single newline at
    end.
  "
  [text]
  (->> text
       (my-split-lines)
       (pop-if #{"%"})
       (reduce
         (fn [acc line]
           (if (= line "%")
             (conj acc [])
             (conj (pop acc) (conj (peek acc) line))))
         [[]])
       (map (partial str/join "\n"))))
