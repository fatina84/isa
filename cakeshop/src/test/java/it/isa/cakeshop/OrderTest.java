package it.isa.cakeshop;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import it.isa.cakeshop.model.Cake;
import it.isa.cakeshop.model.Category;
import it.isa.cakeshop.model.Order;

// Test per la classe Order
public class OrderTest {

  @Test
  public void testTotalPrice() {
    Cake c1 = new Cake("Torta Cioccolato", Category.BIRTHDAY, 20.0);
    Cake c2 = new Cake("Torta Vaniglia", Category.ENGAGEMENT, 30.0);

    Order order = new Order("Mario", Arrays.asList(c1, c2));
    assertEquals(50.0, order.getTotalPrice(), 0.001);
  }

  @Test
  public void testToStringIncludesCustomerName() {
    Cake cake = new Cake("Torta Limone", Category.CUSTOM, 15.0);
    Order order = new Order("Luca", List.of(cake));

    String output = order.toString();
    assertTrue(output.contains("Luca"));
    assertTrue(output.contains("Torta Limone"));
    assertTrue(output.contains("15.00"));
  }
}
