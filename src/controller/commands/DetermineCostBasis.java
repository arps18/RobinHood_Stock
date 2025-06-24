package controller.commands;

import java.util.Scanner;
import model.StockModel;
import view.StockView;

/**
 * This class determines the cost basis of the flexible portfolio.
 */
public class DetermineCostBasis extends AbstractCommand {

  /**
   * The constructor which takes the scanner object.
   *
   * @param scanner the scanner object.
   */
  public DetermineCostBasis(Scanner scanner) {
    super(scanner);
  }

  @Override
  public void execute(StockView stockView, StockModel stockModel) throws Exception {
    stockView.showMessage("Please enter the portfolio name you want to determine the cost basis: ");
    String name = input();

    // enter date yyyy-mm-dd
    stockView.showMessage("Please enter the date you want to determine(yyyy-mm-dd): ");
    String date = input();

    double cost;
    try {
      cost = stockModel.getCostBasis(name, date);
    } catch (IllegalArgumentException e) {
      stockView.showMessage(e.getMessage());
      stockView.showMessage("\n");
      return;
    }
    stockView.showMessage("Portfolio: " + name
        + " date: " + date
        + " cost basis: $" + cost
        + "\n");
  }
}
