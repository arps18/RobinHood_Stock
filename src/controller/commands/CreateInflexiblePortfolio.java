package controller.commands;

import java.util.Scanner;
import model.StockModel;
import view.StockView;

/**
 * This is an inflexible portfolio which doesn't allow any modification once the portfolio is
 * created.
 */
public class CreateInflexiblePortfolio extends AbstractCommand {

  /**
   * The constructor which takes the Scanner object.
   *
   * @param scanner the scanner object
   */
  public CreateInflexiblePortfolio(Scanner scanner) {
    super(scanner);
  }

  @Override
  public void execute(StockView stockView, StockModel stockModel) throws Exception {
    // create portfolio
    // enter portfolio name
    stockView.showMessage("Please enter the inflexible portfolio name you want to create: ");
    String name = input();

    try {
      // handle new portfolio name
      stockModel.createInflexiblePortfolio(name);
    } catch (IllegalArgumentException e) {
      stockView.showMessage(e.getMessage());
      stockView.showMessage("\n");
      return;
    }
    stockView.showMessage("Create succeed!\n");
    while (true) {
      // action choice:
      // add stock / finish
      stockView.showMessage("You can press + to add a stock to " + name
          + ", else press any key to continue.\n");
      // get input choice
      String createAction = input();

      if (createAction.equals("+")) {
        // add stock
        // enter stock symbol
        stockView.showMessage("Please enter the stock symbol: ");
        String symbol = input();

        // enter share
        stockView.showMessage("Please enter the quantity of share: ");
        String shares = input();
        try {
          stockModel.addStock(name, symbol, shares);
        } catch (Exception e) {
          stockView.showMessage(e.getMessage());
          stockView.showMessage("\n");
          continue;
        }
        stockView.showMessage("Add succeed!\n");
      } else {
        break;
      }
    }
  }
}
