(ns cljs-tic-tac-toe.views
  (:require [re-frame.core :as re-frame]
            [cljs-tic-tac-toe.subs :as subs]
            [cljs-tic-tac-toe.events :as events]
            ))

(defn cell [x y]
  (let [c (re-frame/subscribe [::subs/cell x y])
        w (re-frame/subscribe [::subs/winner])]
    (fn []
      (case @c
        :x [:span "X"]
        :o [:span "O"]
        (if @w
          [:span " "]
          [:button {:on-click #(re-frame/dispatch [::events/move x y])} " "]
          )))))

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

(defn title [turn winner]
  (let [w (case winner
                  :x "X"
                  :o "O"
                  nil)
        t (if (= :x turn) "X" "O")]
  (if (not (empty? w))
    (str "The Winner is " w)
    (str "it is " (if (= :x t) "X" "O") "'s turn"))))

(defn main-panel []
  (let [turn (re-frame/subscribe [::subs/turn])
        winner (re-frame/subscribe [::subs/winner])
        db (re-frame/subscribe [::subs/db])]
    [:div (title @turn @winner)
      [:div [grid]
      [:button {:on-click #(re-frame/dispatch [::events/initialize-db])}
       "RESET"]
        [:div
         (pr-str @db)]]]))
