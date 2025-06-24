package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The class implements the StockData interface which represents stock symbol and the date, which
 * returns the value of the stock on that particular day.
 **/
class AlphaVantageStockDataImpl implements StockData {

  //      stock symbol   date
  //      List[0]: open, List[1]: high, List[2]: low, List[3]: close, list[4]: volume
  private final Map<String, Map<LocalDate, List<Double>>> prices;

  private final String apiKeys;

  /**
   * API KEYS added to get the data from the API.
   */
  public AlphaVantageStockDataImpl() {
    prices = new HashMap<>();
    apiKeys = "TEQDX6VFYR1L21XE";
  }

  private Map<LocalDate, List<Double>> getStockPrices(String symbol) {
    // reduce the api call
    if (!prices.containsKey(symbol)) {
      getPricesFromApi(symbol);
    }

    return prices.get(symbol);
  }

  private void getPricesFromApi(String symbol) throws RuntimeException {
    // call API to get result
    String url = "https://www.alphavantage"
            + ".co/query?function=TIME_SERIES_DAILY"
            + "&outputsize=full"
            + "&symbol"
            + "=" + symbol + "&apikey=" + apiKeys + "&datatype=csv";

    String apiResult = callApi(url);

    Map<LocalDate, List<Double>> result = new HashMap<>();

    String[] array = apiResult.split("\n");

    if (array.length < 5) {
      throw new IllegalArgumentException("The stock symbol " + symbol + " is invalid!");
    }

    for (int i = 1; i < array.length; i++) {
      String[] str = array[i].split(",");
      LocalDate date = LocalDate.parse(str[0]);
      List<Double> list = Arrays.stream(Arrays.copyOfRange(str, 1, str.length))
              .map(Double::parseDouble)
              .collect(Collectors.toList());
      result.put(date, list);
    }

    prices.put(symbol, result);
  }

  private String callApi(String spec) throws RuntimeException {
    URL url = null;
    try {
      url = new URL(spec);
    } catch (MalformedURLException e) {
      throw new RuntimeException("Fail to get the data from API!");
    }

    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
      in = url.openStream();
      int b;

      while ((b = in.read()) != -1) {
        output.append((char) b);
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Can't find any data from API");
    }

    return output.toString();
  }

  @Override
  public double getClosePrice(String symbol, LocalDate date) throws RuntimeException {
    if (symbol == null || symbol.isEmpty()) {
      throw new IllegalArgumentException("The Symbol cannot be null or empty!");
    }

    if (symbol.contains(" ")) {
      throw new IllegalArgumentException("The Symbol cannot contain space!");
    }

    if (date == null) {
      throw new IllegalArgumentException("The date cannot be null!");
    }

    Map<LocalDate, List<Double>> stocks = getStockPrices(symbol);

    Optional<LocalDate> minDate = stocks.keySet().stream()
            .min(LocalDate::compareTo);

    if (minDate.isPresent() && minDate.get().isAfter(date)) {
      throw new RuntimeException("The date is earlier than the listing date!");
    }

    // check date and price
    if (!stocks.containsKey(date) || stocks.get(date).size() < 4) {
      throw new IllegalArgumentException(
              "The symbol " + symbol + " does not have prices in " + date);
    }

    return stocks.get(date).get(3);
  }

  @Override
  public boolean isStockSymbolValid(String symbol) throws RuntimeException {
    if (symbol == null || symbol.isEmpty()) {
      throw new IllegalArgumentException("The Symbol cannot be null or empty!");
    }

    if (symbol.contains(" ")) {
      throw new IllegalArgumentException("The Symbol cannot contain space!");
    }

    if (prices.containsKey(symbol)) {
      return true;
    }

    try {
      getPricesFromApi(symbol);
    } catch (RuntimeException e) {
      return false;
    }

    return prices.containsKey(symbol);
  }
}