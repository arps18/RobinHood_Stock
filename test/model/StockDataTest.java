package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for StockData.
 */
public class StockDataTest {

  private StockData stockData;

  @Before
  public void setUp() {
    stockData = new AlphaVantageStockDataImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetClosePriceEmptySymbol() {
    stockData.getClosePrice("  ", LocalDate.parse("2022-10-03"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetClosePriceNullSymbol() {
    stockData.getClosePrice(null, LocalDate.parse("2022-10-03"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetClosePriceNullDate() {
    stockData.getClosePrice("GOOG", null);
  }

  @Test
  public void testGetClosePrice() {
    double goog = stockData.getClosePrice("GOOG", LocalDate.parse("2022-10-03"));

    assertEquals(99.3, goog, 0.0001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIsStockSymbolValidInvalidSymbol() {
    stockData.isStockSymbolValid(" ");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIsStockSymbolValidNullSymbol() {
    stockData.isStockSymbolValid(null);
  }

  @Test
  public void testIsStockSymbolValid() {
    assertTrue(stockData.isStockSymbolValid("GOOG"));
    assertTrue(stockData.isStockSymbolValid("AAPL"));
    assertFalse(stockData.isStockSymbolValid("GOOGAVS"));
  }
}