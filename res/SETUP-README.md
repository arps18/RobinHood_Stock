### Installation

_Follow the below steps to install and run the JAR file._

1. Visit the stock repository  
   at [https://github.com/Larry546/Stocks.git](https://github.com/Larry546/Stocks.git) or download  
   the project folder.
2. Clone the repo if downloading from GitHub  
   ```  git clone https://github.com/Larry546/Stocks.git ```

3. Open the `res` folder.

4. Open the `stock.jar` file in the terminal/command prompt of your choice.

5. Run the `jar` file
   ``` java -jar stock.jar  ```

6. Enter `t` to start a text-based view and enter `g` to start a GUI one.

### Import the jar file

> We are using the `json-simple`, `jcalendar` & `jfreechart` as a third party library to support our
> application.

1. Go to File -> Project Structure -> Project Settings -> Modules -> Dependencies
2. Click on the + (**plus**) sign -> JARs
3. Choose json-simple-1.1.1.jar under `src` folder
4. Hit **OK**

### Terms and Licenses

> The terms and usage license of the `json-simple` library can be found
> at: https://github.com/fangyidong/json-simple/blob/master/LICENSE.txt <br>
> The terms and usage license of the `jcalendar` library can be found
> at: https://github.com/toedter/jcalendar/blob/master/JCalendar/jcalendar-license.txt <br>
> The terms and usage license of the `jfreechart` library can be found
> at: https://github.com/jfree/jfreechart/blob/master/licence-LGPL.txt

## Important Notes

- Our API uses list of symbols of the companies listed on the stockExchange which can be  
  founded [here.](https://www.nyse.com/listings_directory/stock)
- Our application can add stocks, and it can retrieve the details of them.
- Our Application will allow stock purchase on minimum of 1 shares and do not allow a client to  
  directly purchase a fractional amount of shares , that's the least requirement of the quantity
  of shares.
- Our application stock symbol is **case-sensitive**, i.e. it only takes in **uppercase letters**.
- Our Application will not allow to load portfolio with the same name.
- Our application saves the portfolio in the files in a `.json` format.
- To load a file, it has to be in a`.json` extension only.
- While accessing the portfolio value on a certain date, and if the date mentioned is a holiday,
  the  
  program will throw the holiday message.
- We have used JFree chart library to support our performance graph.
- To exit the Performance graph dialog box, click the `x`/close on the top window pane to close the
  graph pane.
- The commission fee is always optional, if a user doesn't enter the value, it is assumed as zero.
- The end date in recurring Dollar Cost strategy if not chosen is assumed as the strategy is ever
  lasting.
- The all connected UML diagram can be found
  here: [https://user-images.githubusercontent.com/47818179/204925599-9596ca45-ae14-462c-a5db-1a790e958793.png](https://user-images.githubusercontent.com/47818179/204925599-9596ca45-ae14-462c-a5db-1a790e958793.png)

## Usage

### a. For the GUI Interface

After running the `stock.jar` file successfully, a user has 10 different options which are
available and can choose one at a time.  
![menu-image](GUI.png)

The functionality of each option is as follows:

1.`Create a Portfolio`

- A user can create a flexible portfolio with appropriate portfolio name which can be modified once
  created.  
  ![flexiblePortfolio-image](images/CP.png)

2. `Buy Stock`

- A user can purchase a desired stock for the flexible portfolio.
  ![purchaseStock-image](images/BS.png)

3. `Sell Stock`

- A user can sell a desired stock for the flexible portfolio.
  ![addStock-image](images/SS.png)

4. `Get Value`

- A user can determine the total value of the portfolio on a given particular date.  
  ![totalValue-image](images/GV.png)

5. `Cost Basis`

- A user can determine the cost basis of the portfolio on a given particular date.  
  ![totalValue-image](images/CB.png)

6. `Get performance`

- A user can determine the performance of the portfolio from a given start date to end date.  
  ![totalValue-image](images/GP1.png)
  ![totalValue-image](GP2.png)

7. `Save portfolio`

- A user can save the given portfolio with a desired name and the file will be saved with  
  a `.json` extension.  
  ![save-image](images/SP.png)

8. `Load portfolio`

- A user can load the portfolio file of the `.json` extension and retrieve the details of the  
  same.  
  ![load-image](images/LP.png)

9. `Dollar Cost Averaging`

- A user can invest in the portfolio using the strategy of Dollar Cost Average which can be either
  once or a recurring one.

  ![ThreeStocks-image](images/DCA1.png)
  ![ThreeStocks-image](images/DCA2.png)

10. `Quit`

- A user can quit the application by clicking the `Quit` button.

### b. For the text based Interface

After running the `stock.jar` file successfully, a user has 12 different options which are
available  
and can choose one at a time.  
![menu-image](images/menuBar.png)

The functionality of each option is as follows:

1. `Create an Inflexible Portfolio`

- A user can create an inflexible portfolio with appropriate portfolio name which cannot be modified
  once created.  
  ![createPortfolio-image](images/inflexible.png)
- A user can add a desired stock in the portfolio  
  ![addStock-image](images/addStock.png)

2. `Create a flexible Portfolio`

- A user can create a flexible portfolio with appropriate portfolio name which can be modified once
  created.  
  ![flexiblePortfolio-image](images/flexible.png)

3. A user can purchase a desired stock for the flexible portfolio.
   ![purchaseStock-image](images/purchase.png)
4. A user can sell a desired stock for the flexible portfolio.
   ![addStock-image](images/addStock.png)
5. `Examine composition of an inflexible portfolio`

- A user can examine the inflexible portfolio and see the details of the same.  
  ![composition-image](images/examine.png)

6. `Examine composition of an flexible portfolio on a certain date`

- A user can examine the flexible portfolio on a certain date and see the details of the same.  
  ![composition-image](images/examineByDate.png)

7. `Determine the total value of a portfolio on a certain date.`

- A user can determine the total value of the portfolio on a given particular date.  
  ![totalValue-image](images/totalValue-2.png)

8. `Determine costs basis of a flexible portfolio`

- A user can determine the cost basis of the portfolio on a given particular date.  
  ![totalValue-image](images/costBasis.png)

9. `Get portfolio performance`

- A user can determine the performance of the portfolio from a given start date to end date.  
  ![totalValue-image](images/performance.png)

10. `Save a portfolio as a file`

- A user can save the given portfolio with a desired name and the file will be saved with  
  a `.json` extension.  
  ![save-image](images/save-2.png)

11. `Load a portfolio from a file`

- A user can load the portfolio file of the `.json` extension and retrieve the details of the  
  same.  
  ![load-image](images/load.png)

12. `Quit`

- A user can quit the application by entering `Q` or `q`.

### Detail Instruction to create Portfolio with 3 Different Stocks on a certain Date, total value on a certain date and cost basis.

    - Stock, Quantity, Date, commission fee
    1. AMZN, 40, 2022-04-04, 2 
    2. AAPL, 16, 2022-04-05, 3
    3. TSLA, 33, 2022-04-06, 1

![ThreeStocks-image](images/threeStock.png)
