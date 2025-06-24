package controller.commands;

import java.util.Scanner;
import model.StockModel;
import view.StockView;

/**
 * This is a portfolio command class which allows the user to create a flexible portfolio.
 */
public class CreateFlexiblePortfolio extends AbstractCommand {

  /**
   * The constructor which takes the Scanner object.
   *
   * @param scanner the Scanner object
   */
  public CreateFlexiblePortfolio(Scanner scanner) {
    super(scanner);
  }

  @Override
  public void execute(StockView stockView, StockModel stockModel) throws Exception {
    stockView.showMessage("Please enter the flexible portfolio name you want to create: ");
    String name = input();

    try {
      stockModel.createFlexiblePortfolio(name);
    } catch (IllegalArgumentException e) {
      stockView.showMessage(e.getMessage());
      stockView.showMessage("\n");
      return;
    }

    stockView.showMessage("Create succeed!\n");
  }
}
