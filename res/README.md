# Stocks

> Stocks is an application that helps users who are new to investing to learn about how money could
> grow, in the style of "virtual gambling".
> Similar to virtual gambling, our application will not use real money and investment. Instead it
> will allow the user to create investment portfolios, try out buying and selling of stock, and
> various investment strategies to see how they can grow (or shrink) their money with time.

Our program has the following features:

- Allow a user to create one or more portfolios and different types of portfolios. A user can create
  one or more empty flexible portfolios.

- Allow a user to create a flexible or inflexible type of portfolios. In an inflexible portfolio, it
  cannot be modified once it is created. Whereas in a flexible portfolio, a user can purchase and
  sell stocks whenever they want.

- Examine the composition of a inflexible portfolio and need it determines the value of a flexible
  portfolio on a specific date.

- Examine the costBasis of the flexible portfolio on a certain date.

- Determine the total value of a portfolio on a certain date.

- Examine the performance over a period of time of the portfolio.

- Persist a portfolio so that it can be saved and loaded (i.e. save to and retrieve from files)

- Ability to select a GUI/CLI interface for running the application.

- Make strategy for the portfolio.

- Observe the performance of the portfolio.

## Features

We have implemented the following features in our program:

- [x] Create a flexible portfolio
- [x] Create an inflexible portfolio
- [x] Purchase stocks in a flexible portfolio
- [x] Sell stocks in a flexible portfolio
- [x] Examine composition of an inflexible portfolio
- [x] Examine composition of a flexible portfolio on a certain date
- [x] Determine the total value of a portfolio on a certain date
- [x] Determine the costBasis of the portfolio
- [x] Performance graph of the portfolio
- [x] Save a portfolio as a file
- [x] Load a portfolio from a file
- [x] Option of a GUI or a CLI Interface
- [x] Make strategy for the portfolio
- [x] Observe the visual performance of the portfolio in the form of bar graph

## More About Features

#### 1. Create an Inflexible Portfolio

> The ``InflexiblePortfolio`` allows a user to create an inflexible portfolio by providing name to
> it.
>
> The  ``addStock`` allows a user to add the desired stocks in the portfolio.

#### 2. Create an flexible Portfolio

> The ``FlexiblePortfolio`` allows a user to create a flexible portfolio by providing name to it.

#### 3. Purchase stocks in a flexible Portfolio

> The  ``purchaseStock`` allows a user to add the desired stocks in the portfolio.

#### 4. Sell stocks in a flexible Portfolio

> The  ``sellStock`` allows a user to add the desired stocks in the portfolio.

#### 5. Examine composition of an Inflexible portfolio

> The ``examineComposition`` allows a user to observe the composition of an inflexible portfolio by
> providing name to it.

#### 6. Examine composition of a flexible portfolio on a certain date

> The ``examineCompositionByDate`` allows a user to observe the composition of the flexible
> portfolio by providing name and date to it.

#### 7. Determine the total value of a portfolio on a certain date

> The ``determineTotalValue`` allows a user to observe the total value of a portfolio by providing
> name to it.

#### 8. Determine the costBasis of a portfolio on a certain date

> The ``determineCostBasis`` allows a user to observe the total value of a portfolio by providing
> name to it.

#### 9. Determine the performance of a portfolio on a certain date

> The ``getPerfomance`` allows a user to observe the performance of a portfolio by providing name to
> it.

#### 10. Save a portfolio as a file

> The ``savePortfolio`` allows a user to save a portfolio by providing a file name to it.

#### 11. Load a portfolio from a file

> The ``loadPortfolio`` allows a user to load a portfolio by providing a file name to load it.

#### 12. Ability to select between CLI interface or a GUI interface.

> The ``StockController`` allows a user to select between a CLI or a GUI interface.

#### 13. Make strategy for the portfolio

> The ``DollarCostAveraging`` allows the user to make investment with the help of a strategy.

#### 14. Get the performance of the portfolio in GUI

> The ``PerformanceBarChart`` allows a user to observe the performance of the portfolio with a
> visual bar chart.