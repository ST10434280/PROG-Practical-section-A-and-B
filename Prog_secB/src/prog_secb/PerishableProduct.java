/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog_secb;

/**
 *
 * @author heath
 */
public class PerishableProduct extends Product {
      private int shelfLifeDays;
    public PerishableProduct(String id, String name, double price, int shelfLifeDays) {
        super(id, name, price);
        this.shelfLifeDays = shelfLifeDays;
    }
    @Override public String getType() { return "Perishable"; }
}
    

