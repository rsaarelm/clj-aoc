(ns aoc.main
  (:require [clojure.string :as str]
            [aoc.util :refer [unfortune]]
            [clojure.edn :as edn]))

(defn- load-input
  "Try to load preformatted edn input, otherwise load unparsed input."
  [id]
  (let
    ; Second stage uses same input so only take first 4 chars.
   [truncated-id (subs id 0 4)
    edn-path (str "resources/" truncated-id ".edn")
    source-path (str "resources/" truncated-id ".txt")]
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

(defn run
  ([id] (run id (load-input id)))
  ([id input]
  ; XXX: Is there a nicer way to access dynamic namespace?
   (let
    [ns-sym (symbol (str "aoc.a" id))]
     (require ns-sym)
     (->> input
          (process
           (ns-resolve (find-ns ns-sym) 'read)
           (ns-resolve (find-ns ns-sym) 'eval))))))

; TODO: Support tests with edn inputs. Filename pattern 0000.edn.test.
(defn- run-test
  [id]
  (let
   [pairs (->> (str "resources/" id ".test")
               (slurp)
               (unfortune)
               (partition 2))
    test (fn [[in out]]
           (let
            [result (->> in (run id) (str))]
             (if (= result out)
               (print "\u001b[1;32m.\u001b[0m")
               (println "\u001b[1;31mERROR\u001b[0m" in "->" result))))]
    (run! test pairs)))

(defn -main [& args]
  (cond
    (= (first args) "run") (println (apply run (rest args)))
    (= (first args) "test") (do
                              (println "Running tests...")
                              (apply run-test (rest args)))
    :else (println "Syntax error")))
