package controller;

import java.io.IOException;
import java.util.Map;
import model.Performance;
import model.StockModel;
import view.StockGUIView;


/**
 * This class is the controller of the GUI part of the application. It implements the features of
 * the application and the StockController. It controls all the features that are displayed on the
 * frames of the application.
 */
public class StockGUIControllerImpl implements Features, StockController {

  private final StockModel model;

  private final StockGUIView view;


  /**
   * This constructor takes the stockModel and stockGUIView objects as parameters.
   *
   * @param stockModel   the stockModel object
   * @param stockGUIView the stockGUIView object
   */
  public StockGUIControllerImpl(StockModel stockModel, StockGUIView stockGUIView) {
    this.model = stockModel;
    this.view = stockGUIView;
    this.view.setFeatures(this);
  }

  @Override
  public void start() throws Exception {
    view.showHome();
  }


  @Override
  public void backHome() {
    view.backHome();
  }

  @Override
  public void showCreatePortfolio() {
    view.hideHome();
    view.showCreatePortfolio();
  }

  @Override
  public void showBuyStock() {
    view.hideHome();
    view.showBuyStock();
  }

  @Override
  public void showSellStock() {
    view.hideHome();
    view.showSellStock();
  }

  @Override
  public void showDollarCostAveraging() {
    view.hideHome();
    view.showDollarCostAveraging();
  }

  @Override
  public void showGetValue() {
    view.hideHome();
    view.showGetValue();
  }

  @Override
  public void showGetCostBasis() {
    view.hideHome();
    view.showGetCostBasis();
  }

  @Override
  public void showSavePortfolio() {
    view.hideHome();
    view.showSavePortfolio();
  }

  @Override
  public void showLoadPortfolio() {
    view.hideHome();
    view.showLoadPortfolio();
  }

  @Override
  public void showGetPerformance() {
    view.hideHome();
    view.showGetPerformance();
  }

  @Override
  public void createPortfolio(String name) {
    try {
      model.createFlexiblePortfolio(name);
    } catch (IllegalArgumentException e) {
      view.showError(e.getMessage());
      return;
    }

    view.showMessage("Create Successful!");
  }

  @Override
  public void buyStock(String portfolioName, String symbol, String quantity, String date,
      String fee) {
    try {
      model.purchaseStock(portfolioName, symbol, quantity, date, fee);
    } catch (RuntimeException e) {
      view.showError(e.getMessage());
      return;
    }

    view.showMessage("Purchase Successful!");
  }

  @Override
  public void getValueByDate(String portfolioName, String date) {
    double value = 0;
    try {
      value = model.getTotalValue(portfolioName, date);
    } catch (IllegalArgumentException e) {
      view.showError(e.getMessage());
      return;
    }

    view.showMessage("Portfolio: " + portfolioName
        + " date: " + date
        + " total value(closing price): $" + value);
  }

  @Override
  public void costBasis(String portfolioName, String date) {
    double cost = 0;
    try {
      cost = model.getCostBasis(portfolioName, date);
    } catch (IllegalArgumentException e) {
      view.showError(e.getMessage());
      return;
    }
    view.showMessage("Portfolio: " + portfolioName
        + " date: " + date
        + " cost basis: $" + cost);
  }

  @Override
  public void sellStock(String portfolioName, String symbol, String quantity, String date,
      String fee) {
    try {
      model.sellStock(portfolioName, symbol, quantity, date, fee);
    } catch (RuntimeException e) {
      view.showError(e.getMessage());
      return;
    }

    view.showMessage("Sell Successful!");
  }

  @Override
  public void loadPortfolio(String file) {
    try {
      model.loadPortfolio(file);
    } catch (Exception e) {
      view.showError(e.getMessage());
      return;
    }

    view.showMessage("Load Successful!");
  }

  @Override
  public void savePortfolio(String portfolioName, String fileName) {
    try {
      model.savePortfolio(portfolioName, fileName);
    } catch (IOException e) {
      view.showError("Save Failed");
      return;
    } catch (IllegalArgumentException e) {
      view.showError(e.getMessage());
      return;
    }

    view.showMessage("Save Successful!");
  }

  @Override
  public void getPerformance(String portfolioName, String startDate, String endDate) {
    Performance p = null;
    try {
      p = model.getPortfolioPerformance(portfolioName, startDate, endDate);
    } catch (RuntimeException e) {
      view.showError(e.getMessage());
      return;
    }

    view.showPerformance(p);
  }

  @Override
  public void dollarCostAveraging(String portfolioName, Map<String, String> strategy,
      String startDate,
      String endDate, String frequency, String amount, String fee) {

    try {
      model.dollarCostAveraging(portfolioName, strategy, startDate, endDate, frequency, amount,
          fee);
    } catch (RuntimeException e) {
      view.showError(e.getMessage());
      return;
    }

    view.showMessage("Apply Strategy Successful!");
  }

  @Override
  public void dollarCostAveragingOnce(String portfolioName, Map<String, String> strategy,
      String date, String amount, String fee) {
    try {
      model.dollarCostAveragingOnce(portfolioName, strategy, date, amount, fee);
    } catch (RuntimeException e) {
      view.showError(e.getMessage());
      return;
    }

    view.showMessage("Buy Successful!");
  }


}
