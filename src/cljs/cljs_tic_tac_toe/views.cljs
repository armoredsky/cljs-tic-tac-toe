(ns cljs-tic-tac-toe.views
  (:require [re-frame.core :as re-frame]
            [cljs-tic-tac-toe.subs :as subs]
            ))

(defn cell [x y]
  (let [c (re-frame/subscribe [::subs/cell x y])]
    (fn []
      (case @c
        :x [:span "X"]
        :o [:span "O"]
          [:button {:on-click #()}
           "click"]))))

(defn grid []
  [:table
  [:tbody
    (for [y (range 3)]
    [:tr {:key (str "y-" y)}
      (for [x (range 3)]
        [:td {:key (str "x-" x)}
          "[cell x y]"])])]])

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])
        db (re-frame/subscribe [::subs/db])]
    [:div "Hello from " @name
      [:div [grid]
        [:div
         (pr-str @db)]]]))
