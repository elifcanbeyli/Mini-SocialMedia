/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programming;

import javax.swing.JOptionPane;

/**
 *
 * @author obali
 */
public class Programming {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Başlangıç kullanıcılarını ekle
        DatabaseManager dbManager = new DatabaseManager();
        
        // Varsayılan kullanıcıları ekle (eğer yoksa)
        dbManager.addUser("Onur", "Aa123456!");
        dbManager.addUser("Efkan", "Ee123456!");
        
        // Ana pencereyi başlat
        LaunchPage launchPage = new LaunchPage();
        
        // Veritabanı bağlantısını kapat
        dbManager.closeConnection();
    }
}
