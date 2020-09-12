(ns aoc.util
  (:require [clojure.string :as str]))

(defn unfortune
  "Convert text in unix fortune format into separated text chunks.

  The separator is a line with a single '%' character.

  The last newline is omitted to allow newline-less single line inputs. To add
  a newline, leave an empty line before the separating '%'.

  The last item can be followed by an optional terminal '%'.

  BUGS
  - Any number of trailing newlines will be collapsed to a single newline at
    end."
  [text]
  (->> text
       (str/split-lines)
       ; Hack around str/split-lines eating trailing newlines.
       (#(if (str/ends-with? text "\n\n") (conj % "") %))
       ; Ignore terminal '%'.
       (#(if (= (peek %) "%") (pop %) %))
       (reduce
         (fn [acc line]
           (if (= line "%")
             (conj acc [])
             (conj (pop acc) (conj (peek acc) line))))
         [[]])
       (map (partial str/join "\n"))))
