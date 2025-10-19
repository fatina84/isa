package it.isa.cakeshop.integration;

import static org.junit.jupiter.api.Assertions.*;

import it.isa.cakeshop.cli.CommandHandler;
import it.isa.cakeshop.service.CakeShop;
import it.isa.cakeshop.testutils.MockIOProvider;
import java.util.List;
import org.junit.jupiter.api.Test;

public class AddCakeIntegrationTest {

  @Test
  void testAddCakeCommand() {
    CakeShop shop = new CakeShop();
    MockIOProvider io = new MockIOProvider();

    // Simula comando "add" con input dell'utente
    io.addInput("add");
    io.addInput("Torta al cioccolato");
    io.addInput("COMPLEANNO");
    io.addInput("25.50");
    io.addInput("exit");

    CommandHandler handler = new CommandHandler(shop, io);
    handler.run();

    // Verifica che la torta sia stata effettivamente aggiunta
    assertEquals(1, shop.getAllCakes().size());
    assertEquals("Torta al cioccolato", shop.getAllCakes().get(0).getName());

    List<String> output = io.getOutputs();
    assertTrue(output.stream().anyMatch(s -> s.contains("Torta aggiunta con successo")));
  }
}
