package it.isa.cakeshop.utils;

/**
 * Interfaccia per gestire input/output, così da permettere test unitari facilmente (mockando
 * l'input dell'utente).
 */
public interface IOProvider {
  // Legge una riga da input, mostrando un prompt.
  String readLine(String prompt);

  // Stampa un messaggio senza newline.

  void print(String message);

  // Stampa un messaggio con newline.
  void println(String message);
}
