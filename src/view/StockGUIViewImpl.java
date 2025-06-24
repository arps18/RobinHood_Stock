package view;

import controller.Features;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import model.Performance;

/**
 * This class implements the various view methods and extends the Jframe and implements the
 * StockGUIView. It implements the methods which are to be viewed in the application.
 */
public class StockGUIViewImpl extends JFrame implements StockGUIView {

  private Features features;
  private Screen screen;
  private JButton createPortfolioButton;
  private JButton buyStockButton;
  private JButton sellStockButton;
  private JButton getValueButton;
  private JButton costBasisButton;
  private JButton savePortfolioButton;
  private JButton loadPortfolioButton;
  private JButton dollarCostAveragingButton;
  private JButton getPerformanceButton;

  /**
   * This constructor acts as view setup in the application would look like. It takes various inputs
   * from the user and according to the input provided it implements them.
   */
  public StockGUIViewImpl() {
    super("Home");
    this.setSize(400, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    this.setLayout(new GridLayout(5, 2));
    init();
  }


  private void init() {
    createPortfolioButton = new JButton("Create Portfolio");
    buyStockButton = new JButton("Buy Stock");
    sellStockButton = new JButton("Sell Stock");
    getValueButton = new JButton("Get Value");
    costBasisButton = new JButton("Get Cost Basis");
    savePortfolioButton = new JButton("Save Portfolio");
    loadPortfolioButton = new JButton("Load Portfolio");
    dollarCostAveragingButton = new JButton("Dollar Cost Averaging");
    getPerformanceButton = new JButton("Get Performance");
    JButton quitButton = new JButton("Quit");

    this.add(createPortfolioButton);
    this.add(dollarCostAveragingButton);
    this.add(buyStockButton);
    this.add(sellStockButton);
    this.add(getValueButton);
    this.add(costBasisButton);
    this.add(savePortfolioButton);
    this.add(loadPortfolioButton);
    this.add(getPerformanceButton);
    this.add(quitButton);

    quitButton.addActionListener(e -> System.exit(0));
  }


  @Override
  public void setFeatures(Features f) {
    this.features = f;
    createPortfolioButton.addActionListener(e -> f.showCreatePortfolio());
    buyStockButton.addActionListener(e -> f.showBuyStock());
    sellStockButton.addActionListener(e -> f.showSellStock());
    dollarCostAveragingButton.addActionListener(e -> f.showDollarCostAveraging());
    getValueButton.addActionListener(e -> f.showGetValue());
    costBasisButton.addActionListener(e -> f.showGetCostBasis());
    savePortfolioButton.addActionListener(e -> f.showSavePortfolio());
    loadPortfolioButton.addActionListener(e -> f.showLoadPortfolio());
    getPerformanceButton.addActionListener(e -> f.showGetPerformance());
  }

  @Override
  public void showHome() {
    this.setVisible(true);
  }

  @Override
  public void backHome() {
    screen.hideScreen();
    showHome();
    screen = null;
  }

  @Override
  public void hideHome() {
    this.setVisible(false);
  }

  @Override
  public void showCreatePortfolio() {
    screen = new CreatePortfolio();
    screen.setFeature(features);
    screen.showScreen();
  }

  @Override
  public void showBuyStock() {
    screen = new BuyStock();
    screen.setFeature(features);
    screen.showScreen();
  }

  @Override
  public void showSellStock() {
    screen = new SellStock();
    screen.setFeature(features);
    screen.showScreen();
  }

  @Override
  public void showDollarCostAveraging() {
    screen = new DollarCostAveraging();
    screen.setFeature(features);
    screen.showScreen();
  }

  @Override
  public void showGetValue() {
    screen = new GetValue();
    screen.setFeature(features);
    screen.showScreen();
  }

  @Override
  public void showGetCostBasis() {
    screen = new CostBasis();
    screen.setFeature(features);
    screen.showScreen();
  }

  @Override
  public void showLoadPortfolio() {
    screen = new LoadPortfolio();
    screen.setFeature(features);
    screen.showScreen();
  }

  @Override
  public void showSavePortfolio() {
    screen = new SavePortfolio();
    screen.setFeature(features);
    screen.showScreen();
  }

  @Override
  public void showGetPerformance() {
    screen = new GetPerformance();
    screen.setFeature(features);
    screen.showScreen();
  }

  @Override
  public void showError(String error) {
    screen.showError(error);
  }

  @Override
  public void showMessage(String message) {
    screen.showResult(message);
  }

  @Override
  public void showPerformance(Performance p) {
    PerformanceBarChart barChart = new PerformanceBarChart(p);
    barChart.setVisible(true);
  }


}
