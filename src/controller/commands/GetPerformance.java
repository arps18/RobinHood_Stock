package controller.commands;

import java.util.Scanner;
import model.Performance;
import model.StockModel;
import view.StockView;

/**
 * This class determines the performance of the portfolio.
 */
public class GetPerformance extends AbstractCommand {

  /**
   * The constructor which takes the scanner object.
   *
   * @param scanner the scanner object
   */
  public GetPerformance(Scanner scanner) {
    super(scanner);
  }

  @Override
  public void execute(StockView stockView, StockModel stockModel) throws Exception {
    // enter portfolio name
    stockView.showMessage("Please enter the portfolio name you want to get the performance: ");
    String name = input();

    // enter date yyyy-mm-dd
    stockView.showMessage("Please enter the start date(yyyy-mm-dd): ");
    String start = input();

    // enter date yyyy-mm-dd
    stockView.showMessage("Please enter the end date(yyyy-mm-dd): ");
    String end = input();

    try {
      Performance performance = stockModel.getPortfolioPerformance(name, start, end);
      stockView.showMessage(performance.toString());
    } catch (RuntimeException e) {
      stockView.showMessage(e.getMessage());
      stockView.showMessage("\n");
    }
  }
}
