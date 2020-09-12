(ns aoc.main
  (:require [cli-matic]))

(defn- run []
  (print "TODO"))

(def CLI
  {:command     "aoc"
   :description "Advent of Code runner"
   :subcommands [{:command  "run"
                  :description "Run code samples"
                  :runs run}]})

(defn -main []
  (print "MAIN!"))
