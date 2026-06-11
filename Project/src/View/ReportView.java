package View;

import Model.User.User;
import Model.Report.Report;
import Model.Report.ReportDAO;
import Model.Wallet.Wallet;
import Model.Wallet.WalletDAO;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class ReportView extends JFrame {
    private User user;
    
    public ReportView(User user){
        this.user = user;
        
        setTitle("Financial Report");
        setSize(800, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel(
                "Financial Report",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        
        JTextArea reportArea = new JTextArea();
        
        reportArea.setEditable(false);
        
        StringBuilder laporan =
                new StringBuilder();
        
        WalletDAO walletDAO =
                new WalletDAO();
        
        ReportDAO reportDAO =
                new ReportDAO();
        
        ArrayList<Wallet> wallets =
                walletDAO.getByUserId(
                        user.getId()
                );
        
        for (Wallet wallet : wallets) {
            
            Report report =
                    reportDAO.generateReport(
                            wallet.getId()
                    );
            
            laporan.append("====================================\n");
            
            laporan.append("Wallet : " + wallet.getNama() + "\n");
            
            laporan.append("Total Pemasukan : Rp " + report.getTotalPemasukan() + "\n");
            
            laporan.append("Total Pengeluaran : Rp " + report.getTotalPengeluaran() + "\n");
            
            laporan.append("Selisih : Rp " + report.getSaldoAkhir() + "\n\n");
        }
        
        reportArea.setText(
                laporan.toString()
        );
        JPanel buttonPanel = new JPanel();
                JButton btnKembali = new JButton("Kembali");
                buttonPanel.add(btnKembali);

                btnKembali.addActionListener(e -> {

                new DashboardView(user)
                        .setVisible(true);

                dispose();

                });

        panel.add(title, BorderLayout.NORTH);
        panel.add(new JScrollPane(reportArea), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
        
    }
}
