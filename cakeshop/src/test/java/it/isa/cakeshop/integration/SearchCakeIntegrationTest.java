package it.isa.cakeshop.integration;

import static org.junit.jupiter.api.Assertions.*;

import it.isa.cakeshop.cli.CommandHandler;
import it.isa.cakeshop.model.Cake;
import it.isa.cakeshop.model.Category;
import it.isa.cakeshop.service.CakeShop;
import it.isa.cakeshop.testutils.MockIOProvider;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SearchCakeIntegrationTest {

  @Test
  void testSearchCakeCommand() {
    CakeShop shop = new CakeShop();
    shop.addCake(new Cake("Torta alle fragole", Category.SAN_VALENTINO, 18.0));
    shop.addCake(new Cake("Torta al limone", Category.COMPLEANNO, 20.0));

    MockIOProvider io = new MockIOProvider();
    io.addInput("3");
    io.addInput("fragole");
    io.addInput("exit");

    CommandHandler handler = new CommandHandler(shop, io);
    handler.run();

    List<String> output = io.getOutputs();

    assertTrue(output.stream().anyMatch(line -> line.contains("Torta alle fragole")));
    assertFalse(output.stream().anyMatch(line -> line.contains("Torta al limone")));
  }
}
