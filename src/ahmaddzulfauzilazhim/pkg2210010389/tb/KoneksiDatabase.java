/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ahmaddzulfauzilazhim.pkg2210010389.tb;

/**
 *
 * @author Ahmad Dzul Fauzil Azhim
 */
import java.sql.*;

public class KoneksiDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/kepegawaian_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection sambungkan() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
