package it.isa.cakeshop;

import it.isa.cakeshop.utils.IOConsole;
import it.isa.cakeshop.utils.IOProvider;

// Classe principale dell'applicazione
public class App {
  public static void main(String[] args) {
    // Crea un nuovo negozio di torte
    CakeShop shop = new CakeShop();

    // Avvia il gestore dei comandi
    IOProvider io = new IOConsole();
    CommandHandler handler = new CommandHandler(shop, io);

    // Avvia il programma CLI
    handler.run();
  }
}
