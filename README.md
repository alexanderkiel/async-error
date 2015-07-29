# Async Error

[![Build Status](https://travis-ci.org/alexanderkiel/async-error.svg?branch=master)](https://travis-ci.org/alexanderkiel/async-error)

A Clojure(Script) library which provides [core.async][1] error handling 
utilities.

## Install

To install, just add the following to your project dependencies:

```clojure
[org.clojars.akiel/async-error "0.1"]
```

## Usage

### In Clojure

```clojure
(:require [async-error.core :refer [go-try <?]])
```

### In ClojureScript

```clojure
(:require [async-error.core :refer-macros [go-try <?]])
```

### In Clojure and ClojureScript

```clojure
(defn read-both [ch-a ch-b]
  (go-try
    (let [a (<? ch-a)
          b (<? ch-b)]
      [a b])))
```

This function returns a channel conveying either a vector of a and b or one of
the errors conveyed by ch-a or ch-b. It will never read from ch-b if ch-a 
returns an error.

## Related Work

* Blog Post [Asynchronous Error Handling][2] from David Nolen
* Blog Post [Working with core.async: Exceptions in go blocks][3] from Martin Trojer
* Issue [ASYNC-61][4]


## License

Copyright © 2015 Alexander Kiel

Distributed under the Eclipse Public License, the same as Clojure.

[1]: <https://github.com/clojure/core.async>
[2]: <http://swannodette.github.io/2013/08/31/asynchronous-error-handling/>
[3]: <http://martintrojer.github.io/clojure/2014/03/09/working-with-coreasync-exceptions-in-go-blocks/>
[4]: <http://dev.clojure.org/jira/browse/ASYNC-61>
