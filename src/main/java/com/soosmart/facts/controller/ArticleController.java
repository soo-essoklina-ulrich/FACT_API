package com.soosmart.facts.controller;


import com.soosmart.facts.dto.Article.ArticleDTO;
import com.soosmart.facts.dto.Article.SaveArticleDTO;
import com.soosmart.facts.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "article")
@AllArgsConstructor
public class ArticleController {
    private ArticleService articleService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArticleDTO> save(@RequestBody SaveArticleDTO articleDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(this.articleService.save_article(articleDTO));
    }

    @GetMapping
    public ResponseEntity<List<ArticleDTO>> getall() {
        return ResponseEntity.status(HttpStatus.OK).body(this.articleService.list_article());
    }

    @PutMapping("{id}")
    public ResponseEntity<ArticleDTO> update(@PathVariable UUID id, @RequestBody SaveArticleDTO articleDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(this.articleService.update_article(id,articleDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        this.articleService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}