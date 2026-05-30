/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Transaction;

/**
 *
 * @author Admin
 */
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import Model.Connector;

public class TransactionDAO {
    private ArrayList<Transaction> transactions;

    public TransactionDAO() {
        transactions =new ArrayList<>();
    }
    // CREATE
    public void createTransaction(
        Transaction trx
    ){

        String query =
                "INSERT INTO transactions " +
                "(wallet_id, category_id, jumlah, deskripsi, tanggal) " +
                "VALUES (?, ?, ?, ?, ?)";

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
                    trx.getWalletId()
            );

            ps.setInt(
                    2,
                    trx.getCategoryId()
            );

            ps.setDouble(
                    3,
                    trx.getJumlah()
            );

            ps.setString(
                    4,
                    trx.getDeskripsi()
            );

            ps.setDate(
                    5,
                    Date.valueOf(
                            trx.getTanggal()
                    )
            );

            ps.executeUpdate();

            System.out.println(
                    "Transaksi berhasil ditambah"
            );

        }

        catch(SQLException e){

            e.printStackTrace();

        }

    }

    // READ BY ID
    public Transaction getTransaction(
        int id
    ){

        String query =
                "SELECT * FROM transactions " +
                "WHERE id=?";

        try(

                Connection conn =
                        Connector.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                query
                        )

        ){

            ps.setInt(1,id);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()){

                return new Transaction(

                        rs.getInt("id"),

                        rs.getInt("wallet_id"),

                        rs.getInt("category_id"),

                        rs.getDouble("jumlah"),

                        rs.getString("deskripsi"),

                        rs.getDate("tanggal")
                        .toLocalDate()

                );

            }

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return null;

    }

    // READ ALL
    public ArrayList<Transaction>
    getAllTransaction(){

        ArrayList<Transaction>
                transactions =
                new ArrayList<>();

        String query =
                "SELECT * FROM transactions";

        try(

                Connection conn =
                        Connector.connect();

                Statement st =
                        conn.createStatement();

                ResultSet rs =
                        st.executeQuery(query)

        ){

            while(rs.next()){

                Transaction trx =
                        new Transaction(

                                rs.getInt("id"),

                                rs.getInt("wallet_id"),

                                rs.getInt("category_id"),

                                rs.getDouble("jumlah"),

                                rs.getString("deskripsi"),

                                rs.getDate("tanggal")
                                .toLocalDate()

                        );

                transactions.add(trx);

            }

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return transactions;

    }

    // UPDATE
    public boolean updateTransaction(
        Transaction trx
    ){

        String query =
                "UPDATE transactions " +
                "SET wallet_id=?, " +
                "category_id=?, " +
                "jumlah=?, " +
                "deskripsi=?, " +
                "tanggal=? " +
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
                    trx.getWalletId()
            );

            ps.setInt(
                    2,
                    trx.getCategoryId()
            );

            ps.setDouble(
                    3,
                    trx.getJumlah()
            );

            ps.setString(
                    4,
                    trx.getDeskripsi()
            );

            ps.setDate(
                    5,
                    Date.valueOf(
                            trx.getTanggal()
                    )
            );

            ps.setInt(
                    6,
                    trx.getId()
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

    // DELETE
    public boolean deleteTransaction(
        int id
    ){

        String query =
                "DELETE FROM transactions " +
                "WHERE id=?";

        try(

                Connection conn =
                        Connector.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                query
                        )

        ){

            ps.setInt(1,id);

            int rows =
                    ps.executeUpdate();

            return rows > 0;

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return false;

    }
    // Get by Wallet ID
    public ArrayList<Transaction>
    getByWalletId(
            int walletId
    ){

        ArrayList<Transaction>
                transactions =
                new ArrayList<>();

        String query =
                "SELECT * FROM transactions " +
                "WHERE wallet_id=?";

        try(

                Connection conn =
                        Connector.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                query
                        )

        ){

            ps.setInt(1,walletId);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                Transaction trx =
                        new Transaction(

                                rs.getInt("id"),

                                rs.getInt("wallet_id"),

                                rs.getInt("category_id"),

                                rs.getDouble("jumlah"),

                                rs.getString("deskripsi"),

                                rs.getDate("tanggal")
                                .toLocalDate()

                        );

                transactions.add(trx);

            }

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return transactions;

    }
    // Get by Category
    public ArrayList<Transaction>
    getByCategory(
            int categoryId
    ){

        ArrayList<Transaction>
                transactions =
                new ArrayList<>();

        String query =
                "SELECT * FROM transactions " +
                "WHERE category_id=?";

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
                    categoryId
            );

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                Transaction trx =
                        new Transaction(

                                rs.getInt("id"),

                                rs.getInt("wallet_id"),

                                rs.getInt("category_id"),

                                rs.getDouble("jumlah"),

                                rs.getString("deskripsi"),

                                rs.getDate("tanggal")
                                .toLocalDate()

                        );

                transactions.add(
                        trx
                );

            }

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return transactions;

    }
    // Get by Date
    public ArrayList<Transaction>
    getByDate(
            LocalDate tanggal
    ){

        ArrayList<Transaction>
                transactions =
                new ArrayList<>();

        String query =
                "SELECT * FROM transactions " +
                "WHERE tanggal=?";

        try(

                Connection conn =
                        Connector.connect();

                PreparedStatement ps =
                        conn.prepareStatement(
                                query
                        )

        ){

            ps.setDate(
                    1,
                    Date.valueOf(
                            tanggal
                    )
            );

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                Transaction trx =
                        new Transaction(

                                rs.getInt("id"),

                                rs.getInt("wallet_id"),

                                rs.getInt("category_id"),

                                rs.getDouble("jumlah"),

                                rs.getString("deskripsi"),

                                rs.getDate("tanggal")
                                .toLocalDate()

                        );

                transactions.add(
                        trx
                );

            }

        }

        catch(SQLException e){

            e.printStackTrace();

        }

        return transactions;

    }
}