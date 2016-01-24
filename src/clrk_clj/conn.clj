(ns clrk-clj.conn
  (:require [datomic.api :as d]))

(def uri "datomic:dev://localhost:4334/clrk")
(d/create-database uri)
(def conn (d/connect uri))
