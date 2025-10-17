package it.isa.cakeshop.integration;

import static org.junit.jupiter.api.Assertions.*;

import it.isa.cakeshop.cli.CommandHandler;
import it.isa.cakeshop.service.CakeShop;
import it.isa.cakeshop.testutils.MockIOProvider;
import java.util.List;
import org.junit.jupiter.api.Test;

public class InvalidCommandIntegrationTest {

  @Test
  void testInvalidCommandAndHelp() {
    MockIOProvider io = new MockIOProvider();
    CakeShop shop = new CakeShop();

    io.addInput("xyz"); // comando non valido
    io.addInput("help"); // mostra i comandi
    io.addInput("exit");

    CommandHandler handler = new CommandHandler(shop, io);
    handler.run();

    List<String> output = io.getOutputs();
    assertTrue(output.stream().anyMatch(s -> s.contains("Comando non riconosciuto")));
    assertTrue(output.stream().anyMatch(s -> s.contains("Comandi disponibili")));
  }
}
