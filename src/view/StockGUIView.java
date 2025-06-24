package view;

import controller.Features;
import model.Performance;

/**
 * This interface acts a view for the GUI for the application. The view has various functionalities
 * that is to be viewed in the application.
 */
public interface StockGUIView extends StockView {

  /**
   * It sets the feature that is chosen by the user. It takes the parameter Features.
   *
   * @param f takes feature object
   */
  void setFeatures(Features f);

  /**
   * Shows the Home Screen.
   */
  void showHome();

  /**
   * Reverts to Home Screen.
   */
  void backHome();

  /**
   * Hides the Home Screen to display the new Screen.
   */
  void hideHome();

  /**
   * Displays the createPortfolio Screen.
   */
  void showCreatePortfolio();

  /**
   * Displays the BuyStock Screen.
   */
  void showBuyStock();

  /**
   * Displays the SellStock Screen.
   */
  void showSellStock();

  /**
   * Displays the DollarCostAveraging Screen.
   */
  void showDollarCostAveraging();

  /**
   * Displays the GetValue Screen.
   */
  void showGetValue();

  /**
   * Displays the GetCostBasis Screen.
   */
  void showGetCostBasis();

  /**
   * Displays the LoadPortfolio Screen.
   */
  void showLoadPortfolio();

  /**
   * Displays the SavePortfolio Screen.
   */
  void showSavePortfolio();

  /**
   * Displays the GetPerformance Screen.
   */
  void showGetPerformance();

  /**
   * Displays the Error Screen.
   *
   * @param error error message
   */
  void showError(String error);

  /**
   * Displays the performance.
   *
   * @param p performance
   */
  void showPerformance(Performance p);
}
