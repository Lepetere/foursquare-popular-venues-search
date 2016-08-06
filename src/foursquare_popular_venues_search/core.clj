(ns foursquare-popular-venues-search.core
  (:require [clj-http.client :as client])
  (:use [cheshire.core :only [parse-string]])
  (:use clojure.pprint)
  (:gen-class))

(def client-id "0PQJ1WYAFXCPN2IPBLN4ABB2X2DIQVDLK2XCKCAS4555LMVK")
(def client-secret "3XN0Z24WDBDI0AMZ2MUQIXMPGJNH11JC4LSQRMTOMDV3CTIR")

(defn -main
  [& args]
  (let [response (client/get "https://api.foursquare.com/v2/venues/explore"
          {:query-params {
            "client_id" client-id,
            "client_secret" client-secret,
            "v" "20130815",
            "near" "Chicago, IL"
            }})]
      (pprint (parse-string (get response :body)))))
