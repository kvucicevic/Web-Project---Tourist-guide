package group.raf.webproject.repository.comment;

import group.raf.webproject.database.model.Article;
import group.raf.webproject.database.model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentRepositoryImpl implements CommentRepository {

    private static final String URL = "jdbc:mariadb://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection connect(){
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Comment addCommentForArticle(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;

        try {
            connection = connect();
            String sql = "INSERT INTO Comment (id, Articleid, author, content) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, comment.getId());
            preparedStatement.setInt(2, comment.getArticleId());
            preparedStatement.setString(3, comment.getAuthor());
            preparedStatement.setString(4, comment.getContent());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Optional.ofNullable(preparedStatement).ifPresent(p -> {
                    try { p.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
                Optional.ofNullable(connection).ifPresent(c -> {
                    try { c.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return comment;
    }

    @Override
    public List<Comment> allCommentsForArticle(Article article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Comment> comments = new ArrayList<>();

        try {
            connection = connect();
            String sql = "SELECT * FROM Comment WHERE Articleid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, article.getId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getInt("id"));
                comment.setArticleId(resultSet.getInt("Articleid"));
                comment.setAuthor(resultSet.getString("author"));
                comment.setContent(resultSet.getString("content"));
                comments.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Optional.ofNullable(resultSet).ifPresent(r -> {
                    try { r.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
                Optional.ofNullable(preparedStatement).ifPresent(p -> {
                    try { p.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
                Optional.ofNullable(connection).ifPresent(c -> {
                    try { c.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return comments;
    }
}
