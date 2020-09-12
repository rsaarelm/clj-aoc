run:
    clj -m aoc.main

test:
    clj -A:test:runner

lint:
    clj -Sdeps '{:deps {clj-kondo {:mvn/version "RELEASE"}}}' -m clj-kondo.main --lint src
