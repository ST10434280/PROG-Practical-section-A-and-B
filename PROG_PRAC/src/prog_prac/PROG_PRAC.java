package prog_prac;

import java.util.ArrayList;
import java.util.Scanner;

public class PROG_PRAC {

    public static void main(String[] args) {                                    //Farrell(2023)
        Scanner scanner = new Scanner(System.in);

        System.out.println("LATEST SERIES - 2025");
        System.out.println("************************");

        String input;
        do {
            System.out.print("Enter (1) to launch menu or any other key to exit: ");
            input = scanner.nextLine();

            switch (input) {
                case "1":
                    // Create the Series app and run the menu
                    Series app = new Series(scanner, new ArrayList<>());
                    app.showMenu(); // loops until user selects (6) Exit
                    break;
                default:
                    System.out.println("Exiting program...");
            }
        } while (input.equals("1"));

        scanner.close();
    }
}


//Referance list
// Farrell,J.2023.JAVA Programming tenth edition.Boston.Cengage.
//Coding with John, 2022.Java Unit Testing with JUnit - Tutorial - How to Create And Use Unit Tests.
//[video online] available at: https://www.youtube.com/watch?v=vZm0lHciFsQ [accessed 2 september 2025].
//