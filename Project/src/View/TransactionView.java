package View;

import Model.User.User;
import Model.Transaction.Transaction;
import Model.Transaction.TransactionDAO;
import Model.Wallet.Wallet;
import Model.Wallet.WalletDAO;
import Model.Category.Category;
import Model.Category.CategoryDAO;
import Model.Budget.BudgetDAO;
import Controller.TransactionController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class TransactionView extends JFrame {

    private User user;

    private JTable table;
    private DefaultTableModel model;

    public TransactionView(User user) {

        this.user = user;

        setTitle("Transaction Management");
        setSize(900, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel(
                "Transaction Management",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        24
                )
        );

        model = new DefaultTableModel(
                new String[]{
                        "ID",
                        "Wallet",
                        "Kategori",
                        
                        "Jumlah",
                        "Deskripsi",
                        "Tanggal"
                },
                0
        );

        table = new JTable(model);
        
        table.getColumnModel().getColumn(5).setPreferredWidth(200);

        JButton btnTambah =
                new JButton("Tambah Transaksi");
        JButton btnEdit =
                new JButton("Edit Transaksi");
        JButton btnHapus =
                new JButton("Hapus Transaksi");
        
        JPanel buttonPanel = new JPanel();
        
        buttonPanel.add(btnTambah);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnHapus);
        
        panel.add(
                title,
                BorderLayout.NORTH
        );

        panel.add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        panel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(panel);

        loadTransaction();

        btnTambah.addActionListener(e -> {
            try {
                WalletDAO walletDAO =
                        new WalletDAO();
                
                CategoryDAO categoryDAO =
                        new CategoryDAO();
                
                BudgetDAO budgetDAO =
                        new BudgetDAO();
                
                ArrayList<Wallet> wallets =
                        walletDAO.getByUserId(
                                user.getId()
                        );
                
                if (wallets.isEmpty()) {
                    
                    JOptionPane.showMessageDialog(
                            this,
                            "Buat wallet terlebih dahulu"
                    );
                    
                    return;
                }
                
                ArrayList<Category> categories =
                        categoryDAO.getAllCategory();
                
                if (categories.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Kategori belum tersedia"
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
                        (String) JOptionPane.showInputDialog(
                                this,
                                "Pilih Wallet",
                                "Wallet",
                                JOptionPane.PLAIN_MESSAGE, null,
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
                            )
                            ) {
                        
                        walletDipilih = w;
                        break;
                    
                    }
                }
                
                String[] categoryNames =
                        new String[categories.size()];
                
                for (int i = 0; i < categories.size(); i++) {
                    
                    categoryNames[i] = 
                            categories.get(i).getNama();
                
                }
                
                String selectedCategory =
                        (String) JOptionPane.showInputDialog(
                                this,
                                "Pilih Kategori",
                                "Kategori",
                                JOptionPane.PLAIN_MESSAGE, null,
                                categoryNames,
                                categoryNames[0]
                        );
                
                if (selectedCategory == null) {
                    return;
                }
                
                Category kategoriDipilih = null;
                
                for (Category c : categories) {
                    
                    if (
                            c.getNama().equals(
                                    selectedCategory
                            )
                            
                            ) {
                        kategoriDipilih = c;
                        break;
                    }
                
                }

                String jumlahStr =
                        JOptionPane.showInputDialog(
                                this,
                                "Masukkan Jumlah"
                        );
                
                if (
                        jumlahStr == null
                        ||
                        jumlahStr.isEmpty()
                        ) {
                    
                    return;
                
                }
                
                double jumlah =
                        Double.parseDouble(
                                jumlahStr
                        );
                
                String deskripsi =
                        JOptionPane.showInputDialog(
                                this,
                                "Deskripsi"
                        );
                
                Transaction trx =
                        new Transaction(
                                0,
                                walletDipilih.getId(),
                                kategoriDipilih.getId(),
                                
                                jumlah,
                                deskripsi,
                                LocalDate.now()
                        );
                
                TransactionController controller =
                        new TransactionController(
                                walletDAO,
                                categoryDAO,
                                budgetDAO
                        );
                
                controller.tambahTransaksi(
                        trx
                );
                
                loadTransaction();
                
                JOptionPane.showMessageDialog(
                        this,
                        "Transaksi berhasil ditambahkan"
                );
            
            } catch (Exception ex) {
                ex.printStackTrace();
                
                JOptionPane.showMessageDialog(
                        this,
                        "Error : " + ex.getMessage()
                );
            
            }
        });
        
        btnEdit.addActionListener(e -> {
            
            int row =
                    table.getSelectedRow();
            
            if(row == -1){
                JOptionPane.showMessageDialog(
                        this,
                        "Pilih transaksi terlebih dahulu"
                );
                
                return;
            }
            
            try{
                
                int transactionId =
                        Integer.parseInt(
                                table.getValueAt(row,0).toString()
                        );
                //int walletId =
                        //Integer.parseInt(
                                //table.getValueAt(row,1).toString()
                        //);
                
                TransactionDAO trxDAO =
                        new TransactionDAO();
                
                Transaction trxLama =
                        trxDAO.getTransaction(
                                transactionId
                        );
                
                CategoryDAO categoryDAO =
                        new CategoryDAO();
                
                ArrayList<Category> categories =
                        categoryDAO.getAllCategory();
                
                String[] categoryNames =
                        new String[categories.size()];
                
                for(int i=0;i<categories.size();i++){
                    
                    categoryNames[i] = categories.get(i).getNama();
                
                }
                
                String selectedCategory =
                        (String)
                        
                        JOptionPane.showInputDialog(
                                this,
                                "Pilih Kategori",
                                "Edit Transaksi",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                categoryNames,
                                categoryNames[0]
                        );
                
                if(selectedCategory == null)
                    return;
                
                Category kategoriDipilih =
                        null;
                
                for(Category c : categories){
                    
                    if(
                            c.getNama().equals(selectedCategory)
                            
                            ){
                        kategoriDipilih = c;
                        break;
                    
                    }
                
                }
                
                String jumlahBaru =
                        
                        JOptionPane.showInputDialog(
                                this,
                                "Jumlah Baru",
                                trxLama.getJumlah()
                        );
                
                if(jumlahBaru == null
                        ||
                        jumlahBaru.trim().isEmpty()){
                    return;
                }
                
                String deskripsiBaru =
                        JOptionPane.showInputDialog(
                                this,
                                "Deskripsi Baru",
                                trxLama.getDeskripsi()
                        );
                if(deskripsiBaru == null){
                    return;
                }
                
//                Transaction trxBaru =
//                        new Transaction(
//                                transactionId,
//                                walletId,
//                                kategoriDipilih.getId(),
//                                kategoriDipilih.getJenis().toString(),
//                                Double.parseDouble(
//                                        jumlahBaru),
//                                deskripsiBaru,
//                                trxLama.getTanggal()
//                        );
                
                Transaction trxBaru =
                        new Transaction(
                                transactionId,
                                trxLama.getWalletId(),
                                kategoriDipilih.getId(),
                                
                                Double.parseDouble(
                                        jumlahBaru),
                                deskripsiBaru,
                                trxLama.getTanggal()
                        );

                WalletDAO walletDAO =
                        new WalletDAO();

                BudgetDAO budgetDAO =
                        new BudgetDAO();

                TransactionController controller =
                        new TransactionController(
                                walletDAO,
                                categoryDAO,
                                budgetDAO
                        );

                controller.updateTransaction(
                        trxBaru
                );

                loadTransaction();

                JOptionPane.showMessageDialog(
                        this,
                        "Transaksi berhasil diupdate"
                );


            }catch(Exception ex){
                ex.printStackTrace();

                JOptionPane.showMessageDialog(
                        this,
                        "Error: " + ex.getMessage()
                );

            }
        
        });
        
        
        btnHapus.addActionListener(e -> {

            int row =
                    table.getSelectedRow();

            if(row == -1){

                JOptionPane.showMessageDialog(
                        this,
                        "Pilih transaksi terlebih dahulu"
                );
                return;
            
            }

            int id =
                    Integer.parseInt(
                            table.getValueAt(row,0).toString()
                    );

            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Hapus transaksi ini?",
                            "Konfirmasi",
                            JOptionPane.YES_NO_OPTION
                    );

            if(confirm == JOptionPane.YES_OPTION){

                WalletDAO walletDAO =
                        new WalletDAO();

                CategoryDAO categoryDAO =
                        new CategoryDAO();

                BudgetDAO budgetDAO =
                        new BudgetDAO();

                TransactionController controller =
                        new TransactionController(
                                walletDAO,
                                categoryDAO,
                                budgetDAO
                        );

                controller.deleteTransaction(id);

                loadTransaction();

                JOptionPane.showMessageDialog(
                        this,
                        "Transaksi berhasil dihapus"
                );

            }
        
        });
        JButton btnKembali = new JButton("Kembali");
        buttonPanel.add(btnKembali);
        
        btnKembali.addActionListener(e -> {

        new DashboardView(user)
                .setVisible(true);

        dispose();

        });
    
    }

    private void loadTransaction() {

        model.setRowCount(0);

        WalletDAO walletDAO =
                new WalletDAO();
        
        CategoryDAO categoryDAO = 
                new CategoryDAO();

        TransactionDAO transactionDAO =
                new TransactionDAO();

        ArrayList<Wallet> wallets =
                walletDAO.getByUserId(
                        user.getId()
                );

        for (Wallet wallet : wallets) {

            ArrayList<Transaction> transaksi =
                    transactionDAO.getByWalletId(
                            wallet.getId()
                    );

            for (Transaction trx : transaksi) {
                
                String namaKategori = 
                        categoryDAO.getCategory(
                                trx.getCategoryId()
                        ).getNama();

                model.addRow(
                        new Object[]{
                                trx.getId(),
                                wallet.getNama(),
                                namaKategori,
                                //trx.getWalletId(),
                                //trx.getCategoryId(),
//                                trx.getJenis(),
                                trx.getJumlah(),
                                trx.getDeskripsi(),
                                trx.getTanggal()
                        }
                );

            }

        }

    }

}