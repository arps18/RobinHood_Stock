package controller.commands;

import java.util.Scanner;
import model.StockModel;
import view.StockView;

/**
 * This class performs the loading of the portfolio.
 */
public class LoadPortfolio extends AbstractCommand {

  /**
   * The constructor which takes the scanner object.
   *
   * @param scanner the scanner object
   */
  public LoadPortfolio(Scanner scanner) {
    super(scanner);
  }

  @Override
  public void execute(StockView stockView, StockModel stockModel) throws Exception {
    // load a portfolio
    // enter file name
    stockView.showMessage(
        "Please enter the file(json, include the extension) "
            + "name you want to load as a portfolio: ");
    String fileName = input();

    // handle load
    try {
      stockModel.loadPortfolio(fileName);
    } catch (Exception e) {
      stockView.showMessage(e.getMessage());
      stockView.showMessage("\n");
      return;
    }
    stockView.showMessage("Action succeed!\n");
  }
}
