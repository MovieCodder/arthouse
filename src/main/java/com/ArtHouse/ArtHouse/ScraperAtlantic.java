package com.ArtHouse.ArtHouse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class ScraperAtlantic {
    Elements movieElements;
    String dateSelector;
    String filmSelector;
    String url;

    public ScraperAtlantic(String dateSelector, String filmSelector, String url) {
        this.dateSelector = dateSelector;
        this.filmSelector = filmSelector;
        this.url = url;
        movieElements = null;
    }

    public void fetchAndParse () throws IOException {
        Document website = Jsoup.connect(url).get();

        movieElements = website.select(dateSelector + "," + filmSelector);
    }

    static void printFilms(Map<String,List<String>> mapOfFilms) {
        for (Map.Entry<String, List<String>> entry : mapOfFilms.entrySet()) {
            System.out.println("Date: " + entry.getKey());
            for (String movie : entry.getValue()) {
                System.out.println("  - " + movie);
            }
            System.out.println();
        }
    }

    public Map<String, List<String>> getMovies() {
        String currentDate="";
        Map<String,List<String>> mapOfFilms = new LinkedHashMap<String,List<String>>();
        for (Element element : movieElements) {
            if (element.is(dateSelector)) {
                ;

                    currentDate = element.text();

                mapOfFilms.putIfAbsent(currentDate, new ArrayList<>());

            }

            else if (element.is(filmSelector)) {
                String movieDetails = element.text().trim();
                // Add movie details to the list for the current date
                if (!currentDate.isEmpty()) {
                    mapOfFilms.get(currentDate).add(movieDetails);
                }
            }

        }
        return mapOfFilms;
    }




}


