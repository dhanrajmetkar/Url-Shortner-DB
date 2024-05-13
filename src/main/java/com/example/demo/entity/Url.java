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

    public Url(String shorturl, String url) {
        this.shorturl=shorturl;
        this.url=url;

    }
    public String url() {
       return url;
    }

    public String getShorturl() {
        return shorturl;
    }
}
