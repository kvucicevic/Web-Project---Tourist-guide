package group.raf.webproject.repository.article;

import group.raf.webproject.database.model.Article;

import java.util.List;

public interface ArticleRepository {

    Article addArticle(Article article);
    List<Article> allArticles();
    List<Article> allArticlesByMostRead();
    List<Article> allArticlesFromDestination(Integer destinationId);
    List<Article> allArticlesByActivityType(String activityType);
    Article findArticleById(Integer id);
    Article updateArticle(Article article);
    Article deleteArticleById(Integer id);
}
