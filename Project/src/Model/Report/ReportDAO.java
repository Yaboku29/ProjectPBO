package Model.Report;

import Model.Connector;
import java.sql.*;

public class ReportDAO {
    public Report generateReport(
            int walletId
    ) {

        double pemasukan = 0;
        double pengeluaran = 0;

        String sql =
                "SELECT jenis, jumlah FROM transactions WHERE wallet_id= ?";

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

            while (rs.next()) {

                String jenis =
                        rs.getString("jenis");

                double jumlah =
                        rs.getDouble("jumlah");

                if (
                        jenis.equalsIgnoreCase(
                                "Pemasukan"
                        )
                ) {

                    pemasukan += jumlah;

                }

                else {

                    pengeluaran += jumlah;

                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return new Report(
                pemasukan,
                pengeluaran
        );
    }
}
