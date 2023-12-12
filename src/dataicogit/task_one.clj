(ns dataicogit.task_one
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io])
  (:import (java.io PushbackReader)))


(defn read-invoice [file-path]
  (with-open [rdr (PushbackReader. (io/reader file-path))]
    (edn/read rdr)))


(defn valid-invoice-item? [item]
  (let [has-iva-19 (some #(and (= (:tax/category %) :iva) (= (:tax/rate %) 19))
                         (:taxable/taxes item))
        has-retention-1 (some #(and (= (:retention/category %) :ret_fuente) (= (:retention/rate %) 1))
                              (:retentionable/retentions item))]
    (and (or has-iva-19 has-retention-1) (not (and has-iva-19 has-retention-1)))))


(defn process-invoice [invoice]
  (->> (:invoice/items invoice)
       (filter valid-invoice-item?)))

(defn task-one [file-path]
  (let [invoice (read-invoice file-path)
        valid-items (process-invoice invoice)]
    (println "Valid invoice items:" [(:invoice/id invoice) (count valid-items)])))