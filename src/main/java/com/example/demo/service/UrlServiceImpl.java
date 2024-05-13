package com.example.demo.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.demo.entity.Domain;
import com.example.demo.repository.DomainRepository;
import com.example.demo.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Url;

@Service
public class UrlServiceImpl implements UrlService,DomainService {
   @Autowired
    UrlRepository urlRepository;
   @Autowired
           DomainRepository domainRepository;

    List<Url> urls=new ArrayList<>();
    List<Domain> domains=new ArrayList<>();

    public Url addUrlDb(Url url) {
        return urlRepository.save(url);
    }
    public Domain addDomainDb(Domain domain) {
        return domainRepository.save(domain);
    }

    public List<Url> getAllUrl() {
        for (Url url : urlRepository.findAll()) {
            urls.add(url);
        }
        return urls;
    }
    public List<Domain> getAllDomain() {
        for (Domain domain : domainRepository.findAll()) {
            domains.add(domain);
        }
        return domains;
    }

    public Url addUrl(String urlstring) {
            UUID uuid = UUID.randomUUID();
        for (Url url : urlRepository.findAll()) {
            String temp=url.getUrl();
            if(temp.equals(urlstring))
                {
                    return url;
                }
        }
            Url url1 = new Url(uuid.toString().substring(0, 4), urlstring);
                return url1;
        }

}
