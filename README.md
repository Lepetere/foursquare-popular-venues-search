# Foursquare Popular Venues Search

A simple web app that queries the [Foursquare API](https://developer.foursquare.com/) for recommended venues near a given location. This was done as an exercise to become familiar with the Clojure web stack and was implemented using [Ring](https://github.com/ring-clojure/ring) and [Hiccup](https://github.com/weavejester/hiccup).

The desired location is passed as a URL parameter, then the Foursquare API is queried internally, the result is parsed and a summary will be sent to the browser in the form of an HTML table.

## Requirements

To run you need to have at least java version `1.7.0` installed (check with `java -version` in the terminal). Also you need [Leiningen](http://leiningen.org/) to run the project (you can install this on Mac using [Homebrew](http://brew.sh/) and the formula `lein`).

## How to install
`git clone` this repository, then do `cd foursquare-popular-venues-search` and `lein run` in the console. This will fetch dependencies and start a web-server on port `8080`.

## How to use
To use just go to your browser of choice and enter `e.g. http://localhost:8080/?location=london`. Replace 'london' with your location of choice and this will output a list of recommended venues in or near that location. Enjoy!
