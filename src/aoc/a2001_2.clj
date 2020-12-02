(ns aoc.a2001-2
  (:require [aoc.a2001-1 :refer [run]]
            [aoc.util :refer [sl]]
            [clojure.string :as str]
            [clojure.test :refer [is]]))

(defn- r [input] (run 3 (sl input)))
(run!
 (fn [[a b]] (is (= a b)))
 (partition
  2
  [(r "1721
       979
       366
       299
       675
       1456")
   241861950]))

(defn -main [& args] (->> (slurp *in*) (str/trim) (run 3) (println)))
