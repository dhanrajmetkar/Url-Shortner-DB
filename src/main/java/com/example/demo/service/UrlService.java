package com.example.demo.service;

import com.example.demo.entity.Domain;
import com.example.demo.entity.Url;

import java.util.List;
import java.util.Map;

public interface UrlService {
    public Url addUrlDb(Url url);

    Iterable<Url> getAllUrl();

    Iterable<Domain> getAllDomain();

    List<Map.Entry<String, Integer>> getcount();

    Url getUrl(String key);

    Url addUrl(String url);
}
