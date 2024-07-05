package com.example.demo.controller;

import com.example.demo.entity.Domain;
import com.example.demo.entity.Url;
import com.example.demo.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping()
public class UrlController {

    @Autowired
    private UrlService urlService;

    RedirectView redirectView = new RedirectView();

    @GetMapping(path="/allurl")
    public @ResponseBody Iterable<Url> getAll() {
        return urlService.getAllUrl();
    }

    @GetMapping(path="/alld")
    public @ResponseBody Iterable<Domain> getd() {
        return urlService.getAllDomain();
    }

    @GetMapping("/getCount")
    public ResponseEntity<Object> getcount()
    {
        List<Map.Entry<String, Integer>> list = urlService.getcount();
        if(list.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return
                ResponseEntity.of(Optional.of(list));

    }
    @GetMapping("/{key}")
    public RedirectView getUrl(@PathVariable("key") String key) {
        Url temp = urlService.getUrl(key);
        String url = temp.getUrl();
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
    }
    @PostMapping("/shortUrl")
        public String shorturl(@RequestBody String url)
        {
            Url temp = this.urlService.addUrl(url);
            String key="http://localhost:8080/"+temp.getShorturl();
            redirectView.setUrl(key);
            urlService.addUrlDb(temp);
            return key;
        }
}