package model;

import java.util.Map;

/**
 * This class implements the mock stock Model.
 */
public class MockStockModel implements StockModel {

  private StringBuilder log;

  /**
   * This constructor takes StringBuilder log.
   *
   * @param log the log of the StringBuilder
   */
  public MockStockModel(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void createInflexiblePortfolio(String portfolioName) throws IllegalArgumentException {
    log.append("create inflexible ");
    log.append(portfolioName);
    log.append("\n");
  }

  @Override
  public void createFlexiblePortfolio(String portfolioName) throws IllegalArgumentException {
    log.append("create flexible ");
    log.append(portfolioName);
    log.append("\n");
  }

  @Override
  public String getPortfolioComposition(String portfolioName) throws IllegalArgumentException {
    log.append("composition ");
    log.append(portfolioName);
    log.append("\n");

    return null;
  }

  @Override
  public String getPortfolioCompositionByDate(String portfolioName, String date)
      throws IllegalArgumentException {
    log.append("composition by date ");
    log.append(portfolioName);
    log.append(" ");
    log.append(date);
    log.append("\n");
    return null;
  }

  @Override
  public void addStock(String portfolioName, String symbol, String shares)
      throws IllegalArgumentException {
    log.append("add ");
    log.append(portfolioName);
    log.append(" ");
    log.append(symbol);
    log.append(" ");
    log.append(shares);
    log.append("\n");
  }

  @Override
  public void purchaseStock(String portfolioName, String symbol, String shares, String date,
      String commissionFee) throws RuntimeException {

    log.append("Purchase Stock for ");
    log.append(portfolioName);
    log.append(" ");
    log.append(symbol);
    log.append(" ");
    log.append(shares);
    log.append(" ");
    log.append(date);
    log.append(" ");
    log.append(commissionFee);
    log.append("\n");
  }

  @Override
  public void sellStock(String portfolioName, String symbol, String shares, String date,
      String commissionFee) throws RuntimeException {

    log.append("Sell Stock for ");
    log.append(portfolioName);
    log.append(" ");
    log.append(symbol);
    log.append(" ");
    log.append(shares);
    log.append(" ");
    log.append(date);
    log.append(" ");
    log.append(commissionFee);
    log.append("\n");
  }

  @Override
  public double getTotalValue(String portfolioName, String date) throws IllegalArgumentException {
    log.append("value ");
    log.append(portfolioName);
    log.append(" ");
    log.append(date);
    log.append("\n");

    return 0;
  }

  @Override
  public double getCostBasis(String portfolioName, String date) throws IllegalArgumentException {

    log.append("Get cost basis of ");
    log.append(portfolioName);
    log.append(" ");
    log.append(date);
    log.append("\n");
    return 0;
  }

  @Override
  public void savePortfolio(String portfolioName, String fileName) {
    log.append("save ");
    log.append(portfolioName);
    log.append(" ");
    log.append(fileName);
    log.append("\n");
  }

  @Override
  public void loadPortfolio(String fileName) {
    log.append("load ");
    log.append(fileName);
    log.append("\n");
  }

  @Override
  public Performance getPortfolioPerformance(String portfolioName, String startDate,
      String endDate) throws RuntimeException {
    log.append("performance of portfolio ");
    log.append(portfolioName);
    log.append(" ");
    log.append("from");
    log.append(" ");
    log.append(startDate);
    log.append(" ");
    log.append(endDate);
    log.append("\n");

    return null;
  }

  @Override
  public void dollarCostAveraging(String portfolioName, Map<String, String> strategy,
      String startDate, String endDate, String frequency, String amount, String commissionFee)
      throws RuntimeException {
    // Nothing to Mock
  }

  @Override
  public void dollarCostAveragingOnce(String portfolioName, Map<String, String> strategy,
      String date, String amount, String commissionFee) throws RuntimeException {
    // Nothing to Mock
  }
}
