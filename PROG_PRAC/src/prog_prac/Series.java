package prog_prac;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//

///**
// *
// * @author heath

public class Series {                                               //Farrell(2023)

    private final Scanner scanner;
    private final List<SeriesModel> mySeries;

    public Series(Scanner scanner, List<SeriesModel> backingList) {      //Farrell(2023)
        this.scanner = scanner;
        this.mySeries = backingList;
    }

    public void showMenu() {                                             //Farrell(2023)
        while (true) {
            printMenu();
            int option = readInt("choose an option from (1-6)");

            switch (option) {
                case 1:
                    CaptureSeries();
                    break;
                case 2:
                    SearchSeries();
                    break;
                case 3:
                    UpdateSeries();
                    break;
                case 4:
                    DeleteSeries();
                    break;
                case 5:
                    SeriesReport();
                case 6: {
                    ExitSeriesApplication();
                    return;
                }
                default:
                    System.out.println("Unknown Option Please 1 - 6");

            }
        }
    }

    public void CaptureSeries() {                                          //Farrell(2023)
        System.out.println();
        System.out.println("CAPTURE A NEW SERIES");
        System.out.println("********************");

        String id = readLine("Enter the series id: ").trim();
        String name = readLine("Enter the series name: ").trim();
        // Age must be a number between 2 and 18 inclusive
        int age = readAgeWithValidation();
        String episodes = readLine("Enter the number of episodes for " + name + ": ").trim();

        SeriesModel model = new SeriesModel(id, name, String.valueOf(age), episodes);
        mySeries.add(model);

        System.out.println("Series processed successfully!!!");
        System.out.println();
    }
// (2) Search

    public void SearchSeries() {                                        //Farrell(2023)
        System.out.println();
        String id = readLine("Enter the series id to search: ").trim();

        SeriesModel s = findById(id);
        System.out.println("----------------------------------------");
        if (s == null) {
            System.out.println("Series with Series Id: " + id + " was not found!");
        } else {
            printSeriesDetails(s);
        }
        System.out.println("----------------------------------------");
        System.out.println();
    }

    // (3) Update
    public void UpdateSeries() {                                                //Farrell(2023)
        System.out.println();
        String id = readLine("Enter the series id to update: ").trim();

        SeriesModel s = findById(id);
        if (s == null) {
            System.out.println("Series with Series Id: " + id + " was not found!");
            System.out.println();
            return;
        }

        String newName = readLine("Enter the series name: ").trim();
        int newAge = readAgeWithValidation();
        String newEpisodes = readLine("Enter the number of episodes: ").trim();

        setSeriesName(s, newName);
        setSeriesAge(s, String.valueOf(newAge));
        setSeriesEpisodes(s, newEpisodes);

        // Show the updated record 
        System.out.println("Updated record:");
        printSeriesDetails(s);
        System.out.println();
    }

    // (4) Delete
    public void DeleteSeries() {                                                //Farrell(2023)
        System.out.println();
        String id = readLine("Enter the series id to delete: ").trim();

        SeriesModel s = findById(id);
        if (s == null) {
            System.out.println("Series with Series Id: " + id + " was not found!");
            System.out.println();
            return;
        }

        String confirm = readLine(
                "Are you sure you want to delete series " + id + " from the system? Yes (y) to delete: ").trim();

        if (confirm.equalsIgnoreCase("y")) {
            mySeries.remove(s);
            System.out.println("----------------------------------------");
            System.out.println("Series with Series Id: " + id + " WAS deleted!");
            System.out.println("----------------------------------------");
        } else {
            System.out.println("Delete cancelled.");
        }
        System.out.println();
    }

    // (5) Report
    public void SeriesReport() {                                                //Farrell(2023)
        System.out.println();
        if (mySeries.isEmpty()) {
            System.out.println("No series captured yet.");
            System.out.println();
            return;
        }

        int i = 1;
        for (SeriesModel s : mySeries) {
            System.out.println("Series " + i);
            System.out.println("----------------------------------------");
            printSeriesDetails(s);
            System.out.println("----------------------------------------");
            i++;
        }
        System.out.println();
    }

    // (6) Exit
    public void ExitSeriesApplication() {                                       //Farrell(2023)
        System.out.println("Leaving menu...");
    }

    //methodes
    private void printMenu() {                                                  //Farrell(2023)
        System.out.println();
        System.out.println("Please select one of the following menu items:");
        System.out.println("(1) Capture a new series.");
        System.out.println("(2) Search for a series.");
        System.out.println("(3) Update series age restriction.");
        System.out.println("(4) Delete a series.");
        System.out.println("(5) Print series report - 2025.");
        System.out.println("(6) Exit Application.");
    }

    private void printSeriesDetails(SeriesModel s) {                            //Farrell(2023)
        System.out.println("SERIES ID: " + getSeriesID(s));
        System.out.println("SERIES NAME: " + getSeriesName(s));
        System.out.println("SERIES AGE RESTRICTION: " + getSeriesAge(s));
        System.out.println("SERIES NUMBER OF EPISODES: " + getSeriesEpisodes(s));
    }

    private SeriesModel findById(String id) {                                   //Farrell(2023)
        for (SeriesModel s : mySeries) {
            if (getSeriesID(s).equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    private int readAgeWithValidation() {                                       //Farrell(2023)
        while (true) {
            String input = readLine("Enter the series age restriction: ").trim();
            try {
                int age = Integer.parseInt(input);
                if (age >= 2 && age <= 18) {
                    return age;
                }

                System.out.println("You have entered an incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
            } catch (NumberFormatException e) {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
            }
        }
    }

    private int readInt(String prompt) {                                        //Farrell(2023)
        while (true) {
            String raw = readLine(prompt);
            try {
                return Integer.parseInt(raw.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter digits only.");
            }
        }
    }

    private String readLine(String prompt) {                                    //Farrell(2023)
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private String getSeriesID(SeriesModel s) {                                                     //farrell,2023
        try {
            return (String) s.getClass().getMethod("getSeriesID").invoke(s);
        } catch (Exception ignore) {
        }
        try {
            return (String) s.getClass().getField("SeriesId").get(s);
        } catch (Exception ignore) {
        }
        try {
            return (String) s.getClass().getField("seriesID").get(s);
        } catch (Exception ignore) {
        }
        return ""; // fallback
    }

    private String getSeriesName(SeriesModel s) {                                       //farrell,2023
        try {
            return (String) s.getClass().getMethod("getSeriesName").invoke(s);
        } catch (Exception ignore) {
        }
        try {
            return (String) s.getClass().getField("SeriesName").get(s);
        } catch (Exception ignore) {
        }
        try {
            return (String) s.getClass().getField("seriesName").get(s);
        } catch (Exception ignore) {
        }
        return "";
    }

    private String getSeriesAge(SeriesModel s) {                                    //farrell,2023
        try {
            return (String) s.getClass().getMethod("getSeriesAge").invoke(s);
        } catch (Exception ignore) {
        }
        try {
            return (String) s.getClass().getField("SeriesAge").get(s);
        } catch (Exception ignore) {
        }
        try {
            return (String) s.getClass().getField("seriesAge").get(s);
        } catch (Exception ignore) {
        }
        return "";
    }

    private String getSeriesEpisodes(SeriesModel s) {                                       //farrell,2023
        try {
            return (String) s.getClass().getMethod("getSeriesNumberOfEpisodes").invoke(s);
        } catch (Exception ignore) {
        }
        try {
            return (String) s.getClass().getField("SeriesNumberOfEpisodes").get(s);
        } catch (Exception ignore) {
        }
        try {
            return (String) s.getClass().getField("seriesNumberOfEpisodes").get(s);
        } catch (Exception ignore) {
        }
        return "";
    }

    private void setSeriesName(SeriesModel s, String value) {                               //farrell,2023
        try {
            s.getClass().getMethod("setSeriesName", String.class).invoke(s, value);
            return;
        } catch (Exception ignore) {
        }
        try {
            s.getClass().getField("SeriesName").set(s, value);
        } catch (Exception ignore) {
        }
    }

    private void setSeriesAge(SeriesModel s, String value) {                                    //farrell,2023
        try {
            s.getClass().getMethod("setSeriesAge", String.class).invoke(s, value);
            return;
        } catch (Exception ignore) {
        }
        try {
            s.getClass().getField("SeriesAge").set(s, value);
        } catch (Exception ignore) {
        }
    }

    private void setSeriesEpisodes(SeriesModel s, String value) {                           //farrell,2023
        try {
            s.getClass().getMethod("setSeriesNumberOfEpisodes", String.class).invoke(s, value);
            return;
        } catch (Exception ignore) {
        }
        try {
            s.getClass().getField("SeriesNumberOfEpisodes").set(s, value);
        } catch (Exception ignore) {
        }
    }
}
