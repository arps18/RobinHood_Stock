package controller.commands;

import java.util.Scanner;
import model.StockModel;
import view.StockView;

/**
 * This class performs to sell the stock from the portfolio.
 */
public class SellStock extends AbstractCommand {

  /**
   * The constructor which takes the scanner object.
   *
   * @param scanner the scanner object
   */
  public SellStock(Scanner scanner) {
    super(scanner);
  }

  @Override
  public void execute(StockView stockView, StockModel stockModel) throws Exception {
    stockView.showMessage("Please enter the portfolio name you want to sell the stock at: ");
    String name = input();

    stockView.showMessage("Please enter the symbol of stock you want to sell: ");
    String symbol = input();

    // enter share
    stockView.showMessage("Please enter the share quantity you want to sell: ");
    String shares = input();

    // enter date yyyy-mm-dd
    stockView.showMessage("Please enter the date you want to sell(yyyy-mm-dd): ");
    String date = input();

    stockView.showMessage("Please enter the commission fee for this transaction: ");
    String commissionFee = input();

    try {
      stockModel.sellStock(name, symbol, shares, date, commissionFee);
    } catch (RuntimeException e) {
      stockView.showMessage(e.getMessage());
      stockView.showMessage("\n");
      return;
    }

    stockView.showMessage("Sell succeed!\n");
  }
}
