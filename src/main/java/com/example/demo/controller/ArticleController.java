package com.example.demo.controller;

import com.example.demo.article.Article;
import com.example.demo.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping(value = "/article")
    public void create(@RequestBody Article article){
        articleService.create(article);
    }

    @GetMapping(value = "/article")
    public ResponseEntity<ArrayList<Article>> selectAllArticls(){
        ArrayList<Article> articles = articleService.selectAllArticls();
        return ResponseEntity.ok(articles);
    }

    @GetMapping(value = "/article/{id}")
    public ResponseEntity<Article> selectAllArticls(@PathVariable int id){
        Article article = articleService.findUserById(id);
        return ResponseEntity.ok(article);
    }

    @PutMapping(value = "/article/{id}")
    public void update(@PathVariable int id,@RequestBody Article article){
        articleService.update(id,article);
    }

    @DeleteMapping(value = "/article/{id}")
    public void delete(@PathVariable int id){
        articleService.delete(id);
    }
}
