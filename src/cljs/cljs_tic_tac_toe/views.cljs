(ns cljs-tic-tac-toe.views
  (:require [re-frame.core :as re-frame]
            [cljs-tic-tac-toe.subs :as subs]
            [cljs-tic-tac-toe.events :as events]
            ))

(defn cell [x y]
  (let [c (re-frame/subscribe [::subs/cell x y])]
    (fn []
      (case @c
        :x [:span "X"]
        :o [:span "O"]
          [:button {:on-click #(re-frame/dispatch [::events/move x y])}
           "click"]))))

(defn grid []
  [:table
  [:tbody
    (for [y (range 3)]
    [:tr {:key (str "y-" y)}
      (for [x (range 3)]
        [:td {:key (str "x-" x) :style {:width 30
                                :height 30
                                :text-align :center}}
          [cell x y]])])]])

(defn main-panel []
  (let [turn (re-frame/subscribe [::subs/turn])
        db (re-frame/subscribe [::subs/db])]
    [:div (str "it is " (if (= :x @turn) "X" "O") "'s turn")
      [:div [grid]
        [:div
         (pr-str @db)]]]))
