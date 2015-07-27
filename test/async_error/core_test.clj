(ns async-error.core-test
  (:require [clojure.test :refer :all]
            [async-error.core :refer :all]
            [clojure.core.async :refer [<!! chan put!]]))

(deftest <??-test
  (testing "Throws error from channel."
    (let [e (ex-info "foo" {})
          ch (chan)]
      (put! ch e)
      (is (thrown? Exception (<?? ch))))))

(deftest try-go-test
  (testing "Returns error from channel."
    (let [e (ex-info "foo" {})
          ch (chan)]
      (put! ch e)
      (is (= e (<!! (try-go (<? ch))))))))
