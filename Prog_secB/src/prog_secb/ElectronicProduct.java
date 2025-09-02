/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog_secb;

/**
 *
 * @author heath
 */
public class ElectronicProduct extends Product {
     private int warrantyMonths;
    public ElectronicProduct(String id, String name, double price, int warrantyMonths) {
        super(id, name, price);
        this.warrantyMonths = warrantyMonths;
    }
    @Override public String getType() { return "Electronic"; }
}

