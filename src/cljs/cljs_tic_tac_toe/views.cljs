(ns cljs-tic-tac-toe.views
  (:require [re-frame.core :as re-frame]
            [cljs-tic-tac-toe.subs :as subs]
            ))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div "Hello from " @name]))
