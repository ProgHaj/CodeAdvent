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
  (map #(Integer. (re-find #"\d+" %)) (str/split tmp #"x")))

(defn everyhting [tmp]
  (let [sorted (sort (do-it tmp))]
    (calculate (into [] sorted))))

(apply + (map everyhting file))


;part 2

(defn calculate-ribbon [sorted-array]
  )

(defn functional-solution-wowowo [file]
  (loop [coll file
         result 0]
    (if (empty? coll)
      result
      (let [sorted-first (sort (str/split (first coll) #"x"))]
        (recur (rest coll) (+ result (calculate-ribbon sorted-first)))))))
