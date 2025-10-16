package it.isa.cakeshop.io;

import java.util.Scanner;

// Implementazione di IOProvider che usa la console reale (System.in/out).
public class IOConsole implements IOProvider {
  private Scanner scanner = new Scanner(System.in);

  @Override
  public String readLine(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine();
  }

  @Override
  public void print(String message) {
    System.out.print(message);
  }

  @Override
  public void println(String message) {
    System.out.println(message);
  }
}
