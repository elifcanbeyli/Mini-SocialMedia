package programming;

import programming.DatabaseManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ConnectedUsers {

    private DatabaseManager dbManager;
    private JFrame frame;
    private JLabel label;

    public ConnectedUsers() {
        dbManager = new DatabaseManager();

        // Veritabanından kullanıcıları al
        List<String> users = dbManager.getUsersWithConnectedDate();

        frame = new JFrame("Başarılı Girişler");

        // Kullanıcıları liste olarak ekle
        int yPosition = 50;  // Başlangıç y konumu
        for (String user : users) {
            JLabel userLabel = new JLabel(user);  // Kullanıcı adını içeren JLabel oluştur
            userLabel.setBounds(50, yPosition, 300, 30);  // Y konumunu her seferinde artır
            userLabel.setFont(new Font(null, Font.PLAIN, 18));
            frame.add(userLabel);
            yPosition += 35;  // Sonraki kullanıcı için y konumunu arttır
        }

        // Frame ayarları
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);  // Frame boyutu
        frame.setLayout(null);  // Layoutsuz
        frame.setVisible(true);  // Ekranda göster
    }

    public static void main(String[] args) {
        new ConnectedUsers();
    }
}
