/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programming;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PasswordPage implements ActionListener {

    private DatabaseManager dbManager;
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Giriş");
    JButton resetButton = new JButton("Sıfırla");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("Kullanıcı Adı:");
    JLabel userPasswordLabel = new JLabel("Parola:");
    JLabel messageLabel = new JLabel();
    
    public PasswordPage(String userID, String password) {
        dbManager = new DatabaseManager();
        
        userIDLabel.setBounds(50, 100, 75, 25);
        userPasswordLabel.setBounds(50, 150, 75, 25);
        
        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));
        
        userIDField.setBounds(125, 100, 200, 25);
        userPasswordField.setBounds(125, 150, 200, 25);
        
        loginButton.setBounds(125, 200, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        
        resetButton.setBounds(225, 200, 100, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            userIDField.setText("");
            userPasswordField.setText("");
        }
        
        if (e.getSource() == loginButton) {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());
            
            if (dbManager.checkUser(userID, password)) {
                messageLabel.setForeground(Color.green);
                messageLabel.setText("Giriş başarılı!");
                dbManager.updateConnectedDate(userID);
                frame.dispose();
                NewWindow myWindow = new NewWindow(userID);
            } else {
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Giriş başarısız!");
            }
        }
    }
    
    public void closeConnection() {
        if (dbManager != null) {
            dbManager.closeConnection();
        }
    }
}
