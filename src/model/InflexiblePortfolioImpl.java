package model;

import static util.Utils.checkString;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This class implements the inflexible portfolio which doesn't allow the user to modify the
 * portfolio.
 */
class InflexiblePortfolioImpl extends AbstractPortfolio {

  /**
   * The constructor which takes the title of the portfolio and the stockData.
   *
   * @param title     the title of the portfolio
   * @param stockData the StockData object
   */
  InflexiblePortfolioImpl(String title, StockData stockData) {
    super(title, stockData);
  }

  @Override
  public List<Stock> getComposition() throws IllegalArgumentException {
    return new ArrayList<>(stocks.values());
  }

  @Override
  public void addStock(String symbol, double shares) throws RuntimeException {
    if (shares <= 0) {
      throw new IllegalArgumentException("The share amount must more than 0!");
    }

    if (stocks.containsKey(symbol)) {
      stocks.get(symbol).addShares(shares);
    } else {
      Stock stock = new StockImpl(symbol, shares);
      stocks.put(symbol, stock);
    }
  }

  @Override
  public void savePortfolio(String fileName) throws IOException, IllegalArgumentException {
    checkString(fileName);

    JSONObject jsonObject = new JSONObject();

    jsonObject.put("Name", this.title);
    jsonObject.put("Type", "inflexible");

    JSONArray stocks = new JSONArray();

    for (Stock stock : this.stocks.values()) {
      JSONObject stockobj = new JSONObject();
      stockobj.put("Symbol", stock.getSymbol());
      stockobj.put("Shares", stock.getShares());
      stocks.add(stockobj);
    }

    jsonObject.put("Stock", stocks);

    FileWriter writer = new FileWriter(fileName + ".json");
    writer.write(jsonObject.toString());

    writer.close();
  }

  @Override
  protected List<Stock> getCompositionHelper(LocalDate date) throws IllegalArgumentException {
    return getComposition();
  }

}
