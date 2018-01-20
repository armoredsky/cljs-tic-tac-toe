(ns cljs-tic-tac-toe.events
  (:require [re-frame.core :as re-frame]
            [cljs-tic-tac-toe.db :as db]))

(re-frame/reg-event-db
 ::initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
  ::cell
  (fn  [_ x y]
    (println (str x ":" y))
    db/default-db))

(defn rotate [turn]
  (if (= turn :x)
    :o
    :x))

(re-frame/register-handler
  ::move
  (fn [db [_ x y]]
    (let [turn (:turn db)
          board (:board db)
          new-board (assoc board [x y] turn)]
      (-> db
        (assoc :turn (rotate turn))
        (assoc :board new-board)))))
