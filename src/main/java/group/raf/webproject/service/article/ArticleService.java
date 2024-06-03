package group.raf.webproject.service.article;

import group.raf.webproject.database.model.Article;
import group.raf.webproject.database.model.Comment;
import group.raf.webproject.dto.article.ArticleRequestDTO;
import group.raf.webproject.dto.article.ArticleResponseDTO;
import group.raf.webproject.dto.comment.CommentRequestDTO;
import group.raf.webproject.dto.comment.CommentResponseDTO;

import java.util.List;

public interface ArticleService {

    ArticleResponseDTO add(ArticleRequestDTO articleRequestDTO);

    List<ArticleResponseDTO> findAll();

    CommentResponseDTO addCommentForArticle(Integer id, CommentRequestDTO commentRequestDTO);

    List<CommentResponseDTO> allCommentsForArticle(Integer id);

    List<ArticleResponseDTO> allArticlesByMostRead();

    List<ArticleResponseDTO> allArticlesFromDestination(Integer destinationId);

    List<ArticleResponseDTO> allArticlesByActivityType(String activityType);

    ArticleResponseDTO findById(Integer id);

    ArticleResponseDTO update(Integer id, ArticleRequestDTO articleRequestDTO);

    ArticleResponseDTO delete(Integer id);
}
