(defproject org.clojars.akiel/async-error "0.2-SNAPSHOT"
  :description "Error Handling Utils for core.async."
  :url "https://github.com/alexanderkiel/async-error"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.0.0"
  :pedantic? :abort

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.494"]
                 [org.clojure/core.async "0.3.442"]
                 [org.clojure/tools.reader "1.0.0-beta4"]])
