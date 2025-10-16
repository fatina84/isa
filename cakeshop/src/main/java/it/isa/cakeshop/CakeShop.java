package it.isa.cakeshop;

import java.util.*;

// Classe che gestisce torte e ordini
public class CakeShop {
    private List<Cake> cakes = new ArrayList<>();     // Elenco delle torte disponibili
    private List<Order> orders = new ArrayList<>();   // Elenco degli ordini effettuati

    // Aggiunge una nuova torta
    public void addCake(Cake cake) {
        cakes.add(cake);
    }

    // Restituisce le torte di una categoria
    public List<Cake> getCakesByCategory(Category category) {
        return cakes.stream()
                .filter(cake -> cake.getCategory() == category)
                .toList();
    }

    // Cerca torte per parola chiave
    public List<Cake> searchCakes(String keyword) {
        return cakes.stream()
                .filter(cake -> cake.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    // Elenca tutte le torte
    public void listAllCakes() {
        if (cakes.isEmpty()) {
            System.out.println("Nessuna torta disponibile.");
        } else {
            cakes.forEach(System.out::println);
        }
    }

    // Restituisce tutte le torte disponibili
    public List<Cake> getAllCakes() {
        return cakes;
    }

    // Aggiunge un ordine
    public void addOrder(Order order) {
        orders.add(order);                // Salva in memoria
        OrderStorage.saveOrder(order);   // Salva nel file
        System.out.println("✅ Ordine salvato su file!");
    }


    // Elenca tutti gli ordini
    public void listAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("Nessun ordine trovato.");
        } else {
            orders.forEach(order -> {
                System.out.println(order);
                System.out.println("----------------------------");
            });
        }
    }
}
