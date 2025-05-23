package programming;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author obali
 */
public class NewWindow {
    
    private DatabaseManager dbManager;
    JFrame frame = new JFrame();
    JLabel label;
    
    NewWindow(String username) {
        dbManager = new DatabaseManager();
        
        // Hoş geldiniz mesajı
        label = new JLabel("Hoş geldiniz " + username + "! Başarılı giriş sağladınız.");
        label.setBounds(0, 0, 500, 150);
        label.setFont(new Font(null, Font.PLAIN, 25));
        
        frame.add(label);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public void closeConnection() {
        if (dbManager != null) {
            dbManager.closeConnection();
        }
    }
}
