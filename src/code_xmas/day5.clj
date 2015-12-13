(ns code-xmas.day5
  (:require [clojure.string :as str]))

(def vowel? (set "aeiou"))
(def evil-combinations ["ab" "cd" "pq" "xy"])
(def nornlist (str/split (slurp "resources/nice-or-naughty-list") #"\n"))

(defn nice? [coll]
  (loop [coll coll
         amount {:vowels 0 :double 0 :evil false}
         last-first false]
    (let [vowels (:vowels amount)
          double (:double amount)
          evil (:evil amount)]
      
        (if (empty? coll)
          (if (and (>= vowels 3) (>= double  1) (= evil false))
                    true
                    false)
          (recur (apply str (rest coll))
                 {:vowels (if (vowel? (first coll))
                            (inc vowels)
                            vowels)
                  :double (if (= last-first (first coll))
                            (inc double)
                            double)
                  :evil (if (true? evil)
                          true
                          (if (false? last-first)
                            false
                            (if (empty? (filter true? (map #(= (str last-first (first coll)) %) evil-combinations)))
                              false
                              true)))}
                 (first coll))))))



(count (filter true? (map nice? nornlist)))

;;Part2
(defn nice-fixed? [coll]
  (loop [coll coll
         amount {:two-twice 0 :letter-between 0}
         last-first false]
    (if (empty? coll)
      (and (>= (:two-twice amount) 1) (>= (:letter-between amount) 1))
      (let [first-coll (first coll)
            twice (:two-twice amount)
            between (:letter-between amount)]
        (recur (apply str (rest coll))
               {:two-twice (if (empty? (filter true? (map #(=  (str last-first first-coll) (str %1 %2)) (rest coll) (rest (rest coll)))))
                             twice
                             (inc twice))
                :letter-between (if (= last-first (second coll)) ;Bug med kortare ord
                                  (inc between)
                                  between)}
               (first coll))))))
;;RIGHT BUT NOT GOOD LOOKING D': too tired for this

(count (filter true? (map nice-fixed? nornlist)))




