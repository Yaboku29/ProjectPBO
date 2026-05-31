package Model.Wallet;

import Model.Connector;
import java.sql.*;
import java.util.ArrayList;

public class WalletDAO {
    
    public boolean createWallet(Wallet wallet) {

        String sql =
                "INSERT INTO wallets(user_id,nama,saldo) VALUES(?,?,?)";

        try (
                Connection conn =
                        Connector.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(
                    1,
                    wallet.getUserId()
            );

            stmt.setString(
                    2,
                    wallet.getNama()
            );

            stmt.setDouble(
                    3,
                    wallet.getSaldo()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    public Wallet getWallet(int id) {

        String sql =
                "SELECT * FROM wallets WHERE id=?";

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

                return new Wallet(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("nama"),
                        rs.getDouble("saldo")
                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    public ArrayList<Wallet> getAllWallet() {

        ArrayList<Wallet>
                wallets =
                new ArrayList<>();

        String sql =
                "SELECT * FROM wallets";

        try (
                Connection conn =
                        Connector.getConnection();

                Statement stmt =
                        conn.createStatement();

                ResultSet rs =
                        stmt.executeQuery(sql)
        ) {

            while (rs.next()) {

                wallets.add(
                        new Wallet(
                                rs.getInt("id"),
                                rs.getInt("user_id"),
                                rs.getString("nama"),
                                rs.getDouble("saldo")
                        )
                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return wallets;
    }

    public boolean updateWallet(
            Wallet wallet
    ) {

        String sql =
                "UPDATE wallets SET user_id=?, nama=?, saldo=? WHERE id=?";

        try (
                Connection conn =
                        Connector.getConnection();

                PreparedStatement stmt =
                        conn.prepareStatement(sql)
        ) {

            stmt.setInt(
                    1,
                    wallet.getUserId()
            );

            stmt.setString(
                    2,
                    wallet.getNama()
            );

            stmt.setDouble(
                    3,
                    wallet.getSaldo()
            );

            stmt.setInt(
                    4,
                    wallet.getId()
            );

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    public boolean deleteWallet(
            int id
    ) {

        String sql =
                "DELETE FROM wallets WHERE id=?";

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
    
    public ArrayList<Wallet> getByUserId(
        int userId
    ) {

    ArrayList<Wallet> wallets =
            new ArrayList<>();

    String sql =
            "SELECT * FROM wallets WHERE user_id=?";

    try (
            Connection conn =
                    Connector.getConnection();

            PreparedStatement stmt =
                    conn.prepareStatement(sql)
            ) {

        stmt.setInt(
                1,
                userId
        );

        ResultSet rs =
                stmt.executeQuery();

        while (rs.next()) {

            wallets.add(
                    new Wallet(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("nama"),
                            rs.getDouble("saldo")
                    )
            );

        }

    } catch (SQLException e) {

        e.printStackTrace();

    }

    return wallets;
    
    }
    
}
