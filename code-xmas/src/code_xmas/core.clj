(ns code-xmas.core
  (:require [clojure.string :as str]))

(def file (str/split (slurp "resources/brackets") #"\s"))

(defn bracket-left? [char]
  (if (= char \()
    1
    -1))

(apply + (apply map bracket-left? file))

;part 2
