run +args:
    clj -m aoc.main run {{args}}

test:
    clj -A:test:runner

lint:
    clj -Sdeps '{:deps {clj-kondo {:mvn/version "RELEASE"}}}' -m clj-kondo.main --lint src

fmt:
    clojure -Sdeps '{:deps {cljfmt {:mvn/version "0.6.4"}}}' -m cljfmt.main fix src/ test/ deps.edn
