package model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * This interface represents a portfolio which can be created. It has methods which are used to
 * modify and observe the portfolio.
 */
interface Portfolio {

  /**
   * Gets the composition of the portfolio.
   *
   * @return the composition of the given portfolio
   * @throws IllegalArgumentException invalid portfolio name
   */
  List<Stock> getComposition() throws IllegalArgumentException;

  /**
   * Gets the composition of the portfolio by date.
   *
   * @param date date on which composition is to be checked
   * @return the composition of the given portfolio on a given date
   * @throws IllegalArgumentException invalid portfolio name
   */
  List<Stock> getCompositionByDate(LocalDate date) throws IllegalArgumentException;

  /**
   * Adds stocks to the portfolio.
   *
   * @param symbol Symbol of the share
   * @param shares quantity of the share
   * @throws RuntimeException invalid stock symbol or quantity of shares
   */
  void addStock(String symbol, double shares) throws RuntimeException;

  /**
   * Purchase the stock of the company listed in stock market.
   *
   * @param symbol        symbol of the company
   * @param shares        quantity of the share
   * @param date          date of purchase
   * @param commissionFee the fee to be taken as commission
   * @throws RuntimeException invalid stock symbol, quantity of shares or date
   */
  void purchaseStock(String symbol, double shares, LocalDate date, double commissionFee)
      throws RuntimeException;

  /**
   * Sell the stock of the company listed in stock market.
   *
   * @param symbol        symbol of the company
   * @param shares        quantity of the share
   * @param date          date of selling
   * @param commissionFee the fee to be taken as commission
   * @throws RuntimeException invalid stock symbol, quantity of shares or date
   */
  void sellStock(String symbol, double shares, LocalDate date, double commissionFee)
      throws RuntimeException;

  /**
   * Gets the total value of the portfolio.
   *
   * @param date date at which the total value is to be checked
   * @return the value of the portfolio
   * @throws IllegalArgumentException invalid date
   */
  double getTotalValue(LocalDate date) throws IllegalArgumentException;

  /**
   * Get the cost basis of the portfolio.
   *
   * @param date date at which the cost basis is to be checked
   * @return the cost basis of the portfolio
   * @throws IllegalArgumentException invalid date
   */
  double getCostBasis(LocalDate date) throws IllegalArgumentException;

  /**
   * Saves the portfolio.
   *
   * @param fileName the filename which is to be saved
   * @throws IOException              invalid input or output
   * @throws IllegalArgumentException invalid fileName
   */
  void savePortfolio(String fileName) throws IOException,
      IllegalArgumentException;


  /**
   * Get the performance of the portfolio.
   *
   * @param startDate the start date of the portfolio performance to be checked
   * @param endDate   the end date of the portfolio performance to be checked
   * @return the performance graph of the portfolio
   * @throws RuntimeException invalid date
   */
  Performance getPortfolioPerformance(LocalDate startDate, LocalDate endDate)
      throws RuntimeException;

  /**
   * Calculates the Dollar cost Average of the portfolio once.
   *
   * @param strategy      the strategy of the portfolio provided
   * @param date          start date of the Dollar cost Average
   * @param amount        the amount of money that is to be used
   * @param commissionFee the fee to be taken as commission
   * @throws RuntimeException invalid strategy, startDate, endDate, frequency, amount or commission
   *                          Fee
   */
  void dollarCostAveraging(Map<String, Double> strategy, LocalDate date, double amount,
      double commissionFee)
      throws RuntimeException;

}
