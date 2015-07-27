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

(defn read-both [ch-a ch-b]
  (try-go
    (let [a (<? ch-a)
          b (<? ch-b)]
      [a b])))

(deftest read-both-test
  (testing "Returns error from ch-b."
    (let [e (ex-info "foo" {})
          ch-a (chan)
          ch-b (chan)]
      (put! ch-a e)
      (is (= e (<!! (read-both ch-a ch-b))))))
  (testing "Returns error from ch-b."
    (let [e (ex-info "foo" {})
          ch-a (chan)
          ch-b (chan)]
      (put! ch-a 1)
      (put! ch-b e)
      (is (= e (<!! (read-both ch-a ch-b))))))
  (testing "Doesn't read from ch-b if ch-a returns an error."
    (let [e (ex-info "foo" {})
          ch-a (chan)
          ch-b (chan)]
      (put! ch-a e)
      (put! ch-b 1)
      (read-both ch-a ch-b)
      (is (= 1 (<!! ch-b))))))
