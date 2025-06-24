package model;

/**
 * The class implements the Stock interface which represents stock symbols and quantity.
 **/
class StockImpl implements Stock {

  private final String symbol;
  private double shares;

  /**
   * This constructor takes the symbol of the shares and the quantity of share.
   *
   * @param symbol the symbol of the share
   * @param shares the quantity of the share
   * @throws IllegalArgumentException if there is invalid stock symbol or invalid quantity of
   *                                  shares
   */
  public StockImpl(String symbol, double shares) throws IllegalArgumentException {
    if (symbol == null || symbol.isEmpty()) {
      throw new IllegalArgumentException("The Symbol cannot be null or empty!");
    }

    if (symbol.contains(" ")) {
      throw new IllegalArgumentException("The Symbol cannot contain space!");
    }

    if (shares < 0) {
      throw new IllegalArgumentException("The share amount must more than or less than 0!");
    }

    this.symbol = symbol;
    this.shares = shares;
  }

  @Override
  public String getSymbol() {
    return this.symbol;
  }

  @Override
  public double getShares() {
    return this.shares;
  }

  @Override
  public void addShares(double amount) throws IllegalArgumentException {
    if (this.shares + amount < 0) {
      throw new IllegalArgumentException("The share amount must more than or equal to 0!");
    }
    this.shares += amount;
  }

  @Override
  public String toString() {
    return symbol + "," + String.format("%.2f", shares);
  }
}
