package View;

import Model.User.User;
import Model.Report.Report;
import Model.Report.ReportDAO;
import Model.Wallet.Wallet;
import Model.Wallet.WalletDAO;

import javax.swing.*;
import java.awt.*;

public class DashboardView extends JFrame {
    
    private User user;
    
    public DashboardView(User user) {
        this.user = user;
        
        setTitle("Finance Tracker Dashboard");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(200, 600));
        sidebar.setBackground(new Color(91, 95, 239));
        sidebar.setLayout(new GridLayout(10, 1, 10, 10));

        JButton btnDashboard = new JButton("Dashboard");
        JButton btnCategory = new JButton("Category");
        JButton btnWallet = new JButton("Wallet");
        JButton btnTransaction = new JButton("Transaction");
        JButton btnBudget = new JButton("Budget");
        JButton btnReport = new JButton("Report");
        JButton btnLogout = new JButton("Logout");

        sidebar.add(btnDashboard);
        sidebar.add(btnCategory);
        sidebar.add(btnWallet);
        sidebar.add(btnTransaction);
        sidebar.add(btnBudget);
        sidebar.add(btnReport);
        sidebar.add(btnLogout);
        
        JPanel content = new JPanel();
        content.setBackground(new Color(245, 247, 251));
        content.setLayout(null);

        JLabel welcome = new JLabel("Welcome, " + user.getUsername());
        welcome.setFont(new Font("Poppins", Font.BOLD, 24));
        welcome.setBounds(40, 40, 400, 40);
        
//        ReportDAO reportDAO =
//        new ReportDAO();
//        /*sementara wallet_id = 1*/
//        Report report =
//        reportDAO.generateReport(1);
        
        WalletDAO walletDAO = new WalletDAO();
        
        ReportDAO reportDAO = new ReportDAO();
        
        double totalIncome = 0;
        double totalExpense = 0;
        double totalBalance = 0;
        
        for (Wallet wallet :
                walletDAO.getByUserId(
                        user.getId()
                )) {

            Report report =
                    reportDAO.generateReport(
                            wallet.getId()
                    );

            totalIncome +=
                    report.getTotalPemasukan();
            
            totalExpense +=
                    report.getTotalPengeluaran();
            
            totalBalance +=
                    report.getSaldoAkhir();
        
        }
        
        JPanel cardBalance = createCard("Total Balance", "Rp " + totalBalance);
        
        JPanel cardIncome = createCard("Income", "Rp " + totalIncome);
        
        JPanel cardExpense = createCard("Expense","Rp " + totalExpense);
        
        cardBalance.setBounds(40, 120, 220, 120);
        cardIncome.setBounds(290, 120, 220, 120);
        cardExpense.setBounds(540, 120, 220, 120);
        
        content.add(welcome);
        content.add(cardBalance);
        content.add(cardIncome);
        content.add(cardExpense);

        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(content, BorderLayout.CENTER);
        
        add(mainPanel);
        
        btnCategory.addActionListener(e -> {
            new CategoryView(user).setVisible(true);
        });
        
        btnReport.addActionListener(e -> {
            new ReportView(user).setVisible(true);
        });
        
        btnBudget.addActionListener(e -> {
            new BudgetView(user).setVisible(true);
        });
        
        btnTransaction.addActionListener(e -> {
            new TransactionView(user).setVisible(true);
        });
        
        btnWallet.addActionListener(e -> {
            new WalletView(user).setVisible(true);
        });
        
        btnLogout.addActionListener(e -> {
            new LoginView().setVisible(true);
            dispose();
        });
}
    
    private JPanel createCard(String title, String value){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Poppins", Font.PLAIN, 16));
        lblTitle.setBounds(20, 20, 150, 25);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Poppins", Font.BOLD, 20));
        lblValue.setBounds(20, 55, 180, 35);

        panel.add(lblTitle);
        panel.add(lblValue);

        return panel;
    }
}
