package programming;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:users.db";
    private Connection connection;
    
    public DatabaseManager() {
        try {
            // SQLite JDBC sürücüsünü yükle
            Class.forName("org.sqlite.JDBC");
            createTable();
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC sürücüsü bulunamadı: " + e.getMessage());
        }
    }
    
    private void createTable() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                        "id INTEGER PRIMARY KEY 4AUTOINCREMENT," +
                        "username TEXT UNIQUE NOT NULL," +
                        "password TEXT NOT NULL" +
                        "connectedDate DATE NULL)";
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Veritabanı oluşturma hatası: " + e.getMessage());
        }
    }
    
    public boolean addUser(String username, String password) {
        try {
            // Eğer kullanıcı zaten varsa ekleme
            if (checkUser(username, password)) {
                return true;
            }
            
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Kullanıcı ekleme hatası: " + e.getMessage());
            return false;
        }
    }
    
    public boolean checkUser(String username, String password) {
        try {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Eğer kullanıcı varsa true döner
        } catch (SQLException e) {
            System.out.println("Kullanıcı kontrol hatası: " + e.getMessage());
            return false;
        }
    }

    //Db de bu username a ait kullanıcıyı bul ve giriş tarihini şuan yap
    public  boolean updateConnectedDate(String username){
        try {
            String sql = " UPDATE users SET connectedDate = CURRENT_DATE WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Kullanıcı düzenleme hatası: " + e.getMessage());
            return false;
        }
    }

    // Giriş tarihi boş olmayan bütün kullanıcıları getir
    public List<String> getUsersWithConnectedDate() {
        List<String> users = new ArrayList<>();
        try {
            String sql = "SELECT username FROM users WHERE connectedDate IS NOT NULL";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                users.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            System.out.println("Kullanıcı listeleme hatası: " + e.getMessage());
        }
        return users;
    }



    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Bağlantı kapatma hatası: " + e.getMessage());
        }
    }
} 