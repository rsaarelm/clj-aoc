(ns aoc.util
  (:require [clojure.string :as str]))

(defn- unfortune
  "Convert text in unix fortune format into separated text chunks.

  The separator is a line with a single '%' character.

  The last newline is omitted to allow newline-less single line inputs. To add
  a newline, leave an empty line before the separating '%'.

  The last item can be followed by an optional terminal '%'.

  BUGS
  - Any number of trailing newlines will be collapsed to a single newline at
    end."
  [lines]
  (->> lines
       ; Ignore terminal '%'.
       (#(if (= (peek %) "%") (pop %) %))
       (reduce
        (fn [acc line]
          (if (= line "%")
            (conj acc [])
            (conj (pop acc) (conj (peek acc) line))))
        [[]])
       (map (partial str/join "\n"))))

(defn text->chunks
  "Split multiline chunks if using fortune format, otherwise split lines."
  [text]
  (let
   [lines- (str/split-lines text)
       ; Hack around str/split-lines eating trailing newlines.
    lines (if (str/ends-with? text "\n\n") (conj lines- "") lines-)
    fortune-file? (some #{"%"} lines)]
    (if fortune-file? (unfortune lines) lines)))

(defn re-read
  "Tokenize a line with a regex and convert numbery results to numbers.

  When called without a regex, converts a single token to a number if it's
  numbery, otherwise returns it as is."
  ([str]
   (let
    [expr (if (empty? str) str (read-string str))]
     (if (number? expr) expr str)))
  ([regexps input]
   (let [match (fn [str]
                 (if (seqable? regexps)
                   (some #(re-find % str) regexps)
                   (re-find regexps str)))]
     (->> input
          (match)
          (rest)
          (map re-read)))))
