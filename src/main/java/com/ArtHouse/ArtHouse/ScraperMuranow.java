package com.ArtHouse.ArtHouse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;


public class ScraperMuranow {
    Elements movieElements;
    String dateSelector;
    String filmSelector;
    String url;

    public ScraperMuranow( String dateSelector, String filmSelector, String url) {
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

        }
    }

    public Map<List<String>, List<String>> getMovies() throws ParseException {
        String currentDate="";

        Map<List<String>,List<String>> mapOfFilms = new LinkedHashMap<>();
        for (Element element : movieElements) {
            if (element.is(dateSelector)) {
                Elements spans = element.select("span:nth-child(1), span:nth-child(2), span:nth-child(3)");
                if(spans.size() >= 3) {
                    currentDate = spans.get(0).text() + spans.get(1).text() + spans.get(2).text();
                }
                List<String> dates = Arrays.asList(currentDate, DateParser.parsePolishDate(currentDate));
                mapOfFilms.putIfAbsent(
                        dates,
                        new ArrayList<>()
                );

            }

            else if (element.is(filmSelector)) {
                String movieDetails = element.text().trim();
                List<String> dates = Arrays.asList(currentDate, DateParser.parsePolishDate(currentDate));
                if (!currentDate.isEmpty()) {

                    mapOfFilms.get(dates).add(movieDetails);
                }
            }

        }

        return mapOfFilms;
    }




}


