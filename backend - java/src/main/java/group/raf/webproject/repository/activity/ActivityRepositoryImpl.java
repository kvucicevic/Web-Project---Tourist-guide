package group.raf.webproject.repository.activity;

import group.raf.webproject.database.model.Activity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityRepositoryImpl implements ActivityRepository {

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
    public Activity addActivityForDestination(Integer id, Activity activity) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;

        try {
            connection = connect();
            String sql = "INSERT INTO Activity (name, Destinationid) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //preparedStatement.setInt(1, activity.getId());
            preparedStatement.setString(1, activity.getName());
            preparedStatement.setInt(2, activity.getDestinationId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                activity.setId(resultSet.getInt(1));
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

        return activity;
    }

    @Override
    public List<Activity> allCommentsForDestination(Integer destinationId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Activity> activities = new ArrayList<>();

        try {
            connection = connect();
            String sql = "SELECT * FROM Activity WHERE Destinationid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, destinationId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Activity activity = new Activity();
                activity.setId(resultSet.getInt("id"));
                activity.setName(resultSet.getString("name"));
                activity.setDestinationId(resultSet.getInt("Destinationid"));
                activities.add(activity);
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

        return activities;
    }
}
