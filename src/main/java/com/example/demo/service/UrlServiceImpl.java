package com.example.demo.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.example.demo.entity.Domain;
import com.example.demo.repository.DomainRepository;
import com.example.demo.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Url;
import com.example.demo.repository.UserRepository;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private DomainRepository domainRepository;

    public Url addUser(Url user) {
        return urlRepository.save(user);
    }

    private List<Domain> mp= domainRepository.findAll();
    List<Url> li=urlRepository.findAll();

        public Url getUrl(String key)
        {

            Url url=null;
            url= li.stream().filter(i->i.getShorturl().equals(key)).findFirst().get();
            url.setCount(url.getCount()+1);
            return url;
        }


        public Url addurl(String url) {
            UUID uuid = UUID.randomUUID();

            for (Url url1 : li) {
                String temp=url1.getUrlString();
                if(temp.equals(url))
                {
                    return url1;
                }
            }

            URL ur;
            try {
                ur = new URL(url);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

//                if(domainRepository.findOne(ur.getHost()))
//                {
//              domainRepository.save(
//                }
//                else {
//                    mp.put(ur.getHost(),mp.get(ur.getHost())+1);
//                }

                Url url1 = new Url(uuid.toString().substring(0, 4), url, 1);
                return url1;

        }

//        public List<Map.Entry<String, Integer>> getcount()
//        {
//
//
//            List<Map.Entry<String, Integer>> list = new ArrayList<>(mp.entrySet());
//
//            //Using Entry's comparingByValue() method for sorting in ascending order
//            list.sort(Map.Entry.comparingByValue());
//            Collections.reverse(list);
//            if(list.size()<=3)
//                return list;
//            List<Map.Entry<String, Integer>> firstThreeElements = new ArrayList<>();
//            for (int i = 0; i < 3; i++) {
//                firstThreeElements.add(list.get(i));
//            }
//            return firstThreeElements;
//
//
}
