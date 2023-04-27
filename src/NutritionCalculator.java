import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class NutritionCalculator {
    public static void main(String[] args) {
            boolean action;

            //create cal(x), fat(y),carbs,salt in beginning 0
            float totalkcal = 0.0f;
            float totalprotein = 0.0f;
            float totalcarbs = 0.0f;
            float totalfat = 0.0f;
            float totalsalt = 0.0f;


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


            System.out.println("Product list: ");
            //Program prints out all items (food) to choose (from SQL)
            Database database = new Database();
            Database.printFoodList();

            //food LOOP
            do {
                //Interaction with SQL (food list print out)
                System.out.println("Please enter FOOD item name from list:");
                //SELECT * FROM (SQL)
                Database.inputFromUser();
                float kcalForItem = Database.calculateKcal();
                float proteinForItem = Database.calculateProtein();

                //Program asks to enter grams of item (Scanner)
                System.out.println("Please enter grams of item you ate:");
                float mass = scanner.nextFloat();

                //Calculate the index
                float index = mass / 10.0f;

                //Calculate calories, protein ect interacting with SQL
                float totalKcalForItem = kcalForItem * index;
                System.out.println("Kcal for Item: " + totalKcalForItem);

                float totalProteinForItem = proteinForItem * index;
                System.out.println("Protein for Item: " + totalProteinForItem);

                //Add calculated values to variables before LOOP

                //Program does calculations and asks for continue (y/n)
                System.out.println("Do you want to continue? (y/n)");
                char answer = scanner.next().charAt(0);
                action = (answer == 'y');

                totalkcal += totalKcalForItem;
                totalprotein += totalProteinForItem;


            } while (action);

            System.out.println("Total kcal: " + totalkcal);
            System.out.println("Total protein: " + totalprotein);

            System.out.println("Drinks list");
            Database.printDrinksList();

            boolean action1;
            //drink LOOP
            do {
                //Program prints out all items to choose (from SQL)
                //Interaction with SQL (food list print out)
                System.out.println("Please enter DRINK item name from list:");
                scanner.nextLine();
                String drinkItem = scanner.nextLine().toLowerCase();
                //SELECT * FROM (SQL)


                //Program asks to enter grams of item (Scanner)
                System.out.println("Please enter milliliters of item you ate:");
                float milliliters = scanner.nextFloat();

                //Calculate the index
                float index = milliliters / 10.0f;

                //Calculate calories, protein ect interacting with SQL


                //Add calculated values to variables before LOOP

                //Program does calculations and asks for continue (y/n)

                System.out.println("Do you want to continue? (y/n)");
                char answer1 = scanner.next().charAt(0);
                action1 = (answer1 == 'y');
            } while (action1);


            //Program prints out result and compares (if else in switch)

            //Suggestions


        }
    }
}