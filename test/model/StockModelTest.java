package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests for StockModel.
 */
public class StockModelTest {

  private StockModel obj;

  @Before
  public void setup() {
    obj = new StockModelImpl();

    obj.createFlexiblePortfolio("f1");
    obj.createInflexiblePortfolio("p1");
  }

  // sample
  @Test
  public void testDollarCostAveraging() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "20");
    obj.dollarCostAveraging("f1", strategy, "2022-01-01", "2022-05-20", "30", "1000", "2");

    double cost1 = obj.getCostBasis("f1", "2022-01-05");
    double cost2 = obj.getCostBasis("f1", "2022-02-07");
    double cost3 = obj.getCostBasis("f1", "2022-03-07");
    double cost4 = obj.getCostBasis("f1", "2022-04-05");
    double cost5 = obj.getCostBasis("f1", "2022-05-25");

    assertEquals(1006, cost1, 0.01);
    assertEquals(2012, cost2, 0.01);
    assertEquals(3018, cost3, 0.01);
    assertEquals(4024, cost4, 0.01);
    assertEquals(5030, cost5, 0.01);

    double value1 = obj.getTotalValue("f1", "2022-01-05");
    double value2 = obj.getTotalValue("f1", "2022-02-07");
    double value3 = obj.getTotalValue("f1", "2022-03-07");
    double value4 = obj.getTotalValue("f1", "2022-04-05");
    double value5 = obj.getTotalValue("f1", "2022-05-25");

    assertEquals(955.64, value1, 0.01);
    assertEquals(1919.01, value2, 0.01);
    assertEquals(2701.43, value3, 0.01);
    assertEquals(4021.46, value4, 0.01);
    assertEquals(3852.98, value5, 0.01);
  }

  @Test
  public void testDollarCostAveragingNewPortfolio() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "20");
    obj.dollarCostAveraging("f2", strategy, "2022-01-01", "2022-05-20", "30", "1000", "2");
    double cost1 = obj.getCostBasis("f2", "2022-01-05");
    double cost2 = obj.getCostBasis("f2", "2022-02-07");
    double cost3 = obj.getCostBasis("f2", "2022-03-07");
    double cost4 = obj.getCostBasis("f2", "2022-04-05");
    double cost5 = obj.getCostBasis("f2", "2022-05-25");

    assertEquals(1006, cost1, 0.01);
    assertEquals(2012, cost2, 0.01);
    assertEquals(3018, cost3, 0.01);
    assertEquals(4024, cost4, 0.01);
    assertEquals(5030, cost5, 0.01);
  }

  @Test
  public void testDollarCostAveragingSingleStock() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "100");

    obj.dollarCostAveraging("f1", strategy, "2022-01-01", "2022-05-20", "30", "1000", "2");
    String composition1 = obj.getPortfolioCompositionByDate("f1", "2022-01-05");
    String composition2 = obj.getPortfolioCompositionByDate("f1", "2022-02-05");
    String composition3 = obj.getPortfolioCompositionByDate("f1", "2022-03-05");
    String composition4 = obj.getPortfolioCompositionByDate("f1", "2022-04-05");
    String composition5 = obj.getPortfolioCompositionByDate("f1", "2022-05-25");

    assertEquals("GOOG,0.34", composition1);
    assertEquals("GOOG,0.68", composition2);
    assertEquals("GOOG,1.06", composition3);
    assertEquals("GOOG,1.41", composition4);
    assertEquals("GOOG,1.82", composition5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostAveragingMoreFraction() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "30");
    obj.dollarCostAveraging("f1", strategy, "2022-01-01", "2022-05-20", "30", "1000", "2");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostAveragingLessThanHundred() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "10");
    obj.dollarCostAveraging("f1", strategy, "2022-01-01", "2022-05-20", "30", "1000", "2");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostAveragingZeroFraction() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "0");
    strategy.put("TSLA", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "20");
    obj.dollarCostAveraging("f1", strategy, "2022-01-01", "2022-05-20", "30", "1000", "2");

  }

  @Test(expected = RuntimeException.class)
  public void testDollarCostAveragingNegativeCommission() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "20");
    obj.dollarCostAveraging("f1", strategy, "2022-01-01", "2022-05-20", "30", "1000", "-2");

  }

  @Test(expected = RuntimeException.class)
  public void testDollarCostAveragingNegativeFrequency() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "30");
    obj.dollarCostAveraging("f1", strategy, "2022-01-01", "2022-05-20", "-30", "1000", "2");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostAveragingNegativeAmount() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "30");
    obj.dollarCostAveraging("f1", strategy, "2022-01-01", "2022-05-20", "30", "-1000", "2");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostAveragingFalseDate() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "30");
    obj.dollarCostAveraging("f1", strategy, "2022-06-01", "2022-05-20", "30", "1000", "2");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostAveragingEmptyStrategy() {
    Map<String, String> strategy = new HashMap<>();
    obj.dollarCostAveraging("f1", strategy, "2022-06-01", "2022-05-20", "30", "1000", "2");

  }

  @Test
  public void testDollarCostAveragingOnce() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "20");
    obj.dollarCostAveragingOnce("f1", strategy, "2022-01-05", "1000", "2");
    double cost = obj.getCostBasis("f1", "2022-01-05");

    assertEquals(1006, cost, 0.01);

    double value = obj.getTotalValue("f1", "2022-01-05");

    assertEquals(1000, value, 0.01);
  }

  @Test
  public void testDollarCostAveragingOnceNewPortfolio() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "20");
    obj.dollarCostAveragingOnce("f2", strategy, "2022-01-05", "1000", "2");

    double cost = obj.getCostBasis("f2", "2022-01-05");

    assertEquals(1006, cost, 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostAveragingOnceNegativeAmount() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "20");
    obj.dollarCostAveragingOnce("f1", strategy, "2022-01-05", "-1000", "2");


  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostAveragingOnceNegativeFee() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "20");
    obj.dollarCostAveragingOnce("f1", strategy, "2022-01-05", "1000", "-2");


  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostAveragingOnceLessFraction() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "10");
    obj.dollarCostAveragingOnce("f1", strategy, "2022-01-05", "1000", "2");


  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostAveragingOnceMoreFraction() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "40");
    obj.dollarCostAveragingOnce("f1", strategy, "2022-01-05", "1000", "2");


  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostAveragingOnceEmptyStrategy() {
    Map<String, String> strategy = new HashMap<>();
    obj.dollarCostAveragingOnce("f1", strategy, "2022-01-05", "1000", "2");


  }

  @Test(expected = IllegalArgumentException.class)
  public void testDollarCostAveragingHoliday() {
    Map<String, String> strategy = new HashMap<>();
    strategy.put("GOOG", "50");
    strategy.put("AAPL", "30");
    strategy.put("AMZN", "20");
    obj.dollarCostAveragingOnce("f1", strategy, "2022-10-01", "1000", "2");


  }

  /* create */

  /* Null portfolio Test */
  @Test(expected = IllegalArgumentException.class)
  public void nullStringInflexiblePortfolioTest() {

    obj.createInflexiblePortfolio(null);
  }

  /* Empty portfolio Test */
  @Test(expected = IllegalArgumentException.class)
  public void emptyStringInflexiblePortfolioTest() {

    obj.createInflexiblePortfolio("");
  }

  /* Null portfolio Test */
  @Test(expected = IllegalArgumentException.class)
  public void nullStringFlexiblePortfolioTest() {

    obj.createFlexiblePortfolio(null);
  }

  /* Empty portfolio Test */
  @Test(expected = IllegalArgumentException.class)
  public void emptyStringFlexiblePortfolioTest() {

    obj.createFlexiblePortfolio("");
  }

  /* Same Portfolio check Test */
  @Test(expected = IllegalArgumentException.class)
  public void portfolioAlreadyExists1() {

    obj.createInflexiblePortfolio("p1");
  }

  /* Same Portfolio check Test */
  @Test(expected = IllegalArgumentException.class)
  public void portfolioAlreadyExists2() {

    obj.createInflexiblePortfolio("f1");
  }

  /* Same Portfolio check Test */
  @Test(expected = IllegalArgumentException.class)
  public void portfolioAlreadyExists3() {

    obj.createFlexiblePortfolio("p1");
  }

  /* Same Portfolio check Test */
  @Test(expected = IllegalArgumentException.class)
  public void portfolioAlreadyExists4() {

    obj.createFlexiblePortfolio("f1");
  }

  /* getPortfolioComposition */

  /* CreatePortfolio Empty Portfolio check*/
  @Test
  public void emptyInflexiblePortfolioCompositionCheck() {

    assertEquals("", obj.getPortfolioComposition("p1"));
  }

  /* CreatePortfolio and GetComposition check */
  @Test
  public void checkInflexiblePortfolioCompositionTest() {

    obj.addStock("p1", "GOOG", "10");
    assertEquals("GOOG,10.00", obj.getPortfolioComposition("p1"));
  }

  /* CreatePortfolio and GetComposition check */
  @Test(expected = RuntimeException.class)
  public void checkFlexiblePortfolioCompositionTest() {

    obj.purchaseStock("f1", "GOOG", "10", "2022-10-03", "2");

    assertEquals("GOOG,10.00", obj.getPortfolioComposition("f1"));
  }


  /* Portfolio Composition doesn't exit Test */
  @Test(expected = IllegalArgumentException.class)
  public void portfolioCompositionNotExistTest() {

    obj.addStock("p1", "GOOG", "10");
    obj.getPortfolioComposition("p2");
  }

  /* Portfolio Composition null Test */
  @Test(expected = IllegalArgumentException.class)
  public void portfolioCompositionNullTest() {

    obj.addStock("p1", "GOOG", "10");
    obj.getPortfolioComposition(null);
  }

  /* Portfolio Composition empty Test */
  @Test(expected = IllegalArgumentException.class)
  public void portfolioNameEmptyTest() {

    obj.addStock("p1", "GOOG", "10");
    obj.getPortfolioComposition("");
  }

  /* getPortfolioCompositionByDate */

  @Test(expected = RuntimeException.class)
  public void getInflexiblePortfolioCompositionByDateTest() {
    obj.addStock("p1", "GOOG", "20");
    obj.getPortfolioCompositionByDate("p1", "2022-10-03");
  }

  @Test
  public void getFlexiblePortfolioCompositionByDateTest() {
    initFlexible();

    assertEquals("", obj.getPortfolioCompositionByDate("f1", "2022-10-01"));
    assertEquals("GOOG,20.00", obj.getPortfolioCompositionByDate("f1", "2022-10-04"));
    assertEquals("GOOG,40.00", obj.getPortfolioCompositionByDate("f1", "2022-10-11"));
    assertEquals("GOOG,30.00", obj.getPortfolioCompositionByDate("f1", "2022-10-13"));
    assertEquals("GOOG,30.00\nAAPL,30.00", obj.getPortfolioCompositionByDate("f1", "2022-10-15"));
  }

  @Test
  public void getFlexiblePortfolioCompositionInFutureTest() {
    initFlexible();

    assertEquals("GOOG,30.00\nAAPL,30.00", obj.getPortfolioCompositionByDate("f1", "2023-10-03"));
  }

  /* addStock */

  /* Portfolio doesn't exist check */
  @Test(expected = IllegalArgumentException.class)
  public void portfolioDoesNotExistTest() {
    obj.addStock("p2", "GOOG", "10");
  }

  /* Add empty stock Portfolio name test */
  @Test(expected = IllegalArgumentException.class)
  public void addEmptyStockTest() {

    obj.addStock("", "GOOG", "11");
  }

  /* Add null stock Portfolio name test */
  @Test(expected = IllegalArgumentException.class)
  public void addNullStockTest() {

    obj.addStock(null, "GOOG", "11");
  }

  /* Add lowercase stock symbol test */
  @Test
  public void addLowercaseStockTest() {

    obj.addStock("p1", "goog", "10");
    assertEquals(993, obj.getTotalValue("p1", "2022-10-03"), 0.01);
  }

  /* Add stock empty Symbol test */
  @Test(expected = IllegalArgumentException.class)
  public void addEmptyStockSymbolTest() {

    obj.addStock("p1", "", "11");
  }

  /* Add null empty Symbol test */
  @Test(expected = IllegalArgumentException.class)
  public void addNullStockSymbolTest() {

    obj.addStock("p1", null, "11");
  }

  /* Add stock zero Shares test */
  @Test(expected = IllegalArgumentException.class)
  public void addEmptyStockShareTest() {

    obj.addStock("p1", "GOOG", "0");
  }

  /* Add stock negative Shares test */
  @Test(expected = IllegalArgumentException.class)
  public void addNegativeStockShareTest() {

    obj.addStock("p1", "GOOG", "-3");
  }

  @Test(expected = IllegalArgumentException.class)
  public void addFractionStockShareTest() {

    obj.addStock("p1", "GOOG", "10.5");
  }

  /* Add stock exact 10 Shares test */
  @Test
  public void addTenStockShareTest() {

    obj.addStock("p1", "GOOG", "10");
    assertEquals("GOOG,10.00", obj.getPortfolioComposition("p1"));
  }

  /* Stock already exists test */
  @Test
  public void stockAlreadyExistsTest() {

    obj.addStock("p1", "GOOG", "20");
    obj.addStock("p1", "GOOG", "20");
    assertEquals("GOOG,40.00", obj.getPortfolioComposition("p1"));
  }

  /* Stock symbol invalid test */
  @Test(expected = RuntimeException.class)
  public void stockSymbolInvalidTest() {

    obj.addStock("p1", "G$$G", "20");
  }

  @Test(expected = RuntimeException.class)
  public void addStcokForFlexiblePortfolio() {
    obj.addStock("f1", "GOOG", "20");
  }

  /* purchaseStock */

  @Test(expected = RuntimeException.class)
  public void purchaseStockInFuture() {
    obj.purchaseStock("f1", "GOOG", "20", "2023-10-03", "4");
  }

  @Test(expected = RuntimeException.class)
  public void purchaseStockForInflexiblePortfolio() {
    obj.purchaseStock("p1", "GOOG", "20", "2022-10-03", "4");
  }

  @Test(expected = RuntimeException.class)
  public void purchaseStockForEmtpy() {
    obj.purchaseStock("", "GOOG", "20", "2022-10-03", "4");
  }

  @Test(expected = RuntimeException.class)
  public void purchaseStockForNull() {
    obj.purchaseStock(null, "GOOG", "20", "2022-10-03", "4");
  }

  @Test
  public void purchaseStockForFlexiblePortfolio() {
    obj.purchaseStock("f1", "GOOG", "20", "2022-11-09", "2");
    assertEquals("GOOG,20.00", obj.getPortfolioCompositionByDate("f1", "2022-11-10"));
  }

  @Test(expected = RuntimeException.class)
  public void purchaseFractionStockForFlexiblePortfolio() {
    obj.purchaseStock("f1", "GOOG", "20.3", "2022-11-09", "2");
  }

  @Test(expected = RuntimeException.class)
  public void purchaseNegativeStockForFlexiblePortfolio() {
    obj.purchaseStock("f1", "GOOG", "-20", "2022-11-09", "2");
  }

  @Test(expected = RuntimeException.class)
  public void purchaseInvalidStockForFlexiblePortfolio() {
    obj.purchaseStock("f1", "GODSADSOG", "20", "2022-11-09", "2");
  }

  @Test(expected = RuntimeException.class)
  public void purchaseStockForFlexiblePortfolioHoliday() {
    obj.purchaseStock("f1", "GOOG", "20", "2022-10-01", "2");
  }

  @Test(expected = RuntimeException.class)
  public void purchaseStockForFlexiblePortfolioInvalidDay() {
    obj.purchaseStock("f1", "GOOG", "20", "2022-18-08", "2");
  }

  @Test(expected = RuntimeException.class)
  public void purchaseStockForFlexiblePortfolioNullDay() {
    obj.purchaseStock("f1", "GOOG", "20", null, "2");
  }

  @Test(expected = RuntimeException.class)
  public void purchaseStockForFlexiblePortfolioNegativeCommissionFee() {
    obj.purchaseStock("f1", "GOOG", "20", "2022-18-08", "-2");
  }

  @Test
  public void checkCommissionFeeForPurchase() {
    obj.purchaseStock("f1", "GOOG", "20", "2022-10-03", "10");
    assertEquals(10, obj.getCostBasis("f1", "2022-10-03") - obj.getTotalValue("f1", "2022-10-03"),
        0.01);
  }

  /* sellStock */

  @Test(expected = RuntimeException.class)
  public void sellStockForInflexiblePortfolio() {
    obj.sellStock("p1", "GOOG", "20", "2022-10-03", "4");
  }

  @Test(expected = RuntimeException.class)
  public void sellStockForEmtpy() {
    obj.sellStock("", "GOOG", "20", "2022-10-03", "4");
  }

  @Test(expected = RuntimeException.class)
  public void sellStockForNull() {
    obj.sellStock(null, "GOOG", "20", "2022-10-03", "4");
  }

  @Test(expected = RuntimeException.class)
  public void sellInvalidForNull() {
    obj.sellStock(null, "GOdsadsaOG", "20", "2022-10-03", "4");
  }

  @Test(expected = RuntimeException.class)
  public void sellFractionStockForFlexiblePortfolio() {
    obj.purchaseStock("f1", "GOOG", "100", "2022-10-03", "2");
    obj.sellStock("f1", "GOOG", "20.3", "2022-11-09", "2");
  }

  @Test(expected = RuntimeException.class)
  public void sellNegativeStockForFlexiblePortfolio() {
    obj.purchaseStock("f1", "GOOG", "100", "2022-10-03", "2");
    obj.sellStock("f1", "GOOG", "-20", "2022-11-09", "2");
  }

  @Test(expected = RuntimeException.class)
  public void sellStockForFlexiblePortfolioHoliday() {
    obj.purchaseStock("f1", "GOOG", "100", "2022-10-03", "2");
    obj.sellStock("f1", "GOOG", "20", "2022-10-08", "2");
  }

  @Test(expected = RuntimeException.class)
  public void sellStockForFlexiblePortfolioInvalidDay() {
    obj.purchaseStock("f1", "GOOG", "100", "2022-10-03", "2");
    obj.sellStock("f1", "GOOG", "20", "2022-18-08", "2");
  }

  @Test(expected = RuntimeException.class)
  public void sellStockForFlexiblePortfolioNullDay() {
    obj.purchaseStock("f1", "GOOG", "100", "2022-10-03", "2");
    obj.sellStock("f1", "GOOG", "20", null, "2");
  }

  @Test(expected = RuntimeException.class)
  public void sellStockForFlexiblePortfolioNegativeCommissionFee() {
    obj.purchaseStock("f1", "GOOG", "100", "2022-10-03", "2");
    obj.sellStock("f1", "GOOG", "20", "2022-18-08", "-2");
  }

  @Test
  public void checkCommissionFeeForSell() {
    obj.purchaseStock("f1", "GOOG", "20", "2022-10-03", "10");
    obj.sellStock("f1", "GOOG", "20", "2022-10-04", "20");
    obj.addStock("p1", "GOOG", "20");
    assertEquals(30, obj.getCostBasis("f1", "2022-10-04") - obj.getTotalValue("p1", "2022-10-03"),
        0.01);
  }

  @Test(expected = RuntimeException.class)
  public void sellStockInFuture() {
    obj.purchaseStock("f1", "GOOG", "100", "2022-10-03", "2");
    obj.sellStock("f1", "GOOG", "20", "2023-11-09", "2");
  }

  /* getTotalValue */
  @Test
  public void getTotalValueFlexiblePortfolio() {

    initFlexible();

    assertEquals(0, obj.getTotalValue("f1", "2022-09-30"), 0.01);
    assertEquals(1986, obj.getTotalValue("f1", "2022-10-03"), 0.01);
    assertEquals(3948.4, obj.getTotalValue("f1", "2022-10-10"), 0.01);
    assertEquals(2949, obj.getTotalValue("f1", "2022-10-12"), 0.01);
    assertEquals(7295.7, obj.getTotalValue("f1", "2022-10-17"), 0.01);
  }

  @Test
  public void getTotalValueInflexiblePortfolio() {
    obj.addStock("p1", "GOOG", "20");
    assertEquals(1883.4, obj.getTotalValue("p1", "2022-11-10"), 0.1);
  }

  /* Portfolio total value check */
  @Test
  public void totalValuePortfolioTest() {

    obj.addStock("p1", "GOOG", "10");
    assertEquals(29473.699999999997, obj.getTotalValue("p1", "2021-12-15"), 0.1);
  }

  /* Portfolio total value Holiday check */
  @Test(expected = IllegalArgumentException.class)
  public void totalValueInflexiblePortfolioHolidayTest() {

    obj.addStock("p1", "GOOG", "10");
    obj.getTotalValue("p1", "2021-12-19");
  }

  /* Portfolio total value Holiday check */
  @Test(expected = IllegalArgumentException.class)
  public void totalValueFlexiblePortfolioHolidayTest() {

    obj.purchaseStock("f1", "GOOG", "20", "2022-10-03", "2");
    obj.getTotalValue("f1", "2022-11-05");
  }

  @Test(expected = IllegalArgumentException.class)
  public void totalValueFlexiblePortfolioFutureTest() {

    obj.purchaseStock("f1", "GOOG", "20", "2022-10-03", "2");
    obj.getTotalValue("f1", "2023-11-05");
  }

  /* Portfolio total value Holiday check */
  @Test(expected = IllegalArgumentException.class)
  public void invalidDateTestTotalValueForFlexible() {

    obj.purchaseStock("f1", "GOOG", "20", "2022-10-03", "2");
    obj.getTotalValue("f1", "2021/19/12");
  }

  /* Portfolio total value null check */
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullDateTestTotalValueForFlexible() {

    obj.purchaseStock("f1", "GOOG", "20", "2022-10-03", "2");
    obj.getTotalValue("f1", null);
  }

  /* Portfolio total value Holiday check */
  @Test(expected = IllegalArgumentException.class)
  public void invalidDateTestTotalValue() {

    obj.addStock("p1", "GOOG", "10");
    obj.getTotalValue("p1", "2021/19/12");
  }

  /* Portfolio total value null check */
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullDateTestTotalValue() {

    obj.addStock("p1", "GOOG", "10");
    obj.getTotalValue("p1", null);
  }

  /* Portfolio total value null check */
  @Test(expected = IllegalArgumentException.class)
  public void invalidNullPortfolioTestTotalValue() {
    obj.getTotalValue(null, "2021-12-19");
  }


  /* getCostBasis */

  @Test(expected = IllegalArgumentException.class)
  public void getCostBasisForInflexiblePortfolio() {
    obj.addStock("p1", "GOOG", "10");
    obj.getCostBasis("p1", "2021-12-19");
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCostBasisForNull() {
    obj.getCostBasis(null, "2021-12-19");
  }

  @Test(expected = IllegalArgumentException.class)
  public void getCostBasisForNonExist() {
    obj.getCostBasis("p213", "2021-12-19");
  }

  @Test
  public void getCostBasisFlexiblePortfolio() {
    initFlexible();

    assertEquals(0, obj.getCostBasis("f1", "2022-09-30"), 0.01);
    assertEquals(1988, obj.getCostBasis("f1", "2022-10-03"), 0.01);
    assertEquals(3966.2, obj.getCostBasis("f1", "2022-10-10"), 0.01);
    assertEquals(3970.2, obj.getCostBasis("f1", "2022-10-12"), 0.01);
    assertEquals(8125.6, obj.getCostBasis("f1", "2022-10-17"), 0.01);
    assertEquals(8125.6, obj.getCostBasis("f1", "2023-10-17"), 0.01);

  }

  /* save Portfolio */

  @Test
  public void saveFilePortfolioDoesntTest() {
    try {
      obj.savePortfolio("p2", "portfolio1");
      fail("Test failed");
    } catch (Exception e) {
      // do nothing.
    }
  }
  /*Test save null Portfolio*/

  @Test
  public void saveNullFilePortfolioDoesntTest() {
    try {
      obj.savePortfolio(null, "portfolio1");
      fail("Test failed");
    } catch (Exception e) {
      // do nothing.
    }
  }
  /*Test save null Portfolio*/

  @Test
  public void saveNullFileNamePortfolioDoesntTest() {
    try {
      obj.savePortfolio("p1", null);
      fail("Test failed");
    } catch (Exception e) {
      // do nothing.
    }
  }

  /*Test save Portfolio*/

  @Test
  public void saveFileInvalidFileTest() {
    try {
      obj.savePortfolio("p1", "portfolio portfolio");
      fail("Test failed");
    } catch (Exception e) {
      // do nothing.
    }
  }

  @Test
  public void saveInflexiblePortfolio() {

    obj.addStock("p1", "GOOG", "30");

    try {
      obj.savePortfolio("p1", "portfolio1");
    } catch (Exception e) {
      fail("Test failed!");
    }

    try {
      JSONParser parser = new JSONParser();

      FileReader reader = new FileReader("test1.json");
      Object parse = parser.parse(reader);

      JSONObject jsonObject = (JSONObject) parse;

      String type = (String) jsonObject.get("Type");
      String name = (String) jsonObject.get("Name");
      assertEquals("inflexible", type);
      assertEquals("testP1", name);

      JSONArray stocks = (JSONArray) jsonObject.get("Stock");
      Object stock = stocks.get(0);
      JSONObject s = (JSONObject) stock;
      String symbol = (String) s.get("Symbol");
      String shares = String.valueOf((double) s.get("Shares"));
      assertEquals("GOOG", symbol);
      assertEquals("30.0", shares);
    } catch (Exception e) {
      fail("Test failed!");
    }
  }

  @Test
  public void saveFlexiblePortfolio() {

    obj.purchaseStock("f1", "GOOG", "20", "2022-10-03", "2");

    try {
      obj.savePortfolio("f1", "flexiblePortfolio1");
    } catch (Exception e) {
      fail("Test failed!");
    }

    try {
      JSONParser parser = new JSONParser();

      FileReader reader = new FileReader("flexiblePortfolio1.json");
      Object parse = parser.parse(reader);

      JSONObject jsonObject = (JSONObject) parse;

      String type = (String) jsonObject.get("Type");
      String name = (String) jsonObject.get("Name");
      assertEquals("flexible", type);
      assertEquals("f1", name);

      JSONArray stocks = (JSONArray) jsonObject.get("Stock");
      Object stock = stocks.get(0);
      JSONObject s = (JSONObject) stock;
      String symbol = (String) s.get("Symbol");
      String shares = String.valueOf((double) s.get("Shares"));
      assertEquals("GOOG", symbol);
      assertEquals("20.0", shares);
      JSONArray transactions = (JSONArray) s.get("Transactions");
      JSONObject t = (JSONObject) transactions.get(0);
      String tDate = (String) t.get("Date");
      String tType = (String) t.get("Type");
      String tShares = String.valueOf((double) t.get("Shares"));
      String tCommissionFee = String.valueOf((double) t.get("CommissionFee"));

      assertEquals("2022-10-03", tDate);
      assertEquals("buy", tType);
      assertEquals("20.0", tShares);
      assertEquals("2.0", tCommissionFee);
    } catch (Exception e) {
      fail("Test failed!");
    }
  }

  /* loadPortfolio */

  /*test the load file*/
  @Test
  public void loadFileInvalidFormatTest() {

    try {
      obj.loadPortfolio("inavlidFormat.json");
      fail("Test failed");
    } catch (Exception e) {
      // do nothing.
    }
  }

  /*test the load file*/
  @Test
  public void loadFileInvalidStockSymbolTest() {

    try {
      obj.loadPortfolio("inavlidStock.json");
      fail("Test failed");
    } catch (Exception e) {
      // do nothing.
    }
  }

  /* load file not found test*/
  @Test
  public void fileNotFoundTest() {

    try {
      obj.loadPortfolio("testone.json");
      fail("File Not Found!");
    } catch (Exception e) {
      // do nothing.
    }
  }

  /*Load Invalid file format name*/

  @Test
  public void invalidFileNameLoadTest() {

    try {
      obj.loadPortfolio("testone.xml");
      fail("Test failed");
    } catch (Exception e) {
      // do nothing.
    }
  }
  /*Load null file format name*/

  @Test
  public void nullFileNameLoadTest() {

    try {
      obj.loadPortfolio(null);
      fail("Test failed");
    } catch (Exception e) {
      // do nothing.
    }
  }

  @Test
  public void loadInflexiblePortfolioTest() {

    try {
      obj.loadPortfolio("test1.json");
    } catch (Exception e) {
      fail("test failed");
    }
    assertEquals("GOOG,30.00", obj.getPortfolioComposition("testP1"));
  }

  @Test
  public void loadFlexiblePortfolioTest() {

    try {
      obj.loadPortfolio("test2.json");
    } catch (Exception e) {
      fail("test failed");
    }
    assertEquals("GOOG,80.00\nAAPL,30.00",
        obj.getPortfolioCompositionByDate("testP2", "2022-11-03"));
    assertEquals(0, obj.getCostBasis("testP2", "2022-09-30"), 0.01);
    assertEquals(9932, obj.getCostBasis("testP2", "2022-10-03"), 0.01);
    assertEquals(14320, obj.getCostBasis("testP2", "2022-10-04"), 0.01);
    assertEquals(14325, obj.getCostBasis("testP2", "2022-10-05"), 0.01);
  }


  /* getPortfolioPerformance */

  @Test(expected = IllegalArgumentException.class)
  public void getPortfolioPerformanceInvalidDay() {
    obj.addStock("p1", "GOOG", "50");
    obj.getPortfolioPerformance("p1", "2022-10-01", "2022-09-10");
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPortfolioPerformanceFuture() {
    obj.addStock("p1", "GOOG", "50");
    System.out.println(obj.getPortfolioPerformance("p1", "2022-10-01", "2023-09-10"));

  }

  @Test
  public void getInflexiblePortfolioPerformanceDaily() {
    obj.addStock("p1", "GOOG", "50");

    assertEquals("Performance of portfolio p1 from 2022-10-01 to 2022-10-20\n"
            + "\n"
            + "2022-10-03: *****************\n"
            + "2022-10-04: *********************************\n"
            + "2022-10-05: ********************************\n"
            + "2022-10-06: ********************************\n"
            + "2022-10-07: ******************\n"
            + "2022-10-10: **************\n"
            + "2022-10-11: ***********\n"
            + "2022-10-12: ************\n"
            + "2022-10-13: *******************\n"
            + "2022-10-14: ******\n"
            + "2022-10-17: ************************\n"
            + "2022-10-18: ***************************\n"
            + "2022-10-19: **********************\n"
            + "2022-10-20: ***********************\n"
            + "\n"
            + "Base: $4800    Scale: * = $10\n",
        obj.getPortfolioPerformance("p1", "2022-10-01", "2022-10-20").toString());
  }

  @Test
  public void getInflexiblePortfolioPerformanceWeekly() {
    obj.addStock("p1", "GOOG", "50");

    assertEquals("Performance of portfolio p1 from 2022-09-01 to 2022-11-10\n"
            + "\n"
            + "2022-09-02: *************************************\n"
            + "2022-09-16: *************************\n"
            + "2022-09-30: ******\n"
            + "2022-10-14: ********\n"
            + "2022-10-28: *******\n"
            + "2022-11-10: *\n"
            + "\n"
            + "Base: $4700    Scale: * = $20\n",
        obj.getPortfolioPerformance("p1", "2022-09-01", "2022-11-10").toString());
  }

  @Test
  public void getInflexiblePortfolioPerformanceMonthly() {
    obj.addStock("p1", "GOOG", "50");

    assertEquals("Performance of portfolio p1 from 2022-01-01 to 2022-11-10\n"
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
            + "Scale: * = $5000\n",
        obj.getPortfolioPerformance("p1", "2022-01-01", "2022-11-10").toString());
  }

  @Test
  public void getInflexiblePortfolioPerformanceThreeMonthly() {
    obj.addStock("p1", "GOOG", "50");

    assertEquals("Performance of portfolio p1 from 2019-05-01 to 2022-11-10\n"
            + "\n"
            + "May 2019: ************\n"
            + "Aug 2019: ************\n"
            + "Nov 2019: **************\n"
            + "Feb 2020: **************\n"
            + "May 2020: ***************\n"
            + "Aug 2020: *****************\n"
            + "Nov 2020: ******************\n"
            + "Feb 2021: *********************\n"
            + "May 2021: *************************\n"
            + "Aug 2021: ******************************\n"
            + "Nov 2021: *****************************\n"
            + "Feb 2022: ***************************\n"
            + "May 2022: ***********************\n"
            + "Aug 2022: **\n"
            + "Nov 2022: *\n"
            + "\n"
            + "Scale: * = $5000\n",
        obj.getPortfolioPerformance("p1", "2019-05-01", "2022-11-10").toString());
  }

  @Test
  public void getInflexiblePortfolioPerformanceThreeYearly() {
    obj.addStock("p1", "GOOG", "50");

    assertEquals("Performance of portfolio p1 from 2010-05-01 to 2022-11-10\n"
            + "\n"
            + "2014: ******\n"
            + "2015: ********\n"
            + "2016: ********\n"
            + "2017: ***********\n"
            + "2018: ***********\n"
            + "2019: **************\n"
            + "2020: ******************\n"
            + "2021: *****************************\n"
            + "2022: *\n"
            + "\n"
            + "Scale: * = $5000\n",
        obj.getPortfolioPerformance("p1", "2010-05-01", "2022-11-10").toString());
  }

  @Test
  public void getFlexiblePortfolioPerformanceDaily() {
    obj.purchaseStock("f1", "GOOG", "50", "2016-01-04", "2");

    assertEquals("Performance of portfolio f1 from 2022-10-01 to 2022-10-20\n"
            + "\n"
            + "2022-10-03: *****************\n"
            + "2022-10-04: *********************************\n"
            + "2022-10-05: ********************************\n"
            + "2022-10-06: ********************************\n"
            + "2022-10-07: ******************\n"
            + "2022-10-10: **************\n"
            + "2022-10-11: ***********\n"
            + "2022-10-12: ************\n"
            + "2022-10-13: *******************\n"
            + "2022-10-14: ******\n"
            + "2022-10-17: ************************\n"
            + "2022-10-18: ***************************\n"
            + "2022-10-19: **********************\n"
            + "2022-10-20: ***********************\n"
            + "\n"
            + "Base: $4800    Scale: * = $10\n",
        obj.getPortfolioPerformance("f1", "2022-10-01", "2022-10-20").toString());
  }

  @Test
  public void getFlexiblePortfolioPerformanceWeekly() {
    obj.purchaseStock("f1", "GOOG", "50", "2016-01-04", "2");

    assertEquals("Performance of portfolio f1 from 2022-09-01 to 2022-11-10\n"
            + "\n"
            + "2022-09-02: *************************************\n"
            + "2022-09-16: *************************\n"
            + "2022-09-30: ******\n"
            + "2022-10-14: ********\n"
            + "2022-10-28: *******\n"
            + "2022-11-10: *\n"
            + "\n"
            + "Base: $4700    Scale: * = $20\n",
        obj.getPortfolioPerformance("f1", "2022-09-01", "2022-11-10").toString());
  }

  @Test
  public void getFlexiblePortfolioPerformanceMonthly() {
    obj.purchaseStock("f1", "GOOG", "50", "2016-01-04", "2");

    assertEquals("Performance of portfolio f1 from 2022-01-01 to 2022-11-10\n"
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
            + "Scale: * = $5000\n",
        obj.getPortfolioPerformance("f1", "2022-01-01", "2022-11-10").toString());
  }

  @Test
  public void getFlexiblePortfolioPerformanceThreeMonthly() {
    obj.purchaseStock("f1", "GOOG", "50", "2016-01-04", "2");

    assertEquals("Performance of portfolio f1 from 2019-05-01 to 2022-11-10\n"
            + "\n"
            + "May 2019: ************\n"
            + "Aug 2019: ************\n"
            + "Nov 2019: **************\n"
            + "Feb 2020: **************\n"
            + "May 2020: ***************\n"
            + "Aug 2020: *****************\n"
            + "Nov 2020: ******************\n"
            + "Feb 2021: *********************\n"
            + "May 2021: *************************\n"
            + "Aug 2021: ******************************\n"
            + "Nov 2021: *****************************\n"
            + "Feb 2022: ***************************\n"
            + "May 2022: ***********************\n"
            + "Aug 2022: **\n"
            + "Nov 2022: *\n"
            + "\n"
            + "Scale: * = $5000\n",
        obj.getPortfolioPerformance("f1", "2019-05-01", "2022-11-10").toString());
  }

  @Test
  public void getFlexiblePortfolioPerformanceThreeYearly() {
    obj.purchaseStock("f1", "GOOG", "50", "2016-01-04", "2");

    assertEquals("Performance of portfolio f1 from 2010-05-01 to 2022-11-10\n"
            + "\n"
            + "2010: \n"
            + "2011: \n"
            + "2012: \n"
            + "2013: \n"
            + "2014: \n"
            + "2015: \n"
            + "2016: ********\n"
            + "2017: ***********\n"
            + "2018: ***********\n"
            + "2019: **************\n"
            + "2020: ******************\n"
            + "2021: *****************************\n"
            + "2022: *\n"
            + "\n"
            + "Scale: * = $5000\n",
        obj.getPortfolioPerformance("f1", "2010-05-01", "2022-11-10").toString());
  }

  @Test
  public void testMultipleStock() {

    obj.addStock("p1", "GOOG", "30");
    obj.addStock("p1", "AMZN", "30");
    obj.addStock("p1", "A", "30");
    obj.addStock("p1", "AA", "30");
    obj.addStock("p1", "ABC", "30");
    obj.addStock("p1", "AAC", "30");
    obj.addStock("p1", "MSFT", "30");
    obj.addStock("p1", "TSLA", "30");
    obj.addStock("p1", "AAPL", "30");
    obj.addStock("p1", "MA", "30");
    obj.addStock("p1", "AAL", "30");
    obj.addStock("p1", "AAMC", "30");
    obj.addStock("p1", "AAP", "30");
    obj.addStock("p1", "AB", "30");
    obj.addStock("p1", "ABB", "30");

    assertEquals("AA,30.00\n"
        + "GOOG,30.00\n"
        + "A,30.00\n"
        + "AB,30.00\n"
        + "ABB,30.00\n"
        + "ABC,30.00\n"
        + "AAPL,30.00\n"
        + "AAC,30.00\n"
        + "AAMC,30.00\n"
        + "AAL,30.00\n"
        + "MSFT,30.00\n"
        + "AAP,30.00\n"
        + "TSLA,30.00\n"
        + "MA,30.00\n"
        + "AMZN,30.00", obj.getPortfolioComposition("p1"));

    assertEquals(50876.4, obj.getTotalValue("p1", "2022-10-03"), 0.001);
  }

  @Test
  public void createInFlexiblePortfolio() {
    obj.addStock("p1", "GOOG", "20");
    assertEquals("GOOG,20.00", obj.getPortfolioComposition("p1"));
  }

  @Test(expected = RuntimeException.class)
  public void createInFlexiblePortfolioWithFlexible() {
    obj.addStock("f1", "GOOG", "20");
    obj.getPortfolioComposition("f1");
  }

  @Test
  public void getInflexiblePortfolioComposition() {
    obj.addStock("p1", "GOOG", "20");

    assertEquals("GOOG,20.00", obj.getPortfolioComposition("p1"));
  }

  @Test(expected = RuntimeException.class)
  public void getFlexiblePortfolioComposition() {
    obj.purchaseStock("f1", "GOOG", "20", "2022-11-09", "2");
    assertEquals("GOOG,20.00", obj.getPortfolioComposition("f1"));
  }

  @Test
  public void getPortfolioCompositionByDateFlexiblePortfolio() {

    obj.purchaseStock("f1", "GOOG", "20", "2022-11-09", "2");

    assertEquals("GOOG,20.00", obj.getPortfolioCompositionByDate("f1", "2022-11-10"));

  }

  @Test(expected = RuntimeException.class)
  public void getPortfolioCompositionByDateInFlexiblePortfolio() {

    obj.addStock("p1", "GOOG", "20");

    obj.getPortfolioCompositionByDate("p1", "2022-11-10");

  }

  @Test(expected = RuntimeException.class)
  public void sellStocksInFlexiblePortfolio() {
    obj.purchaseStock("p1", "GOOG", "20", "2022-11-09", "2");
    obj.sellStock("p1", "GOOG", "5", "2022-11-09", "2");
  }

  @Test
  public void addStockFlexiblePortfolio() {
    obj.addStock("p1", "AAPL", "50");
    assertEquals("AAPL,50.00", obj.getPortfolioComposition("p1"));
  }

  @Test(expected = RuntimeException.class)
  public void addStockInFlexiblePortfolio() {
    obj.addStock("f1", "AAPL", "50");
  }

  @Test
  public void getCostBasisOfPortfolio() {

    obj.purchaseStock("f1", "GOOG", "20", "2022-11-09", "2");
    assertEquals(1750.0, obj.getCostBasis("f1", "2022-11-09"), 0.1);
  }


  private void initFlexible() {
    obj.purchaseStock("f1", "GOOG", "20", "2022-10-03", "2");
    obj.purchaseStock("f1", "GOOG", "20", "2022-10-10", "4");
    obj.sellStock("f1", "GOOG", "10", "2022-10-12", "4");
    obj.purchaseStock("f1", "AAPL", "30", "2022-10-14", "4");
  }
}