(ns code-xmas.core
  (:require [clojure.string :as str]))

(def file (slurp "resources/brackets"))

(defn bracket-left? [char]
  (if (= char \()
    1
    -1))

(apply + (apply map bracket-left? (str/split file #"/s")))

;part 2

(defn position-basement [file]
  (loop [floor 0
         coll file
         pos 0]
    (if (< floor 0)
      pos
      (recur (+ floor (bracket-left? (first coll))) (rest coll) (inc pos)))))

(position-basement file)

