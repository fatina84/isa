package it.isa.cakeshop;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test unitari per la classe CakeShop
public class CakeShopTest {

  private CakeShop shop;

  @BeforeEach
  public void setUp() {
    shop = new CakeShop();
    shop.addCake(new Cake("Torta Tom & Jerry", Category.BIRTHDAY, 300.0));
    shop.addCake(new Cake("Torta Transformer - Bumblebee", Category.BIRTHDAY, 200.0));
    shop.addCake(new Cake("Torta Cestino Fiori", Category.VALENTINES_DAY, 150.0));
  }

  @Test
  public void testAddCake() {
    Cake newCake = new Cake("Torta Fragole", Category.MOTHERS_DAY, 50.0);
    shop.addCake(newCake);

    List<Cake> results = shop.searchCakes("Fiori");
    assertEquals(1, results.size());
    assertEquals("Torta Cestino Fiori", results.get(0).getName());
  }

  @Test
  public void testGetCakesByCategory() {
    List<Cake> birthdayCakes = shop.getCakesByCategory(Category.BIRTHDAY);
    assertEquals(2, birthdayCakes.size());
    assertEquals("Torta Tom & Jerry", birthdayCakes.get(0).getName());
  }

  @Test
  public void testSearchCakes() {
    List<Cake> results = shop.searchCakes("Bumblebee");
    assertEquals(1, results.size());
    assertEquals(Category.BIRTHDAY, results.get(0).getCategory());
  }
}
