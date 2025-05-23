/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programming;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author obali
 */
public class Signin implements ActionListener {
    
    private DatabaseManager dbManager;
    
    JFrame frame = new JFrame();
    JLabel label = new JLabel("Yeni Kullanıcı Ekleme Sayfası");
    JButton YeniKullanici = new JButton("Yeni kayıt");
    JTextField kullIDField = new JTextField();
    JPasswordField kullPasswordField = new JPasswordField();
    JLabel kullIDLabel = new JLabel("Kullanıcı Adı: ");
    JLabel kullPassLabel = new JLabel("Parola: ");
    JLabel messageLabel = new JLabel("Yeni kullanıcı girin");
    
    Signin() {
        // Mesaj kutusu yazı boyutunu büyüt
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 24));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 16));
        
        dbManager = new DatabaseManager();
        
        // Başlık etiketinin konumu
        label.setBounds(100, 30, 250, 35);
        label.setFont(new Font(null, Font.BOLD, 16));
        
        // Kullanıcı adı etiketi ve alanı
        kullIDLabel.setBounds(50, 100, 100, 25);
        kullIDField.setBounds(150, 100, 200, 25);
        
        // Parola etiketi ve alanı
        kullPassLabel.setBounds(50, 150, 75, 25);
        kullPasswordField.setBounds(150, 150, 200, 25);
        
        // Yeni kayıt butonu
        YeniKullanici.setBounds(150, 200, 100, 25);
        
        // Alt mesaj etiketi
        messageLabel.setBounds(125, 250, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));
        
        YeniKullanici.addActionListener(this);
        
        frame.add(label);
        frame.add(kullIDLabel);
        frame.add(kullPassLabel);
        frame.add(messageLabel);
        frame.add(YeniKullanici);
        frame.add(kullIDField);
        frame.add(kullPasswordField);
        
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == YeniKullanici) {
            String password = String.valueOf(kullPasswordField.getPassword());
            String username = kullIDField.getText();

            if (password.length() < 8) {
                JOptionPane.showMessageDialog(null, "Girilen parola en az 8 karakter olucaktır", "Uyarı", JOptionPane.WARNING_MESSAGE);
                kullPasswordField.setText("");
                kullIDField.setText("");
                return;
            }

            boolean hasUpper = false;
            boolean hasLower = false;
            boolean digit = false;
            boolean special = false;

            for (char ch : password.toCharArray()) {
                if (Character.isUpperCase(ch)) {
                    hasUpper = true;
                } else if (Character.isLowerCase(ch)) {
                    hasLower = true;
                } else if (Character.isDigit(ch)) {
                    digit = true;
                } else if (!Character.isLetterOrDigit(ch)) {
                    special = true;
                }
            }

            if (!(hasUpper && hasLower && digit && special)) {
                JOptionPane.showMessageDialog(null, "En az bir büyük, bir küçük harf, bir numara ve özel karakter olmalı", "Uyarı", JOptionPane.WARNING_MESSAGE);
                kullPasswordField.setText("");
                kullIDField.setText("");
                return;
            }

            if (hasUpper && hasLower) {
                if (dbManager.addUser(username, password)) {
                    JOptionPane.showMessageDialog(null, "Başarılı bir şekilde kaydınız yapılmıştır.", "Tebrikler", JOptionPane.PLAIN_MESSAGE);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Kullanıcı kaydı yapılamadı. Kullanıcı adı zaten kullanımda olabilir.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    // Frame kapatıldığında veritabanı bağlantısını kapat
    public void closeConnection() {
        if (dbManager != null) {
            dbManager.closeConnection();
        }
    }
}
