package Model.Category;

import Model.TransactionType;

import Model.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class CategoryDAO {

    // =========================
    // CREATE
    // =========================

    public void createCategory(
            Category category
    ){

        String query =
                "INSERT INTO categories " +
                "(nama,jenis) " +
                "VALUES (?,?)";

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
                    category.getNama()
            );

            ps.setString(
                    2,
                    category
                    .getJenis()
                    .toString()
            );

            ps.executeUpdate();

            System.out.println(
                    "Category berhasil dibuat"
            );

        }

        catch(SQLException e){

            e.printStackTrace();

        }

    }

    // =========================
    // READ SATU
    // =========================

    public Category getCategory(
            int id
    ){

        String query =
                "SELECT * FROM categories " +
                "WHERE id=?";

        try(

                Connection conn =
                        Connector.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                query
                        )

        ){

            ps.setInt(
                    1,
                    id
            );

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()){

                return new Category(

                        rs.getInt("id"),

                        rs.getString("nama"),

                        TransactionType.valueOf(
                                rs.getString("jenis")
                        )

                );

            }

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return null;

    }
    // =========================
    // READ ALL
    // =========================

    public ArrayList<Category>
    getAllCategory(){

        ArrayList<Category>
                categories =
                new ArrayList<>();

        String query =
                "SELECT * FROM categories";

        try(

                Connection conn =
                        Connector.connect();

                Statement st =
                        conn.createStatement();

                ResultSet rs =
                        st.executeQuery(
                                query
                        )

        ){

            while(rs.next()){

                Category category =
                        new Category(

                                rs.getInt("id"),

                                rs.getString("nama"),

                                TransactionType.valueOf(
                                        rs.getString("jenis")
                                )

                        );

                categories.add(
                        category
                );

            }

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return categories;

    }

    // =========================
    // UPDATE
    // =========================

    public boolean updateCategory(
            Category categoryBaru
    ){

        String query =
                "UPDATE categories " +
                "SET nama=?, " +
                "jenis=? " +
                "WHERE id=?";

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
                    categoryBaru.getNama()
            );

            ps.setString(
                    2,
                    categoryBaru
                    .getJenis()
                    .toString()
            );

            ps.setInt(
                    3,
                    categoryBaru.getId()
            );

            int rows =
                    ps.executeUpdate();

            return rows > 0;

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return false;

    }

    // =========================
    // DELETE
    // =========================

    public boolean deleteCategory(
        int id
    ){

        String query =
                "DELETE FROM categories " +
                "WHERE id=?";

        try(

                Connection conn =
                        Connector.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                query
                        )

        ){

            ps.setInt(
                    1,
                    id
            );

            int rows =
                    ps.executeUpdate();

            return rows > 0;

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return false;

    }

    // =========================
    // GET BY JENIS
    // =========================

    public ArrayList<Category>
    getByJenis(
            TransactionType jenis
    ){

        ArrayList<Category>
                categories =
                new ArrayList<>();

        String query =
                "SELECT * FROM categories" +
                "WHERE jenis=?";

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
                    jenis.toString()
            );

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                Category category =
                        new Category(

                                rs.getInt("id"),

                                rs.getString("nama"),

                                TransactionType.valueOf(
                                        rs.getString("jenis")
                                )

                        );

                categories.add(
                        category
                );

            }

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return categories;

    }

}