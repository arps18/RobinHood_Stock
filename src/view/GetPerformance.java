package view;

import static util.Utils.date2String;

import com.toedter.calendar.JDateChooser;
import controller.Features;
import javax.swing.JButton;
import javax.swing.JTextField;

class GetPerformance extends AbstractScreen {

  private JTextField portfolioName;
  private JDateChooser startDate;
  private JDateChooser endDate;

  private JButton get;


  GetPerformance() {
    super("Get Performance", 400, 250);

    portfolioName = createTextField();
    addInput("Portfolio Name: ", portfolioName);

    startDate = createDateChooser();
    addDateChooser("Start Date: ", startDate);

    endDate = createDateChooser();
    addDateChooser("End Date: ", endDate);

    get = new JButton("Get");
    addButton(get);
  }

  @Override
  public void setFeature(Features f) {
    super.setFeature(f);
    get.addActionListener(
        e -> f.getPerformance(portfolioName.getText(), date2String(startDate.getDate()),
            date2String(endDate.getDate())));
  }
}
