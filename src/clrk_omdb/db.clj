(ns clrk-omdb.db
  (:require [datomic.api :as d]
            [clrk-omdb.omdb :as omdb])
  (:use [clrk-omdb.schemas]))

(def uri "datomic:dev://localhost:4334/clrk")

(d/create-database uri)
(def conn (d/connect uri))
(d/transact conn schemas)

(defn new-user 
  "Create a new user"
  [email name]
  (let [tx-data {:db/id (d/tempid :db.part/user)
                 :user/name name
                 :user/email email}]
    (d/transact conn [tx-data])))

(defn add-movie 
  "Add movie by imdbID"
  [imdbID title]
  (let [tx-data {:db/id (d/tempid :db.part/movies)
                :movie/title title
                :movie/imdb-id imdbID}]
    (d/transact conn [tx-data])))

(defn recommend-user 
  "Recommend a movie to a user"
  [email imdbID]
  (let [imdb ok
        tx-data {:db/id [:user/email email]
                 :user/recommended-movies imdbID}]
    (d/transact conn [tx-data])))
