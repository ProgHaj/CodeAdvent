(ns code-xmas.day3
  (:require [clojure.string :as str]))

(def file (slurp "resources/day3"))



(defn direction [tmp]
  (cond
   (= tmp \^) [0 1]
   (= tmp \v) [0 -1]
   (= tmp \<) [-1 0]
   (= tmp \>) [1 0]))


(defn amount-of-houses [coll]
  (let [position [0 0]
        result #{position}
        ]
    (loop [coll coll
           result result
           position position]
      (if (empty? (str (first coll)))
        result
        (let [new-position (map + position (direction (first coll)))]
          (recur (apply str (rest coll)) 
                 (conj result new-position)
                 new-position
                 ))))))



(count (amount-of-houses file))

;part 2
(count (into (amount-of-houses (take-nth 2 file)) (amount-of-houses (take-nth 2 (rest file)))))

