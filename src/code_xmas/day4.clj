(ns code-xmas.day4
  (:require [clojure.string :as str]))

(import 'java.security.MessageDigest)

;part1
(defn correct-number? [coll]
  (loop [current 0
         coll coll]
    (if (= 2 current)
      (let [first (first coll)]
        (if (and (> first 0) (< first 16))
          true
          false))
      (if (= 0 (first coll))
        (recur (inc current) (rest coll))
        false))))
;part2
(defn correct-number? [coll]
  (loop [current 0
         coll coll]
    (if (= 3 current)
      coll
      (if (= 0 (first coll))
        (recur (inc current) (rest coll))
        false))))
;;

(defn get-crypto [str crypto-kind]
  (let [message-digest (MessageDigest/getInstance crypto-kind)
        str->bytes (.getBytes str "UTF-8")
        digest (.digest message-digest str->bytes)]
    (correct-number? (seq digest))))

(defn calculate-number [key]
  (loop [number 0]
    (if (get-crypto (str key number) "MD5")
      (str key number)
      (recur (inc number)))))

(calculate-number "iwrupvqb")
