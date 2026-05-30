package View;

import Helper.Session;

import Model.Wallet.*;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class WalletFrame extends JFrame {

    private JTable walletTable;

    private DefaultTableModel tableModel;

    private JButton tambahButton;

    private JButton deleteButton;

    private JButton backButton;

    private WalletDAO walletDAO;

    public WalletFrame(){

        walletDAO =
                new WalletDAO();

        setTitle(
                "Wallet"
        );

        setSize(
                700,
                400
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

        tableModel.addColumn("Nama");

        tableModel.addColumn("Saldo");

        walletTable =
                new JTable(
                        tableModel
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        walletTable
                );

        loadTable();

        // =========================
        // BUTTON
        // =========================

        tambahButton =
                new JButton(
                        "Tambah Wallet"
                );

        deleteButton =
                new JButton(
                        "Delete Wallet"
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
        // TAMBAH ACTION
        // =========================

        tambahButton.addActionListener(

                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ){

                        tambahWallet();

                    }

                }

        );

        // =========================
        // DELETE ACTION
        // =========================

        deleteButton.addActionListener(

                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ){

                        deleteWallet();

                    }

                }

        );

        // =========================
        // BACK ACTION
        // =========================

        backButton.addActionListener(

                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ){

                        new DashboardFrame();

                        dispose();

                    }

                }

        );

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

            Object[] row = {

                    wallet.getId(),

                    wallet.getNama(),

                    wallet.getSaldo()

            };

            tableModel.addRow(
                    row
            );

        }

    }

    // =========================
    // TAMBAH WALLET
    // =========================

    private void tambahWallet(){

        try{

            String nama =
                    JOptionPane.showInputDialog(
                            this,
                            "Nama Wallet"
                    );

            if(
                    nama == null
                    ||
                    nama.trim().isEmpty()
            ){

                return;

            }

            String saldoInput =
                    JOptionPane.showInputDialog(
                            this,
                            "Saldo Awal"
                    );

            double saldo =
                    Double.parseDouble(
                            saldoInput
                    );

            Wallet wallet =
                    new Wallet(

                            0,

                            Session.currentUser
                            .getId(),

                            nama,

                            saldo

                    );

            walletDAO.createWallet(
                    wallet
            );

            JOptionPane.showMessageDialog(

                    this,

                    "Wallet berhasil dibuat"

            );

            loadTable();

        }

        catch(NumberFormatException e){

            JOptionPane.showMessageDialog(

                    this,

                    "Saldo harus angka",

                    "Error",

                    JOptionPane.ERROR_MESSAGE

            );

        }

        catch(Exception e){

            JOptionPane.showMessageDialog(

                    this,

                    e.getMessage(),

                    "Error",

                    JOptionPane.ERROR_MESSAGE

            );

        }

    }

    // =========================
    // DELETE WALLET
    // =========================

    private void deleteWallet(){

        int selectedRow =
                walletTable.getSelectedRow();

        if(selectedRow == -1){

            JOptionPane.showMessageDialog(

                    this,

                    "Pilih wallet terlebih dahulu"

            );

            return;

        }

        int id = (int)
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

            walletDAO.deleteWallet(
                    id
            );

            JOptionPane.showMessageDialog(

                    this,

                    "Wallet berhasil dihapus"

            );

            loadTable();

        }

    }

}