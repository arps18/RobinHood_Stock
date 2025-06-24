package controller.commands;

import java.util.Scanner;
import model.StockModel;
import view.StockView;

/**
 * This class determines the total value of the portfolio.
 */
public class DetermineTotalValue extends AbstractCommand {

  /**
   * The constructor which takes the scanner object.
   *
   * @param scanner the scanner object
   */
  public DetermineTotalValue(Scanner scanner) {
    super(scanner);
  }

  @Override
  public void execute(StockView stockView, StockModel stockModel) throws Exception {
    // determine total value
    // enter portfolio name
    stockView.showMessage(
        "Please enter the portfolio name you want to determine the total value: ");
    String name = input();

    // enter date yyyy-mm-dd
    stockView.showMessage("Please enter the date you want to determine(yyyy-mm-dd): ");
    String date = input();

    double value = 0;
    try {
      value = stockModel.getTotalValue(name, date);
    } catch (RuntimeException e) {
      stockView.showMessage(e.getMessage());
      stockView.showMessage("\n");
      return;
    }
    stockView.showMessage("Portfolio: " + name
        + " date: " + date
        + " total value(closing price): $" + value
        + "\n");
  }
}
