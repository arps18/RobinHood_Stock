package view;

import static util.Utils.date2String;

import com.toedter.calendar.JDateChooser;
import controller.Features;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * This class implements the feature get value for the respective portfolio in the application. It
 * extends the AbstractScreen class.It takes the name of the portfolio and date as input from the
 * user and implements them according to it.
 */
class GetValue extends AbstractScreen {

  private JTextField portfolioName;
  private JDateChooser date;

  private JButton get;

  /**
   * This constructor provides the functionality of get value for the respective portfolio in the
   * application. It takes portfolio name and date as inputs from the user and according to the
   * input provided it implements them.
   */
  GetValue() {
    super("Get Value of Portfolio", 450, 190);
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
    this.get.addActionListener(
        e -> f.getValueByDate(portfolioName.getText(), date2String(date.getDate())));
  }
}
