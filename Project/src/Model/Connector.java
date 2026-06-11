package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    private static Connection connection;

    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                String url = "jdbc:mysql://localhost:3306/walet_db";
                String user = "root";
                String password = "";

//                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

                connection = DriverManager.getConnection(url, user, password);

                System.out.println("Koneksi berhasil!");
            }

        } catch (SQLException e) {
            System.out.println("Koneksi gagal : " + e.getMessage());
        }

        return connection;
    }
}



//import java.sql.*;
//
//public class Connector {
//    private static String jdbc_driver="com.mysql.cj.jdbc.Driver";
//    private static String nama_db="wallet_db";
//    private static String url_db="jdbc:mysql://localhost:3306/"+nama_db;
//    private static String username_db="root";
//    private static String password_db="";
//    
//    static Connection conn;
//    // Mencoba menghubungkan program kita dengan ke database MySQL.
//    public static Connection connect() {
//        try {
//            // 1. Register driver yang akan dipakai
//            Class.forName(jdbc_driver);
//            
//            // 2. Buat koneksi ke database
//            conn = DriverManager.getConnection(url_db, username_db, password_db);
//
//            // 3. Menampilkan pesan "Connection Success" jika berhasil terhubung ke database.
//            System.out.println("MySQL Connected");
//        } catch (ClassNotFoundException | SQLException exception) {
//            // Menampilkan pesan error ketika MySQL gagal terhubung.
//            System.out.println("Connection Failed: " + exception.getLocalizedMessage());
//        }
//        return conn;
//    }
//}

