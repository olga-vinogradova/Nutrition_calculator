import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class NutritionCalculator {
    public static void main(String[] args) {
        char action = 'y';

        //create cal(x), fat(y),carbs,salt in beginning 0
        float kcal = 0.0f;
        float protein = 0.0f;
        float carbs = 0.0f;
        float fat = 0.0f;
        float salt = 0.0f;

        String dbURL = "jdbc:mysql://localhost:3306/java35";
        String username = "root";
        String password = "Transcom01!";

        //User inputs population group
        System.out.println("Please choose your population group (1,2,3)");
        System.out.println("1 - teenager (<18)");
        System.out.println("2 - adult (male)");
        System.out.println("3 - adult (female)");
        Scanner scanner = new Scanner(System.in);
        int ageGroup = scanner.nextInt();

        //User inputs weight
        System.out.println("Please enter your weight kg");
        int weight = scanner.nextInt();


        //Program prints out all items (food) to choose (from SQL)
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

        //food LOOP
        do {
            //Interaction with SQL (food list print out)
            System.out.println("Please enter item name from list:");
            String foodItem = scanner.nextLine();
            //SELECT * FROM (SQL)


            //Program asks to enter grams of item (Scanner)
            System.out.println("Please enter grams of item you ate:");
            float mass = scanner.nextFloat();

            //Calculate the index
            float index = mass/10.0f;

            //Calculate calories, protein ect interacting with SQL


            //Add calculated values to variables before LOOP

            //Program does calculations and asks for continue (y/n)
            System.out.println("Do you want to continue? (y/n)");
            action = scanner.nextLine().charAt(0);

        }while (action == 'n');



        //drink LOOP
        do {
            //Program prints out all items to choose (from SQL)
            //Interaction with SQL (food list print out)
            System.out.println("Please enter item name from list:");
            String drinkItem = scanner.nextLine();
            //SELECT * FROM (SQL)


            //Program asks to enter grams of item (Scanner)
            System.out.println("Please enter milliliters of item you ate:");
            float milliliters = scanner.nextFloat();

            //Calculate the index
            float index = milliliters/10.0f;

            //Calculate calories, protein ect interacting with SQL


            //Add calculated values to variables before LOOP

            //Program does calculations and asks for continue (y/n)
            System.out.println("Do you want to continue? (y/n)");
            action = scanner.nextLine().charAt(0);

        }while (action == 'n');


        //Program prints out result and compares (if else in switch)

        //Suggestions




    }
}
