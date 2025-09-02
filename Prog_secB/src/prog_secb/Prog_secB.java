
package prog_secb;
import java.util.Scanner;
/**
 *
 * @author heath
 */
public class Prog_secB {

    
    
    private static final Scanner in = new Scanner(System.in);                   //farrell,2023
    private static final InventoryService inv = new InventoryService(50); // capacity

    public static void main(String[] args) {
        while (true) {                                                          //farrell,2023
            printMenu();
            int op = readIntInRange("Choose option (1-7): ", 1, 7);

            switch (op) {                                                       //farrell,2023
                case 1 -> addProductFlow();
                case 2 -> searchFlow();
                case 3 -> updatePriceFlow();
                case 4 -> deleteFlow();
                case 5 -> recordSaleFlow();
                case 6 -> System.out.println(inv.generateWeeklyReport());
                case 7 -> { System.out.println("Goodbye!"); return; }
            }
            System.out.println(); // spacing
        }
    }

    /* ----------------- Menu actions ----------------- */

    private static void addProductFlow() {                                      //farrell,2023
        System.out.println("\nADD PRODUCT");
        String id   = readNonEmpty("Enter product ID: ");
        String name = readNonEmpty("Enter name: ");
        double price = readDoubleMin("Enter price: ", 0);

        int type = readIntInRange("Type? (1) Perishable  (2) Electronic: ", 1, 2);

        boolean ok;                                                             //farrell,2023
        if (type == 1) {
            int days = readIntMin("Enter shelf life in days: ", 1);
            ok = inv.addProduct(new PerishableProduct(id, name, price, days));
        } else {
            int warranty = readIntMin("Enter warranty in months: ", 0);
            ok = inv.addProduct(new ElectronicProduct(id, name, price, warranty));
        }

        System.out.println(ok ? "Product added." : "Could not add (duplicate ID or full).");
    }

    private static void searchFlow() {                                          //farrell,2023
        System.out.println("\nSEARCH PRODUCT");
        String id = readNonEmpty("Enter product ID: ");
        Product p = inv.findById(id);
        if (p == null) {
            System.out.println("Not found.");
        } else {
            int units = inv.totalUnitsSold(id);
            double rev = inv.revenueForProduct(id);
            System.out.printf("[%s] %s | %s | R%.2f | Units sold: %d | Revenue: R%.2f%n",           //ChatGPT,2025
                    p.getId(), p.getName(), p.getType(), p.getPrice(), units, rev);
        }
    }

    private static void updatePriceFlow() {                                    //farrell,2023 
        System.out.println("\nUPDATE PRICE");
        String id = readNonEmpty("Enter product ID: ");
        double newPrice = readDoubleMin("Enter new price: ", 0);
        boolean ok = inv.updatePrice(id, newPrice);
        System.out.println(ok ? "Price updated." : "Product not found.");
    }

    private static void deleteFlow() {                                          //farrell,2023
        System.out.println("\nDELETE PRODUCT");
        String id = readNonEmpty("Enter product ID: ");
        String confirm = readNonEmpty("Type Y to confirm delete: ");
        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("Cancelled.");
            return;
        }
        boolean ok = inv.deleteById(id);
        System.out.println(ok ? "Deleted." : "Product not found.");
    }

    private static void recordSaleFlow() {                                      //farrell,2023
        System.out.println("\nRECORD SALE");
        String id   = readNonEmpty("Enter product ID: ");
        int day1to7 = readIntInRange("Day of week (1=Mon .. 7=Sun): ", 1, 7);
        int qty     = readIntMin("Quantity (>0): ", 1);

        try {
            boolean ok = inv.recordSale(id, day1to7 - 1, qty); // convert to 0..6
            System.out.println(ok ? "Sale recorded." : "Product not found.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /* ----------------- UI helpers ----------------- */

    private static void printMenu() {
        System.out.println("""
            =======================
            INVENTORY MENU
            (1) Add product
            (2) Search product
            (3) Update price
            (4) Delete product
            (5) Record sale
            (6) Weekly report
            (7) Exit
            =======================""");
    }

    private static String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = in.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Please enter a value.");
        }
    }

    private static int readIntMin(String prompt, int minInclusive) {
        while (true) {
            System.out.print(prompt);
            try {
                int v = Integer.parseInt(in.nextLine().trim());
                if (v >= minInclusive) return v;
            } catch (NumberFormatException ignored) { }
            System.out.printf("Enter an integer >= %d.%n", minInclusive);       //ChatGPT,2025
        }
    }

    private static int readIntInRange(String prompt, int minInclusive, int maxInclusive) {
        while (true) {
            System.out.print(prompt);
            try {
                int v = Integer.parseInt(in.nextLine().trim());
                if (v >= minInclusive && v <= maxInclusive) return v;
            } catch (NumberFormatException ignored) { }
            System.out.printf("Enter an integer between %d and %d.%n", minInclusive, maxInclusive);         //ChatGPT,2025
        }
    }

    private static double readDoubleMin(String prompt, double minInclusive) {
        while (true) {
            System.out.print(prompt);
            try {
                double v = Double.parseDouble(in.nextLine().trim());
                if (v >= minInclusive) return v;
            } catch (NumberFormatException ignored) { }
            System.out.printf("Enter a number >= %.2f.%n", minInclusive);       //ChatGPT,2025
        }
    }

    
}
