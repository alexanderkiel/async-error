(defproject org.clojars.akiel/async-error "0.3"
  :description "Error Handling Utils for core.async."
  :url "https://github.com/alexanderkiel/async-error"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.0.0"
  :pedantic? :abort

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-doo "0.1.8"]]

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/core.async "0.4.474"]]

  :profiles {:dev
             {:dependencies
              [[org.clojure/clojurescript "1.10.339"]]}}

  :source-paths ["src"]
  :test-paths ["test/cljc"]

  :cljsbuild
  {:builds
   {:test
    {:source-paths ["src" "test/cljc" "test/cljs"]
     :compiler
     {:output-to "out/testable.js"
      :main async-error.doo-runner
      :optimizations :simple
      :process-shim false}}}}

  :clean-targets ["target" "out"]

  :aliases
  {"cljs-nashorn-tests" ["doo" "nashorn" "test" "once"]
   "cljs-phantom-tests" ["doo" "phantom" "test" "once"]
   "all-tests" ["do" "test," "cljs-nashorn-tests," "cljs-phantom-tests"]
   "lint" ["eastwood" "{:linters [:all] :exclude-linters [:keyword-typos]}"]})
