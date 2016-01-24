(ns clrk-clj.omdb
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]
            )
  (:gen-class :main true))

(def omdb-url "http://www.omdbapi.com/")

(defn omdb-get 
  "Given a dictionary of GET parameters, returns the results of a call to OMDB"
  [params]
  (:body (client/get omdb-url {:as :auto, :query-params params})))

(defn find-movie
  "Find a movie by title"
  [title]
  (:Search (omdb-get {:s title})))

(defn get-movie
  "Get a movie by search result or imdbID"
  [input]
  (let [imdbID (if (:imdbID input) (:imdbID input) input)] 
    (omdb-get {:i imdbID})))
