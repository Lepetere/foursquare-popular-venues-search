(ns foursquare-popular-venues-search.core
  (:require [clj-http.client :as client])
  (:use [ring.middleware.params :only [wrap-params]])
  (:use [ring.util.response :only [response]])
  (:use [cheshire.core :only [parse-string]])
  (:use ring.adapter.jetty)
  (:use hiccup.core)
  (:gen-class))

(def client-id "0PQJ1WYAFXCPN2IPBLN4ABB2X2DIQVDLK2XCKCAS4555LMVK")
(def client-secret "3XN0Z24WDBDI0AMZ2MUQIXMPGJNH11JC4LSQRMTOMDV3CTIR")

(defn perform-foursquare-explore-request
  "Performs a request to the explore endpoint of the foursquare api.
  Returns recommended and popular venues near a given (geocodable) location."
  [location]
  (get (parse-string (:body (client/get "https://api.foursquare.com/v2/venues/explore"
          {:query-params {
            "client_id" client-id,
            "client_secret" client-secret,
            "v" "20130815",
            "near" location
            }}))) "response"))

(defn get-recommended-items
  "Extracts the recommended items from the foursquare api response"
  [data]
  (get (reduce #(if (= "recommended" (get %2 "name")) %2 %1) (get data "groups")) "items"))

(defn build-html-document
  "Builds an html document. Pass your content to be inserted into body."
  [content]
  (html [:html [:head] [:body content]]))

(defn build-html-table
  "Builds a commpact html table out of the data received from the foursquare
  explore endpoint."
  [data]
  (html
    [:h4 "Results for: " (get (get data "geocode") "displayString")]
    [:table
      [:thead [:tr [:th "Name"] [:th "URL"] [:th "Address"]]]
      [:tbody (map #(vec [:tr
        [:td (get (get % "venue") "name")]
        [:td (get (get % "venue") "url")]
        [:td (get (get (get % "venue") "location") "address")]])
          (get-recommended-items data))]]))

(defn handler [request]
  (let [location (get (:query-params request) "location")]
    (response
      (build-html-document (build-html-table
        (perform-foursquare-explore-request location))))))

(def app
  ;; apply middleware to correctly handle url params
  (wrap-params handler))

(defn -main
  [& args]
  (run-jetty app {:port 8080}))
