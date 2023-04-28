import java.sql.*;
import java.util.Scanner;
import java.sql.*;
import java.util.Scanner;

public class Database {
    private static String dbURL = "jdbc:mysql://localhost:3306/java35";
    private static String username = "root";
    private static String password = "Ezers123)";

    public static void printFoodList() {

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM food");
            while (rs.next()) {
                System.out.print(rs.getString(1) + "\n");
            }
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void printDrinksList() {

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM drinks");
            while (rs.next()) {
                System.out.print(rs.getString(1) + "\n");
            }
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public static float calculateNutritionFood(String nutrient, String foodItem) {

        float nutrientPer10Grams = 0;
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            String sql = "SELECT " + nutrient + " FROM food WHERE productName = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, foodItem.trim());
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                nutrientPer10Grams = rs.getFloat(nutrient);


            }
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return nutrientPer10Grams;

    }

    public static float calculateNutritionDrink(String nutrient, String drinkItem) {

        float nutrientPer10Milliliters = 0;
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            String sql = "SELECT " + nutrient + " FROM drinks WHERE drinkName = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, drinkItem.trim());
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                nutrientPer10Milliliters = rs.getFloat(nutrient);


            }
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return nutrientPer10Milliliters;
    }

}
