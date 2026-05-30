package View;

import Controller.AuthController;

import Model.User.User;

import Helper.Session;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private JTextField usernameField;

    private JPasswordField passwordField;

    private JButton loginButton;

    private JButton registerButton;

    private AuthController authController;

    public LoginFrame(){

        authController =
                new AuthController();

        setTitle(
                "Login"
        );

        setSize(
                400,
                300
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
        // COMPONENT
        // =========================

        JLabel usernameLabel =
                new JLabel(
                        "Username"
                );

        usernameField =
                new JTextField();

        JLabel passwordLabel =
                new JLabel(
                        "Password"
                );

        passwordField =
                new JPasswordField();

        loginButton =
                new JButton(
                        "Login"
                );

        registerButton =
                new JButton(
                        "Register"
                );

        // =========================
        // PANEL USERNAME
        // =========================

        JPanel usernamePanel =
                new JPanel(
                        new BorderLayout()
                );

        usernamePanel.add(
                usernameLabel,
                BorderLayout.NORTH
        );

        usernamePanel.add(
                usernameField,
                BorderLayout.CENTER
        );

        // =========================
        // PANEL PASSWORD
        // =========================

        JPanel passwordPanel =
                new JPanel(
                        new BorderLayout()
                );

        passwordPanel.add(
                passwordLabel,
                BorderLayout.NORTH
        );

        passwordPanel.add(
                passwordField,
                BorderLayout.CENTER
        );

        // =========================
        // PANEL BUTTON
        // =========================

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(
                loginButton
        );

        buttonPanel.add(
                registerButton
        );

        // =========================
        // ADD COMPONENT
        // =========================

        add(
                usernamePanel
        );

        add(
                passwordPanel
        );

        add(
                buttonPanel
        );

        // =========================
        // LOGIN ACTION
        // =========================

        loginButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ){

                        login();

                    }

                }
        );

        // =========================
        // REGISTER ACTION
        // =========================

        registerButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ){

                        new RegisterFrame();

                        dispose();

                    }

                }
        );

        setVisible(true);

    }

    // =========================
    // LOGIN METHOD
    // =========================

    private void login(){

        try{

            String username =
                    usernameField
                    .getText()
                    .trim();

            String password =
                    String.valueOf(
                            passwordField
                            .getPassword()
                    ).trim();

            // =========================
            // VALIDASI KOSONG
            // =========================

            if(
                    username.isEmpty()
                    ||
                    password.isEmpty()
            ){

                JOptionPane.showMessageDialog(

                        this,

                        "Username dan Password wajib diisi",

                        "Error",

                        JOptionPane.ERROR_MESSAGE

                );

                return;

            }

            // =========================
            // LOGIN
            // =========================

            User user =
                    authController.login(
                            username,
                            password
                    );

            // =========================
            // LOGIN BERHASIL
            // =========================

            if(user != null){

                Session.currentUser =
                        user;

                JOptionPane.showMessageDialog(

                        this,

                        "Login berhasil",

                        "Success",

                        JOptionPane.INFORMATION_MESSAGE

                );

                new DashboardFrame();

                dispose();

            }

            // =========================
            // LOGIN GAGAL
            // =========================

            else{

                JOptionPane.showMessageDialog(

                        this,

                        "Username atau Password salah",

                        "Login Gagal",

                        JOptionPane.ERROR_MESSAGE

                );

            }

        }

        // =========================
        // ERROR TAK TERDUGA
        // =========================

        catch(Exception e){

            JOptionPane.showMessageDialog(

                    this,

                    "Terjadi error:\n"
                    +
                    e.getMessage(),

                    "System Error",

                    JOptionPane.ERROR_MESSAGE

            );

            e.printStackTrace();

        }

    }
}