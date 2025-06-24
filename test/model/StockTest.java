package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Tests for StockTest.
 */
public class StockTest {

  /*This test does check the symbol of the stock*/
  @Test
  public void getSymbolTest() {
    Stock goog = new StockImpl("GOOG", 10);
    assertEquals("GOOG", goog.getSymbol());
  }

  /*This test does check the Quantity of the stock*/
  @Test
  public void getQuantityTest() {
    Stock goog = new StockImpl("GOOG", 10);
    assertEquals(10, goog.getShares(), 0.01);
  }

  /*Space test isn't working*/
  @Test(expected = IllegalArgumentException.class)
  public void getEmptyStringSymbolTest() {
    new StockImpl(" ", 12);
  }

  @Test(expected = IllegalArgumentException.class)
  public void InvalidShares2() {
    new StockImpl("GOOG", -1);
  }

  @Test
  public void testToString() {
    assertEquals("GOOG,100.00", new StockImpl("GOOG", 100).toString());
  }
}