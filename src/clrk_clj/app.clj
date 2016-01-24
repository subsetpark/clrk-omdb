(ns clrk-clj.app
  (:require [clrk-clj.omdb :as omdb]
            [clrk-clj.core :as core]
            [clrk-clj.db :as db]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.util.response :refer [response]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.adapter.jetty :require [run-jetty]])
  (:gen-class))

(defn get-and-respond
  "Look up a movie and then respond with the OMDB server's response"
  [title]
  (core/get-movie title)
  (let [search-result (first (omdb/find-movie title))]
    (omdb/get-movie search-result)))

(defroutes movie-routes
  (GET "/movies/titles/:title" [title] (response (get-and-respond title)))
  (GET "/movies/:id" [id] (response (omdb/get-movie id)))
  (route/not-found (response {:error "Not Found"})))

(def app
  (-> movie-routes
      (wrap-json-response :pretty)
      (wrap-defaults api-defaults)))

(defn -main [& args]
  (db/migrate)
  (ring.adapter.jetty/run-jetty app {:port 8080}))
