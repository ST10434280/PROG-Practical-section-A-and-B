package prog_secb;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author heath
 */
public class Product {
    private final String id;     // information hiding
    private String name;
    private double price;

    public Product(String id, String name, double price) {                                  //farrell,2023
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id required");
        if (price < 0) throw new IllegalArgumentException("price >= 0");
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() { return id; }                                        //farrell,2023
    public String getName() { return name; }
    public double getPrice() { return price; }
    

    public void setName(String name) { this.name = name; }
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("price >= 0");
        this.price = price;
    }

    
     // @return                                                                     //farrell,2023
    public String getType() { return "General"; }
    
}
