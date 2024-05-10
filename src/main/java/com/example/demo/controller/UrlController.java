package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Url;
import com.example.demo.service.UrlServiceImpl;
import com.example.demo.service.UrlService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping()
public class UrlController {

    @Autowired
    private UrlService urlService;
    @Autowired
    private UrlServiceImpl urlServiceimpl;


      @GetMapping("/{key}")
      public RedirectView getUrl(@PathVariable("key") String key)
      {
          Url temp= urlServiceimpl.getUrl(key);
          String url= temp.getUrlString();
          RedirectView redirectView=new RedirectView();
          redirectView.setUrl(url);
          return redirectView;
      }

        @PostMapping("/shortUrl")
        public String shorturl(@RequestBody String url)
        {
            RedirectView redirectView=new RedirectView();
            Url temp=this.urlServiceimpl.addurl(url);
            String key="http://localhost:8080/"+temp.getShorturl();
            redirectView.setUrl(key);
            urlService.addUser(temp);
            return key;
        }



//      @GetMapping("/getCount")
//      public ResponseEntity<Object> getcount()
//      {
//          List<Map.Entry<String, Integer>>list =urlService.getcount();
//          if(list.isEmpty())
//              return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//          return
//                  ResponseEntity.of(Optional.of(list));
//
//      }
    
}
