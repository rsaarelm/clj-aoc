(ns aoc.main
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn- load-input
  "Try to load preformatted edn input, otherwise load unparsed input."
  [id]
  (let
   [edn-path (str "resources/" id ".edn")
    source-path (str "resources/" id ".txt")]
    (try
      [:edn (edn/read-string (slurp edn-path))]
      (catch java.io.FileNotFoundException _
        (str/trim (slurp source-path))))))

(defn- process
  "Read and eval value or directly eval an already-edn value."
  [read eval val]
  (cond
    (= (first val) :edn) (eval (second val))
    :else (recur read eval [:edn (read val)])))

(defn run [id]
  ; XXX: Is there a nicer way to access dynamic namespace?
  (let
   [ns-sym (symbol (str "aoc.a" id))]
    (require ns-sym)
    (->> id
         (load-input)
         (process
          (ns-resolve (find-ns ns-sym) 'read)
          (ns-resolve (find-ns ns-sym) 'eval))
         (println))))

(defn -main [& args]
  (cond
    (= (first args) "run") (apply run (rest args))
    :else (println "Syntax error")))
