package View;

import Controller.AuthController;

import Model.User.User;

import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {

    private JTextField usernameField;

    private JTextField namaField;

    private JTextField emailField;

    private JPasswordField passwordField;

    private JButton registerButton;

    private JButton backButton;

    private AuthController authController;

    public RegisterFrame(){

        authController =
                new AuthController();

        setTitle(
                "Register"
        );

        setSize(
                400,
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
                        6,
                        1,
                        10,
                        10
                )
        );

        // =========================
        // COMPONENT
        // =========================

        usernameField =
                new JTextField();

        namaField =
                new JTextField();

        emailField =
                new JTextField();

        passwordField =
                new JPasswordField();

        registerButton =
                new JButton(
                        "Register"
                );

        backButton =
                new JButton(
                        "Back"
                );

        // =========================
        // PANEL USERNAME
        // =========================

        JPanel usernamePanel =
                new JPanel(
                        new BorderLayout()
                );

        usernamePanel.add(
                new JLabel("Username"),
                BorderLayout.NORTH
        );

        usernamePanel.add(
                usernameField,
                BorderLayout.CENTER
        );

        // =========================
        // PANEL NAMA
        // =========================

        JPanel namaPanel =
                new JPanel(
                        new BorderLayout()
                );

        namaPanel.add(
                new JLabel("Nama"),
                BorderLayout.NORTH
        );

        namaPanel.add(
                namaField,
                BorderLayout.CENTER
        );

        // =========================
        // PANEL EMAIL
        // =========================

        JPanel emailPanel =
                new JPanel(
                        new BorderLayout()
                );

        emailPanel.add(
                new JLabel("Email"),
                BorderLayout.NORTH
        );

        emailPanel.add(
                emailField,
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
                new JLabel("Password"),
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
                registerButton
        );

        buttonPanel.add(
                backButton
        );

        // =========================
        // ADD COMPONENT
        // =========================

        add(usernamePanel);

        add(namaPanel);

        add(emailPanel);

        add(passwordPanel);

        add(buttonPanel);

        // =========================
        // REGISTER ACTION
        // =========================

        registerButton.addActionListener(

                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e
                    ){

                        register();

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

                        new LoginFrame();

                        dispose();

                    }

                }

        );

        setVisible(true);

    }

    // =========================
    // REGISTER METHOD
    // =========================

    private void register(){

        String username =
                usernameField.getText();

        String nama =
                namaField.getText();

        String email =
                emailField.getText();

        String password =
                String.valueOf(
                        passwordField.getPassword()
                );

        // VALIDASI SEDERHANA

        if(

                username.isEmpty()
                ||
                nama.isEmpty()
                ||
                email.isEmpty()
                ||
                password.isEmpty()

        ){

            JOptionPane.showMessageDialog(
                    this,
                    "Semua field wajib diisi"
            );

            return;

        }

        User user =
                new User(

                        0,

                        username,

                        nama,

                        email,

                        password

                );

        boolean success =
                authController
                .register(user);

        if(success){

            JOptionPane.showMessageDialog(
                    this,
                    "Register berhasil"
            );

            new LoginFrame();

            dispose();

        }

        else{

            JOptionPane.showMessageDialog(
                    this,
                    "Register gagal"
            );

        }

    }

}