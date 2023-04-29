import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NutritionCalculator {
    public static void main(String[] args) {
        int ageGroup;
        int weight = 0;
        boolean action;
        boolean action1;
        float totalkcal = 0.0f;
        float totalprotein = 0.0f;
        float totalcarbs = 0.0f;
        float totalfat = 0.0f;
        float totalsalt = 0.0f;
        Scanner scanner = new Scanner(System.in);

        //User inputs population group. Program checks, if input is valid.
        while (true) {
            System.out.println("Please choose your population group (1, 2 or 3):");
            System.out.println("1 - person <18 years old");
            System.out.println("2 - adult (male)");
            System.out.println("3 - adult (female)");
            ageGroup = scanner.nextInt();
            if (ageGroup == 1 || ageGroup == 2 || ageGroup == 3) {
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
        System.out.println();

        //User inputs weight. Program checks, if input is valid.
        while (weight <= 0) {
            System.out.println("Please enter your weight in kilograms (rounded to the nearest kg without a decimal point): ");
            if (scanner.hasNextInt()) {
                weight = scanner.nextInt();
                if (weight <= 0) {
                    System.out.println("Invalid weight. Weight should be greater than zero.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // clears the invalid input from the scanner
            }
        }
        System.out.println();

        //Program prints out all items (food) to choose (from SQL)
        System.out.println("PRODUCT LIST:");
        Database.printFoodList();
        System.out.println();

        //food LOOP
        List<String> products = Arrays.asList("avocados", "bacon", "beef steak", "bread", "broccoli", "buckwheat", "butter",
                "carrots", "cereal", "chicken breast", "gouda cheese", "meat lasagna", "meatballs", "oatmeal",
                "omelet", "pork chop", "potatoes", "rice", "salmon", "yogurt");
        do {
            System.out.println("Please enter exact item name from the Product list:");
            scanner.nextLine();
            String foodItem = scanner.nextLine().toLowerCase().trim();
            if (!products.contains(foodItem)) {
                System.out.println("Invalid input. Amount will not be calculated. When prompted again, please enter valid item name from the list.");
            } else {
                System.out.println("You have entered a valid item. Amount will be calculated.");
            }

            float kcalForItem = Database.calculateNutritionFood("kcal", foodItem);
            float proteinForItem = Database.calculateNutritionFood("protein", foodItem);
            float carbsForItem = Database.calculateNutritionFood("carbs", foodItem);
            float fatsForItem = Database.calculateNutritionFood("fat", foodItem);
            float saltForItem = Database.calculateNutritionFood("salt", foodItem);
            System.out.println();

            //Program asks to enter grams of item (Scanner)
            System.out.println("Please enter item amount in grams:");
            float mass = scanner.nextFloat();
            System.out.println();

            //Calculate the index
            float index = mass / 10.0f;

            System.out.printf("%.2f grams of %s contains:\n", mass, foodItem);

            //Calculate calories, protein. etc. interacting with SQL
            float totalKcalForItem = kcalForItem * index;
            System.out.printf("Kcal: %.2f\n", totalKcalForItem);

            float totalProteinForItem = proteinForItem * index;
            System.out.printf("Protein: %.2f\n", totalProteinForItem);

            float totalCarbsForItem = carbsForItem * index;
            System.out.printf("Carbs: %.2f\n", totalCarbsForItem);

            float totalFatsForItem = fatsForItem * index;
            System.out.printf("Fats: %.2f\n", totalFatsForItem);

            float totalSaltForItem = saltForItem * index;
            System.out.printf("Salt: %.4f\n", totalSaltForItem);
            System.out.println();

            //Program does calculations and asks if user wants to enter more items (y/n)
            System.out.println("Do you want to add more products from the list? (y/n)");
            char answer = scanner.next().charAt(0);
            action = (answer == 'y');

            totalkcal += totalKcalForItem;
            totalprotein += totalProteinForItem;
            totalcarbs += totalCarbsForItem;
            totalfat += totalFatsForItem;
            totalsalt += totalSaltForItem;

        } while (action);
        System.out.println();

        //Program prints out all items (drinks) to choose (from SQL)
        System.out.println("DRINKS LIST:");
        Database.printDrinksList();
        System.out.println();

        //drink LOOP
        List<String> drinks = Arrays.asList("apple juice", "beer", "coca cola", "coffee", "milk", "orange juice", "red wine", "soft drink", "tea", "water");
        do {
            System.out.println("Please enter exact item name from the Drinks list:");
            scanner.nextLine();
            String drinkItem = scanner.nextLine().toLowerCase().trim();
            if (!drinks.contains(drinkItem)) {
                System.out.println("Invalid input. Amount will not be calculated. When prompted again, please enter valid item name from the list.");
            } else {
                System.out.println("You have entered a valid item. Amount will be calculated.");
            }

            float kcalForDrink = Database.calculateNutritionDrink("kcal", drinkItem);
            float proteinForDrink = Database.calculateNutritionDrink("protein", drinkItem);
            float carbsForDrink = Database.calculateNutritionDrink("carbs", drinkItem);
            float fatsForDrink = Database.calculateNutritionDrink("fat", drinkItem);
            float saltForDrink = Database.calculateNutritionDrink("salt", drinkItem);
            System.out.println();

            System.out.println("Please enter item amount in milliliters:");
            float volume = scanner.nextFloat();
            System.out.println();

            float index = volume / 10.0f;

            System.out.printf("%.2f milliliters of %s contains:\n", volume, drinkItem);

            float totalKcalForDrink = kcalForDrink * index;
            System.out.printf("Kcal: %.2f\n", totalKcalForDrink);

            float totalProteinForDrink = proteinForDrink * index;
            System.out.printf("Protein: %.2f\n", totalProteinForDrink);

            float totalCarbsForDrink = carbsForDrink * index;
            System.out.printf("Carbs: %.2f\n", totalCarbsForDrink);

            float totalFatsForDrink = fatsForDrink * index;
            System.out.printf("Fats: %.2f\n", totalFatsForDrink);

            float totalSaltForDrink = saltForDrink * index;
            System.out.printf("Salt: %.4f\n", totalSaltForDrink);
            System.out.println();

            //Program does calculations and asks for continue (y/n)
            System.out.println("Do you want to continue to choose drink items? (y/n)");
            char answer = scanner.next().charAt(0);
            action1 = (answer == 'y');

            totalkcal += totalKcalForDrink;
            totalprotein += totalProteinForDrink;
            totalcarbs += totalCarbsForDrink;
            totalfat += totalFatsForDrink;
            totalsalt += totalSaltForDrink;

        } while (action1);
        System.out.println();

        System.out.println("YOUR TOTAL CONSUMPTION TODAY:");
        System.out.printf("Total kcal: %.2f\n", totalkcal);
        System.out.printf("Total protein: %.2f\n", totalprotein);
        System.out.printf("Total carbs: %.2f\n", totalcarbs);
        System.out.printf("Total fat: %.2f\n", totalfat);
        System.out.printf("Total salt: %.2f\n", totalsalt);
        System.out.println();
        System.out.println("RECOMMENDATIONS:\n");

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
    public static void validationForCalories(int maxNorm, int minNorm, float totalKcal){
        if (totalKcal > maxNorm) {
            System.out.println("""
                    1. Your calorie intake is higher than daily norm!\s
                    Focus on nutrient-dense foods (fruits, vegetables, whole grains, low-fat milk products, seafood, eggs, peas, nuts) and avoid sugary and high-fat foods.\s
                    Practice portion control and do regular exercises.\s
                    """);
        } else if (totalKcal < minNorm) {
            System.out.println("""
                    1. Your calorie intake is less than daily norm!\s
                    Choose foods that are high in nutrients and calories - nuts, seeds, avocados, whole grains and healthy fats.\s
                    Try also milk, smoothies and juice.\s
                    """);
        } else {
            System.out.println("1. Your calorie intake is in daily norm! - You are doing great!\n");
        }
    }
    public static void validationForProteins(float index, int weight, float totalProtein ){
        if (totalProtein > index * weight) {
            System.out.println("""
                    2. Your protein intake is higher than daily norm!\s
                    Limit high-protein animal products like red meat, poultry. Choose leaner protein sources - fish, legumes, tofu.\s
                    Incorporate more plant-based proteins - beans, lentils and nuts.\s
                    Monitor your portion sizes and avoid overeating high-protein foods.\s
                    """);
        } else if (totalProtein < index * weight ){
            System.out.println("""
                    2. Your protein intake is less than daily norm!\s
                    Choose protein-rich foods like lean meats, poultry, fish, eggs, dairy products and legumes.\s
                    Aim to consume protein at every meal, to help distribute your intake evenly throughout the day.\s
                    """);
        } else {
            System.out.println("2. Your protein intake is in daily norm! - You are doing well!\n");
        }
    }
    public static void validationForCarbs(int norm, float totalCarbs){
        if (totalCarbs > norm) {
            System.out.println("""
                    3. Your carbs intake is higher than daily norm!\s
                    Avoid processed foods like white bread, pasta and baked goods!\s
                    Choose whole grains like brown rice, quinoa, which are rich in fiber and other nutrients.\s
                    Incorporate more vegetables. Avoid sugary drinks like soda, juice and sports drinks!\s
                    """);
        } else if (totalCarbs < norm) {
            System.out.println("""
                    3. Your carbs intake is less than daily norm!\s
                    Incorporate health carbohydrate sources into your diet - fruits, vegetables, whole grains, legumes.\s
                    Choose brown rice, quinoa, beans, lentils and chickpeas.\s
                    """);
        } else {
            System.out.println("3. Your carbs intake is in daily norm! - You are doing well! \n");
        }
    }
    public static void validationForFats(int norm, float totalFats){
        if (totalFats > norm) {
            System.out.println("""
                    4. Your fats intake is higher than daily norm!\s
                    Look for products, with lower fat content and avoid foods that ar high in saturated and trans fats.\s
                    Choose lean protein sources - chicken, turkey and fish, also plant-based protein - beans, lentils, and tofu.\s
                    Increase your intake of fruits and vegetables!\s
                    Use cooking methods that require less added fat, such as baking, broiling, grilling or roasting.\s
                    Limit high-fat snacks and desserts such as chips, fried foods, ice cream, cakes and pastries!\s
                    """);
        } else if (totalFats < norm) {
            System.out.println("""
                    4. Your fats intake is less than daily norm!\s
                    Choose healthy fat sources - avocados, nuts, seeds, fatty fish, olive oil and coconut oil.\s
                    Eat more fatty fish such as salmon, mackerel and sardines.\s
                    Incorporate full-fat dairy products - whole milk, cheese and yogurt.\s
                    Snack nuts and seeds like almonds, walnuts, chia seeds ands flaxseeds.\s
                    """);
        } else {
            System.out.println("4. Your fats intake is in daily norm! - You are doing well!\n");
        }
    }
    public static void validationForSalt(float totalSalt){
        if (totalSalt > 2.4) {
            System.out.println("""
                    5. Your salt intake is higher than daily norm!\s
                    Pay attention to the sodium content in the foods you eat and choose lower-sodium options whenever possible.\s
                    Limit processed and packaged foods because many of them are high in sodium. Choose fresh, whole foods instead.\s
                    Use herbs and spices instead of salt - try using garlic, onion, lemon juice, vinegar and pepper to flavor you food.\s
                    """);
        } else if (totalSalt < 2.4) {
            System.out.println("""
                    5. Your salt intake is less than daily norm!\s
                    Add a small amount of salt to your meals, but be mindful not to overdo it!\s
                    Choose salty snacks - salted nuts, popcorn and pretzels, but be mindful of portion sizes and choose healthier snack when possible.\s
                    Drink fluids that contain salt - sport drinks and broth can help you to increase salt intake and also provide hydration.\s
                    """);
        } else {
            System.out.println("5. Your salt intake is in daily norm! - You are doing well!");
        }
    }
}
