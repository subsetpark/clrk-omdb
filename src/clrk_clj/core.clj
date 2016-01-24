(ns clrk-clj.core
  (:require [clrk-clj.omdb :as omdb]
            [clrk-clj.db :as cdb]
            [datomic.api :as d])
  (:gen-class :main true))

(defn q-id
  "Get a movie entity from the db by imdbID"
  [db imdbID]
  (d/q '[:find ?e . 
         :in $ ?i
         :where [?e :movie/imdb-id ?i]]
       db imdbID))

(defn get-user-id-by-email
  "Get a user entity from the db by email"
  [db email]
  (d/q '[:find ?e .
         :in $ ?email
         :where [?e :user/email ?email]]
       db email))

(defn get-user-by-email
  [db email]
  (d/pull db '[*] [:user/email email]))

(defn get-movie
  "Get a movie from the database, or OMDB if it's not yet available"
  [title]
  (let [db (cdb/db)
        omdb-info (first (omdb/find-movie title))
        imdbID (:imdbID omdb-info)
        movie-entity (q-id db imdbID)]
    (if (nil? movie-entity)
      ; import movie from omdb
      (let [tx-info (cdb/add-movie imdbID (:Title omdb-info))]
        (first (vals (:tempids @tx-info)))) 
      movie-entity)))

