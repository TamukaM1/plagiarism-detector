package com.manjemu.plagiarisimdetector.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebScraper {
    public List<String> scrapeContent(String query) {
        List<String> documents = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.researchsite.example.com/search?q=" + query).get();
            Elements elements = doc.select(".document-content");
            elements.forEach(element -> documents.add(element.text()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return documents;
    }
}
