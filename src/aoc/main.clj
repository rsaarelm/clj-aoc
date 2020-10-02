; DEPRECATED
(ns aoc.main
  (:require [clojure.string :as str]
            [aoc.util :refer [text->chunks]]))

(defn- parse-id
  [id]
  (let [[day part] (str/split id #"-")]
    {:ns (symbol (str "aoc.a" day)),
     :f (symbol (str "p" part)),
     :input (str "resources/" day ".txt")
     :test (str "resources/" day "-" part ".test")}))

(defn exec
  [ns-sym func-sym input]
  (require ns-sym)
  ((ns-resolve (find-ns ns-sym) func-sym) input))

; (str/split "1234-1" #"-") -> ["1234" "1"]

(defn- run-test
  [id]
  (let
   [info (parse-id id)
    pairs (->> (slurp (:test info))
               (text->chunks)
               (partition 2))
    test (fn [[in out]]
           (let
            [result (->> in (exec (:ns info) (:f info)) (str))]
             (if (= result out)
               (print "\u001b[1;32m.\u001b[0m")
               (do
                 (println "\u001b[1;31mERROR\u001b[0m" in "->" result)
                 (System/exit 1)))))]
    (run! test pairs)))

(defn- run
  [id]
  (let
   [info (parse-id id)]
    (exec (:ns info) (:f info) (str/trim (slurp (:input info))))))

(defn -main [& args]
  (cond
    (= (first args) "run") (println (apply run (rest args)))
    (= (first args) "test") (do
                              (println "Running tests...")
                              (apply run-test (rest args)))
    :else (println "Syntax error")))
