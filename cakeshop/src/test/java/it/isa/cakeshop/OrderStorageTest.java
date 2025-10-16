package it.isa.cakeshop;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import org.junit.jupiter.api.*;

import it.isa.cakeshop.model.Cake;
import it.isa.cakeshop.model.Category;
import it.isa.cakeshop.model.Order;
import it.isa.cakeshop.storage.OrderStorage;

// Test per il salvataggio su file degli ordini
public class OrderStorageTest {

  private static final String FILE_NAME = "./data/ordini.txt";

  @BeforeEach
  public void cleanFile() throws IOException {
    Files.deleteIfExists(Paths.get(FILE_NAME));
  }

  @Test
  public void testOrderIsSavedToFile() throws IOException {
    Cake cake = new Cake("Torta Prova", Category.CUSTOM, 19.99);
    Order order = new Order("Test Cliente", List.of(cake));

    OrderStorage.saveOrder(order);

    Path path = Paths.get(FILE_NAME);
    assertTrue(Files.exists(path));

    String content = Files.readString(path);
    assertTrue(content.contains("Test Cliente"));
    assertTrue(content.contains("Torta Prova"));
    assertTrue(content.contains("19.99"));
  }
}
