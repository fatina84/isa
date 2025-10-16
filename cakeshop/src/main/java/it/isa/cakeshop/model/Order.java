package it.isa.cakeshop.model;

import it.isa.cakeshop.utils.PriceFormatter;
import java.util.*;

// Classe che rappresenta un ordine
public class Order {
  private String customerName; // Nome del cliente
  private List<Cake> orderedCakes; // Elenco delle torte ordinate
  private Date orderDate; // Data dell'ordine

  // Costruttore
  public Order(String customerName, List<Cake> orderedCakes) {
    this.customerName = customerName;
    this.orderedCakes = orderedCakes;
    this.orderDate = new Date(); // Imposta la data corrente
  }

  // Metodo per ottenere il prezzo totale
  public double getTotalPrice() {
    return orderedCakes.stream().mapToDouble(Cake::getPrice).sum();
  }

  public String getCustomerName() {
    return customerName;
  }

  public List<Cake> getOrderedCakes() {
    return orderedCakes;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  // Rappresentazione in stringa
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("🧾 Ordine di ").append(customerName).append(" (").append(orderDate).append(")\n");
    for (Cake cake : orderedCakes) {
      sb.append("  - ").append(cake).append("\n");
    }
    sb.append(String.format("Totale: %s", PriceFormatter.format(getTotalPrice())));
    return sb.toString();
  }
}
