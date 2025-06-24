package controller;

import java.util.Map;

/**
 * This interface list all the features of the application GUI. It covers the featuring methods that
 * is to be displayed and show the respective screens of the features.
 */
public interface Features {

  /**
   * It reverts to Home Screen.
   */
  void backHome();

  /**
   * Controls the createPortfolio.
   */
  void showCreatePortfolio();

  /**
   * Controls the showBuyStock.
   */
  void showBuyStock();

  /**
   * Controls the showSellStock.
   */
  void showSellStock();

  /**
   * Controls the showDollarCostAveraging.
   */
  void showDollarCostAveraging();

  /**
   * Controls the showGetValue.
   */
  void showGetValue();

  /**
   * Controls the showGetCostBasis.
   */
  void showGetCostBasis();

  /**
   * Controls the showSavePortfolio.
   */
  void showSavePortfolio();

  /**
   * Controls the showLoadPortfolio.
   */
  void showLoadPortfolio();

  /**
   * Controls the showGetPerformance.
   */
  void showGetPerformance();

  /**
   * Implements the createPortfolio.
   *
   * @param name takes the name of the portfolio
   */
  void createPortfolio(String name);

  /**
   * Implements the buyStock.
   *
   * @param portfolioName the name of the Portfolio
   * @param symbol        the stock symbol
   * @param quantity      the quantity of the shares
   * @param date          date of the purchase of the stocks
   * @param fee           the commission fee to be charged
   */
  void buyStock(String portfolioName, String symbol, String quantity, String date, String fee);

  /**
   * Implements the getValueByDate.
   *
   * @param portfolioName the name of the Portfolio
   * @param date          date at which value is to be calculated
   */
  void getValueByDate(String portfolioName, String date);

  /**
   * Implements the costBasis.
   *
   * @param portfolioName the name of the Portfolio
   * @param date          date at which cost basis is to be calculated
   */
  void costBasis(String portfolioName, String date);

  /**
   * Implements the sellStock.
   *
   * @param portfolioName the name of the Portfolio
   * @param symbol        the stock symbol
   * @param quantity      the quantity of the shares
   * @param date          date of the selling of the stocks
   * @param fee           the commission fee to be charged
   */
  void sellStock(String portfolioName, String symbol, String quantity, String date, String fee);

  /**
   * Implements the loadPortfolio.
   *
   * @param file the file name to be loaded
   */
  void loadPortfolio(String file);

  /**
   * Implements the savePortfolio.
   *
   * @param portfolioName the name of the Portfolio
   * @param fileName      the file name to be saved
   */
  void savePortfolio(String portfolioName, String fileName);

  /**
   * Implements the getPerformance.
   *
   * @param portfolioName the name of the Portfolio
   * @param startDate     the start date of the performance
   * @param endDate       the end date of the performance
   */
  void getPerformance(String portfolioName, String startDate, String endDate);

  /**
   * Implements the dollar cost average.
   *
   * @param portfolioName the name of the Portfolio
   * @param strategy      the strategy to be undertaken
   * @param startDate     start date of the dollar cost average
   * @param endDate       end date of the dollar cost average
   * @param frequency     the duration of days when it is to be implemented
   * @param amount        the amount used for investing
   * @param fee           the commission fee to be charged
   */
  void dollarCostAveraging(String portfolioName, Map<String, String> strategy, String startDate,
      String endDate, String frequency, String amount, String fee);

  /**
   * Implements the dollar cost average once.
   *
   * @param portfolioName the name of the Portfolio
   * @param strategy      the strategy to be undertaken
   * @param date          date of the dollar cost average
   * @param amount        the amount used for investing
   * @param fee           the commission fee to be charged
   */
  void dollarCostAveragingOnce(String portfolioName, Map<String, String> strategy, String date,
      String amount, String fee);

}
