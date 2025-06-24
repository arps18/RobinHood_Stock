package controller.commands;

import java.util.Scanner;
import model.StockModel;
import view.StockView;

/**
 * This class examines the composition of the portfolio.
 */
public class ExamineComposition extends AbstractCommand {

  /**
   * The constructor which takes the scanner object.
   *
   * @param scanner the scanner object
   */
  public ExamineComposition(Scanner scanner) {
    super(scanner);
  }

  @Override
  public void execute(StockView stockView, StockModel stockModel) throws Exception {
    // examine composition
    // enter portfolio name
    stockView.showMessage("Please enter the inflexible portfolio name you want to examine: ");
    String name = input();

    // print composition
    String compostion = "";
    try {
      compostion = stockModel.getPortfolioComposition(name);
    } catch (RuntimeException e) {
      stockView.showMessage(e.getMessage());
      stockView.showMessage("\n");
      return;
    }
    stockView.showMessage(name + ":\n" + compostion + "\n");
  }
}
