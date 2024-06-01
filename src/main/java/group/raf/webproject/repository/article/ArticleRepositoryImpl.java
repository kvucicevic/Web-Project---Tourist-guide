package group.raf.webproject.repository.article;

import group.raf.webproject.database.model.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleRepositoryImpl implements ArticleRepository {

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
    public Article addArticle(Article article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;

        try {
            connection = connect();
            String sql = "INSERT INTO Article (id, Userid, title, date, text, visitNo, Destinationid) VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, article.getId());
            preparedStatement.setInt(2, article.getUser().getId());
            preparedStatement.setString(3, article.getTitle());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(article.getDate().toString()));
            preparedStatement.setString(5, article.getText());
            preparedStatement.setInt(6, article.getVisitNo());
            preparedStatement.setInt(7, article.getDestination().getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                article.setId(resultSet.getInt(1));
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

        return article;
    }

    @Override
    public List<Article> allArticles() {
        List<Article> articles = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connect();
            String sql = "SELECT * FROM Article";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getInt("id"));
                //article.getUser().setId(resultSet.getInt("Userid"));
                article.setTitle(resultSet.getString("title"));
                article.setDate(resultSet.getTimestamp("date"));
                article.setText(resultSet.getString("text"));
                article.setVisitNo(resultSet.getInt("visitNo"));
                //article.getDestination().setId(resultSet.getInt("Destinationid"));
                articles.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Optional.ofNullable(resultSet).ifPresent(r -> {
                    try { r.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
                Optional.ofNullable(statement).ifPresent(s -> {
                    try { s.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
                Optional.ofNullable(connection).ifPresent(c -> {
                    try { c.close(); } catch (SQLException e) { e.printStackTrace(); }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return articles;
    }

    @Override
    public Article findArticleById(Integer id) {
        Article article = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connect();
            String sql = "SELECT * FROM Article WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                article = new Article();
                article.setId(resultSet.getInt("id"));
                article.getUser().setId(resultSet.getInt("Userid"));
                article.setTitle(resultSet.getString("title"));
                article.setDate(resultSet.getTimestamp("date"));
                article.setText(resultSet.getString("text"));
                article.setVisitNo(resultSet.getInt("visitNo"));
                article.getDestination().setId(resultSet.getInt("Destinationid"));
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

        return article;
    }

    @Override
    public Article updateArticle(Article article) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connect();
            String sql = "UPDATE Article SET Userid = ?, title = ?, date = ?, text = ?, visitNo = ?, Destinationid = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, article.getUser().getId());
            preparedStatement.setString(2, article.getTitle());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(article.getDate().toString()));
            preparedStatement.setString(4, article.getText());
            preparedStatement.setInt(5, article.getVisitNo());
            preparedStatement.setInt(6, article.getDestination().getId());
            preparedStatement.setInt(7, article.getId());
            preparedStatement.executeUpdate();
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

        return article;
    }

    @Override
    public Article deleteArticleById(Integer id) {
        Article article = findArticleById(id);
        if (article != null) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = connect();
                String sql = "DELETE FROM Article WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
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
        }
        return article;
    }
}
