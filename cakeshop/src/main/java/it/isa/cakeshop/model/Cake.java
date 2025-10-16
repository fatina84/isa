package it.isa.cakeshop.model;

import it.isa.cakeshop.utils.PriceFormatter;

// Classe che rappresenta una torta
public class Cake {
  private String name; // Nome della torta
  private Category category; // Categoria della torta (es. Compleanno, San Valentino...)
  private double price; // Prezzo della torta

  // Costruttore
  public Cake(String name, Category category, double price) {
    this.name = name;
    this.category = category;
    this.price = price;
  }

  // Metodi getter
  public String getName() {
    return name;
  }

  public Category getCategory() {
    return category;
  }

  public double getPrice() {
    return price;
  }

  // Metodo per stampare la torta in formato leggibile
  @Override
  public String toString() {
    return String.format(
        "Torta: %s | Categoria: %s | Prezzo: %s", name, category, PriceFormatter.format(price));
  }
}
