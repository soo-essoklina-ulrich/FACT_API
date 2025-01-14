package com.soosmart.facts.Implement;

import com.soosmart.facts.dto.Article.ArticleDTO;
import com.soosmart.facts.dto.Article.SaveArticleDTO;
import com.soosmart.facts.entity.Article;
import com.soosmart.facts.exceptions.EntityNotFound;
import com.soosmart.facts.mapper.ResponseMapper;
import com.soosmart.facts.repository.ArticleDAO;
import com.soosmart.facts.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ArticleImpl implements ArticleService {

    private final ArticleDAO articleDAO;
    private final ResponseMapper responseMapper;

    public ArticleImpl(ArticleDAO articleDAO, ResponseMapper responseMapper) {
        this.articleDAO = articleDAO;
        this.responseMapper = responseMapper;
    }

    @Override
    public ArticleDTO save_article(SaveArticleDTO articleDTO) {

        Article article = Article.builder().libelle(articleDTO.libelle()).prix_unitaire(articleDTO.prix_unitaire()).build();
        Article save = this.articleDAO.save(article);
        return this.responseMapper.responseArticleDTO(save);
    }

    @Override
    public List<ArticleDTO> list_article() {
        return this.articleDAO.findAllBySupprimerIsFalse().stream().map(this.responseMapper::responseArticleDTO).collect(Collectors.toList());
    }

    @Override
    public ArticleDTO update_article(UUID id_article, SaveArticleDTO articleDTO) {
        Optional<Article> articleOld = this.articleDAO.findById(id_article).stream().findFirst();
        if (articleOld.isPresent()) {
            Article articleupdate = articleOld.get();
            articleupdate.setLibelle(articleDTO.libelle());
            articleupdate.setPrix_unitaire(articleDTO.prix_unitaire());

            return this.responseMapper.responseArticleDTO(this.articleDAO.save(articleupdate));
        }
        throw new EntityNotFound("Article not found");
    }

    @Override
    public boolean delete(UUID id) {

        Optional<Article> article = this.articleDAO.findById(id).stream().findFirst();
        if (article.isPresent()) {
            article.get().setSupprimer(true);
            this.articleDAO.save(article.get());
            return true;
        }
        throw new EntityNotFound("Article not found");

    }
}
