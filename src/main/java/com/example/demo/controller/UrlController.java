package com.example.demo.controller;

import com.example.demo.entity.Domain;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Url;
import com.example.demo.service.UrlServiceImpl;
import com.example.demo.service.UrlService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.management.openmbean.CompositeData;
@RestController
@RequestMapping()
public class UrlController {

    @Autowired
    private UrlService urlService;
   @Autowired
    private UrlServiceImpl urlServiceimpl;
    RedirectView redirectView = new RedirectView();
    @GetMapping(path="/allurl")
    public @ResponseBody Iterable<Url> getAll() {
        return urlServiceimpl.getAllUrl();
    }
    @GetMapping(path="/alld")
    public @ResponseBody Iterable<Domain> getd() {
        return urlServiceimpl.getAllDomain();
    }
    @GetMapping("/getCount")
    public ResponseEntity<Object> getcount()
    {
        List<Map.Entry<String, Integer>>list =urlServiceimpl.getcount();
        if(list.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return
                ResponseEntity.of(Optional.of(list));

    }
    @GetMapping("/{key}")
    public RedirectView getUrl(@PathVariable("key") String key)
    {
        Url temp= urlServiceimpl.getUrl(key);
        String url= temp.getUrl();
        RedirectView redirectView=new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
    }
    @PostMapping("/shortUrl")
        public String shorturl(@RequestBody String url)
        {
            Url temp=this.urlServiceimpl.addUrl(url);
            String key="http://localhost:8080/"+temp.getShorturl();
            redirectView.setUrl(key);
            URL ur;
            try {
                ur = new URL(url);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            String domain=ur.getHost();
            Domain domainobj=new Domain();
            int id;
            boolean flag=true;
            List<Domain> domains = urlServiceimpl.getAllDomain();
            if(domains.size()>0) {
                for (Domain domain1 : domains) {
                    String d1 = domain1.getDomainName();
                    if (domain.equals(d1)) {
                        int n = domain1.getCount();
                        domain1.setCount(n + 1);
                        flag=false;
                        urlServiceimpl.addDomainDb(domain1);
                    }
                }
            }
            domainobj.setDomainName(domain);
            domainobj.setCount(1);

            urlService.addUrlDb(temp);
            if(flag)
            urlServiceimpl.addDomainDb(domainobj);
            return key;
        }
}