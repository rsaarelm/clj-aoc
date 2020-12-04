# Example
#     run 1525-1
# Expects files src/aoc/a1525_1.clj and resources/1525.txt
run task:
    @DAY={{task}}; clj -m aoc.a{{task}} < resources/${DAY:0:4}.txt

unittest:
    clj -A:test:runner

init day:
    #!/usr/bin/env bash
    if [ ! -f src/aoc/a{{day}}_1.clj ]; then
    ./init-day.sh {{day}}
    else
    echo "Day already set up."
    fi

lint:
    clj -Sdeps '{:deps {clj-kondo/clj-kondo {:mvn/version "RELEASE"}}}' -m clj-kondo.main --lint src

fmt:
    clojure -Sdeps '{:deps {cljfmt/cljfmt {:mvn/version "RELEASE"}}}' -m cljfmt.main fix src/ test/ deps.edn

# Run headless nREPL server, needed by fireplace.vim
nrepl:
    clj -R:nREPL -m nrepl.cmdline
