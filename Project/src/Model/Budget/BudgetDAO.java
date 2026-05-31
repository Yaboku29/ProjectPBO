package Model.Budget;

import Model.Connector;
import java.sql.*;

public class BudgetDAO {

    public boolean createBudget(
            Budget budget
    ) {

        String sql =
                "INSERT INTO budgets(wallet_id,limit_harian) VALUES(?,?)";

        try (
                Connection conn =
                        Connector.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(
                    1,
                    budget.getWalletId()
            );

            stmt.setDouble(
                    2,
                    budget.getLimitHarian()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    public Budget getByWalletId(
            int walletId
    ) {

        String sql =
                "SELECT * FROM budgets WHERE wallet_id=?";

        try (
                Connection conn =
                        Connector.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(
                    1,
                    walletId
            );

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                return new Budget(
                        rs.getInt("id"),
                        rs.getInt("wallet_id"),
                        rs.getDouble("limit_harian")
                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    public boolean updateBudget(
            Budget budget
    ) {

        String sql =
                "UPDATE budgets SET limit_harian=? WHERE id=?";

        try (
                Connection conn =
                        Connector.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setDouble(
                    1,
                    budget.getLimitHarian()
            );

            stmt.setInt(
                    2,
                    budget.getId()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    public boolean deleteBudget(
            int id
    ) {

        String sql =
                "DELETE FROM budgets WHERE id=?";

        try (
                Connection conn =
                        Connector.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(
                    1,
                    id
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }
    
    // GET BUDGET BERDASARKAN ID
    public Budget getBudget(int id) {

        String sql =
                "SELECT * FROM budgets WHERE id=?";
        try (
                Connection conn =
                        Connector.getConnection();

                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                
                return new Budget(
                        rs.getInt("id"),
                        rs.getInt("wallet_id"),
                        rs.getDouble("limit_harian")
                );
            
            }

        } catch (SQLException e) {
            
            e.printStackTrace();
        
        }
        
        return null;
    }
    
    
    public java.util.ArrayList<Budget> getAllBudget() {
        java.util.ArrayList<Budget> budgets =
                new java.util.ArrayList<>();
        
        String sql =
                "SELECT * FROM budgets";
        try (
                Connection conn = Connector.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
                ) {
            while (rs.next()) {
                budgets.add(
                        new Budget(
                                rs.getInt("id"),
                                rs.getInt("wallet_id"),
                                rs.getDouble("limit_harian")
                        )
                );
            
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return budgets;
    }

}