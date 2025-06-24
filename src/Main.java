import controller.StockController;
import controller.StockControllerImpl;
import controller.StockGUIControllerImpl;
import java.io.InputStreamReader;
import java.util.Scanner;
import model.StockModelImpl;
import view.StockGUIViewImpl;
import view.StockViewImpl;

/**
 * <p>
 * Demonstrates a command-line-based Stock application. The application is factored out into a
 * model, view and controller.
 * </p>
 */
public class Main {

  /**
   * Implementation of the main.
   */
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    StockController controller;
    StockModelImpl model = new StockModelImpl();

    while (true) {
      System.out.print("Text-based or GUI?(t/g): ");
      String option = scanner.nextLine();
      if (option.equals("t")) {
        controller = new StockControllerImpl(new StockViewImpl(System.out), model,
            new InputStreamReader(System.in));
        break;
      } else if (option.equals("g")) {
        controller = new StockGUIControllerImpl(model, new StockGUIViewImpl());
        break;
      } else {
        System.out.println("Invalid Choice, try again!");
      }
    }

    try {
      controller.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
