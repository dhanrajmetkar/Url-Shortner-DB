package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String domainName;
    Integer count;
}
