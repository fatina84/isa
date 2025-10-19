package it.isa.cakeshop;

import static org.junit.jupiter.api.Assertions.*;

import it.isa.cakeshop.model.Cake;
import it.isa.cakeshop.model.Category;
import it.isa.cakeshop.service.CakeShop;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test unitari per la classe CakeShop
public class CakeShopTest {

  private CakeShop shop;

  @BeforeEach
  public void setUp() {
    shop = new CakeShop();
    shop.addCake(new Cake("Torta Tom & Jerry", Category.COMPLEANNO, 300.0));
    shop.addCake(new Cake("Torta Transformer - Bumblebee", Category.COMPLEANNO, 200.0));
    shop.addCake(new Cake("Torta Cestino Fiori", Category.SAN_VALENTINO, 150.0));
  }

  @Test
  public void testAddCake() {
    Cake newCake = new Cake("Torta Fragole", Category.FESTA_DELLA_MAMMA, 50.0);
    shop.addCake(newCake);

    List<Cake> results = shop.searchCakes("Fiori");
    assertEquals(1, results.size());
    assertEquals("Torta Cestino Fiori", results.get(0).getName());
  }

  @Test
  public void testGetCakesByCategory() {
    List<Cake> COMPLEANNOCakes = shop.getCakesByCategory(Category.COMPLEANNO);
    assertEquals(2, COMPLEANNOCakes.size());
    assertEquals("Torta Tom & Jerry", COMPLEANNOCakes.get(0).getName());
  }

  @Test
  public void testSearchCakes() {
    List<Cake> results = shop.searchCakes("Bumblebee");
    assertEquals(1, results.size());
    assertEquals(Category.COMPLEANNO, results.get(0).getCategory());
  }
}
