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
        System.out.println("1 - person <18 years old");
        System.out.println("2 - adult (male)");
        System.out.println("3 - adult (female)");
        Scanner scanner = new Scanner(System.in);
        int ageGroup = scanner.nextInt();

        //User inputs weight
        System.out.println("Please enter your weight kg");
        int weight = scanner.nextInt();


        //Program prints out all items (food) to choose (from SQL)
        System.out.println("Product list: " + "\n");
        Database database = new Database();
        Database.printFoodList();

        //food LOOP

        do {
            //Interaction with SQL (food list print out)
            System.out.println("Please enter FOOD item name from list:");
            //SELECT * FROM (SQL)
            scanner.nextLine();
            String foodItem = scanner.nextLine().toLowerCase();
            float kcalForItem = Database.calculateNutritionFood("kcal", foodItem);
            float proteinForItem = Database.calculateNutritionFood("protein", foodItem);
            float carbsForItem = Database.calculateNutritionFood("carbs", foodItem);
            float fatsForItem = Database.calculateNutritionFood("fat", foodItem);
            float saltForItem = Database.calculateNutritionFood("salt", foodItem);


            //Program asks to enter grams of item (Scanner)
            System.out.println("Please enter grams of item you ate:");
            float mass = scanner.nextFloat();

            //Calculate the index
            float index = mass / 10.0f;

            //Calculate calories, protein ect interacting with SQL
            float totalKcalForItem = kcalForItem * index;
            System.out.printf("Kcal for Item: %.2f\n", totalKcalForItem);

            float totalProteinForItem = proteinForItem * index;
            System.out.println("Protein for Item: " + totalProteinForItem);

            float totalCarbsForItem = carbsForItem * index;
            System.out.println("Carbs for Item: " + totalCarbsForItem);

            float totalFatsForItem = fatsForItem * index;
            System.out.println("Fats for Item: " + totalFatsForItem);

            float totalSaltForItem = saltForItem * index;
            System.out.println("Salt for Item: " + totalSaltForItem);


            //Program does calculations and asks for continue (y/n)
            System.out.println("Do you want to continue? (y/n)");
            char answer = scanner.next().charAt(0);
            action = (answer == 'y');

            totalkcal += totalKcalForItem;
            totalprotein += totalProteinForItem;
            totalcarbs += totalCarbsForItem;
            totalfat += totalFatsForItem;
            totalsalt += totalSaltForItem;


        } while (action);


        //drink LOOP

        System.out.println("Drinks list:" + "\n");
        Database.printDrinksList();


        boolean action1;


        do {
            //Interaction with SQL (food list print out)
            System.out.println("Please enter DRINK item name from list:");
            //SELECT * FROM (SQL)
            scanner.nextLine();
            String drinkItem = scanner.nextLine().toLowerCase();
            float kcalForDrink = Database.calculateNutritionDrink("kcal", drinkItem);
            float proteinForDrink = Database.calculateNutritionDrink("protein", drinkItem);
            float carbsForDrink = Database.calculateNutritionDrink("carbs", drinkItem);
            float fatsForDrink = Database.calculateNutritionDrink("fat", drinkItem);
            float saltForDrink = Database.calculateNutritionDrink("salt", drinkItem);


            //Program asks to enter grams of item (Scanner)
            System.out.println("Please enter milliliters of item you drank:");
            float volume = scanner.nextFloat();

            //Calculate the index
            float index = volume / 10.0f;

            //Calculate calories, protein ect interacting with SQL
            float totalKcalForDrink = kcalForDrink * index;
            System.out.println("Kcal for Drink: " + totalKcalForDrink);

            float totalProteinForDrink = proteinForDrink * index;
            System.out.println("Protein for Drink: " + totalProteinForDrink);

            float totalCarbsForDrink = carbsForDrink * index;
            System.out.println("Carbs for Drink: " + totalCarbsForDrink);

            float totalFatsForDrink = fatsForDrink * index;
            System.out.println("Fats for Drink: " + totalFatsForDrink);

            float totalSaltForDrink = saltForDrink * index;
            System.out.println("Salt for Drink: " + totalSaltForDrink);


            //Program does calculations and asks for continue (y/n)
            System.out.println("Do you want to continue? (y/n)");
            char answer = scanner.next().charAt(0);
            action1 = (answer == 'y');

            totalkcal += totalKcalForDrink;
            totalprotein += totalProteinForDrink;
            totalcarbs += totalCarbsForDrink;
            totalfat += totalFatsForDrink;
            totalsalt += totalSaltForDrink;


        } while (action1);

        System.out.println("Total kcal: " + totalkcal);
        System.out.println("Total protein: " + totalprotein);
        System.out.println("Total carbs: " + totalcarbs);
        System.out.println("Total fat: " + totalfat);
        System.out.println("Total salt: " + totalsalt);

// Comparing
            if(ageGroup == 1){
                validationForCalories(2200, 1800, totalkcal);
                validationForProteins(0.5f, weight, totalprotein);
                validationForCarbs(200, totalcarbs);
                validationForFats(60, totalfat);
                validationForSalt(totalsalt);
            }else if(ageGroup == 2){
                validationForCalories(2200, 2000, totalkcal);
                validationForProteins(0.8f, weight, totalprotein);
                validationForCarbs(300, totalcarbs);
                validationForFats(80, totalfat);
                validationForSalt(totalsalt);
            }else{
                validationForCalories(2800,2600, totalkcal);
                validationForProteins(0.8f, weight, totalprotein);
                validationForCarbs(400, totalcarbs);
                validationForFats(100, totalfat);
                validationForSalt(totalsalt);
            }

        }

            public static void validationForCalories(int maxNorm, int minNorm, float totalkcal){
                // Validation for calories

                    if (totalkcal > maxNorm) {
                        System.out.println("Your calorie intake is higher than daily norm!");
                    } else if (totalkcal <= minNorm) {
                        System.out.println("Your calorie intake is less than daily norm!");
                    } else {
                        System.out.println("Your calorie intake is in daily norm!");
                    }

    }
    public static void validationForProteins(float index, int weight, float totalProtein ){
        // Validation for calories

        if (totalProtein > index * weight) {
            System.out.println("Your protein intake is higher than daily norm!");
        } else if (totalProtein < index * weight ){
            System.out.println("Your protein intake is less than daily norm!");
        } else {
            System.out.println("Your protein intake is in daily norm!");
        }

    }
    public static void validationForCarbs(int norm, float totalCarbs){
        // Validation for calories

        if (totalCarbs > norm) {
            System.out.println("Your carbs intake is higher than daily norm!");
        } else if (totalCarbs < norm) {
            System.out.println("Your carbs intake is less than daily norm!");
        } else {
            System.out.println("Your carbs intake is in daily norm!");
        }

    }
    public static void validationForFats(int norm, float totalFats){
        // Validation for calories

        if (totalFats > norm) {
            System.out.println("Your fats intake is higher than daily norm!");
        } else if (totalFats < norm) {
            System.out.println("Your fats intake is less than daily norm!");
        } else {
            System.out.println("Your fats intake is in daily norm!");
        }

    }
    public static void validationForSalt(float totalSalt){
        // Validation for calories

        if (totalSalt > 2.4) {
            System.out.println("Your salt intake is higher than daily norm!");
        } else if (totalSalt < 2.4) {
            System.out.println("Your salt intake is less than daily norm!");
        } else {
            System.out.println("Your salt intake is in daily norm!");
        }

    }
            //Suggestions



    }
