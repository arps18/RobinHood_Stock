package model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import util.Pair;

/**
 * Abstract base class for implementations of {@link Portfolio}. This class contains all the method
 * definitions that are common to the concrete implementations of the {@link Portfolio} interface. A
 * new implementation of the interface has the option of extending this class, or directly
 * implementing all the methods.
 */
abstract class AbstractPortfolio implements Portfolio {

  protected final String title;

  protected final StockData stockData;

  protected final Map<String, Stock> stocks;

  /**
   * This constructor takes title and StockData object.
   *
   * @param title     title of the portfolio
   * @param stockData stockData object
   */
  AbstractPortfolio(String title, StockData stockData) {
    this.title = title;
    this.stockData = stockData;
    this.stocks = new HashMap<>();
  }

  @Override
  public List<Stock> getComposition() throws IllegalArgumentException {
    throw new IllegalArgumentException("This portfolio cannot get composition!");
  }

  @Override
  public Performance getPortfolioPerformance(LocalDate startDate, LocalDate endDate)
      throws RuntimeException {
    List<Pair<String, Double>> list = getPriceList(startDate, endDate);

    List<Double> values = list.stream().map(Pair::getSecond).collect(Collectors.toList());

    Double max = Collections.max(values);
    Double min = Collections.min(values);

    int scale = getPerformanceScale(max, min);
    int base = getPerformanceBase(max, min, scale);

    return new Performance(this.title, startDate, endDate, list, scale, base);
  }

  @Override
  public double getTotalValue(LocalDate date) throws IllegalArgumentException {
    return getCompositionHelper(date)
        .stream()
        .mapToDouble(
            stock ->
                stock.getShares() * stockData.getClosePrice(stock.getSymbol(), date))
        .sum();
  }

  @Override
  public List<Stock> getCompositionByDate(LocalDate date) throws IllegalArgumentException {
    throw new RuntimeException("This portfolio cannot examine composition by certain date!");
  }

  @Override
  public void addStock(String symbol, double shares) throws RuntimeException {
    throw new RuntimeException("This portfolio cannot add stock!");
  }

  @Override
  public void purchaseStock(String symbol, double shares, LocalDate date, double commissionFee)
      throws RuntimeException {
    throw new RuntimeException("This portfolio cannot purchase stock!");
  }

  @Override
  public void sellStock(String symbol, double shares, LocalDate date, double commissionFee)
      throws RuntimeException {
    throw new RuntimeException("This portfolio cannot sell stock!");
  }

  @Override
  public double getCostBasis(LocalDate date) throws IllegalArgumentException {
    throw new RuntimeException("This portfolio cannot get cost basis!");
  }

  @Override
  public void dollarCostAveraging(Map<String, Double> strategy, LocalDate date,
      double amount, double commissionFee)
      throws IllegalArgumentException {
    throw new IllegalArgumentException("This portfolio cannot invest dollar cost averaging!");
  }

  /**
   * The helper method for composition.
   *
   * @param date the date on which composition is to be checked
   * @return the List of the stock
   * @throws IllegalArgumentException invalid date or date format
   */
  protected abstract List<Stock> getCompositionHelper(LocalDate date)
      throws IllegalArgumentException;

  private int getPerformanceScale(double maxValue, double minValue) {
    List<Integer> scales = new ArrayList<>();

    for (int i = 1; i < 1e8; i *= 10) {
      scales.add(i);
    }
    for (int i = 2; i < 2 * 1e8; i *= 10) {
      scales.add(i);
    }
    for (int i = 5; i < 5 * 1e8; i *= 10) {
      scales.add(i);
    }
    Collections.sort(scales);

    int idx = 0;
    while ((maxValue - minValue) / scales.get(idx) > 50) {
      idx++;
    }

    return scales.get(idx);
  }

  private int getPerformanceBase(double max, double min, int scale) {
    int base = 0;
    String minString = String.valueOf((int) min);
    int len = minString.length();
    int idx = 0;
    while ((max - base) / scale > 50) {
      base += ((minString.charAt(idx) - '0') * Math.pow(10, len - idx - 1));
      idx++;
    }

    return base;
  }

  private TimeSpan getTimeSpan(LocalDate start, LocalDate end) throws IllegalArgumentException {
    long day = ChronoUnit.DAYS.between(start, end);
    long weeks = ChronoUnit.WEEKS.between(start, end);
    long month = ChronoUnit.MONTHS.between(start, end);
    long year = ChronoUnit.YEARS.between(start, end);

    if (day < 5) {
      throw new IllegalArgumentException("The end date is so close to the start date!");
    }

    if (year > 30) {
      throw new IllegalArgumentException("The end date is so far to the start date!");
    }

    if (day <= 30) { // 5 - 30 days
      return TimeSpan.DAY;
    } else if (weeks <= 30) { // 31 - 210 days (7m)
      return TimeSpan.WEEK;
    } else if (month <= 30) { // 211(30w/7m) - 900 days (30m / 3y)
      return TimeSpan.MONTH;
    } else if (month <= 90) { // 901 days - 2700 days (8y);
      return TimeSpan.THREEMONTHS;
    } else { // 2700 days - 10950 days
      return TimeSpan.YEAR;
    }
  }

  private List<Pair<String, Double>> getPriceList(LocalDate start, LocalDate end)
      throws IllegalArgumentException {

    TimeSpan timeSpan = getTimeSpan(start, end);

    switch (timeSpan) {
      case DAY:
        return getDailyValueList(start, end);
      case WEEK:
        return getWeeklyValueList(start, end);
      case MONTH:
        return getMonthlyValueList(start, end, 1);
      case THREEMONTHS:
        return getMonthlyValueList(start, end, 3);
      case YEAR:
        return getYearlyValueList(start, end);
      default:
        return getDailyValueList(start, end);
    }
  }

  private List<Pair<String, Double>> getDailyValueList(LocalDate start, LocalDate end)
      throws IllegalArgumentException {

    List<Pair<String, Double>> list = new ArrayList<>();

    LocalDate date = start;

    while (date.isBefore(end) || date.isEqual(end)) {
      // get value
      LocalDate currentDate = date;
      List<Stock> stocks = getCompositionHelper(currentDate);

      try {
        double value = stocks.stream()
            .mapToDouble(
                stock -> stock.getShares() * stockData.getClosePrice(stock.getSymbol(),
                    currentDate))
            .sum();

        list.add(new Pair<>(currentDate.toString(), value));
      } catch (IllegalArgumentException ignored) {
        // pass
      }

      date = date.plusDays(1);
    }

    return list;
  }

  private List<Pair<String, Double>> getWeeklyValueList(LocalDate start, LocalDate end)
      throws IllegalArgumentException {
    List<Pair<String, Double>> list = new ArrayList<>();

    // to Friday
    LocalDate date = start.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

    HashSet<LocalDate> set = new HashSet<>();

    while ((date.isBefore(end) || date.isEqual(end)) && (date.isAfter(start) || date.isEqual(
        start))) {

      if (set.contains(date)) {
        break;
      }
      LocalDate currentDate = date;
      List<Stock> stocks = getCompositionHelper(currentDate);

      try {
        double value = stocks.stream()
            .mapToDouble(
                stock -> stock.getShares() * stockData.getClosePrice(stock.getSymbol(),
                    currentDate))
            .sum();

        set.add(date);
        list.add(new Pair<>(currentDate.toString(), value));
        LocalDate next = date.plusWeeks(1).with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        if (next.isAfter(end)) {
          date = end;
        } else {
          date = next;
        }
      } catch (IllegalArgumentException e) {
        date = date.plusDays(-1);
      } catch (RuntimeException e) {
        date = date.plusWeeks(1).with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
      }
    }

    return list;
  }

  private List<Pair<String, Double>> getMonthlyValueList(LocalDate start, LocalDate end,
      int monthSpan) throws IllegalArgumentException {
    List<Pair<String, Double>> list = new ArrayList<>();

    // to the last day of the month
    LocalDate date = start.with(TemporalAdjusters.lastDayOfMonth());
    HashSet<LocalDate> set = new HashSet<>();
    while ((date.isBefore(end) || date.isEqual(end)) && (date.isAfter(start) || date.isEqual(
        start))) {
      LocalDate currentDate = date;
      List<Stock> stocks = getCompositionHelper(currentDate);
      if (set.contains(date)) {
        break;
      }

      try {
        double value = stocks.stream()
            .mapToDouble(
                stock -> stock.getShares() * stockData.getClosePrice(stock.getSymbol(),
                    currentDate))
            .sum();

        String month = currentDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String year = String.valueOf(currentDate.getYear());

        list.add(new Pair<>(month + " " + year, value));
        set.add(date);
        LocalDate next = date.plusMonths(monthSpan).with(TemporalAdjusters.lastDayOfMonth());
        if (next.isAfter(end)) {
          date = end;
        } else {
          date = next;
        }
      } catch (IllegalArgumentException e) {
        date = date.plusDays(-1);
      } catch (RuntimeException e) {
        date = date.plusDays(monthSpan).with(TemporalAdjusters.lastDayOfMonth());
      }
    }
    return list;
  }

  private List<Pair<String, Double>> getYearlyValueList(LocalDate start, LocalDate end)
      throws IllegalArgumentException {
    List<Pair<String, Double>> list = new ArrayList<>();

    // to the last day of the year
    LocalDate date = start.with(TemporalAdjusters.lastDayOfYear());
    HashSet<LocalDate> set = new HashSet<>();
    while ((date.isBefore(end) || date.isEqual(end)) && (date.isAfter(start) || date.isEqual(
        start))) {
      LocalDate currentDate = date;
      List<Stock> stocks = getCompositionHelper(currentDate);
      if (set.contains(date)) {
        break;
      }

      try {
        double value = stocks.stream()
            .mapToDouble(
                stock -> stock.getShares() * stockData.getClosePrice(stock.getSymbol(),
                    currentDate))
            .sum();

        String year = String.valueOf(currentDate.getYear());
        list.add(new Pair<>(year, value));
        set.add(date);
        LocalDate next = date.plusYears(1).with(TemporalAdjusters.lastDayOfYear());
        if (next.isAfter(end)) {
          date = end;
        } else {
          date = next;
        }
      } catch (IllegalArgumentException e) {
        date = date.plusDays(-1);
      } catch (RuntimeException e) {
        date = date.plusYears(1).with(TemporalAdjusters.lastDayOfYear());
      }
    }
    return list;
  }

  private enum TimeSpan {
    DAY, WEEK, MONTH, THREEMONTHS, YEAR
  }
}
