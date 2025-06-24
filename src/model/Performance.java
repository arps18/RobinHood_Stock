package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import util.Pair;

/**
 * This class checks the performance of a portfolio over a period of time. This class contains the
 * information of the portfolio, which is used to store portfolio performance result which is
 * represented in the form of a bar chart.
 */
public class Performance {

  private String portfolioName;
  private LocalDate start;
  private LocalDate end;
  private List<Pair<String, Double>> priceList;
  private int scale;
  private int base;

  /**
   * This constructor is used to store the result of the performance of the portfolio.
   *
   * @param portfolioName the name of the portfolio
   * @param start         the start date which is to be used to see the performance
   * @param end           the end date which is to be used to see the performance
   * @param priceList     the list of the prices from the start to end date
   * @param scale         the range of values of the portfolios
   * @param base          the timestamp of the portfolio
   */
  public Performance(String portfolioName, LocalDate start, LocalDate end,
      List<Pair<String, Double>> priceList, int scale, int base) {
    this.portfolioName = portfolioName;
    this.start = start;
    this.end = end;
    this.priceList = priceList;
    this.scale = scale;
    this.base = base;
  }

  public List<Pair<String, Double>> getPriceList() {
    return Collections.unmodifiableList(priceList);
  }

  public String getPortfolioName() {
    return portfolioName;
  }

  public LocalDate getStart() {
    return start;
  }

  public LocalDate getEnd() {
    return end;
  }

  public int getScale() {
    return scale;
  }

  public int getBase() {
    return base;
  }

  public String getTitle() {
    return "Performance of portfolio " + this.portfolioName + " from "
        + start + " to " + end;
  }

  @Override
  public String toString() {
    List<Pair<String, String>> performance = new ArrayList<>();

    for (Pair<String, Double> pair : priceList) {
      double v = pair.getSecond() - base;
      StringBuilder asterisk = new StringBuilder();
      while (v > 0) {
        asterisk.append("*");
        v -= scale;
      }
      performance.add(new Pair<>(pair.getFirst(), asterisk.toString()));
    }

    String performanceString = performance.stream().map(Pair::toString)
        .collect(Collectors.joining("\n"));

    StringBuilder footer = new StringBuilder();
    if (base > 0) {
      footer.append("Base: $").append(base).append("    ");
    }
    footer.append("Scale: * = $").append(scale);

    return getTitle() + "\n\n" + performanceString + "\n\n" + footer + "\n";
  }
}
