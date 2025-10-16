package it.isa.cakeshop.cli;

import it.isa.cakeshop.io.IOProvider;
import it.isa.cakeshop.model.Cake;
import it.isa.cakeshop.model.Category;
import it.isa.cakeshop.service.CakeShop;
import java.util.List;

// Gestione delle operazioni relative alle torte
public class CakeHandler {
  private final CakeShop shop;
  private final IOProvider io;

  public CakeHandler(CakeShop shop, IOProvider io) {
    this.shop = shop;
    this.io = io;
  }

  // Metodo per aggiungere una nuova torta leggendo i dati dall'utente
  public void addCake() {
    String name = io.readLine("Nome della torta: ");
    String catStr =
        io.readLine("Categoria (BIRTHDAY, ENGAGEMENT, MOTHERS_DAY, VALENTINES_DAY, CUSTOM): ")
            .toUpperCase();
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
  public void searchCakes() {
    String keyword = io.readLine("Parola chiave: ");
    List<Cake> results = shop.searchCakes(keyword);

    if (results.isEmpty()) {
      io.println("Nessuna torta trovata.");
    } else {
      results.forEach(cake -> io.println(cake.toString()));
    }
  }

  public void listCakes() {
    shop.listAllCakes();
  }
}
