package group.raf.webproject.repository.destination;

import group.raf.webproject.database.model.Destination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DestinationRepositoryImpl implements DestinationRepository {

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
    public Destination addDestination(Destination destination) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;

        try {
            connection = connect();
            String sql = "INSERT INTO Destination (id, name, description) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, destination.getId());
            preparedStatement.setString(2, destination.getName());
            preparedStatement.setString(3, destination.getDescription());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                destination.setId(resultSet.getInt(1));
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

        return destination;
    }

    @Override
    public List<Destination> allDestinations() {
        List<Destination> destinations = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connect();
            String sql = "SELECT * FROM Destination";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Destination destination = new Destination();
                destination.setId(resultSet.getInt("id"));
                destination.setName(resultSet.getString("name"));
                destination.setDescription(resultSet.getString("description"));
                destinations.add(destination);
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

        return destinations;
    }

    public Destination getDestinationById(String id) {
        Destination destination = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connect();
            String sql = "SELECT * FROM Destination WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                destination = new Destination();
                destination.setId(resultSet.getInt("id"));
                destination.setName(resultSet.getString("name"));
                destination.setDescription(resultSet.getString("description"));
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

        return destination;
    }

    public Destination getDestinationByName(String name) {
        Destination destination = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connect();
            String sql = "SELECT * FROM Destination WHERE name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                destination = new Destination();
                destination.setId(resultSet.getInt("id"));
                destination.setName(resultSet.getString("name"));
                destination.setDescription(resultSet.getString("description"));
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

        return destination;
    }

    public Destination updateDestination(Destination destination) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connect();
            String sql = "UPDATE Destination SET name = ?, description = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, destination.getName());
            preparedStatement.setString(2, destination.getDescription());
            preparedStatement.setInt(3, destination.getId());
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

        return destination;
    }

    public Destination deleteDestination(String id) {
        Destination destination = getDestinationById(id);
        if (destination != null) {
            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = connect();
                String sql = "DELETE FROM Destination WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, id);
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
        return destination;
    }
}

