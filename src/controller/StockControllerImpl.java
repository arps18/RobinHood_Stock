package controller;

import controller.commands.Command;
import controller.commands.CreateFlexiblePortfolio;
import controller.commands.CreateInflexiblePortfolio;
import controller.commands.DetermineCostBasis;
import controller.commands.DetermineTotalValue;
import controller.commands.ExamineComposition;
import controller.commands.ExamineCompositionByDate;
import controller.commands.GetPerformance;
import controller.commands.LoadPortfolio;
import controller.commands.PurchaseStock;
import controller.commands.SavePortfolio;
import controller.commands.SellStock;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;
import model.StockModel;
import view.StockView;

/**
 * This class represents the Stock Controller which controls the view and the model.
 */
public class StockControllerImpl implements StockController {

  private final Readable in;

  private final Map<String, Function<Scanner, Command>> knownCommands;

  private final StockView stockView;

  private final StockModel stockModel;

  /**
   * The constructor which takes inputs.
   *
   * @param stockView  the stock view
   * @param stockModel the stock model
   * @param i          reads the input
   */
  public StockControllerImpl(StockView stockView, StockModel stockModel, Readable i) {
    knownCommands = new HashMap<>();
    this.stockView = stockView;
    this.stockModel = stockModel;
    this.in = i;
  }


  @Override
  public void start() throws Exception {

    Objects.requireNonNull(stockView);
    Objects.requireNonNull(stockModel);

    Scanner scanner = new Scanner(in);

    stockView.showMessage("Welcome!\n");

    knownCommands.put("1", CreateInflexiblePortfolio::new);
    knownCommands.put("2", CreateFlexiblePortfolio::new);
    knownCommands.put("3", PurchaseStock::new);
    knownCommands.put("4", SellStock::new);
    knownCommands.put("5", ExamineComposition::new);
    knownCommands.put("6", ExamineCompositionByDate::new);
    knownCommands.put("7", DetermineTotalValue::new);
    knownCommands.put("8", DetermineCostBasis::new);
    knownCommands.put("9", GetPerformance::new);
    knownCommands.put("10", SavePortfolio::new);
    knownCommands.put("11", LoadPortfolio::new);

    while (true) {
      // action choice:
      // 1.  create inflexible portfolio
      // 2.  create flexible portfolio
      // 3.  Purchase stock in a flexible portfolio
      // 4.  Sell stock in a flexible portfolio
      // 5.  examine composition of a inflexible portfolio
      // 6.  examine composition of a flexible portfolio by certain date
      // 7.  determine total value
      // 8.  determine costs basis of a flexible portfolio
      // 9.  get portfolio performance
      // 10. save a portfolio
      // 11. load a portfolio
      // Q/q. quit

      stockView.showMessage(
          "You can choose:\n"
              + "1.  Create inflexible portfolio\n"
              + "2.  Create flexible portfolio\n"
              + "3.  Purchase stock in a flexible portfolio\n"
              + "4.  Sell stock in a flexible portfolio\n"
              + "5.  Examine portfolio composition of a inflexible portfolio\n"
              + "6.  Examine composition of a flexible portfolio by certain date\n"
              + "7.  Determine total value\n"
              + "8.  Determine costs basis of a flexible portfolio\n"
              + "9.  Get portfolio performance\n"
              + "10. Save a portfolio\n"
              + "11. Load a portfolio\n"
              + "Q/q. quit\n");
      Command c;
      String action = scanner.nextLine();
      if (action.equalsIgnoreCase("q")) {
        return;
      }

      Function<Scanner, Command> cmd = knownCommands.getOrDefault(action, null);
      if (cmd == null) {
        stockView.showMessage("Invalid choice, please enter again!\n");
        continue;
      } else {
        c = cmd.apply(scanner);
        try {
          c.execute(stockView, stockModel);
        } catch (Exception e) {
          stockView.showMessage(e.getMessage());
        }
      }

      stockView.showMessage("Press Q/q to end, else press any key for Main menu.\n");
      try {
        String input = scanner.nextLine();
        if (input.equals("Q") || input.equals("q")) {
          return;
        }
      } catch (RuntimeException e) {
        return;
      }
    }
  }
}
