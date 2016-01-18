(ns clrk-omdb.cache
  (:require [clojure.core.cache :as cache]))

(def C (cache/fifo-cache-factory {} :threshold 128))

(defn cache-result 
  [key f]
  (if (cache/has? C key)
      (cache/hit C key)
      (cache/miss C key (f))))

