(ns async-error.core
  (:require [clojure.core.async :refer [<! <!! go]]))

(defn throw-err [e]
  (when (instance? Throwable e) (throw e))
  e)

(defmacro <?
  "Like <! but throws errors."
  [ch]
  `(throw-err (<! ~ch)))

(defmacro <??
  "Like <!! but throws errors."
  [ch]
  `(throw-err (<!! ~ch)))

(defmacro go-try
  "Like go but catches the first thrown error and returns it."
  [& body]
  `(go
     (try
       ~@body
       (catch Throwable t# t#))))
