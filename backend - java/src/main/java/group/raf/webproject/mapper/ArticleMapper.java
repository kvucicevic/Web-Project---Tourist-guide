package group.raf.webproject.mapper;

import group.raf.webproject.database.model.Article;
import group.raf.webproject.dto.article.ArticleRequestDTO;
import group.raf.webproject.dto.article.ArticleResponseDTO;

public class ArticleMapper {

    public Article articleRequestDTOToArticle(ArticleRequestDTO articleRequestDTO) {
        Article article = new Article();
//        article.setUserId(articleRequestDTO.getUserId());
        article.setTitle(articleRequestDTO.getTitle());
        article.setDate(articleRequestDTO.getDate());
        article.setText(articleRequestDTO.getText());
        article.setVisitNo(articleRequestDTO.getVisitNo());
        article.setDestinationId(articleRequestDTO.getDestinationId());
        return article;
    }

    public ArticleResponseDTO articleToArticleResponseDTO(Article article) {
        ArticleResponseDTO articleResponseDTO = new ArticleResponseDTO();
        articleResponseDTO.setId(article.getId());
        articleResponseDTO.setDate(article.getDate());
        articleResponseDTO.setText(article.getText());
        articleResponseDTO.setTitle(article.getTitle());
        articleResponseDTO.setVisitNo(article.getVisitNo());
        articleResponseDTO.setDestinationId(article.getDestinationId());
        return articleResponseDTO;
    }

}
