package controller;

/**
 * The interface controls the view and the model component. It takes the inputs, informs the model
 * what to do and view what to display
 */
public interface StockController {

  /**
   * The controller controls the view and the Model.
   *
   * @throws Exception when an invalid input or output occurs
   */
  void start() throws Exception;
}

