package it.isa.cakeshop.utils;

public class PriceFormatter {
  public static final String CURRENCY = "€";

  public static String format(double price) {
    return String.format("%.2f%s", price, CURRENCY);
  }
}
