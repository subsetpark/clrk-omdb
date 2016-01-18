(ns clrk-omdb.app
  (:require [clrk-omdb.core :as omdb]
            [compojure.core :refer :all]
            [ring.util.response :refer [response]]
            [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.defaults :refer :all]
            [ring.adapter.jetty :require [run-jetty]])
  (:gen-class))

(defroutes movie-routes
  (GET "/movies/:title" [title] (response (first (omdb/find-movie title)))))

(def app
  (-> movie-routes
      (wrap-json-response :pretty)
      (wrap-defaults api-defaults)))

(defn -main [& args]
  (ring.adapter.jetty/run-jetty app {:port 8080}))
