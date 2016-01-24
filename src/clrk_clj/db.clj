(ns clrk-clj.db
  (:require [datomic.api :as d])
  (:use [clrk-clj.conn]
        [clrk-clj.schemas.schemas]
        [clrk-clj.schemas.user-unique-migration]))

(defn db [] (d/db conn))

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
  [user movie]
  (let [tx-data {:db/id user
                 :user/recommended-movies movie}]
    (d/transact conn [tx-data])))
