package View;

import Helper.Session;

import Model.Transaction.*;

import Controller.TransactionController;

import Model.Wallet.*;

import Model.Category.*;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import java.awt.*;

import java.time.LocalDate;

import java.util.ArrayList;

public class TransactionFrame extends JFrame {

    private JTable transactionTable;

    private DefaultTableModel tableModel;

    private JButton tambahButton;
    
    private JButton editButton;

    private JButton deleteButton;

    private JButton backButton;

    private TransactionDAO transactionDAO;

    private TransactionController transactionController;

    private WalletDAO walletDAO;

    private CategoryDAO categoryDAO;

    public TransactionFrame(){
        
        transactionDAO =
                new TransactionDAO();

        transactionController =
                new TransactionController();

        walletDAO =
                new WalletDAO();

        categoryDAO =
                new CategoryDAO();

        setTitle(
                "Transaction"
        );

        setSize(
                900,
                500
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(
                null
        );

        setLayout(
                new BorderLayout()
        );

        // =========================
        // TABLE
        // =========================

        tableModel =
                new DefaultTableModel();

        tableModel.addColumn("ID");

        tableModel.addColumn("Wallet");

        tableModel.addColumn("Category");

        tableModel.addColumn("Jumlah");

        tableModel.addColumn("Deskripsi");

        tableModel.addColumn("Tanggal");

        transactionTable =
                new JTable(
                        tableModel
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        transactionTable
                );

        loadTable();

        // =========================
        // BUTTON
        // =========================

        tambahButton =
                new JButton(
                        "Tambah Transaction"
                );

        deleteButton =
                new JButton(
                        "Delete Transaction"
                );
        
        editButton =
        new JButton(
                "Edit Transaction"
        );
        
        backButton =
                new JButton(
                        "Back"
                );
        
        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(
                tambahButton
        );
        buttonPanel.add(
                editButton
        );
        buttonPanel.add(
                deleteButton
        );

        buttonPanel.add(
                backButton
        );

        // =========================
        // ADD COMPONENT
        // =========================

        add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // ACTION TAMBAH
        // =========================

        tambahButton.addActionListener(e -> {

            tambahTransaction();

        });
        
        // =========================
        // ACTION EDIT
        // =========================
        
        editButton.addActionListener(e -> {

            editTransaction();

        });

        // =========================
        // ACTION DELETE
        // =========================

        deleteButton.addActionListener(e -> {

            deleteTransaction();

        });

        // =========================
        // ACTION BACK
        // =========================

        backButton.addActionListener(e -> {

            new DashboardFrame();

            dispose();

        });

        setVisible(true);

    }

    // =========================
    // LOAD TABLE
    // =========================

    private void loadTable(){

        tableModel.setRowCount(0);

        ArrayList<Wallet>
                wallets =
                walletDAO.getByUserId(

                        Session.currentUser
                        .getId()

                );

        for(Wallet wallet : wallets){

            ArrayList<Transaction>
                    transactions =
                    transactionDAO
                    .getByWalletId(
                            wallet.getId()
                    );

            for(Transaction transaction
                    : transactions){

                Category category =
                        categoryDAO
                        .getCategory(
                                transaction
                                .getCategoryId()
                        );

                Object[] row = {

                        transaction.getId(),

                        wallet.getNama(),

                        category.getNama(),

                        transaction.getJumlah(),

                        transaction.getDeskripsi(),

                        transaction.getTanggal()

                };

                tableModel.addRow(
                        row
                );

            }

        }

    }

    // =========================
    // TAMBAH TRANSACTION
    // =========================

    private void tambahTransaction(){

        try{

            // =========================
            // WALLET
            // =========================

            ArrayList<Wallet>
                    wallets =
                    walletDAO.getByUserId(

                            Session.currentUser
                            .getId()

                    );
            if(wallets.isEmpty()){

                JOptionPane.showMessageDialog(

                        this,

                        "Buat wallet terlebih dahulu"

                );

                return;

            }
            Wallet selectedWallet =
                    (Wallet)
                    JOptionPane.showInputDialog(

                            this,

                            "Pilih Wallet",

                            "Wallet",

                            JOptionPane.PLAIN_MESSAGE,

                            null,

                            wallets.toArray(),

                            wallets.get(0)

                    );

            if(selectedWallet == null){

                return;

            }

            // =========================
            // CATEGORY
            // =========================

            ArrayList<Category>
                    categories =
                    categoryDAO
                    .getAllCategory();
            if(categories.isEmpty()){

                JOptionPane.showMessageDialog(

                        this,

                        "Category belum tersedia"

                );

                return;

            }
            Category selectedCategory =
                    (Category)
                    JOptionPane.showInputDialog(

                            this,

                            "Pilih Category",

                            "Category",

                            JOptionPane.PLAIN_MESSAGE,

                            null,

                            categories.toArray(),

                            categories.get(0)

                    );

            if(selectedCategory == null){

                return;

            }

            // =========================
            // JUMLAH
            // =========================

            String jumlahInput =
                    JOptionPane.showInputDialog(

                            this,

                            "Jumlah"

                    );

            double jumlah =
                    Double.parseDouble(
                            jumlahInput
                    );

            // =========================
            // DESKRIPSI
            // =========================

            String deskripsi =
                    JOptionPane.showInputDialog(

                            this,

                            "Deskripsi"

                    );

            // =========================
            // OBJECT
            // =========================

            Transaction transaction =
                    new Transaction(

                            0,

                            selectedWallet.getId(),

                            selectedCategory.getId(),

                            jumlah,

                            deskripsi,

                            LocalDate.now()

                    );

            boolean success =
                    transactionController
                    .tambahTransaksi(transaction);

            if(success){

                JOptionPane.showMessageDialog(

                        this,

                        "Transaction berhasil"

                );

                loadTable();

            }

            else{

                JOptionPane.showMessageDialog(

                        this,

                        "Transaction gagal"

                );

            }

        }

        catch(NumberFormatException e){

            JOptionPane.showMessageDialog(

                    this,

                    "Jumlah harus angka"

            );

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(

                    this,

                    e.getMessage()

            );

        }

    }

    // =========================
    // DELETE TRANSACTION
    // =========================

    private void deleteTransaction(){

        int selectedRow =
                transactionTable
                .getSelectedRow();

        if(selectedRow == -1){

            JOptionPane.showMessageDialog(

                    this,

                    "Pilih transaction dahulu"

            );

            return;

        }

        int id =
                (int)
                tableModel.getValueAt(
                        selectedRow,
                        0
                );

        int confirm =
                JOptionPane.showConfirmDialog(

                        this,

                        "Yakin ingin menghapus?",

                        "Konfirmasi",

                        JOptionPane.YES_NO_OPTION

                );

        if(confirm == JOptionPane.YES_OPTION){

            boolean success =
                transactionController
                .deleteTransaction(id);

            if(success){

                JOptionPane.showMessageDialog(

                        this,

                        "Transaction berhasil dihapus"

                );

                loadTable();

            }

                else{

                JOptionPane.showMessageDialog(

                        this,

                        "Gagal menghapus transaction"

                );

            }

        }

    }
    private void editTransaction(){
        int selectedRow =
                transactionTable
                .getSelectedRow();

        if(selectedRow == -1){

            JOptionPane.showMessageDialog(

                    this,

                    "Pilih transaksi dahulu"

            );

            return;

        }

        int id =
                (int)
                tableModel.getValueAt(
                        selectedRow,
                        0
                );

        String deskripsiLama =
                (String)
                tableModel.getValueAt(
                        selectedRow,
                        4
                );

        String tanggalLama =
                tableModel
                .getValueAt(
                        selectedRow,
                        5
                )
                .toString();

        // ======================
        // INPUT BARU
        // ======================

        String deskripsiBaru =
                JOptionPane.showInputDialog(

                        this,

                        "Edit Deskripsi",

                        deskripsiLama

                );

        if(deskripsiBaru == null){

            return;

        }

        String tanggalBaru =
                JOptionPane.showInputDialog(

                        this,

                        "Edit Tanggal (YYYY-MM-DD)",

                        tanggalLama

                );

        if(tanggalBaru == null){

            return;

        }

        try{

            Transaction trx =
                    new Transaction();

            trx.setId(id);

            trx.setDeskripsi(
                    deskripsiBaru
            );

            trx.setTanggal(

                    LocalDate.parse(
                            tanggalBaru
                    )

            );

            boolean success =
                    transactionController.updateTransaction(
                            trx
                    );

            if(success){

                JOptionPane.showMessageDialog(

                        this,

                        "Transaction berhasil diupdate"

                );

                loadTable();

            }

            else{

                JOptionPane.showMessageDialog(

                        this,

                        "Update gagal"

                );

            }

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(

                    this,

                    "Format tanggal salah"

            );

        }

    }
}