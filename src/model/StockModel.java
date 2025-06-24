package model;

import java.io.IOException;
import java.util.Map;

/**
 * The interface acts as a Model and implements the actual functionalities offered by the program.
 */
public interface StockModel {

  /**
   * Creates a portfolio with a desired name.
   *
   * @param portfolioName the name of the portfolio.
   * @throws IllegalStateException if there is not a valid portfolio name.
   */
  void createInflexiblePortfolio(String portfolioName) throws IllegalArgumentException;

  /**
   * Creates a portfolio with a desired name.
   *
   * @param portfolioName the name of the portfolio.
   * @throws IllegalStateException if there is not a valid portfolio name.
   */
  void createFlexiblePortfolio(String portfolioName) throws IllegalArgumentException;

  /**
   * Examines the portfolio's details of any created inflexible portfolio.
   *
   * @param portfolioName the name of the portfolio.
   * @return the composition of portfolio.
   * @throws IllegalStateException if there is not a valid portfolio name or the portfolio does not
   *                               exist.
   */
  String getPortfolioComposition(String portfolioName) throws IllegalArgumentException;

  /**
   * Examines the portfolio's details of any created flexible portfolio by date.
   *
   * @param portfolioName the name of the portfolio.
   * @param date          the date on which shows composition
   * @return the composition of portfolio in a given date.
   * @throws IllegalArgumentException invalid input
   */
  String getPortfolioCompositionByDate(String portfolioName, String date)
      throws IllegalArgumentException;

  /**
   * Adds the stock in the portfolio.
   *
   * @param portfolioName the name of the portfolio
   * @param symbol        the stock symbol of the share
   * @param shares        quantity of the shares
   * @throws IllegalStateException if there is not a valid portfolio name or the portfolio does not
   *                               exist.
   */
  void addStock(String portfolioName, String symbol, String shares) throws RuntimeException;

  /**
   * Purchase the stock of the company listed in stock market.
   *
   * @param portfolioName the name of the portfolio
   * @param symbol        the stock symbol of the share
   * @param shares        quantity of the shares
   * @param date          the date on which stock is purchased
   * @param commissionFee the fee to be charged as commission
   * @throws RuntimeException invalid name,symbol, share quantity,date or fee
   */
  void purchaseStock(String portfolioName, String symbol, String shares, String date,
      String commissionFee)
      throws RuntimeException;

  /**
   * Sell the stock of the company listed in stock market.
   *
   * @param portfolioName the name of the portfolio
   * @param symbol        the stock symbol of the share
   * @param shares        quantity of the shares
   * @param date          the date on which stock is sold
   * @param commissionFee the fee to be charged as commission
   * @throws RuntimeException invalid name,symbol, share quantity,date or fee
   */
  void sellStock(String portfolioName, String symbol, String shares, String date,
      String commissionFee)
      throws RuntimeException;

  /**
   * Gets the total value of the portfolio.
   *
   * @param portfolioName the name of the portfolio
   * @param date          the date on which shows value of stock prices
   * @return the total value of the portfolio
   * @throws IllegalArgumentException if there is not a valid portfolio name or the portfolio does
   *                                  not exist.
   */
  double getTotalValue(String portfolioName, String date) throws IllegalArgumentException;

  /**
   * Gets the cost basis of the portfolio.
   *
   * @param portfolioName the name of the portfolio
   * @param date          the date which shows value of stock prices
   * @return the cost basis of the portfolio
   * @throws IllegalArgumentException if there is not a valid portfolio name or the portfolio does
   *                                  not exist
   */
  double getCostBasis(String portfolioName, String date) throws IllegalArgumentException;

  /**
   * Saves the portfolio with the portfolio name and fileName as Input.
   *
   * @param portfolioName the name of the portfolio.
   * @param fileName      the name of the file.
   * @throws IOException              invalid input
   * @throws IllegalArgumentException invalid portfolio or fileName
   */
  void savePortfolio(String portfolioName, String fileName) throws IOException,
      IllegalArgumentException;

  /**
   * Load a portfolio with a desired file name.
   *
   * @param fileName the name of the file.
   * @throws Exception invalid file
   */
  void loadPortfolio(String fileName) throws Exception;

  /**
   * Gets the performance of the portfolio over the provided span of duration.
   *
   * @param portfolioName the name of the portfolio.
   * @param startDate     the date at which the portfolio evaluation should start
   * @param endDate       the date at which the portfolio evaluation should end
   * @return the performance of the portfolio
   * @throws RuntimeException if it can't find any portfolio to show performance
   */
  Performance getPortfolioPerformance(String portfolioName, String startDate, String endDate)
      throws RuntimeException;

  /**
   * Calculates the Dollar cost Average of the portfolio.
   *
   * @param portfolioName the portfolio used
   * @param strategy      the strategy of the portfolio provided
   * @param startDate     start date of the Dollar cost Average
   * @param endDate       end date of the Dollar cost Average
   * @param frequency     the duration of days in which the strategy is to be implemented
   * @param amount        the amount of money that is to be used
   * @param commissionFee the fee to be taken as commission
   * @throws RuntimeException invalid strategy, startDate, endDate, frequency, amount or commission
   *                          Fee
   */

  void dollarCostAveraging(String portfolioName, Map<String, String> strategy, String startDate,
      String endDate, String frequency, String amount, String commissionFee)
      throws RuntimeException;

  /**
   * Calculates the Dollar cost Average of the portfolio once.
   *
   * @param portfolioName the portfolio used
   * @param strategy      the strategy of the portfolio provided
   * @param date          date of the Dollar cost Average
   * @param amount        the amount of money that is to be used
   * @param commissionFee the fee to be taken as commission
   * @throws RuntimeException invalid strategy, startDate, endDate, frequency, amount or commission
   *                          Fee
   */
  void dollarCostAveragingOnce(String portfolioName, Map<String, String> strategy, String date,
      String amount, String commissionFee)
      throws RuntimeException;

}