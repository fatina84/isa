package it.isa.cakeshop;

// Classe principale dell'applicazione
public class App {
    public static void main(String[] args) {
        // Crea un nuovo negozio di torte
        CakeShop shop = new CakeShop();

        // Avvia il gestore dei comandi
        CommandHandler handler = new CommandHandler(shop);

        // Avvia il programma CLI
        handler.run();
    }
}
