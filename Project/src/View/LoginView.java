package View;

import Controller.UserController;
import Model.User.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView extends JFrame {
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    
    public LoginView(){
        setTitle("Finance Tracker - Login");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 247, 251));
        
        JLabel title = new JLabel("Finance Tracker");
        title.setFont(new Font("Poppins", Font.BOLD, 24));
        title.setBounds(120, 30, 250, 40);
        
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(50, 90, 100, 25);
        
        txtUsername = new JTextField();
        txtUsername.setBounds(50, 115, 320, 35);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(50, 160, 100, 25);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(50, 185, 320, 35);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(50, 240, 150, 40);
        btnLogin.setBackground(new Color(91, 95, 239));
        btnLogin.setForeground(Color.WHITE);

        btnRegister = new JButton("Create Account");
        btnRegister.setBounds(220, 240, 150, 40);
        
        panel.add(title);
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnLogin);
        panel.add(btnRegister);

        add(panel);
        
        btnLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                login();
            }
        });
        
        btnRegister.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new RegisterView().setVisible(true);
                dispose();
            }
        });
    }
    
    private void login(){
        String username = txtUsername.getText();
        String password = String.valueOf(txtPassword.getPassword());
        
        UserController controller = new UserController();
        User user = controller.login(username, password);
        
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login Berhasil");
            new DashboardView(user).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username atau password salah");
        }
    }
}
