(ns async-error.doo-runner
  (:require
    [doo.runner :refer-macros [doo-tests]]
    [async-error.core-test]))

(doo-tests 'async-error.core-test)
