package model;

import static util.Utils.checkString;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This class implements the flexible portfolio which allows the user to modify it.
 */
class FlexiblePortfolioImpl extends AbstractPortfolio {

  private final Map<String, List<Transaction>> transactions;

  /**
   * This constructor takes title and the stockData object.
   *
   * @param title     takes the title of the portfolio
   * @param stockData the stockData object
   */
  FlexiblePortfolioImpl(String title, StockData stockData) {
    super(title, stockData);
    this.transactions = new HashMap<>();
  }

  @Override
  public List<Stock> getCompositionByDate(LocalDate date) throws IllegalArgumentException {
    return getCompositionHelper(date);
  }

  @Override
  public void purchaseStock(String symbol, double shares, LocalDate date, double commissionFee)
      throws RuntimeException {

    if (commissionFee < 0) {
      throw new IllegalArgumentException("The commission fee can not less than 0!");
    }

    if (shares <= 0) {
      throw new IllegalArgumentException("The share amount must more than 0!");
    }

    if (!stocks.containsKey(symbol)) {
      stocks.put(symbol, new StockImpl(symbol, shares));
    } else {
      stocks.get(symbol).addShares(shares);
    }

    double price = stockData.getClosePrice(symbol, date);

    Transaction transaction = new Transaction(date, shares, price, commissionFee);

    if (!transactions.containsKey(symbol)) {
      List<Transaction> list = new ArrayList<>();
      list.add(transaction);
      transactions.put(symbol, list);
    } else {
      transactions.get(symbol).add(transaction);
    }
  }

  @Override
  public void sellStock(String symbol, double shares, LocalDate date, double commissionFee)
      throws RuntimeException {
    if (!stocks.containsKey(symbol) || !transactions.containsKey(symbol)) {
      throw new IllegalArgumentException("The symbol is invalid!");
    }

    if (commissionFee < 0) {
      throw new IllegalArgumentException("The commission fee can not less than 0!");
    }

    if (shares <= 0) {
      throw new IllegalArgumentException("The share amount must more than 0!");
    }

    if (shares > getStockSharesByDate(symbol, date)) {
      throw new IllegalArgumentException(
          "The shares you want to sell is more than current shares!");
    }

    double price = stockData.getClosePrice(symbol, date);

    List<Transaction> list = transactions.get(symbol);
    list.add(new Transaction(date, -shares, price, commissionFee));
    stocks.get(symbol).addShares(-shares);
  }

  @Override
  public double getCostBasis(LocalDate date) throws IllegalArgumentException {
    return transactions.values()
        .stream()
        .mapToDouble(
            transactionList -> transactionList
                .stream()
                .filter(t -> t.getDate().isBefore(date) || t.getDate().isEqual(date))
                .mapToDouble(t -> Math.max(t.getShares(), 0) * t.getPrice() + t.getCommissionFee())
                .sum()
        ).sum();
  }

  @Override
  protected List<Stock> getCompositionHelper(LocalDate date) throws IllegalArgumentException {
    return transactions.entrySet()
        .stream()
        .map(
            entry -> new StockImpl(entry.getKey(),
                entry.getValue()
                    .stream()
                    .filter(t -> t.getDate().isBefore(date) || t.getDate().isEqual(date))
                    .mapToDouble(Transaction::getShares)
                    .sum())
        )
        .filter(t -> t.getShares() > 0)
        .collect(Collectors.toList());
  }

  @Override
  public void savePortfolio(String fileName) throws IOException, IllegalArgumentException {
    checkString(fileName);

    JSONObject jsonObject = new JSONObject();

    jsonObject.put("Name", this.title);
    jsonObject.put("Type", "flexible");

    JSONArray stocks = new JSONArray();

    for (Stock stock : this.stocks.values()) {
      JSONObject stockobj = new JSONObject();
      stockobj.put("Symbol", stock.getSymbol());
      stockobj.put("Shares", stock.getShares());
      JSONArray transactionsArray = new JSONArray();
      for (Transaction t : this.transactions.get(stock.getSymbol())) {
        JSONObject transactionObj = new JSONObject();
        transactionObj.put("Date", t.getDate().toString());
        transactionObj.put("Type", t.getShares() > 0 ? "buy" : "sell");
        transactionObj.put("Shares", Math.abs(t.getShares()));
        transactionObj.put("Price", t.getPrice());
        transactionObj.put("CommissionFee", t.getCommissionFee());
        transactionsArray.add(transactionObj);
      }
      stockobj.put("Transactions", transactionsArray);
      stocks.add(stockobj);
    }

    jsonObject.put("Stock", stocks);

    FileWriter writer = new FileWriter(fileName + ".json");
    writer.write(jsonObject.toString());

    writer.close();
  }

  @Override
  public void dollarCostAveraging(Map<String, Double> strategy, LocalDate date, double amount,
      double commissionFee)
      throws IllegalArgumentException {
    for (Map.Entry<String, Double> entry : strategy.entrySet()) {
      String symbol = entry.getKey();
      double price = stockData.getClosePrice(symbol, date);
      double shares = (amount * (entry.getValue() / 100)) / price;
      this.purchaseStock(symbol, shares, date, commissionFee);
    }
  }

  private double getStockSharesByDate(String symbol, LocalDate date) {
    return transactions.get(symbol).stream()
        .filter(t -> t.getDate().isBefore(date) || t.getDate().isEqual(date))
        .mapToDouble(Transaction::getShares)
        .sum();
  }


}
