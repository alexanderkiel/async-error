(ns async-error.core
  (:require [clojure.core.async :refer [<! <!! go]]))

(defn throw-err [e]
  (when (instance? Throwable e) (throw e))
  e)

(defmacro <?
  "Like <! but throws all errors."
  [ch]
  `(throw-err (<! ~ch)))

(defmacro <??
  "Like <!! but throws all errors."
  [ch]
  `(throw-err (<!! ~ch)))

(defmacro try-go
  "Like go but catches all errors and returns them."
  [& body]
  `(go
     (try
       ~@body
       (catch Throwable t# t#))))
