package controller.commands;

import model.StockModel;
import view.StockView;

/**
 * This is a command interface which has the commands for the various actions performed.
 */
public interface Command {

  /**
   * Executes various commands of the actions.
   *
   * @param stockView  the stockView object
   * @param stockModel the stockModel object
   * @throws Exception when there is an error in the command
   */
  void execute(StockView stockView, StockModel stockModel) throws Exception;
}
