package View;

import Model.User.User;
import Model.Budget.Budget;
import Model.Budget.BudgetDAO;
import Model.Wallet.Wallet;
import Model.Wallet.WalletDAO;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class BudgetView extends JFrame {
    private User user;
    
    public BudgetView(User user){
        this.user = user;
        
        setTitle("Budget");
        setSize(700, 450);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel(
                "Budget Management",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        
        DefaultTableModel model =
                new DefaultTableModel();
        
        model.addColumn("ID");
        model.addColumn("Wallet");
        model.addColumn("Limit Harian");
        
        BudgetDAO budgetDAO =
                new BudgetDAO();
        
        ArrayList<Budget> budgets =
                budgetDAO.getAllBudget();
        
        WalletDAO walletDAO = new WalletDAO();
        
        for (Budget budget : budgets) {
            
            Wallet wallet = walletDAO.getWallet(
                    budget.getWalletId());
            
            model.addRow(
                    new Object[]{
                        budget.getId(),
                        wallet.getNama(),
                        //budget.getWalletId(),
                        budget.getLimitHarian()
                    }
            );
        
        }
        
        JTable table =
                new JTable(model);
        
        JButton btnTambah = new JButton("Tambah Budget");
        
        btnTambah.addActionListener(e -> {
            try {
                //WalletDAO walletDAO =
                        //new WalletDAO();
                
                ArrayList<Wallet> wallets =
                        walletDAO.getByUserId(
                                user.getId()
                        );

                if (wallets.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Belum ada wallet"
                    );

                    return;
                }

                String[] walletNames =
                        new String[wallets.size()];

                for (int i = 0; i < wallets.size(); i++) {

                    walletNames[i] =
                            wallets.get(i).getNama();

                }

                String selectedWallet =
                        (String)
                        JOptionPane.showInputDialog(
                                this,
                                "Pilih Wallet",
                                "Wallet",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                walletNames,
                                walletNames[0]
                        );

                if (selectedWallet == null) {
                    return;
                }

                Wallet walletDipilih = null;
                
                for (Wallet w : wallets) {

                    if (
                            w.getNama().equals(
                            selectedWallet
                            )) {

                        walletDipilih = w;
                        break;

                    }

                }

                String limitStr =
                        JOptionPane.showInputDialog(
                                this,
                                "Masukkan Limit Harian"
                        );

                if (
                        limitStr == null
                        ||
                        limitStr.trim().isEmpty()
                        ) {

                    return;

                }

                double limit =
                        Double.parseDouble(
                                limitStr
                        );

                Budget budget =
                        new Budget(
                                0,
                                walletDipilih.getId(),
                                limit
                        );

                BudgetDAO dao =
                        new BudgetDAO();

                boolean berhasil =
                        dao.createBudget(
                                budget
                        );
                
                if (berhasil) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Budget berhasil ditambahkan"

                    );

                    dispose();

                    new BudgetView(user).setVisible(true);
                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Gagal menambahkan budget"
                    );
                }

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Input tidak valid"
                );

            }
        });
        
//        btnTambah.addActionListener(e -> {
//            
//            try {
//                
//                String walletIdStr =
//                        JOptionPane.showInputDialog(
//                                this,
//                                "Masukkan Wallet ID"
//                        );
//                
//                if (walletIdStr == null) {
//                    return;
//                }
//
//                String limitStr =
//                        JOptionPane.showInputDialog(
//                                this,
//                                "Masukkan Limit Harian"
//                        );
//                
//                if (limitStr == null) {
//                    return;
//                }
//                
//                int walletId =
//                        Integer.parseInt(walletIdStr);
//                
//                double limit = Double.parseDouble(limitStr);
//                
//                Budget budget =
//                        new Budget(
//                                0,
//                                walletId,
//                                limit
//                        );
//
//                BudgetDAO dao =
//                        new BudgetDAO();
//
//                
//                boolean berhasil =
//                        dao.createBudget(
//                                budget
//                        );
//
//                if (berhasil) {
//
//                    JOptionPane.showMessageDialog(
//                            this,
//                            "Budget berhasil ditambahkan"
//                    );
//                    
//                    dispose();
//
//                    new BudgetView(user).setVisible(true);
//                } else {
//                    
//                    JOptionPane.showMessageDialog(
//                            this,
//                            "Gagal menambahkan budget"
//                    );
//                
//                }
//
//            } catch (Exception ex) {
//                
//                JOptionPane.showMessageDialog(
//                        this,
//                        "Input tidak valid"
//                );
//            
//            }
//        });
        
        JButton btnEdit = new JButton("Edit Budget");
        
        btnEdit.addActionListener(e -> {
            
            int row = table.getSelectedRow();
            
            if (row == -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Pilih budget terlebih dahulu"
                );
                return;
            }
            
            try {

                int id =
                        Integer.parseInt(
                                table.getValueAt(row, 0).toString()
                        );
                
                BudgetDAO dao = new BudgetDAO();
            
                Budget budgetLama = dao.getBudget(id);
                
//                int walletId =
//                        Integer.parseInt(
//                                table.getValueAt(row, 1).toString()
//                        );
                
                String limitBaru =
                        JOptionPane.showInputDialog(
                                this,
                                "Limit Harian Baru",
                                table.getValueAt(row, 2)
                        );
                
                if (limitBaru == null) {
                    return;
                }
                
                Budget budget =
                        new Budget(
                                id,
                                budgetLama.getWalletId(),
                                //walletId,
                                Double.parseDouble(limitBaru)
                        );
                
                //BudgetDAO dao =
                        //new BudgetDAO();
                
                if (dao.updateBudget(budget)) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Budget berhasil diupdate"
                    );

                    dispose();
                    new BudgetView(user).setVisible(true);
                }
            
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Input tidak valid"
                );
            }
        
        });
        
        JButton btnHapus = new JButton("Hapus Budget");
        
        btnHapus.addActionListener(e -> {
            
            int row = table.getSelectedRow();
            
            if (row == -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Pilih budget terlebih dahulu"
                );
                return;
            }
            int id =
                    Integer.parseInt(
                            table.getValueAt(row, 0).toString()
                    );
            
            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Hapus budget ini?",
                            "Konfirmasi",
                            JOptionPane.YES_NO_OPTION
                    );
            
            if (confirm == JOptionPane.YES_OPTION) {
                
                BudgetDAO dao =
                        new BudgetDAO();
                
                if (dao.deleteBudget(id)) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Budget berhasil dihapus"
                    );

                    dispose();
                    new BudgetView(user).setVisible(true);
                }
            }
        });
        
        JPanel buttonPanel = new JPanel();
        
        buttonPanel.add(btnTambah);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnHapus);
        
        panel.add(title, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        
    }
}
