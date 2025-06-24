package view;

import java.io.IOException;

/**
 * This class implements the StockView interface which acts as a view Model for the Stocks.
 */
public class StockViewImpl implements StockView {

  private Appendable out;

  /**
   * This is the implementation of the StockView which is the view model of the application. It
   * displays the message of the respective actions.
   *
   * @param o output of the application
   */
  public StockViewImpl(Appendable o) {
    this.out = o;
  }

  @Override
  public void showMessage(String message) {
    try {
      this.out.append(message);
    } catch (IOException e) {
      // do nothing
    }
  }
}
