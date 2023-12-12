(ns dataicogit.task_three
  (:require [clojure.test :refer :all]
            [invoice-item :refer :all]))

(deftest test-subtotal-basic
  (testing "Subtotal without discount"
    (is (= (subtotal {:invoice-item/precise-quantity 10
                      :invoice-item/precise-price    15})
           150))))

(deftest test-subtotal-with-discount
  (testing "Subtotal with discount"
    (is (= (subtotal {:invoice-item/precise-quantity 10
                      :invoice-item/precise-price    20
                      :invoice-item/discount-rate    10})
           180))))

(deftest test-subtotal-100-percent-discount
  (testing "Subtotal with 100% discount"
    (is (= (subtotal {:invoice-item/precise-quantity 5
                      :invoice-item/precise-price    100
                      :invoice-item/discount-rate    100})
           0))))

(deftest test-subtotal-zero-percent-discount
  (testing "Subtotal with 0% discount"
    (is (= (subtotal {:invoice-item/precise-quantity 5
                      :invoice-item/precise-price    100
                      :invoice-item/discount-rate    0})
           500))))

(deftest test-subtotal-negative-values
  (testing "Subtotal with negative values"
    (is (= (subtotal {:invoice-item/precise-quantity -3
                      :invoice-item/precise-price    50
                      :invoice-item/discount-rate    10})
           -135))))

(deftest test-subtotal-missing-keys
  (testing "Subtotal with missing keys"
    (is (= (subtotal {:invoice-item/precise-quantity 2})
           0))
    (is (= (subtotal {:invoice-item/precise-price 20})
           0))
    (is (= (subtotal {})
           0))))



(defn task-three [args]
  (println "Task three")
  (test-subtotal-missing-keys)
  (test-subtotal-negative-values)
  (test-subtotal-zero-percent-discount)
  (test-subtotal-100-percent-discount)
  (test-subtotal-with-discount)
  (test-subtotal-basic)
  (println "Task three done")
  )

