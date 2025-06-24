package model;

import java.time.LocalDate;

/**
 * This interface represents the stocks Data that it retrieve from the source. It takes the stock
 * symbol and the date, which represents the value of shares of that particular date and the value
 * of stock on that day.
 */
interface StockData {

  /**
   * Get the Close Price of the stock in the given date.
   *
   * @param symbol takes the symbol of the listed share
   * @param date   takes the date on which the value of store is to be displayed
   * @return the symbol of the stock and date at which the value is to be found
   * @throws RuntimeException if it can't find any data from API
   */
  double getClosePrice(String symbol, LocalDate date) throws RuntimeException;

  /**
   * Checks the symbol name.
   *
   * @param symbol takes the symbol of the listed share
   * @return if the symbol is valid or not
   * @throws RuntimeException if the symbol is invalid
   */
  boolean isStockSymbolValid(String symbol) throws RuntimeException;
}
