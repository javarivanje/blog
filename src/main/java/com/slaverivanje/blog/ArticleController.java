package com.slaverivanje.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @RequestMapping(method = RequestMethod.GET, value = "/article/{id}")
    public Article findArticle(@PathVariable String id){
        return articleService.findArticle(id);
    }

    @RequestMapping (method = RequestMethod.PUT, value = "/article/{id}")
    public void saveArticle(@RequestBody Article article, @PathVariable String id){
        articleService.saveArticle(article);
    }

    @RequestMapping (method = RequestMethod.GET, value = "/article")
    public ArrayList<Article> findAll(){
        return articleService.findAll();
    }

}
