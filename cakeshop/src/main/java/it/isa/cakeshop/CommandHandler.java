package it.isa.cakeshop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe che gestisce i comandi dell'utente da riga di comando
public class CommandHandler {
    private CakeShop shop;         // Istanza del negozio di torte
    private Scanner scanner = new Scanner(System.in); // Scanner per leggere l'input dell'utente

    // Costruttore
    public CommandHandler(CakeShop shop) {
        this.shop = shop;
    }

    // Metodo principale che avvia il ciclo dei comandi
    public void run() {
        String command;
        System.out.println("Benvenuto alla Cake Shop CLI!");
        System.out.println("Digita 'help' per vedere i comandi disponibili.");
        do {
            System.out.print("> ");
            command = scanner.nextLine();
            handleCommand(command); // Gestisce il comando inserito
        } while (!command.equalsIgnoreCase("exit"));
    }

    // Gestione dei vari comandi
    private void handleCommand(String command) {
        switch (command.toLowerCase()) {
            case "list":
                shop.listAllCakes(); // Elenca tutte le torte
                break;
            case "add":
                handleAdd();         // Aggiunge una nuova torta
                break;
            case "search":
                handleSearch();      // Cerca una torta per nome
                break;
            case "help":
                printHelp();         // Mostra i comandi disponibili
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
            case "exit":
                System.out.println("Arrivederci!");
                break;
            default:
                System.out.println("Comando non riconosciuto. Digita 'help' per assistenza.");
        }
    }

    // Metodo per aggiungere una nuova torta leggendo i dati dall'utente
    private void handleAdd() {
        System.out.print("Nome della torta: ");
        String name = scanner.nextLine();

        System.out.print("Categoria (BIRTHDAY, ENGAGEMENT, MOTHERS_DAY, VALENTINES_DAY, CUSTOM): ");
        String catStr = scanner.nextLine().toUpperCase();

        System.out.print("Prezzo (€): ");
        double price = Double.parseDouble(scanner.nextLine());

        try {
            Category category = Category.valueOf(catStr); // Converte stringa in enum
            shop.addCake(new Cake(name, category, price));
            System.out.println("Torta aggiunta con successo!");
        } catch (IllegalArgumentException e) {
            System.out.println("Categoria non valida.");
        }
    }

    // Metodo per cercare torte per parola chiave
    private void handleSearch() {
        System.out.print("Parola chiave: ");
        String keyword = scanner.nextLine();

        var results = shop.searchCakes(keyword);
        if (results.isEmpty()) {
            System.out.println("Nessuna torta trovata.");
        } else {
            results.forEach(System.out::println);
        }
    }

    // Metodo per creare un nuovo ordine
    private void handleOrder() {
        System.out.print("Nome del cliente: ");
        String customerName = scanner.nextLine();

        List<Cake> selectedCakes = new ArrayList<>();
        List<Cake> allCakes = shop.getAllCakes();

        if (allCakes.isEmpty()) {
            System.out.println("Nessuna torta disponibile per ordinare.");
            return;
        }

        System.out.println("Torte disponibili:");
        for (int i = 0; i < allCakes.size(); i++) {
            System.out.printf("[%d] %s\n", i, allCakes.get(i));
        }

        System.out.println("Inserisci i numeri delle torte che vuoi ordinare (separati da virgola): ");
        System.out.print("Esempio: 0,2,3 > ");
        String[] indices = scanner.nextLine().split(",");

        for (String indexStr : indices) {
            try {
                int index = Integer.parseInt(indexStr.trim());
                if (index >= 0 && index < allCakes.size()) {
                    selectedCakes.add(allCakes.get(index));
                } else {
                    System.out.println("Indice non valido: " + index);
                }
            } catch (NumberFormatException e) {
                System.out.println("Errore di formato: " + indexStr);
            }
        }

        if (!selectedCakes.isEmpty()) {
            Order order = new Order(customerName, selectedCakes);
            shop.addOrder(order);
        } else {
            System.out.println("Nessuna torta selezionata, ordine annullato.");
        }
    }

    private void printOrdersFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("ordini.txt"));
            lines.forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + e.getMessage());
        }
    }



    // Mostra l'elenco dei comandi
    private void printHelp() {
        System.out.println("Comandi disponibili:");
        System.out.println("add    - Aggiungi una nuova torta");
        System.out.println("list   - Elenca tutte le torte");
        System.out.println("search - Cerca torte per nome");
        System.out.println("help   - Mostra questo messaggio");
        System.out.println("exit   - Esci dall'applicazione");
        System.out.println("order  - Crea un nuovo ordine");
        System.out.println("orders - Mostra tutti gli ordini");

    }
}
