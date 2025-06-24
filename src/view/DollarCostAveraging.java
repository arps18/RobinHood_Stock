package view;

import static util.Utils.date2String;

import com.toedter.calendar.JDateChooser;
import controller.Features;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This class implements finding the Dollar Cost Average for the portfolio in the application. It
 * extends the AbstractScreen class.It takes various inputs from the user and implements them
 * according to it.
 */
class DollarCostAveraging extends AbstractScreen {

  private JTextField portfolioName;
  private Strategy strategy;
  private JLabel start;
  private JDateChooser startDate;
  private JLabel end;
  private JDateChooser endDate;
  private JLabel frequencyLabel;
  private JTextField frequency;
  private JPanel frequencyPanel;
  private JTextField amount;
  private JTextField commissionFee;

  private JCheckBox isPeriod;

  private JButton submit;

  /**
   * This constructor provides the functionality of dollar cost average for the respective portfolio
   * in the application. It takes various inputs from the user and according to the input provided
   * it implements them.
   */
  DollarCostAveraging() {
    super("Dollar Cost Averaging", 400, 450);

    portfolioName = createTextField();
    addInput("Portfolio Name: (Enter new portfolio name to create new)", portfolioName);

    strategy = new Strategy();
    addComponent(strategy);
    strategy.setAlignmentX(SwingConstants.CENTER);

    isPeriod = new JCheckBox("Whether buy periodically", null, false);
    addComponent(isPeriod);

    startDate = createDateChooser();
    start = createLabel("Buy Date: ");
    addComponent(start);
    addComponent(startDate);

    endDate = createDateChooser();
    end = createLabel("End Date: ");
    addComponent(end);
    addComponent(endDate);

    frequency = createTextField();
    frequencyLabel = createLabel("Frequency:");
    frequencyPanel = createInputWithUnit(frequency, "Days");
    addComponent(frequencyLabel);
    addComponent(frequencyPanel);

    amount = createTextField();
    addInputWithUnit("Dollar Amount: ", amount, "$");

    commissionFee = createTextField();
    addInputWithUnit("Commission Fee:(Optional) ", commissionFee, "$");

    submit = new JButton("submit");
    addButton(submit);

    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setViewportView(root);
    add(scrollPane);

    buyOnceState();

    isPeriod.addItemListener(
        e -> {
          if (e.getStateChange() == ItemEvent.SELECTED) {
            buyPeriodState();
          } else {
            buyOnceState();
          }
        });
  }

  @Override
  public void setFeature(Features f) {
    super.setFeature(f);
    this.submit.addActionListener(
        e -> {
          try {
            String portfolioName = this.portfolioName.getText();
            Map<String, String> strategyMap = this.strategy.getStrategy();
            String start = date2String(this.startDate.getDate());
            String end = date2String(this.endDate.getDate());
            String frequencyText = this.frequency.getText();
            String amountText = this.amount.getText();
            String feeText =
                this.commissionFee.getText().isEmpty() ? "0" : this.commissionFee.getText();
            if (this.isPeriod.isSelected()) {
              f.dollarCostAveraging(portfolioName, strategyMap, start, end, frequencyText,
                  amountText, feeText);
            } else {
              f.dollarCostAveragingOnce(portfolioName, strategyMap, start, amountText, feeText);
            }
          } catch (Exception exp) {
            this.showError(exp.getMessage());
          }
        });
  }

  private void buyOnceState() {
    this.start.setText("Buy Date:");
    this.end.setVisible(false);
    this.endDate.setVisible(false);
    this.frequencyLabel.setVisible(false);
    this.frequencyPanel.setVisible(false);
  }

  private void buyPeriodState() {
    this.start.setText("Start Date:");
    this.end.setVisible(true);
    this.endDate.setVisible(true);
    this.frequencyLabel.setVisible(true);
    this.frequencyPanel.setVisible(true);
  }

  private static class Strategy extends JPanel {

    private JPanel top;
    private JLabel title;
    private JButton addButton;
    private List<SingleStock> stocks;


    Strategy() {
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      top = new JPanel(new FlowLayout(FlowLayout.LEFT));
      title = new JLabel("Stock symbol and weight: ");
      addButton = new JButton("+");

      addButton.addActionListener(e -> addRow());

      top.add(title);
      top.add(addButton);

      add(top);

      stocks = new ArrayList<>();
      addRow();
    }

    void addRow() {
      SingleStock stock = new SingleStock(this);
      stocks.add(stock);
      add(stock);

      revalidate();
      repaint();
    }

    void deleteRow(SingleStock stock) {
      stocks.remove(stock);
      remove(stock);

      revalidate();
      repaint();
    }

    Map<String, String> getStrategy() {
      return stocks.stream()
          .collect(Collectors.toMap(SingleStock::getSymbol, SingleStock::getWeight));
    }
  }

  private static class SingleStock extends JPanel {

    private Strategy parent;
    private JTextField symbol;
    private JTextField weight;

    private JButton delete;

    SingleStock(Strategy s) {
      this.parent = s;

      this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
      symbol = new JTextField();

      weight = new JTextField();
      weight.setHorizontalAlignment(JTextField.RIGHT);

      delete = new JButton("-");
      delete.addActionListener(e -> parent.deleteRow(this));

      JLabel per = new JLabel("%");
      add(symbol);
      add(weight);
      add(per);
      add(delete);
    }

    String getSymbol() {
      return symbol.getText();
    }

    String getWeight() {
      return weight.getText();
    }
  }
}
