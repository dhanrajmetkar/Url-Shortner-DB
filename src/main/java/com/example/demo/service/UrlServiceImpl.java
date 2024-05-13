package com.example.demo.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;
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
        URL ur;
        try {
            ur = new URL(urlstring);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        String domain=ur.getHost();
        Domain domainobj=new Domain();
        int id;
        boolean flag=true;
        List<Domain> domains = domainRepository.findAll();
        if(domains.size()>0) {
            for (Domain domain1 : domains) {
                String d1 = domain1.getDomainName();
                if (domain.equals(d1)) {
                    int n = domain1.getCount();
                    domain1.setCount(n + 1);
                    flag=false;
                    addDomainDb(domain1);
                }
            }
        }
        domainobj.setDomainName(domain);
        domainobj.setCount(1);
        if(flag)
         addDomainDb(domainobj);
        Url url1 = new Url(uuid.toString().substring(0, 4), urlstring);
            return url1;
        }
        public List<Map.Entry<String, Integer>> getcount()
        {
            Map<String,Integer> mp = new HashMap<>();
            for (Domain dom : domainRepository.findAll()) {
                mp.put(dom.getDomainName(), dom.getCount());
            }
            List<Map.Entry<String, Integer>> list = new ArrayList<>(mp.entrySet());
            //Using Entry's comparingByValue() method for sorting in ascending order
            list.sort(Map.Entry.comparingByValue());
            Collections.reverse(list);
            if(list.size()<=3)
                return list;
            List<Map.Entry<String, Integer>> firstThreeElements = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                firstThreeElements.add(list.get(i));
            }
            return firstThreeElements;
        }

    public Url getUrl(String shorturl) {
        return  urlRepository.findByShorturl(shorturl);
    }
}
