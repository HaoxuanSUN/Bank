
## How to compile and run
---------------------------------------------------------------------------
1. Navigate to the directory "hw5" after unzipping the files
2. Run the following instructions:

cd src
javac *.java
java Main

# Data
### AccountDao.java: Interact with data files when account/loan/customers’ stock activities occur.
### TransactionDao.java: Interact with transaction record file when a new transaction happened.
### UserDao.java: Interact with user record file when a new user signs up or is used when looking up a user based on username or ID.
### User.txt: Stores user record. [ID, Username, FName, LName, Password, Birthdate, Saving, Checking, Loan, Securities, CardNumber]
### ManagerBalance.txt: Stores manager(bank)’s balance.
### Stock.txt: Stores stock record. [stock name, price(USD), company name]
### Transaction.txt: Stores transaction record. [ID, UID, FromAccID, ToAccID, Date, Currency, Amount]
### CheckingAccount.txt: Stores checking account record. [ID, UID, Currency, Balance, Data]
### SavingAccount.txt: Stores saving account record. [ID, UID, Currency, Balance, Date]
### LoanAccount.txt: Stores load account record. [ID, UID, Currency, Balance, Data, PaidLoan, UnpaidLoan]
### SecuritiesAccount.txt: Stores securities account record. [ID, UID, Currency, Balance, Date]
### StockList: stores users’ stock activity records. Each user owns a txt file named after userID.

# Model
### ATM.java: ATM object.
### Bank.java: Basic functions for the bank.
## Accounts
### AccountType.java: 4 types of accounts.
### Account.java: Parent class for other Account type classes.
### CheckingAccount.java: Extend from account.java for checking account.
### LoanAccount.java: Extend from account.java for a loan account.
### SavingAccount.java: Extend from account.java for saving account.
### SecuritiesAccount.java: Extend from account.java for securities account.
### AccountBehavior
### IntrestBehavior.java: An interface for interest behavior.
### CheckingAccountIntrestBehavior.java: Implement interface intrestBehavior for checking account.       
### LoanAccountIntrestBehavior.java: Implement interface intrestBehavior for loan account.             
### SavingAccountIntrestBehavior.java: Implement interface intrestBehavior for saving account.
### SecuritiesAccountIntrestBehavior.java: Implement interface intrestBehavior for securities account.
## Currency
### CurrencyType.java: Three types of currency.
### Money.java: Implements comparable<Money>  for balance comparison and calculation.
### Currency.java: Parent class for other currency type classes.
### USD/CNY/INR.java: Extends currency class for USD/CNY/INR.
	
## Stock
### Stock.java: Defines stock information.
### StockList.java: Defines the list for a customer’s own stock.
## Transactions
### TransactionType.java: 6 different transaction types.
### Transaction.java: Defines transaction objects. Parent class of 6 transaction behavior class.	
### Transfer.java: Extends from Transaction class for transfer transaction.
### Deposit.java: Extends from Transaction class for deposit transaction.
### Withdraw.java: Extends from Transaction class for withdrawing transaction.
### PayLoan.java: Extends from Transaction class for loan paying transaction.
### StockBuy.java: Extends from Transaction class for stock buying transaction.
### StockSell.java: Extends from Transaction class for stock selling transaction.
## Users
### User.java: Defines all user objects. Parent class of customer and manager class.
### Customer.java: Extends from User.java.  Contains methods that a customer can do.
### Manager.java: Extends from User.java. Contains methods that a manager can do. e.g. modify the stock list, update stock price, and get customers who have the most unpaid loans.

# Service
### CreateAccount.java: Create saving/checking/securities account.
### MakeTransactions: A user makes a transaction.
### Register.java: User register behavior.
### SingIn.java: User sign-in class.

# Util
### Checker.java: Some checker methods.
### Constants.java: Some constant helper values and methods.
### Factory.java: Follow the factory pattern. 
### ID.java: methods related to ID issues.
### Reader.java: Methods related to reading files and returning contents from files.
### Writer.java: Methods related to writing or modify on files.
	
# swingComponents:
### BackButton.java： the commonly used back button display component
### Button.java: For consistent size,color and shape of JButtons
### Frame.java: for common size, color, functionality of JFrames
### Label.java: for common styling of JLabel
### LogoutButton.java: the commonly used logout button display component
### Panel.java: for common styling of JPanel

# Frames:
## Customer:
## Account:
### AccountFrame.java: Frame used to display all 3 account and a loan request
### CreateAccount.java: To create a new account
### CustomerDashboard.java:  shows all of the users accounts
### CustomerRegistration.java:  registers a new customer to the bank
### PersonalInfo.java:  displays the personal information of the user
### StartFrame.java:  To select between a existing user and a new user
## Manager
### CustomerLookUp.java:  To lookup specific user
### ManagerDashboard.java:  can update socks and can view transactions
### Login.java:  for logging into the user/manager’s account
### LogoFrame.java:  The opening frame of the bank
### UserType.java: (To select between user and manager
