(ns ^:figwheel-always compo-scoring.core
  (:require [reagent.core :as r])
  (:require-macros
    ;; Notice that I am not including the 'devcards.core namespace
    ;; but only the macros. This helps ensure that devcards will only
    ;; be created when the :devcards is set to true in the build config.
    [devcards.core :as dc :refer [defcard deftest]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)

(defn entry-form []
  [:section.entry-form])

(defn compo-summary [competition]
  [:section.compo-summary
   [:img {:src (:banner-url competition)}]
   [:p (:description competition)]
   [:a.entry-btn {:on-click #()}]
   [:a.view-btn {:on-click #()}]])

(defn scoring-page [entry]
  [])

(defn entry-score [entry]
  (if (and (:scored? entry) (:score entry))
    [:span.entry-score (:score entry)]
    [:span.entry-score "?"]))

(defn entry [entry]
  ^{:key (:id entry)}
  [:li
   [:a (:name entry)]
   (entry-score entry)])

(defn entry-list [entries]
  [:ul (map entry entries)])

(defcard entry-list
  (fn [data-atom owner]
    (r/as-element (entry-list @data-atom)))
  [{:id      (random-uuid)
    :name    "A ten point entry"
    :score   10
    :scored? true}
   {:id      (random-uuid)
    :name    "A twenty point entry"
    :score   20
    :scored? true}
   {:id      (random-uuid)
    :name    "An unscored entry (15)"
    :score   15
    :scored? false}])