package model;

import static util.Utils.checkString;
import static util.Utils.checkWeekends;
import static util.Utils.date2String;
import static util.Utils.string2Date;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The class implements the StockModel interface which represents the working of the various
 * functionalities the StockModel provides.
 **/
public class StockModelImpl implements StockModel {

  // portfolios name, stock symbol
  private final Map<String, Portfolio> portfolioMap;

  private final StockData stockData;

  /**
   * Constructor of the StockModel Implementation.
   */
  public StockModelImpl() {
    this.portfolioMap = new HashMap<>();
    this.stockData = new AlphaVantageStockDataImpl();
  }


  @Override
  public void createInflexiblePortfolio(String portfolioName) throws IllegalArgumentException {
    if (this.portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(
          "The portfolio " + portfolioName + " already exists!");
    }

    checkString(portfolioName);

    portfolioMap.put(portfolioName, new InflexiblePortfolioImpl(portfolioName, stockData));
  }

  @Override
  public void createFlexiblePortfolio(String portfolioName) throws IllegalArgumentException {
    if (this.portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(
          "The portfolio " + portfolioName + " already exists!");
    }

    checkString(portfolioName);

    portfolioMap.put(portfolioName,
        new FlexiblePortfolioImpl(portfolioName, stockData));
  }

  @Override
  public String getPortfolioComposition(String portfolioName) throws IllegalArgumentException {
    if (!this.portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(
          "The portfolio " + portfolioName + " does not exist!");
    }

    List<Stock> stocks = this.portfolioMap.get(portfolioName).getComposition();

    return stocks.stream()
        .map(Object::toString)
        .collect(Collectors.joining("\n"));
  }

  @Override
  public String getPortfolioCompositionByDate(String portfolioName, String date)
      throws IllegalArgumentException {
    if (!this.portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(
          "The portfolio " + portfolioName + " does not exist!");
    }

    LocalDate localDate = string2Date(date);

    List<Stock> stocks = this.portfolioMap.get(portfolioName).getCompositionByDate(localDate);

    return stocks.stream()
        .map(Object::toString)
        .collect(Collectors.joining("\n"));
  }

  @Override
  public void addStock(String portfolioName, String symbol, String shares) throws RuntimeException {
    if (!this.portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(
          "The portfolio " + portfolioName + " does not exist!");
    }

    double sharesDouble = 0;

    try {
      sharesDouble = Double.parseDouble(shares);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("The stock shares must be integer!");
    }

    if (sharesDouble % (int) sharesDouble != 0) {
      throw new IllegalArgumentException("The stock shares must be integer!");
    }

    checkString(symbol);

    // check stockName
    if (!stockData.isStockSymbolValid(symbol)) {
      throw new IllegalArgumentException("The stock symbol " + symbol + " is invalid!");
    }

    this.portfolioMap.get(portfolioName).addStock(symbol, sharesDouble);
  }

  @Override
  public void purchaseStock(String portfolioName, String symbol, String shares, String date,
      String commissionFee)
      throws RuntimeException {
    if (!this.portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(
          "The portfolio " + portfolioName + " does not exist!");
    }

    double fee = 0;
    try {
      fee = Double.parseDouble(commissionFee);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("The commission fee must be double!");
    }

    double sharesDouble = 0;
    try {
      sharesDouble = Double.parseDouble(shares);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("The stock shares must be integer!");
    }

    if (sharesDouble % (int) sharesDouble != 0) {
      throw new IllegalArgumentException("The stock shares must be integer!");
    }

    LocalDate localDate = string2Date(date);

    checkWeekends(localDate);

    // check stockName
    if (!stockData.isStockSymbolValid(symbol)) {
      throw new IllegalArgumentException("The stock symbol " + symbol + " is invalid!");
    }

    this.portfolioMap.get(portfolioName).purchaseStock(symbol, sharesDouble, localDate, fee);
  }

  @Override
  public void sellStock(String portfolioName, String symbol, String shares, String date,
      String commissionFee)
      throws RuntimeException {
    if (!this.portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(
          "The portfolio " + portfolioName + " does not exist!");
    }

    double fee = 0;
    try {
      fee = Double.parseDouble(commissionFee);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("The commission fee must be double!");
    }

    double sharesDouble = 0;
    try {
      sharesDouble = Double.parseDouble(shares);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("The stock shares must be integer!");
    }

    if (sharesDouble % (int) sharesDouble != 0) {
      throw new IllegalArgumentException("The stock shares must be integer!");
    }

    LocalDate localDate = string2Date(date);

    checkWeekends(localDate);

    this.portfolioMap.get(portfolioName).sellStock(symbol, sharesDouble, localDate, fee);
  }

  @Override
  public double getTotalValue(String portfolioName, String date) throws IllegalArgumentException {
    if (!this.portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(
          "The portfolio " + portfolioName + " does not exist!");
    }

    LocalDate localDate = string2Date(date);

    checkWeekends(localDate);

    return this.portfolioMap.get(portfolioName).getTotalValue(localDate);
  }

  @Override
  public double getCostBasis(String portfolioName, String date) throws IllegalArgumentException {
    if (!this.portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(
          "The portfolio " + portfolioName + " does not exist!");
    }

    LocalDate localDate = string2Date(date);

    checkWeekends(localDate);

    return this.portfolioMap.get(portfolioName).getCostBasis(localDate);
  }

  @Override
  public void savePortfolio(String portfolioName, String fileName)
      throws IOException, IllegalArgumentException {
    if (!this.portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(
          "The portfolio " + portfolioName + " does not exist!");
    }

    this.portfolioMap.get(portfolioName).savePortfolio(fileName);
  }

  @Override
  public void loadPortfolio(String fileName) throws Exception {
    checkString(fileName);

    JSONParser parser = new JSONParser();

    try {
      FileReader reader = new FileReader(fileName);
      Object parse = parser.parse(reader);

      JSONObject jsonObject = (JSONObject) parse;

      String type = (String) jsonObject.get("Type");
      String name = (String) jsonObject.get("Name");
      if (this.portfolioMap.containsKey(name)) {
        throw new IllegalArgumentException("The portfolio " + name + " exists!");
      }
      if (type.equals("flexible")) {
        loadFlexiblePortfolio(jsonObject);
      } else if (type.equals("inflexible")) {
        loadInflexiblePortfolio(jsonObject);
      } else {
        throw new IllegalArgumentException("The file cannot be loaded!");
      }

      reader.close();
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("Can't find the file " + fileName);
    } catch (ParseException e) {
      throw new FileNotFoundException("Load file failed!");
    } catch (IOException e) {
      throw new IOException("Load file failed!");
    }
  }

  @Override
  public Performance getPortfolioPerformance(String portfolioName, String startDate, String endDate)
      throws RuntimeException {
    if (!this.portfolioMap.containsKey(portfolioName)) {
      throw new IllegalArgumentException(
          "The portfolio " + portfolioName + " does not exist!");
    }

    LocalDate start = string2Date(startDate);
    LocalDate end = string2Date(endDate);

    if (end.isAfter(LocalDate.now())) {
      throw new IllegalArgumentException("The end date cannot be after now!");
    }

    return this.portfolioMap.get(portfolioName).getPortfolioPerformance(start, end);
  }

  @Override
  public void dollarCostAveraging(String portfolioName, Map<String, String> strategy,
      String startDate, String endDate, String frequency, String amount, String commissionFee)
      throws RuntimeException {

    checkString(portfolioName);
    if (endDate == null || endDate.isEmpty()) {
      endDate = date2String(new Date());
    }

    LocalDate start = string2Date(startDate);
    LocalDate end = string2Date(endDate);

    if (end.isBefore(start)) {
      throw new IllegalArgumentException("The end date cannot be before than start date!");
    }

    if (end.isAfter(LocalDate.now())) {
      end = LocalDate.now();
    }

    int frequencyDate = 0;

    try {
      frequencyDate = Integer.parseInt(frequency);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid frequency day!");
    }

    if (frequencyDate < 0) {
      throw new IllegalArgumentException("Invalid frequency day!");
    }

    getAmount(amount);
    getCommissionFee(commissionFee);
    getStrategy(strategy);

    LocalDate date = start;

    while (date.isBefore(end) || date.isEqual(end)) {
      try {
        dollarCostAveragingOnce(portfolioName, strategy, date.toString(), amount, commissionFee);
        date = date.plusDays(frequencyDate);
      } catch (RuntimeException e) {
        date = date.plusDays(1);
      }
    }
  }

  @Override
  public void dollarCostAveragingOnce(String portfolioName, Map<String, String> strategy,
      String date, String amount, String commissionFee) throws RuntimeException {
    checkString(portfolioName);
    if (!this.portfolioMap.containsKey(portfolioName)) {
      createFlexiblePortfolio(portfolioName);
    }

    double amountDouble = getAmount(amount);
    double fee = getCommissionFee(commissionFee);
    Map<String, Double> stringDoubleStrategy = getStrategy(strategy);

    this.portfolioMap.get(portfolioName)
        .dollarCostAveraging(stringDoubleStrategy, string2Date(date), amountDouble, fee);
  }

  private double getAmount(String amount) {

    double amountDouble = 0;

    try {
      amountDouble = Double.parseDouble(amount);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid amount!");
    }

    if (amountDouble <= 0) {
      throw new IllegalArgumentException("Invalid amount!");
    }

    return amountDouble;
  }

  private double getCommissionFee(String commissionFee) {
    double fee = 0;
    try {
      fee = Double.parseDouble(commissionFee);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("The commission fee must be double!");
    }

    if (fee < 0) {
      throw new IllegalArgumentException("Invalid commission fee!");
    }

    return fee;
  }

  private Map<String, Double> getStrategy(Map<String, String> strategy)
      throws IllegalArgumentException {
    double sum = 0;

    Map<String, Double> stringDoubleStrategy = new HashMap<>();

    for (Map.Entry<String, String> entry : strategy.entrySet()) {
      // check stockName
      if (!stockData.isStockSymbolValid(entry.getKey())) {
        throw new IllegalArgumentException("The stock symbol " + entry.getKey() + " is invalid!");
      }
      double weight = 0;
      try {
        weight = Double.parseDouble(entry.getValue());
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid weight!");
      }
      if (weight <= 0) {
        throw new IllegalArgumentException("Invalid weight!");
      }
      sum += weight;
      stringDoubleStrategy.put(entry.getKey(), weight);
    }

    if (sum != 100) {
      throw new IllegalArgumentException("The total weight should be equal to 100%!");
    }

    return stringDoubleStrategy;
  }

  private void loadInflexiblePortfolio(JSONObject jsonObject) {
    String name = (String) jsonObject.get("Name");
    createInflexiblePortfolio(name);
    JSONArray stocks = (JSONArray) jsonObject.get("Stock");
    for (Object stock : stocks) {
      JSONObject s = (JSONObject) stock;
      String symbol = (String) s.get("Symbol");
      String shares = String.valueOf((double) s.get("Shares"));
      addStock(name, symbol, shares);
    }
  }

  private void loadFlexiblePortfolio(JSONObject jsonObject) throws RuntimeException {
    String name = (String) jsonObject.get("Name");
    createFlexiblePortfolio(name);
    JSONArray stocks = (JSONArray) jsonObject.get("Stock");
    for (Object stock : stocks) {
      JSONObject s = (JSONObject) stock;
      String symbol = (String) s.get("Symbol");
      JSONArray transactions = (JSONArray) s.get("Transactions");
      for (Object transaction : transactions) {
        JSONObject t = (JSONObject) transaction;
        String date = (String) t.get("Date");
        String type = (String) t.get("Type");
        String shares = String.valueOf((double) t.get("Shares"));
        String commissionFee = String.valueOf((double) t.get("CommissionFee"));

        double price = (double) t.get("Price");

        if (stockData.getClosePrice(symbol, LocalDate.parse(date)) - price
            > 0.01) {
          throw new RuntimeException("Load failed, price not match!");
        }

        if (type.equals("buy")) {
          purchaseStock(name, symbol, shares, date, commissionFee);
        } else if (type.equals("sell")) {
          sellStock(name, symbol, shares, date, commissionFee);
        } else {
          throw new RuntimeException("Load failed!");
        }
      }
    }
  }
}
