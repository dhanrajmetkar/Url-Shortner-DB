package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String shorturl;
    private String url;
    private Integer count;

    public Url(String shorturl, String url, int count) {
        this.shorturl=shorturl;
        this.url=url;
        this.count=count;
    }
    public String getUrlString() {
       return shorturl;
    }
}
