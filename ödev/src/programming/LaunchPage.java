package programming;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
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

public class LaunchPage implements ActionListener {
    
    private DatabaseManager dbManager;
    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Giriş");
    JButton resetButton = new JButton("Sıfırla");
    JButton singinButton = new JButton("Yeni kayıt");
    JTextField kullIDField = new JTextField();
    JPasswordField kullPasswordField = new JPasswordField();
    JLabel kullIDLabel = new JLabel("Kullanıcı Adı: ");
    JLabel kullPassLabel = new JLabel("Parola: ");
    JLabel messageLabel = new JLabel("Programming II");
    
    LaunchPage() {
        // Mesaj kutusu yazı boyutunu büyüt
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 24));
        UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.BOLD, 16));
        
        dbManager = new DatabaseManager();
        
        // Başlık etiketinin konumu
        messageLabel.setBounds(125, 30, 250, 35);
        messageLabel.setFont(new Font(null, Font.ITALIC, 25));
        
        // Kullanıcı adı etiketi ve alanı
        kullIDLabel.setBounds(50, 100, 100, 25);
        kullIDField.setBounds(150, 100, 200, 25);
        
        // Parola etiketi ve alanı
        kullPassLabel.setBounds(50, 150, 75, 25);
        kullPasswordField.setBounds(150, 150, 200, 25);
        
        // Butonların konumları
        loginButton.setBounds(100, 200, 100, 25);
        resetButton.setBounds(200, 200, 100, 25);
        singinButton.setBounds(300, 200, 100, 25);
        
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        singinButton.addActionListener(this);
        
        frame.add(kullIDLabel);
        frame.add(kullPassLabel);
        frame.add(messageLabel);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(singinButton);
        frame.add(kullIDField);
        frame.add(kullPasswordField);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            kullIDField.setText("");
            kullPasswordField.setText("");
        }
        
        if (e.getSource() == singinButton) {
            Signin signwindow = new Signin();
            JOptionPane.showMessageDialog(null, "Parolanız en az 8 karakter içermeli, " +
                    "bir büyük harf, bir küçük harf, " +
                    "bir rakam ve bir özel karakter içermelidir.", "Uyarı", JOptionPane.WARNING_MESSAGE);
        }
        
        if (e.getSource() == loginButton) {
            String user = kullIDField.getText();
            String password = String.valueOf(kullPasswordField.getPassword());
            
            if (dbManager.checkUser(user, password)) {
                // Özel mesaj kutusu oluştur
                JOptionPane pane = new JOptionPane(
                    "Canım hocam please 100",
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.DEFAULT_OPTION
                );
                
                // Mesaj kutusunu özelleştir
                JDialog dialog = pane.createDialog("Başarılı Giriş");
                
                // Mesaj kutusunun boyutunu ayarla
                dialog.setPreferredSize(new Dimension(400, 200));
                dialog.pack();
                dialog.setLocationRelativeTo(frame);
                dialog.setVisible(true);
                
                frame.dispose();
                NewWindow myWindow = new NewWindow(user);
            } else {
                messageLabel.setForeground(Color.red);
                if (user.isEmpty() || password.isEmpty()) {
                    messageLabel.setText("Kullanıcı adı ve parola gerekli!");
                } else {
                    messageLabel.setText("Giriş başarısız!");
                }
            }
        }
    }
    
    public void closeConnection() {
        if (dbManager != null) {
            dbManager.closeConnection();
        }
    }
}
