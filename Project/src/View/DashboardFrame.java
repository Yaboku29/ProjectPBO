package View;

import Helper.Session;

import Model.Wallet.*;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class DashboardFrame extends JFrame {

    private JLabel welcomeLabel;

    private JLabel saldoLabel;

    private JButton walletButton;

    private JButton transactionButton;
    
    private JButton reportButton;
    
    private JButton logoutButton;

    private WalletDAO walletDAO;

    public DashboardFrame(){

        walletDAO =
                new WalletDAO();

        setTitle(
                "Dashboard"
        );

        setSize(
                500,
                400
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(
                null
        );

        setLayout(
                new GridLayout(
                        5,
                        1,
                        10,
                        10
                )
        );

        // =========================
        // WELCOME
        // =========================

        welcomeLabel =
                new JLabel(

                        "Welcome, "
                        +
                        Session.currentUser
                        .getNama(),

                        SwingConstants.CENTER

                );

        // =========================
        // TOTAL SALDO
        // =========================

        saldoLabel =
                new JLabel(

                        "Total Saldo: Rp "
                        +
                        String.format("%,.0f",hitungTotalSaldo()),

                        SwingConstants.CENTER

                );

        // =========================
        // BUTTON
        // =========================

        walletButton =
                new JButton(
                        "Wallet"
                );

        transactionButton =
                new JButton(
                        "Transaction"
                );
        
        reportButton =
            new JButton(
                    "Daily Report"
            );
        
        logoutButton =
                new JButton(
                        "Logout"
                );

        // =========================
        // ADD COMPONENT
        // =========================

        add(
                welcomeLabel
        );

        add(
                saldoLabel
        );

        add(
                walletButton
        );

        add(
                transactionButton
        );
        
        add(
                reportButton
        );
        
        add(
                logoutButton
        );

        // =========================
        // WALLET ACTION
        // =========================

        walletButton.addActionListener(

                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ){

                        new WalletFrame();

                        dispose();

                    }

                }

        );

        // =========================
        // TRANSACTION ACTION
        // =========================

        transactionButton.addActionListener(

                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ){

                        new TransactionFrame();

                        dispose();

                    }

                }

        );
        
        // =========================
        // DAILY REPORT
        // =========================
        
        reportButton
        .addActionListener(e -> {

            new ReportFrame();

        });
        
        // =========================
        // LOGOUT ACTION
        // =========================

        logoutButton.addActionListener(

                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ){

                        Session.currentUser =
                                null;

                        new LoginFrame();

                        dispose();

                    }

                }

        );

        setVisible(true);

    }

    // =========================
    // HITUNG TOTAL SALDO
    // =========================

    private double hitungTotalSaldo(){

        double total = 0;

        ArrayList<Wallet>
                wallets =
                walletDAO.getByUserId(

                        Session.currentUser
                        .getId()

                );

        for(Wallet wallet : wallets){

            total +=
                    wallet.getSaldo();

        }

        return total;

    }

}