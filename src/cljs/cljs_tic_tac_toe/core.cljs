(ns cljs-tic-tac-toe.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [cljs-tic-tac-toe.events :as events]
            [cljs-tic-tac-toe.views :as views]
            [cljs-tic-tac-toe.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
