package it.isa.cakeshop;

import it.isa.cakeshop.utils.IOProvider;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// Gestione delle operazioni relative agli ordini
public class OrderHandler {
  private final CakeShop shop;
  private final IOProvider io;

  public OrderHandler(CakeShop shop, IOProvider io) {
    this.shop = shop;
    this.io = io;
  }

  // Metodo per creare un nuovo ordine
  public void createOrder() {
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

  // Elenca tutti gli ordini
  public void listOrders() {
    shop.listAllOrders();
  }

  // Legge e stampa gli ordini salvati da file (persistenza).
  public void readOrdersFromFile() {
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
}
