package com.slaverivanje.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article findArticle(String id){
        return articleRepository.findById(id).get();

    }

    public void saveArticle(Article article){
        articleRepository.save(article);
    }

    public ArrayList<Article> findAll(){
        return (ArrayList<Article>) articleRepository.findAll();
    }

}
