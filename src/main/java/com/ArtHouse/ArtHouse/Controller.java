package com.ArtHouse.ArtHouse;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Controller {
    @GetMapping("/muranow")
    public Map<List<String>, List<String>> muranow() throws ParseException {
        ScraperMuranow scraperMuranow = new ScraperMuranow("div.cell-date-header",
                "div.movie-calendar-info__text-details-inner",
                "https://kinomuranow.pl/repertuar");
        try {
            scraperMuranow.fetchAndParse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(scraperMuranow.getMovies());
        return scraperMuranow.getMovies();
    }




}