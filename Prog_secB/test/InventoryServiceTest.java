/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import prog_secb.InventoryService;
import prog_secb.Product;
import prog_secb.PerishableProduct;
import prog_secb.ElectronicProduct;


public class InventoryServiceTest {

    private InventoryService inv;

    @Before
    public void setUp() {
        inv = new InventoryService(5);
        inv.addProduct(new PerishableProduct("P101", "Milk 2L", 24.99, 7));
        inv.addProduct(new ElectronicProduct("E201", "Headphones", 499.00, 24));
    }

    @Test
    public void testSearchFound() {
        Product p = inv.findById("P101");
        assertNotNull(p);
        assertEquals("P101", p.getId());
        assertEquals("Perishable", p.getType());
    }

    @Test
    public void testSearchNotFound() {
        assertNull(inv.findById("X999"));
    }

    @Test
    public void testRecordSaleAndRevenue() {
        assertTrue(inv.recordSale("P101", 1, 3)); // Tue, 3 units
        assertEquals(3, inv.totalUnitsSold("P101"));
        double revenue = inv.revenueForProduct("P101");
        assertEquals(3 * 24.99, revenue, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecordSaleRejectsNegativeQty() {
        inv.recordSale("P101", 0, -1);
    }

    @Test
    public void testUpdateAndDelete() {
        assertTrue(inv.updatePrice("E201", 450.00));
        assertTrue(inv.deleteById("E201"));
        assertNull(inv.findById("E201"));
        assertEquals(1, inv.getSize());
    }
}