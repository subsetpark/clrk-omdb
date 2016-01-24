(ns clrk-omdb.schemas)

(def schemas [
              {:db/id #db/id[:db.part/db]
               :db/ident :db.part/movies
               :db.install/_partition :db.part/db}

			  {:db/id #db/id[:db.part/db]
			   :db/ident :user/name
			   :db/valueType :db.type/string
			   :db/cardinality :db.cardinality/one
			   :db/fulltext true
               :db/index true
			   :db/doc "A user's name"
			   :db.install/_attribute :db.part/db}
              
              {:db/id #db/id[:db.part/db]
			   :db/ident :user/email
			   :db/valueType :db.type/string
			   :db/cardinality :db.cardinality/one
               :db/index true
			   :db/doc "A user's email"
			   :db.install/_attribute :db.part/db}

              {:db/id #db/id[:db.part/db]
			   :db/ident :movie/imdb-id
			   :db/valueType :db.type/string
			   :db/cardinality :db.cardinality/one
			   :db/unique :db.unique/identity
			   :db/doc "imdbID for a movie"
			   :db.install/_attribute :db.part/db}

              {:db/id #db/id[:db.part/db]
			   :db/ident :movie/title
			   :db/valueType :db.type/string
			   :db/cardinality :db.cardinality/one
			   :db/doc "Movie title"
			   :db.install/_attribute :db.part/db}

              {:db/id #db/id[:db.part/db]
			   :db/ident :user/favorite-movies
			   :db/valueType :db.type/ref
			   :db/cardinality :db.cardinality/many
			   :db/doc "A user's favorite movies"
			   :db.install/_attribute :db.part/db}
              
              {:db/id #db/id[:db.part/db]
			   :db/ident :user/recommended-movies
			   :db/valueType :db.type/ref
			   :db/cardinality :db.cardinality/many
			   :db/doc "Movie recommendations for a user"
			   :db.install/_attribute :db.part/db}
			  ])
