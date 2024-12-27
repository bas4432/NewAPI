package com.example.newapi.search.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface NewsSearchService {

    public void SearchAPICall(String keyword) throws JsonProcessingException;
}
