package controller.commands;

import java.util.Scanner;
import model.StockModel;
import view.StockView;

/**
 * This class examines the composition of the portfolio by the date.
 */
public class ExamineCompositionByDate extends AbstractCommand {

  /**
   * The constructor which takes the Scanner object.
   *
   * @param scanner the scanner object
   */
  public ExamineCompositionByDate(Scanner scanner) {
    super(scanner);
  }

  @Override
  public void execute(StockView stockView, StockModel stockModel) throws Exception {
    // examine composition
    // enter portfolio name
    stockView.showMessage("Please enter the flexible portfolio name you want to examine: ");
    String name = input();

    // enter date yyyy-mm-dd
    stockView.showMessage("Please enter the date you want to examine(yyyy-mm-dd): ");
    String date = input();

    // print composition
    String compostion = "";
    try {
      compostion = stockModel.getPortfolioCompositionByDate(name, date);
    } catch (IllegalArgumentException e) {
      stockView.showMessage(e.getMessage());
      stockView.showMessage("\n");
      return;
    }
    stockView.showMessage(name + ":\n" + compostion + "\n");
  }
}
