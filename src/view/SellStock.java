package view;

import static util.Utils.date2String;

import com.toedter.calendar.JDateChooser;
import controller.Features;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * This class implements the feature selling stocks for the respective portfolio in the application.
 * It extends the AbstractScreen class.It takes the name of the portfolio, stock name, quantity,
 * commission fee and selling date as input from the user and implements them according to it.
 */
class SellStock extends AbstractScreen {

  private JTextField portfolioName;
  private JTextField stockName;
  private JTextField quantity;
  private JTextField commissionFee;
  private JDateChooser sellDate;

  private JButton sell;

  /**
   * This constructor provides the functionality of selling the stock for the respective portfolio
   * in the application. It takes various inputs from the user and according to the input provided
   * it implements them.
   */
  SellStock() {
    super("Sell Stock", 400, 320);

    portfolioName = createTextField();
    addInput("Portfolio Name: ", portfolioName);

    stockName = createTextField();
    addInput("Stock symbol: ", stockName);

    quantity = createTextField();
    addInput("Quantity: ", quantity);

    sellDate = createDateChooser();
    addDateChooser("Sell Date: ", sellDate);

    commissionFee = createTextField();
    addInputWithUnit("Commission Fee: (Optional)", commissionFee, "$");

    sell = new JButton("Sell");
    addButton(sell);

  }

  @Override
  public void setFeature(Features f) {
    super.setFeature(f);
    sell.addActionListener(e ->
        f.sellStock(portfolioName.getText(), stockName.getText(), quantity.getText(),
            date2String(sellDate.getDate()),
            commissionFee.getText().isEmpty() ? "0" : commissionFee.getText())
    );
  }

}
