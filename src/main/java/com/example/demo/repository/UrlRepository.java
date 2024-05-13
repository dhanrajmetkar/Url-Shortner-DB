package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Url;
@Repository
public interface UrlRepository extends JpaRepository<Url,Integer> {
    public Url findByShorturl(String shorturl);
}
