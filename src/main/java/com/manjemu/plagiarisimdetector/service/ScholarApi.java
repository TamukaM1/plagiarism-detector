package com.manjemu.plagiarisimdetector.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ScholarApi {
    private final String apiUrl="";

    private List<String> search(String query){
        RestTemplate restTemplate = new RestTemplate();
        //Assuming the API is going to return a lsit of document contents
        List<String> documents = restTemplate.getForObject(apiUrl + "?q"+ query, List.class);
        return documents;
    }
}
