package Model.Category;

import Model.Connector;
import Model.TransactionType;
import java.sql.*;
import java.util.ArrayList;

public class CategoryDAO {
    // CREATE
    public boolean createCategory(Category category) {

        String sql =
                "INSERT INTO categories(nama,jenis) VALUES(?,?)";

        try (
                Connection conn =
                        Connector.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    category.getNama()
            );

            stmt.setString(
                    2,
                    category.getJenis().name()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    // READ SATU
    public Category getCategory(int id) {

        String sql =
                "SELECT * FROM categories WHERE id=?";

        try (
                Connection conn =
                        Connector.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                return new Category(
                        rs.getInt("id"),
                        rs.getString("nama"),
                        TransactionType.valueOf(
                                rs.getString("jenis")
                        )
                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    // READ ALL
    public ArrayList<Category> getAllCategory() {

        ArrayList<Category>
                categories =
                new ArrayList<>();

        String sql =
                "SELECT * FROM categories";

        try (
                Connection conn =
                        Connector.getConnection();

                Statement stmt =
                        conn.createStatement();

                ResultSet rs =
                        stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                categories.add(
                        new Category(
                                rs.getInt("id"),
                                rs.getString("nama"),
                                TransactionType.valueOf(
                                        rs.getString("jenis")
                                )
                        )
                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return categories;
    }
    
    // UPDATE
    public boolean updateCategory(Category category) {
        
        String sql =
                "UPDATE categories SET nama=?, jenis=? WHERE id=?";
        
        try (Connection conn = Connector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
                ) {
            stmt.setString(1, category.getNama());
            stmt.setString(2, category.getJenis().name());
            stmt.setInt(3, category.getId());
            
            return stmt.executeUpdate() > 0;
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    // DELETE
    public boolean deleteCategory(int id) {

        String sql =
                "DELETE FROM categories WHERE id=?";

        try (
                Connection conn =
                        Connector.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

}
