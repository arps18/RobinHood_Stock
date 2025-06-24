package model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Portfolio.
 */
public class PortfolioTest {

  private Portfolio flexiblePortfolio;
  private Portfolio inflexiblePortfolio;

  @Before
  public void setup() {
    StockData stockData = new AlphaVantageStockDataImpl();
    flexiblePortfolio = new FlexiblePortfolioImpl("p1", stockData);
    inflexiblePortfolio = new InflexiblePortfolioImpl("f1", stockData);
  }

  @Test
  public void getInflexiblePortfolioCompositionTest() {
    inflexiblePortfolio.addStock("GOOG", 50);
    List<Stock> expected = new ArrayList<>();
    expected.add(new StockImpl("GOOG", 50));

    List<Stock> list = inflexiblePortfolio.getComposition();
    assertEquals(expected.size(), list.size());
    for (int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i).toString(), list.get(i).toString());
    }
  }

  @Test
  public void getFlexiblePortfolioCompositionByDateTest() {
    flexiblePortfolio.purchaseStock("GOOG", 50,
        LocalDate.parse("2022-11-01"), 2);
    flexiblePortfolio.sellStock("GOOG", 20, LocalDate.parse("2022-11-08"), 2);

    List<Stock> expected1 = new ArrayList<>();
    expected1.add(new StockImpl("GOOG", 50));

    List<Stock> list1 = flexiblePortfolio.getCompositionByDate(LocalDate.parse("2022-11-03"));
    assertEquals(expected1.size(), list1.size());
    for (int i = 0; i < expected1.size(); i++) {
      assertEquals(expected1.get(i).toString(), list1.get(i).toString());
    }

    List<Stock> expected2 = new ArrayList<>();
    expected2.add(new StockImpl("GOOG", 30));

    List<Stock> list2 = flexiblePortfolio.getCompositionByDate(LocalDate.parse("2022-11-09"));
    assertEquals(expected2.size(), list2.size());
    for (int i = 0; i < expected2.size(); i++) {
      assertEquals(expected2.get(i).toString(), list2.get(i).toString());
    }
  }

  @Test(expected = RuntimeException.class)
  public void getInflexiblePortfolioCompositionByDateTest() {
    inflexiblePortfolio.addStock("GOOG", 50);
    List<Stock> expected = new ArrayList<>();
    expected.add(new StockImpl("GOOG", 50));

    inflexiblePortfolio.getCompositionByDate(LocalDate.parse("2022-10-03"));
  }

  @Test(expected = RuntimeException.class)
  public void purchaseStockForInflexiblePortfolioTest() {
    inflexiblePortfolio.purchaseStock("GOOG", 20, LocalDate.parse("2022-10-03"), 3);
  }

  @Test(expected = RuntimeException.class)
  public void sellStockForrInflexiblePortfolioTest() {
    inflexiblePortfolio.sellStock("GOOG", 20, LocalDate.parse("2022-10-03"), 3);
  }

  @Test
  public void getTotalValueForFlexiblePortfolioTest() {
    flexiblePortfolio.purchaseStock("GOOG", 50, LocalDate.parse("2022-11-01"), 2);

    assertEquals(0, flexiblePortfolio.getTotalValue(LocalDate.parse("2022-10-03")), 0.01);
    assertEquals(4445.5, flexiblePortfolio.getTotalValue(LocalDate.parse("2022-11-08")), 0.01);
  }

  @Test
  public void getTotalValueForInflexiblePortfolioTest() {
    inflexiblePortfolio.addStock("GOOG", 50);
    assertEquals(4445.5, inflexiblePortfolio.getTotalValue(LocalDate.parse("2022-11-08")), 0.01);
  }

  @Test
  public void getCostBasisForFlexiblePortfolioTest() {
    flexiblePortfolio.purchaseStock("GOOG", 50, LocalDate.parse("2022-11-01"), 2);

    assertEquals(0, flexiblePortfolio.getCostBasis(LocalDate.parse("2022-10-03")), 0.01);
    assertEquals(4527, flexiblePortfolio.getCostBasis(LocalDate.parse("2022-11-07")), 0.01);
    assertEquals(4527, flexiblePortfolio.getCostBasis(LocalDate.parse("2022-11-08")), 0.01);
  }

  @Test(expected = RuntimeException.class)
  public void getCostBasisForInflexiblePortfolioTest() {
    inflexiblePortfolio.addStock("GOOG", 20);

    inflexiblePortfolio.getCostBasis(LocalDate.parse("2022-11-08"));
  }


  @Test
  public void getInflexiblePortfolioPerformanceTest() {
    inflexiblePortfolio.addStock("GOOG", 20);

    String performance1 = inflexiblePortfolio.getPortfolioPerformance(
        LocalDate.parse("2022-01-08"),
        LocalDate.parse("2022-11-08")).toString();

    assertEquals("Performance of portfolio f1 from 2022-01-08 to 2022-11-08\n"
        + "\n"
        + "Jan 2022: ****************************\n"
        + "Feb 2022: ***************************\n"
        + "Mar 2022: ****************************\n"
        + "Apr 2022: ***********************\n"
        + "May 2022: ***********************\n"
        + "Jun 2022: **********************\n"
        + "Jul 2022: **\n"
        + "Aug 2022: **\n"
        + "Sep 2022: *\n"
        + "Oct 2022: *\n"
        + "Nov 2022: *\n"
        + "\n"
        + "Scale: * = $2000\n", performance1);

    String performance2 = inflexiblePortfolio.getPortfolioPerformance(
        LocalDate.parse("2022-10-08"),
        LocalDate.parse("2022-11-08")).toString();

    assertEquals("Performance of portfolio f1 from 2022-10-08 to 2022-11-08\n"
        + "\n"
        + "2022-10-14: *************************************************\n"
        + "2022-10-28: ***********************************************\n"
        + "2022-11-08: ****************\n"
        + "\n"
        + "Base: $1700    Scale: * = $5\n", performance2);
  }

  @Test
  public void getFlexiblePortfolioPerformanceTest() {
    flexiblePortfolio.purchaseStock("GOOG", 20, LocalDate.parse("2021-10-05"), 5);

    String performance1 = flexiblePortfolio.getPortfolioPerformance(
        LocalDate.parse("2020-01-08"),
        LocalDate.parse("2022-11-08")).toString();

    assertEquals("Performance of portfolio p1 from 2020-01-08 to 2022-11-08\n"
        + "\n"
        + "Jan 2020: \n"
        + "Apr 2020: \n"
        + "Jul 2020: \n"
        + "Oct 2020: \n"
        + "Jan 2021: \n"
        + "Apr 2021: \n"
        + "Jul 2021: \n"
        + "Oct 2021: ******************************\n"
        + "Jan 2022: ****************************\n"
        + "Apr 2022: ***********************\n"
        + "Jul 2022: **\n"
        + "Oct 2022: *\n"
        + "Nov 2022: *\n"
        + "\n"
        + "Scale: * = $2000\n", performance1);

    String performance2 = flexiblePortfolio.getPortfolioPerformance(
        LocalDate.parse("2022-10-08"),
        LocalDate.parse("2022-11-08")).toString();

    assertEquals("Performance of portfolio p1 from 2022-10-08 to 2022-11-08\n"
        + "\n"
        + "2022-10-14: *************************************************\n"
        + "2022-10-28: ***********************************************\n"
        + "2022-11-08: ****************\n"
        + "\n"
        + "Base: $1700    Scale: * = $5\n", performance2);
  }
}
