(ns async-error.core-test
  (:require
    [clojure.test :refer [deftest testing is]]
    [clojure.core.async :refer [go <! chan put! take!]]
    #?@(:clj  [[clojure.core.async :refer [<!!]]
               [async-error.core :refer [go-try <? <??]]]
        :cljs [[clojure.test :refer [async]]
               [async-error.core :refer-macros [go-try <?]]])))

#?(:clj
   (deftest <??-test
     (testing "Throws error from channel."
       (let [e (ex-info "foo" {})
             ch (chan)]
         (put! ch e)
         (is (thrown? Exception (<?? ch)))))))

(deftest go-try-test
  (testing "Returns error from channel."
    (let [e (ex-info "foo" {})
          ch (chan)]
      (put! ch e)
      #?(:clj  (is (= e (<!! (go-try (<? ch)))))
         :cljs (async done (go (is (= e (<! (go-try (<? ch))))) (done)))))))

(defn read-both [ch-a ch-b]
  (go-try
    (let [a (<? ch-a)
          b (<? ch-b)]
      [a b])))

(deftest read-both-test-err-a
  (testing "Returns error from ch-a."
    (let [e (ex-info "foo" {})
          ch-a (chan)
          ch-b (chan)]
      (put! ch-a e)
      #?(:clj  (is (= e (<!! (read-both ch-a ch-b))))
         :cljs (async done (go (is (= e (<! (read-both ch-a ch-b)))) (done)))))))

(deftest read-both-test-err-b
  (testing "Returns error from ch-b."
    (let [e (ex-info "foo" {})
          ch-a (chan)
          ch-b (chan)]
      (put! ch-a 1)
      (put! ch-b e)
      #?(:clj  (is (= e (<!! (read-both ch-a ch-b))))
         :cljs (async done (go (is (= e (<! (read-both ch-a ch-b)))) (done)))))))

(deftest read-both-test-skip-b
  (testing "Doesn't read from ch-b if ch-a returns an error."
    (let [e (ex-info "foo" {})
          ch-a (chan)
          ch-b (chan)]
      (put! ch-a e)
      (put! ch-b 1)
      (read-both ch-a ch-b)
      #?(:clj  (is (= 1 (<!! ch-b)))
         :cljs (async done (go (is (= 1 (<! ch-b))) (done)))))))
