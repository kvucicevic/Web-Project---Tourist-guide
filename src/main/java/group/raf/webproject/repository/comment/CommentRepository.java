package group.raf.webproject.repository.comment;

import group.raf.webproject.database.model.Article;
import group.raf.webproject.database.model.Comment;

import java.util.List;

public interface CommentRepository {

    Comment addCommentForArticle(Comment comment);
    List<Comment> allCommentsForArticle(Article article);
}
