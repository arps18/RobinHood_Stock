package controller.commands;

import java.util.Scanner;
import model.StockModel;
import view.StockView;

/**
 * This class performs to save the portfolio.
 */
public class SavePortfolio extends AbstractCommand {

  /**
   * The constructor which takes the scanner object.
   *
   * @param scanner the scanner object
   */
  public SavePortfolio(Scanner scanner) {
    super(scanner);
  }

  @Override
  public void execute(StockView stockView, StockModel stockModel) throws Exception {
    // save a portfolio
    // enter portfolio name
    stockView.showMessage("Please enter the portfolio name you want to save as: ");
    String portfolioName;

    while (true) {
      try {
        portfolioName = input();
        break;
      } catch (RuntimeException e) {
        stockView.showMessage(e.getMessage());
        stockView.showMessage("\n");
      }
    }
    // enter file name
    stockView.showMessage("Please enter the file name you want to save as: ");
    String fileName;
    while (true) {
      try {
        fileName = input();
        break;
      } catch (RuntimeException e) {
        stockView.showMessage(e.getMessage());
        stockView.showMessage("\n");
      }
    }
    // handle save
    try {
      stockModel.savePortfolio(portfolioName, fileName);
    } catch (Exception e) {
      stockView.showMessage(e.getMessage());
      stockView.showMessage("\n");
      return;
    }
    stockView.showMessage(
        "Action succeed, the portfolio is saved as " + fileName + ".json!\n");
  }
}
