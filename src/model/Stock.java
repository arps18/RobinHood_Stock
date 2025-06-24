package model;

/**
 * This interface represents the stocks that a portfolio has in it. It has a getter method which
 * takes the stock symbol and the quantity of shares of the particular stock required.
 */
interface Stock {

  /**
   * It takes the symbol of the listed share.
   *
   * @return the string of the symbol of the share.
   */
  String getSymbol();

  /**
   * It takes the quantity of the shares to be bought.
   *
   * @return the number of shares which is to be bought
   */
  double getShares();

  /**
   * It adds quantities of shares into current stock.
   *
   * @param amount the amount of shares to be added
   * @throws IllegalArgumentException invalid amount
   */
  void addShares(double amount) throws IllegalArgumentException;
}
