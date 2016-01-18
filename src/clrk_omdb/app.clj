(ns clrk-omdb.app
  (:require [clrk-omdb.core :as omdb]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.util.response :refer [response]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.adapter.jetty :require [run-jetty]])
  (:gen-class))

(defroutes movie-routes
  (GET "/movies/titles/:title" [title] (response (first (omdb/find-movie title))))
  (GET "/movies/:id" [id] (response (omdb/get-movie id)))
  (route/not-found (response {:error "Not Found"})))

(def app
  (-> movie-routes
      (wrap-json-response :pretty)
      (wrap-defaults api-defaults)))

(defn -main [& args]
  (ring.adapter.jetty/run-jetty app {:port 8080}))
