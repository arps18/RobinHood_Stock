package controller.commands;

import java.util.Scanner;

/**
 * Abstract base class for implementations of {@link Command}. This class
 * contains all the method definitions that are common to the concrete
 * implementations of the {@link Command} interface. A new implementation of
 * the interface has the option of extending this class, or directly
 * implementing all the methods.
 */
abstract class AbstractCommand implements Command {

  Scanner scanner;

  /**
   * The AbstractCommand creates a generic command.
   *
   * @param scanner the scanner object
   */
  protected AbstractCommand(Scanner scanner) {
    this.scanner = scanner;
  }

  /**
   * Gets the input from the user.
   *
   * @return the input string
   * @throws RuntimeException when no line is found and scanner is closed
   */
  protected String input() throws RuntimeException {
    return scanner.nextLine();
  }

}
