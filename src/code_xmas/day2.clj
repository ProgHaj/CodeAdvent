(ns code-xmas.day2
  (:require [clojure.string :as str]))

(def file (str/split (slurp "resources/no-math.suchalie") #"\n"))

(defn area [a b]
  (* a b))

(defn side [a b]
  (* 2 (area a b)))

(defn calculate [coll]
  (+ (side (coll 0) (coll 1)) (side (coll 0) (coll 2)) (side (coll 1) (coll 2)) (area (coll 0) (coll 1))))

(defn do-it [tmp]
  (into [] (sort (map #(Integer. (re-find #"\d+" %)) (str/split tmp #"x")))))

(defn everyhting [tmp]
  (let [sorted (do-it tmp)]
    (calculate sorted)))

(apply + (map everyhting file))


;part 2

(defn calculate-ribbon [coll]
  (+ (* (coll 0) 2) (* (coll 1) 2) (* (coll 0) (coll 1) (coll 2))))

(defn functional-solution-wowowo [file]
  (loop [coll file
         result 0]
    (if (empty? coll)
      result
      (let [sorted-first (do-it(first coll))]
        (recur (rest coll) (+ result (calculate-ribbon sorted-first)))))))

(functional-solution-wowowo file)
