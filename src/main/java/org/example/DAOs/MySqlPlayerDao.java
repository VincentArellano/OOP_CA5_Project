package org.example.DAOs;

import com.google.gson.GsonBuilder;
import org.example.DTOs.Player;
import org.example.Exceptions.DaoException;
import org.example.IdGenerator;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlPlayerDao  extends MySqlDao implements PlayerDaoInterface {
    private IdGenerator idGenerator = IdGenerator.getInstance("next-id-store.txt");

    @Override
    public List<Player> findAllPlayers() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Player> playersList = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM PLAYER";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int playerId = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                int age = resultSet.getInt("AGE");
                double height = resultSet.getDouble("HEIGHT");
                Player p = new Player(playerId, name, age, height);
                playersList.add(p);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllPlayers() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllPlayers() " + e.getMessage());
            }
        }
        return playersList;     // may be empty
    }

    @Override
    public Player findPlayerByID(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Player player = null;
        try {
            connection = this.getConnection();

            String query = "SELECT * FROM PLAYER WHERE ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int age = resultSet.getInt("AGE");
                double height = resultSet.getDouble("HEIGHT");

                player = new Player(id, name, age, height);
            }
        } catch (SQLException e) {
            throw new DaoException("findPlayerByID() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findPlayerByID() " + e.getMessage());
            }
        }
        return player;     // reference to User object, or null value
    }

    @Override
    public void deletePlayerByID(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.getConnection();

            String query = "delete from Player where id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("deletePlayerByID() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("deletePlayerByID() " + e.getMessage());
            }
        }
    }

    @Override
    public Player insertPlayer(String name, int age, double height) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Player player = null;
        int id = idGenerator.getNextId();
        try {
            connection = this.getConnection();

            String query = "insert into basketball_oop_CA4.Player values (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setDouble(4, height);
            preparedStatement.executeUpdate();

            player = new Player(id, name, age, height);
        } catch (SQLException e) {
            throw new DaoException("insertPlayer() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("insertPlayer() " + e.getMessage());
            }
        }
        return player;     // reference to User object, or null value
    }

    @Override
    public List<Player> findPlayerUsingAgeFilter(int age) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Player> playersList = new ArrayList<>();

        try {
            connection = this.getConnection();

            String query = "SELECT * FROM PLAYER Where AGE=? Order By NAME";
            ps = connection.prepareStatement(query);
            ps.setInt(1, age);

            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int playerId = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                double height = resultSet.getDouble("HEIGHT");
                Player p = new Player(playerId, name, age, height);
                playersList.add(p);
            }
        } catch (SQLException e) {
            throw new DaoException("findPlayerUsingAgeFilter() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findPlayerUsingAgeFilter() " + e.getMessage());
            }
        }
        return playersList;
    }

    @Override
    public String findAllPlayersJson() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Player> playersList = new ArrayList<>();
        Gson gsonParser = new Gson();
        //If you want to display a neat json string then uncomment the code below and comment the code above
        //Gson gsonParser = new GsonBuilder().setPrettyPrinting().create();
        String playerListJson = null;

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM PLAYER";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int playerId = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                int age = resultSet.getInt("AGE");
                double height = resultSet.getDouble("HEIGHT");
                Player p = new Player(playerId, name, age, height);
                playersList.add(p);
            }
            playerListJson = gsonParser.toJson(playersList);
        } catch (SQLException e) {
            throw new DaoException("findAllPlayersJson() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllPlayersJson() " + e.getMessage());
            }
        }
        return playerListJson;
    }

    @Override
    public String findPlayerByIDJson(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Player player = null;
        Gson gsonParser = new Gson();
        //If you want to display a neat json string then uncomment the code below and comment the code above
        //Gson gsonParser = new GsonBuilder().setPrettyPrinting().create();
        String playerJson = null;

        try {
            connection = this.getConnection();

            String query = "SELECT * FROM PLAYER WHERE ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int age = resultSet.getInt("AGE");
                double height = resultSet.getDouble("HEIGHT");

                player = new Player(id, name, age, height);
                playerJson = gsonParser.toJson(player);
            }
        } catch (SQLException e) {
            throw new DaoException("findPlayerByIDJson() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findPlayerByIDJson() " + e.getMessage());
            }
        }
        return playerJson;
    }
}
