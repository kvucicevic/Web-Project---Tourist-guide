package group.raf.webproject.repository.comment;

import group.raf.webproject.database.model.Comment;

import java.util.List;

public interface CommentRepository {

    Comment addCommentForArticle(Integer id, Comment comment);
    List<Comment> allCommentsForArticle(Integer articleId);
}
