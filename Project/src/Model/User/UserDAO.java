package Model.User;

import Model.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.User.User;

public class UserDAO {

    // =========================
    // CREATE USER
    // =========================

    public boolean createUser(
        User user
    ){

        String query =
                "INSERT INTO users " +
                "(username,nama,email,password) " +
                "VALUES (?,?,?,?)";

        try(

                Connection conn =
                        Connector.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                query
                        )

        ){

            ps.setString(
                    1,
                    user.getUsername()
            );

            ps.setString(
                    2,
                    user.getNama()
            );

            ps.setString(
                    3,
                    user.getEmail()
            );

            ps.setString(
                    4,
                    user.getPassword()
            );

            ps.executeUpdate();

            return true;

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return false;

    }

    // =========================
    // LOGIN
    // =========================

    public User login(

        String username,
        String password

    ){

        String query =
                "SELECT * FROM users " +
                "WHERE username=? " +
                "AND password=?";

        try(

                Connection conn =
                        Connector.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                query
                        )

        ){

            ps.setString(
                    1,
                    username
            );

            ps.setString(
                    2,
                    password
            );

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()){

                return new User(

                        rs.getInt("id"),

                        rs.getString("username"),

                        rs.getString("nama"),

                        rs.getString("email"),

                        rs.getString("password")

                );

            }

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return null;

    }

}