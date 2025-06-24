package view;

import static util.Utils.date2String;

import com.toedter.calendar.JDateChooser;
import controller.Features;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * This class implements the feature buying stocks for the respective portfolio in the application.
 * It extends the AbstractScreen class.It takes the name of the portfolio, stock name, quantity,
 * commission fee, purchasing date and date as input from the user and implements them according to
 * it.
 */
class BuyStock extends AbstractScreen {

  private JTextField portfolioName;
  private JTextField stockName;
  private JTextField quantity;
  private JTextField commissionFee;
  private JDateChooser buyDate;

  private JButton buy;

  /**
   * This constructor provides the functionality of purchasing the stock for the respective
   * portfolio in the application. It takes various inputs from the user and according to the input
   * provided it implements them.
   */
  BuyStock() {
    super("Buy Stock", 400, 320);

    portfolioName = createTextField();
    addInput("Portfolio Name: ", portfolioName);

    stockName = createTextField();
    addInput("Stock symbol: ", stockName);

    quantity = createTextField();
    addInput("Quantity: ", quantity);

    buyDate = createDateChooser();
    addDateChooser("Buy Date: ", buyDate);

    commissionFee = createTextField();
    addInputWithUnit("Commission Fee: (Optional)", commissionFee, "$");

    buy = new JButton("Buy");
    addButton(buy);

  }

  @Override
  public void setFeature(Features f) {
    super.setFeature(f);
    buy.addActionListener(e ->
        f.buyStock(portfolioName.getText(), stockName.getText(), quantity.getText(),
            date2String(buyDate.getDate()),
            commissionFee.getText().isEmpty() ? "0" : commissionFee.getText())
    );
  }

}
