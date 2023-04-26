import java.sql.*;
import java.util.Scanner;

public class Database {
public static void printFoodList(){
    String dbURL = "jdbc:mysql://localhost:3306/java35";
    String username = "root";
    String password = "Ezers123)";
    try (Connection conn = DriverManager.getConnection(dbURL,username,password)){
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM food");
        while (rs.next()){
            System.out.print(rs.getString(1) + "\n");
        }
        conn.close();

    } catch (Exception e){
        System.out.println(e);
    }

}
    public static void printDrinksList(){
        String dbURL = "jdbc:mysql://localhost:3306/java35";
        String username = "root";
        String password = "Ezers123)";
        try (Connection conn = DriverManager.getConnection(dbURL,username,password)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM drinks");
            while (rs.next()){
                System.out.print(rs.getString(1) + "\n");
            }
            conn.close();

        } catch (Exception e){
            System.out.println(e);
        }

    }

    public static void calculateKcal(){
        String dbURL = "jdbc:mysql://localhost:3306/java35";
        String username = "root";
        String password = "Ezers123)";
        try (Connection conn = DriverManager.getConnection(dbURL,username,password)){
            Scanner scanner = new Scanner(System.in);
            String foodItem = scanner.nextLine().toLowerCase();
            String sql = "SELECT kcal FROM food WHERE productName = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            String foodItems[] = {foodItem};
            stmt.setString(1,foodItem.trim());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int kcal = rs.getInt("kcal");
                System.out.println(kcal);
            }
            conn.close();

        } catch (Exception e){
            System.out.println(e);
        }
    }
}
