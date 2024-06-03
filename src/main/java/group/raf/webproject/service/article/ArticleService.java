package group.raf.webproject.service.article;

import group.raf.webproject.dto.article.ArticleRequestDTO;
import group.raf.webproject.dto.article.ArticleResponseDTO;

import java.util.List;

public interface ArticleService {

    ArticleResponseDTO add(ArticleRequestDTO articleRequestDTO);

    List<ArticleResponseDTO> findAll();

    List<ArticleResponseDTO> allArticlesByMostRead();

    List<ArticleResponseDTO> allArticlesFromDestination(ArticleRequestDTO articleRequestDTO);

    List<ArticleResponseDTO> allArticlesByActivityType(String activityType);

    ArticleResponseDTO findById(Integer id);

    ArticleResponseDTO update(Integer id, ArticleRequestDTO articleRequestDTO);

    ArticleResponseDTO delete(Integer id);
}
