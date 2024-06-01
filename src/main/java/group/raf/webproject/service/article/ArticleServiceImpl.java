package group.raf.webproject.service.article;

import group.raf.webproject.database.model.Article;
import group.raf.webproject.dto.article.ArticleRequestDTO;
import group.raf.webproject.dto.article.ArticleResponseDTO;
import group.raf.webproject.mapper.ArticleMapper;
import group.raf.webproject.repository.article.ArticleRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    
    @Inject
    private ArticleRepository articleRepository;
    
    @Inject
    private ArticleMapper articleMapper;
    
    @Override
    public ArticleResponseDTO add(ArticleRequestDTO articleRequestDTO) {
        Article article = articleRepository.addArticle(articleMapper.articleRequestDTOToArticle(articleRequestDTO));
        return articleMapper.articleToArticleResponseDTO(article);
    }

    @Override
    public List<ArticleResponseDTO> findAll() {
        List<ArticleResponseDTO> articleResponseDTOS = new ArrayList<>();
        for(Article Article : articleRepository.allArticles()){
            articleResponseDTOS.add(articleMapper.articleToArticleResponseDTO(Article));
        }
        return articleResponseDTOS;
    }

    @Override
    public ArticleResponseDTO findById(Integer id) {
        return articleMapper.articleToArticleResponseDTO(articleRepository.findArticleById(id));
    }

    @Override
    public ArticleResponseDTO update(Integer id, ArticleRequestDTO articleRequestDTO) {
        Article article = articleRepository.updateArticle(articleMapper.articleRequestDTOToArticle(articleRequestDTO));
        return articleMapper.articleToArticleResponseDTO(article);
    }

    @Override
    public ArticleResponseDTO delete(Integer id) {
        return articleMapper.articleToArticleResponseDTO(articleRepository.deleteArticleById(id));
    }
}
