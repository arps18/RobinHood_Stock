# Design README

This README file presents the design idea of the project

**Table of Contents**

* [Design Change log](#design-change-log)
* [MVC](#mvc)
* [Why MVC ?](#why-mvc)
* [Implementation of MVC](#implementation-of-mvc)
* [Design](#design)

***Stock application helps users who are new to investing to learn about how money could grow, in
the style of "virtual gambling".***

## Design Change Log - Assignment 6

1. Added the strategy for investing <br>
   a. Start to Finish Dollar Cost Strategy
    - In this strategy, an investor creates a portfolio of stocks and determines their relative
      proportion (e.g. 60% stocks, 40% bonds, with the stocks equally divided into technology,
      financial, utility, real estate, consumer staple and consumer discretionary companies and the
      bonds equally divided into federal, state and municipal bonds). Then the investor invests a
      fixed amount of money in this portfolio at regular frequency (e.g. invest $1000 at the
      beginning of each month) over a long period of time, paying no attention to what the stock
      market is doing on the days of the stock purchases.<br>

   b. Dollar Cost Averaging
    - Invest a fixed amount once into an existing portfolio containing multiple stocks, using a
      specified weight for each stock in the portfolio. For example, the user can create a FANG
      portfolio (Facebook, Apple, Netflix, Google) and then specify to invest $2000 in the portfolio
      on a specific date, such that 40% goes towards Facebook, 20% towards Apple, 30% towards
      Netflix and 10% towards Google).


2. Implemented Graphic User Interface

- Added the GUI part to the view which is an interactive component. It has all the listed features
  of the text based interface and additionally supports the various strategies and displays the
  performance of the portfolio.

3. Added the Performance of the portfolio in GUI

- The user can view the performance of the portfolio by selecting the performance option in the GUI.
  The performance of the portfolio can be seen in a bar graph chart form.

4. Extracted Performance as a separate Class

- We extracted the performance from the model in the previous iteration to make it a complete new
  class of its own because it was just on the text view basis whereas the new view is used to show
  the graph visualization compared to the previous CLI based one.

5. The new StockGUIController

- We implemented a new controller class for the new GUI based view which controls the model and the
  view of the GUI based interface and have the new interface called features which is used for the
  interaction of the view.

## Design Change Log - Assignment 5

1. Command Design Pattern
    - We implemented the command Design pattern for making the application extensible. Also, it
      has the following advantages :
        - The command design pattern has a unifying effect, making unrelated lines of code appear
          as  
          if working towards the same purpose.
        - The command design pattern promotes delegation.
        - The command design pattern extends its unifying effect by allowing common operations  
          across commands.
2. New file format
    - We switched our saving and loading file format from `.csv` to `.json` because the flexible  
      portfolio has many fields and transactions (which becomes a nested structure) and it becomes  
      quite efficient way to store in `json` format.
3. Menu
    - We added the new functionalities in the Menu in addition restoring the previous  
      functionalities.
4. Portfolio selection
    - Since the portfolio provides the option of selection of flexible as well as non-flexible  
      portfolio, so where previously portfolio was in StockModel now we extract as a class and  
      provide the two types of portfolio.
5. Rename of StockDataImpl
    - We have renamed the StockData Implementation file to AlphaVantageStockDataImpl for
      indicating  
      the API file.
6. Added Transaction Class
    - We have added a Transaction class in our model which handles various transactions of our  
      application.
7. Output location
    - We have moved our output from controller to the view model.

## MVC

> The Model-View-Controller (MVC) is a well-known design pattern in development process. It is
> way to organize our code. It specifies that a program or application shall consist of data
> model, presentation information and control information. The MVC pattern needs all these
> components to be separated as different objects.

The MVC pattern architecture consists of three layers:

- **Model:** It represents the business layer of application. It is an object to carry the data that
  can also contain the logic to update controller if data is changed.
- **View:** It represents the presentation layer of application. It is used to visualize the data
  that the model contains.
- **Controller:** It works on both the model and view. It is used to manage the flow of    
  application, i.e. data flow in the model object and to update the view whenever data is changed.

## Why MVC?

The reason for choosing the MVC pattern comes with its own benefits which are as follows:

- MVC has the feature of scalability that in turn helps the growth of application.
- The components are easy to maintain because there is less dependency.
- A model can be reused by multiple views that provides reusability of code.
- The developers can work with the three layers (Model, View, and Controller) simultaneously.
- Using MVC, the application becomes more understandable.
- Using MVC, each layer is maintained separately therefore we do not require to deal with
  massive code.
- The extending and testing of application is easier.

## Implementation of MVC

We have implemented MVC pattern in the program by creating the following classes:

- **StockModel**, will act as model layer
- **StockView**, will act as a view layer
- **StockController**, will act a controller layer

## Design

### StockController

> This interface is the controller of the application. The controller reads user's input in a CLI
> (command-Line Interface) or a GUI (Graphical User Interface) pattern.
<hr>  

### Commands

> Adding support for a new text command is easy as adding a new entry to the map. This allows us
> to quickly assemble a list of supported text commands for a controller. The controller's logic
> does not change depend on the number and complexity of supported text commands.
<hr>  

### Portfolio

> The portfolio represents a portfolio which can be used to create an inflexible or a flexible
> type of portfolio. It has methods which are used to modify and observe the portfolio.
<hr>  

### Transaction

> The transaction class lists all the methods used in transaction of a portfolio. It lists the  
> methods such as getting date, Shares, price, and Commission fee.
<hr>  

### Stock

> This interface gets the information of the stock, as one of the stocks to be added in the    
> portfolio, it has the symbol name, and quantity of the shares. There are the methods used to
> get stock symbol and quantity information. Besides, the toString function is also provided to
> output all of this information as a string format.
<hr>  

### StockData

- This interface is used to get the information from the API and store the information. The  
  working of the StockData is as follows:

    1. For determining the total value of a portfolio to observe the price of a stock on a
       certain date, it retrieves the data called from the Alphavantage TIME_SERIES_INTRADAY API to
       get all prices of the stocks included in the portfolio, which is extracted using the hashmap
       and calculates the total value of the portfolio on that particular date.
    2. In order to **reduce the calls of the API**, we will store the information once getting    
       results from querying, in a **set** to store the legal stock name, and a **map** to store    
       stock prices.

### StockModel

- This is the main interface of the application, it implements all the features and carries the    
  portfolios and stock data.
    1. `Map<String, Map<String, Stock>> portfolios` The outer map’s key is the portfolio name,
       and the value is another map. The inner map’s key is the stock symbol name and the value is
       the stock object [mentioned above](#stock).
    2. `stockData` is a StockData object [mentioned above](#stockdata).
    3. `createPortfolio`, is a method to create a new portfolio with the desired name.
    4. `addStock` is a method to add the stock into a certain portfolio with the stock symbol
       and share quantity. It will **only be called** in the process of creating a portfolio.
    5. `getPortfolioComposition` is a method to get the composition of the given portfolio name.
    6. `getTotalValue` is a method to get the total closing price value of the given portfolio on  
       a certain date.
    7. `savePortfolio` is a method to save a certain portfolio as a **JSON file** with the given  
       file name.
    8. `loadPortfolio` is a method to load the portfolio from the given file.

### MockStockModel

> This model is to test whether the inputs provided to the controller were correctly transmitted  
> to the model and whether the results from the model were correctly transmitted to the Appendable  
> object by the controller.

### StockView

> This part is used to visualize the data that the model contains. But the application is used as  
> a text-based user interface, it is empty for now and just meets the integrity of the MVC pattern.

### Features

> This interface acts as a medium between the view and the controller which handles the interaction
> done on the view.

### StockGUIView

> This part acts as a view component for the GUI interface which enables the GUI for the
> application.
***