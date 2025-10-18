package it.isa.cakeshop;

import it.isa.cakeshop.cli.CommandHandler;
import it.isa.cakeshop.io.IOConsole;
import it.isa.cakeshop.io.IOProvider;
import it.isa.cakeshop.service.CakeShop;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

// Classe principale dell'applicazione
public class App {
  public static void main(String[] args) {
    try {
      // Set the system output stream to use UTF-8
      OutputStream out = System.out;
      PrintStream printStream = new PrintStream(out, true, StandardCharsets.UTF_8.name());
      System.setOut(printStream);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Crea un nuovo negozio di torte
    CakeShop shop = new CakeShop();

    // Avvia il gestore dei comandi
    IOProvider io = new IOConsole();
    CommandHandler handler = new CommandHandler(shop, io);

    // Avvia il programma CLI
    handler.run();
  }
}
