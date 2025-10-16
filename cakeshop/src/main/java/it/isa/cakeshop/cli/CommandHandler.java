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
    io.println("Benvenuto alla Cake Shop CLI!");
    io.println("Digita 'help' per vedere i comandi disponibili.");

    do {
      command = io.readLine("> ");
      handleCommand(command.trim());
    } while (!command.equalsIgnoreCase("exit"));
  }

  // Gestione dei vari comandi
  private void handleCommand(String command) {
    switch (command.toLowerCase()) {
      case "list" -> cakeHandler.listCakes();
      case "add" -> cakeHandler.addCake();
      case "search" -> cakeHandler.searchCakes();
      case "order" -> orderHandler.createOrder();
      case "orders" -> orderHandler.listOrders();
      case "orders-file" -> orderHandler.readOrdersFromFile();
      case "help" -> printHelp();
      case "exit" -> io.println("Arrivederci!");
      default -> io.println("Comando non riconosciuto. Digita 'help' per assistenza.");
    }
  }

  // Mostra l'elenco dei comandi
  private void printHelp() {
    io.println("Comandi disponibili:");
    io.println("add         - Aggiungi una nuova torta");
    io.println("list        - Elenca tutte le torte");
    io.println("search      - Cerca torte per nome");
    io.println("order       - Crea un nuovo ordine");
    io.println("orders      - Mostra tutti gli ordini attivi");
    io.println("orders-file - Mostra gli ordini salvati su file");
    io.println("help        - Mostra questo messaggio");
    io.println("exit        - Esci dall'applicazione");
  }
}
