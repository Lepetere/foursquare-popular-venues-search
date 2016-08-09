(ns foursquare-popular-venues-search.core
  (:require [clj-http.client :as client])
  (:use [ring.middleware.json :only [wrap-json-response]])
  (:use [ring.middleware.params :only [wrap-params]])
  (:use [ring.util.response :only [response]])
  (:use [cheshire.core :only [parse-string]])
  (:use ring.adapter.jetty)
  (:gen-class))

(def client-id "0PQJ1WYAFXCPN2IPBLN4ABB2X2DIQVDLK2XCKCAS4555LMVK")
(def client-secret "3XN0Z24WDBDI0AMZ2MUQIXMPGJNH11JC4LSQRMTOMDV3CTIR")

(defn perform-foursquare-explore-request
  "Performs a request to the explore endpoint of the foursquare api.
  Returns recommended and popular venues near a given (geocodable) location."
  [location]
  (parse-string (:body (client/get "https://api.foursquare.com/v2/venues/explore"
          {:query-params {
            "client_id" client-id,
            "client_secret" client-secret,
            "v" "20130815",
            "near" location
            }}))))

(defn handler [request]
  (let [location (get (:query-params request) "location")]
    (response (perform-foursquare-explore-request location))))

(def app
  ;; apply middleware to correctly handle json and url params
  (wrap-params (wrap-json-response handler)))

(defn -main
  [& args]
  (run-jetty app {:port 8080}))
