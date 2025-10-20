package it.isa.cakeshop.cli;

import it.isa.cakeshop.io.IOProvider;
import it.isa.cakeshop.service.CakeShop;

// Classe che gestisce i comandi dell'utente da riga di comando
// Usa IOProvider per rendere il codice testabile (input/output astratti).
public class CommandHandler {
  private final IOProvider io; // Istanza dell'interfaccia IOProvider
  private final CakeHandler cakeHandler;
  private final OrderHandler orderHandler;

  // Costruttore che accetta un'istanza di IOProvider (ConsoleIO o MockIO)
  public CommandHandler(CakeShop shop, IOProvider io) {
    this.io = io;
    this.cakeHandler = new CakeHandler(shop, io);
    this.orderHandler = new OrderHandler(shop, io);
  }

  // Metodo principale che avvia il ciclo dei comandi
  public void run() {
    String command;
    io.println("Benvenuti alla Cake Shop CLI!");
    io.println("Digita 'help' per vedere i comandi disponibili.");

    do {
      command = io.readLine("> ");
      handleCommand(command.trim());
    } while (!command.equalsIgnoreCase("exit"));
  }

  // Gestione dei vari comandi
  private void handleCommand(String command) {
    switch (command.toLowerCase()) {
      case "1" -> cakeHandler.addCake();
      case "2" -> cakeHandler.listCakes();
      case "3" -> cakeHandler.searchCakes();
      case "4" -> orderHandler.createOrder();
      case "5" -> orderHandler.listOrders();
      case "6" -> orderHandler.readOrdersFromFile();
      case "help" -> printHelp();
      case "exit" -> io.println("Arrivederci!");
      default -> io.println("Comando non riconosciuto. Digita 'help' per assistenza.");
    }
  }

  // Mostra l'elenco dei comandi
  private void printHelp() {
    io.println("Comandi disponibili:");
    io.println("1 - Aggiungi una nuova torta");
    io.println("2 - Elenca tutte le torte");
    io.println("3 - Cerca torte per nome");
    io.println("4 - Crea un nuovo ordine");
    io.println("5 - Mostra tutti gli ordini attivi");
    io.println("6 - Mostra gli ordini salvati su file");
    io.println("help - Mostra questo messaggio");
    io.println("exit - Esci dall'applicazione");
  }
}
