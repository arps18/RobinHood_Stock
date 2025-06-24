package view;

import controller.Features;
import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * This class implements to create the new portfolio in the application. It extends the
 * AbstractScreen class.It takes the name of the portfolio as input from the user and creates a new
 * portfolio according to it.
 */
class CreatePortfolio extends AbstractScreen {

  private JTextField portfolioName;

  private JButton create;

  /**
   * This constructor provides the functionality of create the new portfolio in the application. It
   * takes portfolio name inputs from the user and according to the input provided it creates a new
   * portfolio.
   */
  CreatePortfolio() {
    super("Create Portfolio", 400, 150);
    portfolioName = createTextField();
    addInput("Portfolio name: ", portfolioName);

    create = new JButton("create");
    addButton(create);
  }

  @Override
  public void setFeature(Features f) {
    super.setFeature(f);
    create.addActionListener(e -> f.createPortfolio(portfolioName.getText()));
  }
}
