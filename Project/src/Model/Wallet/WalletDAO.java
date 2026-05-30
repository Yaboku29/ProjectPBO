package Model.Wallet;

import Model.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class WalletDAO {

    // =========================
    // CREATE
    // =========================

    public void createWallet(
            Wallet wallet
    ){

        String query =
                "INSERT INTO wallets " +
                "(userID,nama,saldo) " +
                "VALUES (?,?,?)";

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
                    wallet.getUserId()
            );

            ps.setString(
                    2,
                    wallet.getNama()
            );

            ps.setDouble(
                    3,
                    wallet.getSaldo()
            );

            ps.executeUpdate();

            System.out.println(
                    "Wallet berhasil dibuat"
            );

        }

        catch(SQLException e){

            e.printStackTrace();

        }

    }

    // =========================
    // READ SATU
    // =========================

    public Wallet getWallet(
            int id
    ){

        String query =
                "SELECT * FROM wallets " +
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

                return new Wallet(

                        rs.getInt("id"),

                        rs.getInt("userID"),

                        rs.getString("nama"),

                        rs.getDouble("saldo")

                );

            }

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return null;

    }

    // =========================
    // READ SEMUA
    // =========================

    public ArrayList<Wallet>
    getAllWallet(){

        ArrayList<Wallet>
                wallets =
                new ArrayList<>();

        String query =
                "SELECT * FROM wallets";

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

                Wallet wallet =
                        new Wallet(

                                rs.getInt("id"),

                                rs.getInt("userID"),

                                rs.getString("nama"),

                                rs.getDouble("saldo")

                        );

                wallets.add(wallet);

            }

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return wallets;

    }

    // =========================
    // GET BY USER ID
    // =========================

    public ArrayList<Wallet>
    getByUserId(
            int userId
    ){

        ArrayList<Wallet>
                wallets =
                new ArrayList<>();

        String query =
                "SELECT * FROM wallets " +
                "WHERE userID=?";

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
                    userId
            );

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                Wallet wallet =
                        new Wallet(

                                rs.getInt("id"),

                                rs.getInt("userID"),

                                rs.getString("nama"),

                                rs.getDouble("saldo")

                        );

                wallets.add(wallet);

            }

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return wallets;

    }

    // =========================
    // UPDATE
    // =========================

    public boolean updateWallet(
            Wallet walletBaru
    ){

        String query =
                "UPDATE wallets " +
                "SET userID=?, " +
                "nama=?, " +
                "saldo=? " +
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
                    walletBaru.getUserId()
            );

            ps.setString(
                    2,
                    walletBaru.getNama()
            );

            ps.setDouble(
                    3,
                    walletBaru.getSaldo()
            );

            ps.setInt(
                    4,
                    walletBaru.getId()
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

    public boolean deleteWallet(
            int id
    ){

        String query =
                "DELETE FROM wallets " +
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

}