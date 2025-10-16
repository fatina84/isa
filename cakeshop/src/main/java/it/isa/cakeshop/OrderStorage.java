package it.isa.cakeshop;

import it.isa.cakeshop.utils.PriceFormatter;
import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.List;

// Classe per salvare gli ordini su file
public class OrderStorage {
  private static final String FILE_NAME = "./data/ordini.txt"; // Nome del file
  private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm";

  // Salva un ordine su file (in append)
  public static void saveOrder(Order order) {
    try {
      // Ensure the parent directory exists
      Path filePath = Paths.get(FILE_NAME);
      Files.createDirectories(filePath.getParent());

      try (BufferedWriter writer =
          Files.newBufferedWriter(filePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
        writer.write(
            "Ordine di "
                + order.getCustomerName()
                + " - "
                + new SimpleDateFormat(DATE_FORMAT).format(order.getOrderDate()));
        writer.newLine();

        List<Cake> cakes = order.getOrderedCakes();
        for (Cake cake : cakes) {
          writer.write(" - " + cake.toString());
          writer.newLine();
        }

        writer.write(String.format("Totale: %s", PriceFormatter.format(order.getTotalPrice())));
        writer.newLine();
        writer.write("--------------------------------------------------");
        writer.newLine();
      }

    } catch (IOException e) {
      System.out.println("Errore nel salvataggio dell'ordine: " + e.getMessage());
    }
  }
}
