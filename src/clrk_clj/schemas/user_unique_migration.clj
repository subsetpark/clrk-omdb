(ns clrk-clj.schemas.user-unique-migration
  [:require [io.rkn.conformity :as c]
   :use [clrk-clj.conn]])

(def user-unique-norm
  {:user-email-unique
   {:txes [[{:db/id :user/email
             :db/unique :db.unique/value
             :db.alter/_attribute :db.part/db}]]}})

(c/ensure-conforms conn user-unique-norm)
