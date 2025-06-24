package controller.commands;

import java.util.Scanner;
import model.StockModel;
import view.StockView;

/**
 * This class performs the purchase the stock of the company.
 */
public class PurchaseStock extends AbstractCommand {

  /**
   * The constructor which takes the scanner object.
   *
   * @param scanner the scanner object
   */
  public PurchaseStock(Scanner scanner) {
    super(scanner);
  }

  @Override
  public void execute(StockView stockView, StockModel stockModel) throws Exception {
    stockView.showMessage("Please enter the portfolio name you want to purchase the stock at: ");
    String name = input();

    stockView.showMessage("Please enter the symbol of stock you want to purchase: ");
    String symbol = input();

    // enter share
    stockView.showMessage("Please enter the share quantity you want to purchase: ");
    String shares = input();

    // enter date yyyy-mm-dd
    stockView.showMessage("Please enter the date you want to purchase(yyyy-mm-dd): ");
    String date = input();

    stockView.showMessage("Please enter the commission fee for this transaction: ");
    String commissionFee = input();

    try {
      stockModel.purchaseStock(name, symbol, shares, date, commissionFee);
    } catch (RuntimeException e) {
      stockView.showMessage(e.getMessage());
      stockView.showMessage("\n");
      return;
    }

    stockView.showMessage("Purchase succeed!\n");

  }
}
