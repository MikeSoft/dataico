(ns dataicogit.task_two
  (:require
    [cheshire.core :as json]
    [clojure.java.io :as io]
    [clojure.spec.alpha :as s]
    [invoice-spec :as invoicespec]))

(defn read-invoice [file-path]
  (with-open [rdr (io/reader file-path)]
    (json/parse-stream rdr true)))


(defn calculate-total [invoice-data]
  (let [items (get-in invoice-data [:invoice :items])
        total-price (reduce + 0 (map #(:price %) items))]
    total-price))



(defn validate-invoice [invoice-data]
  (println "Invoice:" invoice-data)
  (if (s/conform ::invoicespec/invoice invoice-data)
    (do
      ;(println "Invoice conforms to spec")
      (calculate-total invoice-data))
    (let [explanation (s/explain-str ::invoicespec/invoice invoice-data)]
      (println explanation)
      nil)))


(defn task-two [file-path]
  (let [invoice-data (read-invoice file-path)]
    (if (validate-invoice invoice-data)
      (let [total (calculate-total invoice-data)]
        (println "Total invoice amount:" total)
        total)
      (println "Invalid invoice data"))))

