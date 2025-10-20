package it.isa.cakeshop.integration;

import static org.junit.jupiter.api.Assertions.*;

import it.isa.cakeshop.cli.CommandHandler;
import it.isa.cakeshop.model.Cake;
import it.isa.cakeshop.model.Category;
import it.isa.cakeshop.service.CakeShop;
import it.isa.cakeshop.testutils.MockIOProvider;
import java.io.IOException;
import java.nio.file.*;
import org.junit.jupiter.api.Test;

public class CreateOrderIntegrationTest {

  private static final Path ORDERS_FILE = Paths.get("data/ordini.txt");

  @Test
  void testOrderCommandAndFilePersistence() throws IOException {
    // Cleanup file prima del test
    Files.deleteIfExists(ORDERS_FILE);

    CakeShop shop = new CakeShop();
    shop.addCake(new Cake("Red Velvet", Category.FIDANZAMENTO, 30.0));
    shop.addCake(new Cake("Tiramisù", Category.ALTRO, 22.0));

    MockIOProvider io = new MockIOProvider();
    io.addInput("4");
    io.addInput("Mario Rossi"); // Nome cliente
    io.addInput("0,1"); // Seleziona entrambe le torte
    io.addInput("exit");

    CommandHandler handler = new CommandHandler(shop, io);
    handler.run();

    // Verifica che l'ordine sia stato registrato
    assertEquals(1, shop.getAllOrders().size());
    assertEquals("Mario Rossi", shop.getAllOrders().get(0).getCustomerName());

    // Verifica che il file sia stato scritto
    assertTrue(Files.exists(ORDERS_FILE));
    String content = Files.readString(ORDERS_FILE);
    assertTrue(content.contains("Ordine di Mario Rossi"));
    assertTrue(content.contains("Red Velvet"));
    assertTrue(content.contains("Tiramisù"));
  }
}
