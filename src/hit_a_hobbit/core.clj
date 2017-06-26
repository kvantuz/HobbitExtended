(ns hit-a-hobbit.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(def asym-hobbit-body-parts
    [{:name "head" :size 3}
     {:name "left-eye" :size 1}
     {:name "left-ear" :size 1}
     {:name "mouth" :size 1}
     {:name "nose" :size 2}
     {:name "neck" :size 2}
     {:name "left-shoulder" :size 3}
     {:name "left-upper-arm" :size 3}
     {:name "chest" :size 10}
     {:name "back" :size 10}
     {:name "left-forearm" :size 3}
     {:name "abdomen" :size 6}
     {:name "left-kidney" :size 1}
     {:name "left-hand" :size 2}
     {:name "left-knee" :size 2}
     {:name "left-thigh" :size 4}
     {:name "left-lower-leg" :size 3}
     {:name "left-achilles" :size 1}
     {:name "left-foot" :size 2}
     ])


(defn matching-part [part]
  {:name (clojure.string/replace (:name part) #"^left-" "rigth-")
   :size (:size part)})

 (defn symmetrize-body-parts
   "Expects a seq of maps that have a :name and :size"
   [asym-body-parts]
   (reduce (fn [final-body-parts part]
             (into final-body-parts (set [part (matching-part part)])))
           []
           asym-body-parts))


(defn hit
  "Hit hobbit"
  [asym-body-parts]
  (let [sym-body-parts (symmetrize-body-parts asym-body-parts)
        body-sum (reduce + (map :size sym-body-parts))
        target (rand body-sum)]
    (loop [[part & remaining] sym-body-parts
            accumulated-sum (:size part)]
      (if (>= accumulated-sum target)
        part
        (recur remaining (+ accumulated-sum (:size (first remaining))))))))

(defn hit-string
  [asym-body-parts]
  (str "Hobbit was hitted to "
    (:name (hit asym-body-parts))))
