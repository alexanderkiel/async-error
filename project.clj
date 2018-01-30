(defproject org.clojars.akiel/async-error "0.2"
  :description "Error Handling Utils for core.async."
  :url "https://github.com/alexanderkiel/async-error"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.0.0"
  :pedantic? :abort

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/core.async "0.4.474"]]

  :profiles {:dev
             {:dependencies
              [[org.clojure/clojurescript "1.9.946"]]}})
