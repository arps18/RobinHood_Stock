package view;

import controller.Features;

/**
 * This interface acts as a screen for the application and list the various screens to be displayed
 * in the application.
 */

interface Screen {

  /**
   * It sets the feature that is chosen by the user. It takes the parameter Features.
   *
   * @param f takes feature object
   */
  void setFeature(Features f);

  /**
   * Displays the Screen.
   */
  void showScreen();

  /**
   * Hides the Screen.
   */
  void hideScreen();

  /**
   * Shows the result of the feature executed and takes the parameter result.
   *
   * @param result the result that is executed by the feature
   */
  void showResult(String result);

  /**
   * Shows the error occurred while executing the feature and takes the parameter error.
   *
   * @param error the error that is occurred by execution of the feature
   */
  void showError(String error);
}
