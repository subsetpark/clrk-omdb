(ns clrk-omdb.core
  (:require [clrk-omdb.omdb :as omdb]
            [clrk-omdb.db :as cdb]
            [datomic.api :as d])
  (:gen-class :main true))

(defn q-id
  "Get a movie entity from the db by imdbID"
  [db imdbID]
  (d/q '[:find ?e . 
         :in $ ?i
         :where [?e :movie/imdb-id ?i]]
       db imdbID))

(defn get-movie
  "Get a movie from the database, or OMDB if it's not yet available"
  [title]
  (let [db (d/db cdb/conn)
        omdb-info (first (omdb/find-movie title))
        imdbID (:imdbID omdb-info)
        movie-entity (q-id db imdbID)]
    (if (nil? movie-entity)
      ; import movie from omdb
      (let [tx-info (cdb/add-movie imdbID (:Title omdb-info))]
        (first (vals (:tempids @tx-info)))) 
      movie-entity)))

