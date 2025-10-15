package it.isa.cakeshop;

import it.isa.cakeshop.utils.IOProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

//Classe che gestisce i comandi dell'utente da riga di comando
//Usa IOProvider per rendere il codice testabile (input/output astratti).
public class CommandHandler {
    private final CakeShop shop;         // Istanza del negozio di torte
    private final IOProvider io;        // Istanza dell'interfaccia IOProvider

    //Costruttore che accetta un'istanza di IOProvider (ConsoleIO o MockIO)
    public CommandHandler(CakeShop shop, IOProvider io) {
        this.shop = shop;
        this.io = io;
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
            case "list":
                shop.listAllCakes();
                break;
            case "add":
                handleAdd();
                break;
            case "search":
                handleSearch();
                break;
            case "order":
                handleOrder();
                break;
            case "orders":
                shop.listAllOrders();
                break;
            case "orders-file":
                printOrdersFromFile();
                break;
            case "help":
                printHelp();
                break;
            case "exit":
                io.println("Arrivederci!");
                break;
            default:
                io.println("Comando non riconosciuto. Digita 'help' per assistenza.");
        }
    }

    // Metodo per aggiungere una nuova torta leggendo i dati dall'utente
    private void handleAdd() {
        String name = io.readLine("Nome della torta: ");
        String catStr = io.readLine("Categoria (BIRTHDAY, ENGAGEMENT, MOTHERS_DAY, VALENTINES_DAY, CUSTOM): ").toUpperCase();
        String priceStr = io.readLine("Prezzo (€): ");

        try {
            double price = Double.parseDouble(priceStr);
            Category category = Category.valueOf(catStr);
            Cake cake = new Cake(name, category, price);
            shop.addCake(cake);
            io.println("Torta aggiunta con successo!");
        } catch (NumberFormatException e) {
            io.println("Prezzo non valido. Inserisci un numero.");
        } catch (IllegalArgumentException e) {
            io.println("Categoria non valida. Usa solo le categorie supportate.");
        }
    }

    // Metodo per cercare torte per parola chiave
    private void handleSearch() {
        String keyword = io.readLine("Parola chiave: ");
        List<Cake> results = shop.searchCakes(keyword);

        if (results.isEmpty()) {
            io.println("Nessuna torta trovata.");
        } else {
            results.forEach(cake -> io.println(cake.toString()));
        }
    }

    // Metodo per creare un nuovo ordine
    private void handleOrder() {
        String customerName = io.readLine("Nome del cliente: ");
        List<Cake> selectedCakes = new ArrayList<>();
        List<Cake> allCakes = shop.getAllCakes();

        if (allCakes.isEmpty()) {
            io.println("Nessuna torta disponibile per ordinare.");
            return;
        }

        io.println("Torte disponibili:");
        for (int i = 0; i < allCakes.size(); i++) {
            io.println("[" + i + "] " + allCakes.get(i));
        }

        String input = io.readLine("Inserisci i numeri delle torte da ordinare (es: 0,2,3): ");
        String[] indices = input.split(",");

        for (String indexStr : indices) {
            try {
                int index = Integer.parseInt(indexStr.trim());
                if (index >= 0 && index < allCakes.size()) {
                    selectedCakes.add(allCakes.get(index));
                } else {
                    io.println("Indice non valido: " + index);
                }
            } catch (NumberFormatException e) {
                io.println("Errore di formato: " + indexStr.trim());
            }
        }

        if (!selectedCakes.isEmpty()) {
            Order order = new Order(customerName, selectedCakes);
            shop.addOrder(order);
        } else {
            io.println("Nessuna torta selezionata. Ordine annullato.");
        }
    }

    //Legge e stampa gli ordini salvati da file (persistenza).
    private void printOrdersFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("data/ordini.txt"));
            if (lines.isEmpty()) {
                io.println("Il file degli ordini è vuoto.");
            } else {
                lines.forEach(io::println);
            }
        } catch (IOException e) {
            io.println("Errore nella lettura del file: " + e.getMessage());
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
