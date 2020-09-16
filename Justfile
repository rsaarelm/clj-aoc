run +args:
    clj -m aoc.main run {{args}}

test +args:
    clj -m aoc.main test {{args}}

unittest:
    clj -A:test:runner

init day:
    #!/usr/bin/env bash
    if [ ! -f src/aoc/a{{day}}.clj ]; then
    ./init-day.sh {{day}}
    else
    echo "Day already set up."
    fi

lint:
    clj -Sdeps '{:deps {clj-kondo {:mvn/version "RELEASE"}}}' -m clj-kondo.main --lint src

fmt:
    clojure -Sdeps '{:deps {cljfmt {:mvn/version "0.6.4"}}}' -m cljfmt.main fix src/ test/ deps.edn
