package com.example.demo.repository;

import com.example.demo.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository extends JpaRepository<Domain,Integer> {
}
