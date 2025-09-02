/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog_secb;
import java.util.Arrays;

/**
 *
 * @author heath
 */
public class InventoryService {
   
    private final Product[] catalog;  // array of objects (advanced arrays topic)
    private final int[][] sales;      // 2D array: productIndex x day(0..6)
    private int size = 0;

    private static final String[] DAYS = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};

    public InventoryService(int capacity) {                                          //farrell,2023
        if (capacity <= 0) throw new IllegalArgumentException("capacity > 0");
        this.catalog = new Product[capacity];
        this.sales   = new int[capacity][7];
    }

    public boolean addProduct(Product p) {                                       //farrell,2023
        if (p == null || size == catalog.length) return false;
        if (findIndex(p.getId()) != -1) return false;   // no duplicates
        catalog[size] = p;
        Arrays.fill(sales[size], 0);
        size++;
        return true;
    }

    public Product findById(String id) {                                             //farrell,2023
        int idx = findIndex(id);
        return (idx == -1) ? null : catalog[idx];
    }

    public boolean updatePrice(String id, double newPrice) {                          //farrell,2023
        int idx = findIndex(id);
        if (idx == -1) return false;
        catalog[idx].setPrice(newPrice);
        return true;
    }

    public boolean deleteById(String id) {                                       //farrell,2023
        int idx = findIndex(id);
        if (idx == -1) return false;
        // compact arrays (no gaps)
        for (int i = idx; i < size - 1; i++) {
            catalog[i] = catalog[i + 1];
            sales[i]   = sales[i + 1];
        }
        catalog[size - 1] = null;
        sales[size - 1]   = new int[7];
        size--;
        return true;
    }

    /**
     * @param id Record qty units sold for a product on a day 0..6.
     * @param dayIndex
     * @param qty
     * @return  */
    public boolean recordSale(String id, int dayIndex, int qty) {                //farrell,2023
        if (qty <= 0) throw new IllegalArgumentException("qty > 0");
        if (dayIndex < 0 || dayIndex > 6) throw new IllegalArgumentException("day 0..6");
        int idx = findIndex(id);
        if (idx == -1) return false;
        sales[idx][dayIndex] += qty;
        return true;
    }

    public int totalUnitsSold(String id) {                                       //farrell,2023
        int idx = findIndex(id);
        if (idx == -1) return 0;
        int sum = 0;
        for (int d = 0; d < 7; d++) sum += sales[idx][d];
        return sum;
    }

    public double revenueForProduct(String id) {                                 //farrell,2023
        int idx = findIndex(id);
        if (idx == -1) return 0.0;
        return totalUnitsSold(id) * catalog[idx].getPrice();
    }

    /** Build a neat console report.
     * @return  */
    public String generateWeeklyReport() {                                       //farrell,2023
        StringBuilder sb = new StringBuilder();
        sb.append("=== WEEKLY SALES REPORT ===\n");
        sb.append(String.format("Products: %d%n%n", size));

        for (int i = 0; i < size; i++) {
            Product p = catalog[i];
            sb.append(String.format("[%s] %-18s | %-10s | R%.2f%n",
                    p.getId(), p.getName(), p.getType(), p.getPrice()));
            // daily units
            for (int d = 0; d < 7; d++) {
                sb.append(String.format("  %s: %d", DAYS[d], sales[i][d]));
                if (d < 6) sb.append(" |");
            }
            int units = Arrays.stream(sales[i]).sum();
            double revenue = units * p.getPrice();
            sb.append(String.format("%n  Units: %d | Revenue: R%.2f%n", units, revenue));
            sb.append("--------------------------------------------\n");
        }

        // top seller (by units)
        int bestIdx = -1, bestUnits = -1;
        for (int i = 0; i < size; i++) {
            int units = Arrays.stream(sales[i]).sum();
            if (units > bestUnits) { bestUnits = units; bestIdx = i; }
        }
        if (bestIdx != -1) {
            sb.append(String.format("Top seller: %s (%d units)%n",
                    catalog[bestIdx].getName(), bestUnits));
        } else {
            sb.append("Top seller: n/a\n");
        }
        return sb.toString();
    }

    // --------- helpers ----------
    private int findIndex(String id) {
        if (id == null) return -1;
        for (int i = 0; i < size; i++) {
            if (catalog[i].getId().equalsIgnoreCase(id)) return i;
        }
        return -1;
    }

    // for tests
    public int getSize() { return size; }
}

// ran out of time 