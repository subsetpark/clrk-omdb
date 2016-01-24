(ns clrk-clj.db
  (:require [datomic.api :as d]
            [clrk-clj.schemas.schemas :as m1]
            [clrk-clj.schemas.user-unique-migration :as m2]
            [io.rkn.conformity :as conformity]))

(def uri "datomic:dev://localhost:4334/clrk")
(defn init-db [] 
  (d/create-database uri))

(defn conn [] (d/connect uri))
(defn db [] (d/db (conn)))

(defn migrate [] 
  (conformity/ensure-conforms ((conn) m1/norm))
  (conformity/ensure-conforms ((conn) m2/norm))
  )

(defn new-user 
  "Create a new user"
  [email name]
  (let [tx-data {:db/id (d/tempid :db.part/user)
                 :user/name name
                 :user/email email}]
    (d/transact (conn) [tx-data])))

(defn add-movie 
  "Add movie by imdbID"
  [imdbID title]
  (let [tx-data {:db/id (d/tempid :db.part/movies)
                :movie/title title
                :movie/imdb-id imdbID}]
    (d/transact (conn) [tx-data])))

(defn recommend-user 
  "Recommend a movie to a user"
  [user movie]
  (let [tx-data {:db/id user
                 :user/recommended-movies movie}]
    (d/transact (conn) [tx-data])))
