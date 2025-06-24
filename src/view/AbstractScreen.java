package view;

import com.toedter.calendar.JDateChooser;
import controller.Features;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Abstract base class for implementations of {@link Screen}. This class contains all the method
 * definitions that are common to the concrete implementations of the {@link Screen} interface. A
 * new implementation of the interface has the option of extending this class, or directly
 * implementing all the methods.
 */
abstract class AbstractScreen extends JFrame implements Screen {

  protected JPanel root;

  protected JPanel result;

  protected JPanel buttons;

  protected JPanel bottom;

  protected JButton home;

  protected JLabel message;

  protected AbstractScreen(String caption, int width, int height) {
    super(caption);
    this.setSize(width, height);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    root = new JPanel();
    root.setBorder(new EmptyBorder(10, 10, 10, 30));
    root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));

    home = new JButton("Home");

    buttons = new JPanel();
    buttons.add(home);

    result = new JPanel();
    message = createLabel("");
    message.setVisible(false);
    result.add(message);

    bottom = new JPanel();
    bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
    bottom.add(result);
    bottom.add(buttons);

    this.add(root, BorderLayout.CENTER);
    this.add(bottom, BorderLayout.PAGE_END);
  }


  @Override
  public void showScreen() {
    this.setVisible(true);
  }


  @Override
  public void hideScreen() {
    this.setVisible(false);
  }

  @Override
  public void showResult(String result) {
    this.message.setText(result);
    this.message.setForeground(Color.BLACK);
    this.message.setVisible(true);
  }

  @Override
  public void showError(String error) {
    this.message.setText(error);
    this.message.setForeground(Color.red);
    this.message.setVisible(true);
  }

  @Override
  public void setFeature(Features f) {
    home.addActionListener(e -> f.backHome());
  }

  protected JLabel createLabel(String text) {
    JLabel label = new JLabel(text);
    //    label.setAlignmentX(SwingConstants.CENTER);
    return label;
  }

  protected JTextField createTextField() {
    JTextField textField = new JTextField();
    textField.setAlignmentX(SwingConstants.CENTER);
    return textField;
  }

  protected void addInput(String title, JTextField textField) {
    root.add(createLabel(title));
    root.add(textField);
  }

  protected void addInputWithUnit(String title, JTextField textField, String unit) {
    root.add(createLabel(title));
    root.add(createInputWithUnit(textField, unit));
  }

  protected JPanel createInputWithUnit(JTextField textField, String unit) {

    textField.setHorizontalAlignment(JTextField.RIGHT);
    JPanel panel = new JPanel(new BorderLayout());
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    panel.setAlignmentX(SwingConstants.CENTER);
    panel.add(textField);
    panel.add(createLabel(unit));

    return panel;
  }

  protected JDateChooser createDateChooser() {
    JDateChooser dateChooser = new JDateChooser();
    dateChooser.setDateFormatString("yyyy-MM-dd");

    //dateChooser.setMaxSelectableDate(new Date());

    dateChooser.setAlignmentX(SwingConstants.CENTER);
    return dateChooser;
  }

  protected void addDateChooser(String title, JDateChooser dateChooser) {
    root.add(createLabel(title));
    root.add(dateChooser);
  }

  protected void addButton(JButton button) {
    buttons.add(button, 0);
  }

  protected void addComponent(JComponent component) {
    root.add(component);
  }
}
