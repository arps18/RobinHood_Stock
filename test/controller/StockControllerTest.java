package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.Reader;
import java.io.StringReader;
import model.MockStockModel;
import org.junit.Test;
import view.StockViewImpl;

/**
 * Tests for Stock Controller.
 */
public class StockControllerTest {

  @Test
  public void mockCreateEmptyInflexiblePortfolio() {
    StringBuilder log = new StringBuilder();
    MockStockModel mockStockModel = new MockStockModel(log);
    Reader in = new StringReader(
        "1\ntest\nq\nq");
    StringBuffer out = new StringBuffer();
    StockController controller = new StockControllerImpl(new StockViewImpl(out), mockStockModel,
        in);
    try {
      controller.start();
    } catch (Exception e) {
      fail("test fail");
    }

    assertEquals("Welcome!\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the inflexible portfolio name you want to create: Create succeed!\n"
        + "You can press + to add a stock to test, else press any key to continue.\n"
        + "Press Q/q to end, else press any key for Main menu.\n", out.toString());

    assertEquals("create inflexible test\n", log.toString());
  }

  @Test
  public void mockCreateEmptyFlexiblePortfolio() {
    StringBuilder log = new StringBuilder();
    MockStockModel mockStockModel = new MockStockModel(log);
    Reader in = new StringReader(
        "2\ntest\nq");
    StringBuffer out = new StringBuffer();
    StockController controller = new StockControllerImpl(new StockViewImpl(out), mockStockModel,
        in);
    try {
      controller.start();
    } catch (Exception e) {
      fail("test fail");
    }

    assertEquals("Welcome!\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the flexible portfolio name you want to create: Create succeed!\n"
        + "Press Q/q to end, else press any key for Main menu.\n", out.toString());

    assertEquals("create flexible test\n", log.toString());
  }

  @Test
  public void mockCreateInflexiblePortfolioAndAddStock() {
    StringBuilder log = new StringBuilder();
    MockStockModel mockStockModel = new MockStockModel(log);
    Reader in = new StringReader(
        "1\ntest\n+\nGOOG\n20\nq\nq");
    StringBuffer out = new StringBuffer();
    StockController controller = new StockControllerImpl(new StockViewImpl(out), mockStockModel,
        in);
    try {
      controller.start();
    } catch (Exception e) {
      fail("test fail");
    }

    assertEquals("Welcome!\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the inflexible portfolio name you want to create: Create succeed!\n"
        + "You can press + to add a stock to test, else press any key to continue.\n"
        + "Please enter the stock symbol: Please enter the quantity of share: Add succeed!\n"
        + "You can press + to add a stock to test, else press any key to continue.\n"
        + "Press Q/q to end, else press any key for Main menu.\n", out.toString());

    assertEquals("create inflexible test\n" + "add test GOOG 20\n", log.toString());
  }

  @Test
  public void mockPurchaseStock() {
    StringBuilder log = new StringBuilder();
    MockStockModel mockStockModel = new MockStockModel(log);
    Reader in = new StringReader(
        "2\ntest\nc\n3\ntest\nGOOG\n20\n2022-10-03\n3\nq\n");
    StringBuffer out = new StringBuffer();
    StockController controller = new StockControllerImpl(new StockViewImpl(out), mockStockModel,
        in);
    try {
      controller.start();
    } catch (Exception e) {
      fail("test fail");
    }

    assertEquals("Welcome!\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the flexible portfolio name you want to create: Create succeed!\n"
        + "Press Q/q to end, else press any key for Main menu.\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the portfolio name you want to purchase the stock at: "
        + "Please enter the symbol of stock you want to purchase: "
        + "Please enter the share quantity you want to purchase: "
        + "Please enter the date you want to purchase(yyyy-mm-dd): "
        + "Please enter the commission fee for this transaction: Purchase succeed!\n"
        + "Press Q/q to end, else press any key for Main menu.\n", out.toString());

    assertEquals("create flexible test\n"
        + "Purchase Stock for test GOOG 20 2022-10-03 3\n", log.toString());
  }

  @Test
  public void mockSellStock() {
    StringBuilder log = new StringBuilder();
    MockStockModel mockStockModel = new MockStockModel(log);
    Reader in = new StringReader(
        "2\ntest\nc\n4\ntest\nGOOG\n20\n2022-10-03\n3\nq\n");
    StringBuffer out = new StringBuffer();
    StockController controller = new StockControllerImpl(new StockViewImpl(out), mockStockModel,
        in);
    try {
      controller.start();
    } catch (Exception e) {
      fail("test fail");
    }

    assertEquals("Welcome!\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the flexible portfolio name you want to create: Create succeed!\n"
        + "Press Q/q to end, else press any key for Main menu.\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the portfolio name you want to sell the stock at: "
        + "Please enter the symbol of stock you want to sell: "
        + "Please enter the share quantity you want to sell: "
        + "Please enter the date you want to sell(yyyy-mm-dd): "
        + "Please enter the commission fee for this transaction: Sell succeed!\n"
        + "Press Q/q to end, else press any key for Main menu.\n", out.toString());

    assertEquals("create flexible test\n"
        + "Sell Stock for test GOOG 20 2022-10-03 3\n", log.toString());
  }

  @Test
  public void mockGetInflexiblePortfolioComposition() {
    StringBuilder log = new StringBuilder();
    MockStockModel mockStockModel = new MockStockModel(log);
    Reader in = new StringReader(
        "1\ntest\nq\nc\n5\ntest\nq\n");
    StringBuffer out = new StringBuffer();
    StockController controller = new StockControllerImpl(new StockViewImpl(out), mockStockModel,
        in);
    try {
      controller.start();
    } catch (Exception e) {
      fail("test fail");
    }

    assertEquals("Welcome!\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the inflexible portfolio name you want to create: Create succeed!\n"
        + "You can press + to add a stock to test, else press any key to continue.\n"
        + "Press Q/q to end, else press any key for Main menu.\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the inflexible portfolio name you want to examine: test:\n"
        + "null\n"
        + "Press Q/q to end, else press any key for Main menu.\n", out.toString());

    assertEquals("create inflexible test\ncomposition test\n", log.toString());
  }

  @Test
  public void mockGetFlexiblePortfolioComposition() {
    StringBuilder log = new StringBuilder();
    MockStockModel mockStockModel = new MockStockModel(log);
    Reader in = new StringReader(
        "2\ntest\nc\n6\ntest\n2022-10-03\nq\n");
    StringBuffer out = new StringBuffer();
    StockController controller = new StockControllerImpl(new StockViewImpl(out), mockStockModel,
        in);
    try {
      controller.start();
    } catch (Exception e) {
      fail("test fail");
    }

    assertEquals("Welcome!\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the flexible portfolio name you want to create: Create succeed!\n"
        + "Press Q/q to end, else press any key for Main menu.\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the flexible portfolio name you want to examine: "
        + "Please enter the date you want to examine(yyyy-mm-dd): test:\n"
        + "null\n"
        + "Press Q/q to end, else press any key for Main menu.\n", out.toString());

    assertEquals("create flexible test\ncomposition by date test 2022-10-03\n", log.toString());
  }

  @Test
  public void mockDetermineTotalValue() {
    StringBuilder log = new StringBuilder();
    MockStockModel mockStockModel = new MockStockModel(log);
    Reader in = new StringReader(
        "2\ntest\nc\n7\ntest\n2022-10-03\nq\n");
    StringBuffer out = new StringBuffer();
    StockController controller = new StockControllerImpl(new StockViewImpl(out), mockStockModel,
        in);
    try {
      controller.start();
    } catch (Exception e) {
      fail("test fail");
    }

    assertEquals("Welcome!\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the flexible portfolio name you want to create: Create succeed!\n"
        + "Press Q/q to end, else press any key for Main menu.\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the portfolio name you want to determine the total value: "
        + "Please enter the date you want to determine(yyyy-mm-dd): "
        + "Portfolio: test date: 2022-10-03 total value(closing price): $0.0\n"
        + "Press Q/q to end, else press any key for Main menu.\n", out.toString());

    assertEquals("create flexible test\nvalue test 2022-10-03\n", log.toString());
  }


  @Test
  public void mockGetCostBasis() {
    StringBuilder log = new StringBuilder();
    MockStockModel mockStockModel = new MockStockModel(log);
    Reader in = new StringReader(
        "2\ntest\nc\n8\ntest\n2022-10-13\nq\n");
    StringBuffer out = new StringBuffer();
    StockController controller = new StockControllerImpl(new StockViewImpl(out), mockStockModel,
        in);
    try {
      controller.start();
    } catch (Exception e) {
      fail("test fail");
    }

    assertEquals("Welcome!\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the flexible portfolio name you want to create: Create succeed!\n"
        + "Press Q/q to end, else press any key for Main menu.\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the portfolio name you want to determine the cost basis: "
        + "Please enter the date you want to determine(yyyy-mm-dd): "
        + "Portfolio: test date: 2022-10-13 cost basis: $0.0\n"
        + "Press Q/q to end, else press any key for Main menu.\n", out.toString());

    assertEquals("create flexible test\n"
        + "Get cost basis of test 2022-10-13\n", log.toString());
  }

  @Test
  public void mockGetPerformance() {
    StringBuilder log = new StringBuilder();
    MockStockModel mockStockModel = new MockStockModel(log);
    Reader in = new StringReader(
        "2\ntest\nc\n9\ntest\n2022-10-03\n2022-10-20\nq\n");
    StringBuffer out = new StringBuffer();
    StockController controller = new StockControllerImpl(new StockViewImpl(out), mockStockModel,
        in);
    try {
      controller.start();
    } catch (Exception e) {
      fail("test fail");
    }

    assertEquals("Welcome!\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the flexible portfolio name you want to create: "
        + "Create succeed!\n"
        + "Press Q/q to end, else press any key for Main menu.\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the portfolio name you want to get the performance: "
        + "Please enter the start date(yyyy-mm-dd): "
        + "Please enter the end date(yyyy-mm-dd): "
        + "null"
        + "Press Q/q to end, else press any key for Main menu.\n", out.toString());

    assertEquals("create flexible test\n"
        + "performance of portfolio test from 2022-10-03 2022-10-20\n", log.toString());
  }

  @Test
  public void mockSave() {
    StringBuilder log = new StringBuilder();
    MockStockModel mockStockModel = new MockStockModel(log);
    Reader in = new StringReader(
        "2\ntest\nc\n10\ntest\ntest\nq\n");
    StringBuffer out = new StringBuffer();
    StockController controller = new StockControllerImpl(new StockViewImpl(out), mockStockModel,
        in);
    try {
      controller.start();
    } catch (Exception e) {
      fail("test fail");
    }

    assertEquals("Welcome!\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the flexible portfolio name you want to create: Create succeed!\n"
        + "Press Q/q to end, else press any key for Main menu.\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the portfolio name you want to save as: "
        + "Please enter the file name you want to save as: "
        + "Action succeed, the portfolio is saved as test.json!\n"
        + "Press Q/q to end, else press any key for Main menu.\n", out.toString());

    assertEquals("create flexible test\n"
        + "save test test\n", log.toString());
  }

  @Test
  public void mockLoad() {
    StringBuilder log = new StringBuilder();
    MockStockModel mockStockModel = new MockStockModel(log);
    Reader in = new StringReader(
        "2\ntest\nc\n11\ntest.json\nq\n");
    StringBuffer out = new StringBuffer();
    StockController controller = new StockControllerImpl(new StockViewImpl(out), mockStockModel,
        in);
    try {
      controller.start();
    } catch (Exception e) {
      fail("test fail");
    }

    assertEquals("Welcome!\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the flexible portfolio name you want to create: Create succeed!\n"
        + "Press Q/q to end, else press any key for Main menu.\n"
        + "You can choose:\n"
        + "1.  Create inflexible portfolio\n"
        + "2.  Create flexible portfolio\n"
        + "3.  Purchase stock in a flexible portfolio\n"
        + "4.  Sell stock in a flexible portfolio\n"
        + "5.  Examine portfolio composition of a inflexible portfolio\n"
        + "6.  Examine composition of a flexible portfolio by certain date\n"
        + "7.  Determine total value\n"
        + "8.  Determine costs basis of a flexible portfolio\n"
        + "9.  Get portfolio performance\n"
        + "10. Save a portfolio\n"
        + "11. Load a portfolio\n"
        + "Q/q. quit\n"
        + "Please enter the file(json, include the extension) name you want to "
        + "load as a portfolio: "
        + "Action succeed!\n"
        + "Press Q/q to end, else press any key for Main menu.\n", out.toString());

    assertEquals("create flexible test\n"
        + "load test.json\n", log.toString());
  }

}