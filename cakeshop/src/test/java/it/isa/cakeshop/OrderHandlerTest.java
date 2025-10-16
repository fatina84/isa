package it.isa.cakeshop;

import static org.junit.jupiter.api.Assertions.*;

import it.isa.cakeshop.cli.OrderHandler;
import it.isa.cakeshop.model.Cake;
import it.isa.cakeshop.model.Category;
import it.isa.cakeshop.service.CakeShop;
import it.isa.cakeshop.testutils.MockIOProvider;
import java.util.List;
import org.junit.jupiter.api.*;

public class OrderHandlerTest {

  private CakeShop shop;
  private MockIOProvider io;
  private OrderHandler handler;

  @BeforeEach
  public void setup() {
    shop = new CakeShop();
    io = new MockIOProvider();
    handler = new OrderHandler(shop, io);
  }

  @Test
  public void testCreateOrderWithValidSelection() {
    Cake c1 = new Cake("Choco Cake", Category.BIRTHDAY, 20.0);
    Cake c2 = new Cake("Fruit Cake", Category.CUSTOM, 25.0);
    shop.addCake(c1);
    shop.addCake(c2);

    io.addInput("Alice");
    io.addInput("0,1");

    handler.createOrder();

    assertEquals(1, shop.getAllOrders().size()); // verifica che c'è esattamente un ordine
  }

  @Test
  public void testCreateOrderInvalidIndex() {
    Cake c1 = new Cake("Choco Cake", Category.BIRTHDAY, 20.0);
    shop.addCake(c1);

    io.addInput("Bob");
    io.addInput("5");

    handler.createOrder();

    List<String> output = io.getOutputs();
    assertTrue(output.stream().anyMatch(s -> s.contains("Nessuna torta selezionata")));
  }

  @Test
  public void testCreateOrderEmptyCakeList() {
    io.addInput("NoTorteCliente");

    handler.createOrder();

    List<String> output = io.getOutputs();
    assertTrue(output.stream().anyMatch(s -> s.contains("Nessuna torta disponibile")));
  }
}
