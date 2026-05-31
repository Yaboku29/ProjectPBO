package Model.Transaction;

import Model.Connector;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TransactionDAO {
    // CREATE
    public boolean createTransaction(Transaction trx) {

        String sql =
                "INSERT INTO transactions(wallet_id, category_id, jumlah, deskripsi, tanggal) VALUES(?,?,?,?,?)";

        try (
                Connection conn = Connector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, trx.getWalletId());
            stmt.setInt(2, trx.getCategoryId());
            stmt.setDouble(3, trx.getJumlah());
            stmt.setString(4, trx.getDeskripsi());

            stmt.setDate(
                    5,
                    Date.valueOf(
                            trx.getTanggal()
                    )
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    // READ BY ID
    public Transaction getTransaction(int id) {

        String sql =
                "SELECT * FROM transactions WHERE id=?";

        try (
                Connection conn = Connector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                return new Transaction(
                        rs.getInt("id"),
                        rs.getInt("wallet_id"),
                        rs.getInt("category_id"),
                        rs.getDouble("jumlah"),
                        rs.getString("deskripsi"),
                        rs.getDate("tanggal").toLocalDate()
                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    // READ ALL
    public ArrayList<Transaction> getAllTransaction() {

        ArrayList<Transaction> transactions =
                new ArrayList<>();

        String sql =
                "SELECT * FROM transactions";

        try (
                Connection conn = Connector.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                transactions.add(
                        new Transaction(
                                rs.getInt("id"),
                                rs.getInt("wallet_id"),
                                rs.getInt("category_id"),
                                rs.getDouble("jumlah"),
                                rs.getString("deskripsi"),
                                rs.getDate("tanggal").toLocalDate()
                        )
                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return transactions;
    }

    // UPDATE
    public boolean updateTransaction(Transaction trx) {

        String sql =
                "UPDATE transactions SET wallet_id=?, category_id=?, jumlah=?, deskripsi=?, tanggal=? WHERE id=?";

        try (
                Connection conn = Connector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, trx.getWalletId());
            stmt.setInt(2, trx.getCategoryId());
            
            stmt.setDouble(3, trx.getJumlah());
            stmt.setString(4, trx.getDeskripsi());

            stmt.setDate(
                    5,
                    Date.valueOf(
                            trx.getTanggal()
                    )
            );

            stmt.setInt(6, trx.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    // DELETE
    public boolean deleteTransaction(int id) {

        String sql =
                "DELETE FROM transactions WHERE id=?";

        try (
                Connection conn = Connector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    public ArrayList<Transaction> getByWalletId(int walletId) {

        ArrayList<Transaction> hasil =
                new ArrayList<>();

        String sql =
                "SELECT * FROM transactions WHERE wallet_id=?";

        try (
                Connection conn = Connector.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, walletId);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                hasil.add(
                        new Transaction(
                                rs.getInt("id"),
                                rs.getInt("wallet_id"),
                                rs.getInt("category_id"),
                                rs.getDouble("jumlah"),
                                rs.getString("deskripsi"),
                                rs.getDate("tanggal").toLocalDate()
                        )
                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return hasil;
    }
    
    public ArrayList<Transaction> getByDate(
        LocalDate tanggal) {
        
        ArrayList<Transaction> hasil =
            new ArrayList<>();
        
        String sql =
            "SELECT * FROM transactions WHERE tanggal=?";
        try (
            Connection conn =
                    Connector.getConnection();

            PreparedStatement stmt =
                    conn.prepareStatement(sql)
                ) {

        stmt.setDate(
                1,
                java.sql.Date.valueOf(
                        tanggal
                )
        );

        ResultSet rs =
                stmt.executeQuery();

        while (rs.next()) {

            hasil.add(
                    new Transaction(
                            rs.getInt("id"),
                            rs.getInt("wallet_id"),
                            rs.getInt("category_id"),
                            
                            rs.getDouble("jumlah"),
                            rs.getString("deskripsi"),
                            rs.getDate("tanggal")
                                    .toLocalDate()
                    )
            );
        }
        
        } catch (SQLException e) {

        e.printStackTrace();
        
        }

    return hasil;
    
    }

}
