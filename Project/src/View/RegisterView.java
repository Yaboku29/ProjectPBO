package View;

import Controller.UserController;
import Model.User.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterView extends JFrame {
    
    private JTextField txtUsername;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnRegister;
    private JButton btnBack;
    
    public RegisterView(){
        
        setTitle("Create Account");
        setSize(450, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Create Account");
        title.setFont(new Font("Poppins", Font.BOLD, 24));
        title.setBounds(110, 30, 250, 40);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(50, 90, 100, 25);

        txtUsername = new JTextField();
        txtUsername.setBounds(50, 115, 320, 35);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(50, 160, 100, 25);

        txtEmail = new JTextField();
        txtEmail.setBounds(50, 185, 320, 35);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(50, 230, 100, 25);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(50, 255, 320, 35);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(50, 315, 150, 40);

        btnBack = new JButton("Back Login");
        btnBack.setBounds(220, 315, 150, 40);
        
        panel.add(title);
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnRegister);
        panel.add(btnBack);
        
        add(panel);
        
        btnRegister.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                register();
            }
        });
        
        btnBack.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new LoginView().setVisible(true);
                dispose();
            }
        });
    }
    
    private void register(){
        
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String password = String.valueOf(txtPassword.getPassword());

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        
        UserController controller = new UserController();
        
        if (controller.register(user)) {
            JOptionPane.showMessageDialog(this, "Register berhasil");
            new LoginView().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Register gagal");
        }
    }
}
