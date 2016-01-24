(defproject clrk-omdb "0.1.0"
  :description "Clojure OMDB Client"
  :url "http://github.com/subsetpark/omdb-clj"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.cache "0.6.4"]
                 [clj-http "2.0.0"]
                 [org.clojure/data.json "0.2.6"]
                 [ring/ring-core "1.4.0"]
                 [ring/ring-json "0.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [compojure "1.4.0"]
                 [com.datomic/datomic-pro "0.9.5344"]
                 ]
  :target-path "target/%s"
  :main clrk-omdb.app
  :aot [clrk-omdb.app]
  :profiles {:uberjar {:aot :all}}
  :plugins [[lein-ring "0.9.7"]]  
  :ring {:handler clrk-omdb.app/app}
  :repositories {"my.datomic.com" {:url "https://my.datomic.com/repo"
                                   :creds :gpg}})
