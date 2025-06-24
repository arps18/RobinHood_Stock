package view;

import static util.Utils.date2String;

import com.toedter.calendar.JDateChooser;
import controller.Features;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * This class implements finding the Cost Basis for the respective portfolio in the application. It
 * extends the AbstractScreen class.It takes the name of the portfolio and date as input from the
 * user and implements them according to it.
 */
class CostBasis extends AbstractScreen {

  private JTextField portfolioName;
  private JDateChooser date;

  private JButton get;

  /**
   * This constructor provides the functionality of find the costBasis for the respective portfolio
   * in the application. It takes portfolio name and date inputs from the user and according to the
   * input provided it implements them.
   */
  CostBasis() {
    super("Get Cost Basis", 450, 190);
    portfolioName = createTextField();
    addInput("Portfolio name: ", portfolioName);

    date = createDateChooser();
    addDateChooser("Choose Date: ", date);

    get = new JButton("Get");
    addButton(get);

  }

  @Override
  public void setFeature(Features f) {
    super.setFeature(f);
    get.addActionListener(e -> f.costBasis(portfolioName.getText(), date2String(date.getDate())));
  }

}
