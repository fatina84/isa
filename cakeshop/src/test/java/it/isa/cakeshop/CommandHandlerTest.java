package it.isa.cakeshop;

import static org.junit.jupiter.api.Assertions.*;

import it.isa.cakeshop.cli.CommandHandler;
import it.isa.cakeshop.model.Cake;
import it.isa.cakeshop.model.Category;
import it.isa.cakeshop.service.CakeShop;
import it.isa.cakeshop.testutils.MockIOProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandHandlerTest {

  private CakeShop shop;
  private MockIOProvider io;
  private CommandHandler handler;

  @BeforeEach
  public void setUp() {
    shop = new CakeShop();
    io = new MockIOProvider();
    handler = new CommandHandler(shop, io);
  }

  @Test
  public void testUnknownCommandShowsError() {
    // Comando non riconosciuto
    handlerTestCommand("comandofalso");

    assertTrue(
        io.getOutputs().stream().anyMatch(line -> line.contains("Comando non riconosciuto")));
  }

  @Test
  public void testAddCommandWithValidCake() {
    io.addInput("Torta Test");
    io.addInput("BIRTHDAY");
    io.addInput("29.99");

    handlerTestCommand("add");

    assertEquals(1, shop.getAllCakes().size());
    assertEquals("Torta Test", shop.getAllCakes().get(0).getName());
  }

  @Test
  public void testSearchCommandShowsMatchingCakes() {
    Cake cake1 = new Cake("Torta Fragola", Category.CUSTOM, 15.0);
    Cake cake2 = new Cake("Torta Limone", Category.CUSTOM, 18.0);
    shop.addCake(cake1);
    shop.addCake(cake2);

    io.addInput("fragola");

    handlerTestCommand("search");

    assertTrue(io.getOutputs().stream().anyMatch(line -> line.contains("Torta Fragola")));
    assertFalse(io.getOutputs().stream().anyMatch(line -> line.contains("Torta Limone")));
  }

  @Test
  public void testExitCommandPrintsFarewell() {
    handlerTestCommand("exit");

    assertTrue(io.getOutputs().stream().anyMatch(line -> line.contains("Arrivederci")));
  }

  // Metodo helper per testare singoli comandi senza eseguire l'intero ciclo run()
  private void handlerTestCommand(String command) {
    try {
      var method = CommandHandler.class.getDeclaredMethod("handleCommand", String.class);
      method.setAccessible(true);
      method.invoke(handler, command);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
