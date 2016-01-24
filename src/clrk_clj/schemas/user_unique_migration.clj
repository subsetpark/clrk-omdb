(ns clrk-clj.schemas.user-unique-migration)

(def norm
  {:user-email-unique
   {:txes [[{:db/id :user/email
             :db/unique :db.unique/value
             :db.alter/_attribute :db.part/db}]]}})
