(defproject foursquare-popular-venues-search "0.1.0-SNAPSHOT"
  :description "Search popular and recommended venues near a known place"
  :url "https://github.com/Lepetere/foursquare-popular-venues-search"
  :license {:name "MIT License"
            :url "https://github.com/Lepetere/foursquare-popular-venues-search/blob/master/LICENSE"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "3.1.0"]
                 [cheshire "5.6.3"]]
  :main ^:skip-aot foursquare-popular-venues-search.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
