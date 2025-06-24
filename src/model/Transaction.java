package model;

import java.time.LocalDate;

/**
 * The transaction class lists all the methods used in transaction of a portfolio.
 */
class Transaction {

  private final LocalDate date;
  private final double shares;
  private final double price;
  private final double commissionFee;

  /**
   * The constructor takes date, share, price and the commission fee and creates a new transaction
   * object.
   *
   * @param date          the date at which stock is to be purchased/sold
   * @param shares        the quantity of the shares
   * @param price         the price at which the stock is to be purchased/sold
   * @param commissionFee the fee to be charged as commission
   */
  public Transaction(LocalDate date, double shares, double price, double commissionFee) {
    this.date = date;
    this.shares = shares;
    this.price = price;
    this.commissionFee = commissionFee;
  }

  /**
   * Get the date of the transaction.
   *
   * @return transaction date
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   * Get shares of the transaction.
   *
   * @return the shares of stock
   */
  public double getShares() {
    return shares;
  }

  /**
   * Get unit stock price of the transaction.
   *
   * @return the unit price of the stock
   */
  public double getPrice() {
    return price;
  }

  /**
   * Get commission fee of the transaction.
   *
   * @return the commission fee of the transaction
   */
  public double getCommissionFee() {
    return commissionFee;
  }
}
