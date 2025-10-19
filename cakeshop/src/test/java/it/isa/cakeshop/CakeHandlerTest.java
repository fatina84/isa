package it.isa.cakeshop;

import static org.junit.jupiter.api.Assertions.*;

import it.isa.cakeshop.cli.CakeHandler;
import it.isa.cakeshop.model.Cake;
import it.isa.cakeshop.model.Category;
import it.isa.cakeshop.service.CakeShop;
import it.isa.cakeshop.testutils.MockIOProvider;
import java.util.List;
import org.junit.jupiter.api.*;

public class CakeHandlerTest {

  private CakeShop shop;
  private MockIOProvider io;
  private CakeHandler handler;

  @BeforeEach
  public void setup() {
    shop = new CakeShop();
    io = new MockIOProvider();
    handler = new CakeHandler(shop, io);
  }

  @Test
  public void testAddCakeValidInput() {
    io.addInput("Torta Prova");
    io.addInput("COMPLEANNO");
    io.addInput("25.0");

    handler.addCake();

    List<Cake> cakes = shop.getAllCakes();
    assertEquals(1, cakes.size());
    assertEquals("Torta Prova", cakes.get(0).getName());
    assertEquals(Category.COMPLEANNO, cakes.get(0).getCategory());
    assertEquals(25.0, cakes.get(0).getPrice());
  }

  @Test
  public void testAddCakeInvalidPrice() {
    io.addInput("Torta Error");
    io.addInput("COMPLEANNO");
    io.addInput("abc");

    handler.addCake();

    List<String> outputs = io.getOutputs();
    assertTrue(outputs.stream().anyMatch(s -> s.contains("Prezzo non valido")));
    assertEquals(0, shop.getAllCakes().size());
  }

  @Test
  public void testAddCakeInvalidCategory() {
    io.addInput("Torta Error");
    io.addInput("INVALID");
    io.addInput("10.0");

    handler.addCake();

    List<String> outputs = io.getOutputs();
    assertTrue(outputs.stream().anyMatch(s -> s.contains("Categoria non valida")));
    assertEquals(0, shop.getAllCakes().size());
  }

  @Test
  public void testSearchCakesFound() {
    shop.addCake(new Cake("Cioccolato Extra", Category.ALTRO, 10.0));
    io.addInput("Extra");

    handler.searchCakes();

    List<String> output = io.getOutputs();
    assertTrue(output.stream().anyMatch(s -> s.contains("Cioccolato Extra")));
  }

  @Test
  public void testSearchCakesNotFound() {
    shop.addCake(new Cake("Vaniglia", Category.ALTRO, 8.0));
    io.addInput("Fragola");

    handler.searchCakes();

    List<String> output = io.getOutputs();
    assertTrue(output.stream().anyMatch(s -> s.contains("Nessuna torta trovata")));
  }

  @Test
  public void testSearchCakesReturnsEmptyListIfNoMatch() {
    List<Cake> results = shop.searchCakes("Inesistente");
    assertTrue(results.isEmpty());
  }

  @Test
  public void testAddCakeWithInvalidCategory() {
    io.addInput("Torta X");
    io.addInput("INVALID"); // Categoria non valida
    io.addInput("50.0");

    handler.addCake();

    assertTrue(shop.getAllCakes().isEmpty());
  }
}
