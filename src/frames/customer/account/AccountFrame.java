package frames.customer.account;

import components.swingComponents.*;
import components.swingComponents.Button;
import components.swingComponents.Frame;
import components.swingComponents.Label;
import components.swingComponents.Panel;
import data.Constants;
import frames.customer.CustomerDashboard;
import model.accounts.Account;
import model.accounts.SavingsAccount;
import model.users.Manager;
import model.users.User;
import service.MakeTransactions;
import util.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AccountFrame implements ActionListener, ItemListener {
    Frame accountFrame;
    Panel panel=new Panel();
    Button viewAllTransactions=new Button("View all Transactions",100,20);
    String accountName;
    JRadioButton deposit=new JRadioButton("deposit");
    JRadioButton withdraw=new JRadioButton("withdraw");
    JRadioButton accountToAccount=new JRadioButton("account To account Transaction");
    JRadioButton buy=new JRadioButton("Buy more stocks");
    JRadioButton sell=new JRadioButton("Sell stocks");
    JRadioButton loanPayment=new JRadioButton("Pay Loan");
    ButtonGroup typeOfTransaction=new ButtonGroup();
    User user;
    JDialog dialog;
    Button cancel=new Button("Cancel",100,20);
    Button approve=new Button("Approve",100,20);
    String addSub;
    List<String> stock=new ArrayList<>();
    JComboBox stocks;

    JTextField amountTextField=new JTextField(5);
    JTextField noOfStocksTextField=new JTextField(5);
    BackButton backButton=new BackButton();
    List<String> toAccounts=new ArrayList<>();
    JComboBox accounts;

    Account from;
    Account to=null;

    List<String> file=new ArrayList<>();

    int selectedListTosell;
    public AccountFrame(String accountName,User user){
        this.user=user;
        this.accountName=accountName;
        accountFrame=new Frame();
        panel.setBounds(0,20,380,500);
        addBackButton();
        addInfo();
        addMaketransaction();
        addTransactions();
        accountFrame.add(panel);
        accountFrame.setVisible(true);

    }

    void addBackButton(){
        Panel backPanel=new Panel();
        backPanel.setLayout(new BorderLayout());
        backPanel.setPreferredSize(new Dimension(400,30));
        backButton.addActionListener(this);
        backPanel.add(backButton, BorderLayout.WEST);
        panel.add(backPanel);
    }

    void addInfo(){
        Panel accountInfo=new Panel();
        accountInfo.setLayout(new GridLayout(2,2,20,0));
        accountInfo.add(new Label(accountName));
        if(accountName=="SAVING ACC"){
            from=user.getSaving();
            accountInfo.add(new Label("created at : "+
                    new SimpleDateFormat("MM/dd/yyyy").format(user.getSaving().getDate())));
            accountInfo.add(new Label("$ "+user.getSaving().getBalance().getValue()));
        }
        else if (accountName=="CHECKING ACC"){
            from=user.getChecking();
            accountInfo.add(new Label("created at : "+
                    new SimpleDateFormat("MM/dd/yyyy").format(user.getChecking().getDate())));
            accountInfo.add(new Label("$ "+user.getChecking().getBalance().getValue()));
        }
        else if(accountName=="LOAN ACC"){
            from=user.getLoan();
            accountInfo.add(new Label("created at : "+
                    new SimpleDateFormat("MM/dd/yyyy").format(user.getLoan().getDate())));
            accountInfo.add(new Label("$ "+Double.toString(user.getLoan().getUnpaidLoan().getValue())));

        }
        else{
            from=user.getSecurities();
            accountInfo.add(new Label("created at : "+
                    new SimpleDateFormat("MM/dd/yyyy").format(user.getSecurities().getDate())));
            accountInfo.add(new Label("$ "+user.getSecurities().getBalance().getValue()));
        }
        accountInfo.add(new Panel());
        panel.add(accountInfo);
        Panel gap=new Panel();
        gap.setPreferredSize( new Dimension( 420, 20 ) );

        Panel gap1=new Panel();
        gap1.setPreferredSize( new Dimension( 420, 20 ) );
        panel.add(gap);

        Panel stocksInfo=new Panel();
        if(accountName=="SECURITY ACC"){
            try{
                file = Reader.getLine("src/data/StockList/"+user.getId().toString()+".txt");
//                System.out.println();
                stocksInfo.setLayout(new GridLayout(file.size()-1,2,20,0));
                for (int i=0;i<file.size();i++){
                    if(i!=0){
                        stocksInfo.add(new Label(file.get(i).split(" ")[2]));

                        stocksInfo.add(new Label(file.get(i).split(" ")[3]));

                    }
                }
                if(file.size()>1){
                    stocksInfo.setBorder(BorderFactory.createTitledBorder("Stocks"));
                    panel.add(stocksInfo);
                    panel.add(gap1);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    void addMaketransaction(){

        typeOfTransaction.add(deposit);
        typeOfTransaction.add(withdraw);
        typeOfTransaction.add(accountToAccount);
        typeOfTransaction.add(buy);
        typeOfTransaction.add(sell);

        deposit.addActionListener(this);
        withdraw.addActionListener(this);
        accountToAccount.addActionListener(this);
        loanPayment.addActionListener(this);
        buy.addActionListener(this);
        sell.addActionListener(this);

        Panel transactionPanel=new Panel();
        transactionPanel.setBorder(BorderFactory.createTitledBorder("Make a transactions"));

        if(accountName=="CHECKING ACC" || accountName=="SAVING ACC"){
            transactionPanel.add(deposit);
            transactionPanel.add(withdraw);
            transactionPanel.add(accountToAccount);
        }
        else if(accountName=="SECURITY ACC"){
            transactionPanel.add(buy);
            transactionPanel.add(sell);
        }
        else{
            transactionPanel.add(loanPayment);
        }
        panel.add(transactionPanel);
        Panel gap=new Panel();
        gap.setPreferredSize( new Dimension( 420, 20 ) );
        panel.add(gap);
    }

    void addTransactions(){
        if(accountName!="LOAN ACC") {
            viewAllTransactions.addActionListener(this);
            List<Panel> transactionsPanel = new ArrayList<Panel>();
            Panel transactionPanel = new Panel();
            transactionPanel.setLayout(new GridLayout(6, 1, 0, 10));
            transactionPanel.setBorder(BorderFactory.createTitledBorder("Transactions"));

            for (int i = 0; i < from.print(null).size(); i++) {
                //System.out.println(typeof(from.print(null).get(i)));
                Panel temp = new Panel();
                temp.setLayout(new FlowLayout());
                temp.add(new Label(from.print(null).get(i)));
                transactionPanel.add(temp);
            }
            panel.add(transactionPanel);

            //transactionPanel.add(viewAllTransactions);
            panel.add(transactionPanel);
        }
    }

    void openDialog(String dialogTitle){
        dialog=new JDialog(accountFrame,dialogTitle);
        dialog.getContentPane().setBackground(new Color(0xe0fbfc));
        dialog.setSize(600,120);
        cancel.addActionListener(this);
        approve.addActionListener(this);

        Panel accNo=new Panel();
        accNo.add(new Label("Acc Type: "));
        accNo.add(new Label(accountName.split(" ")[0]));

        Panel amount=new Panel();
        amount.add(new Label("Amount:"));
        amount.add(amountTextField);

        Panel noOfStocksPanel=new Panel();
        noOfStocksPanel.add(new Label("No Of socks:"));
        noOfStocksPanel.add(noOfStocksTextField);

        Panel transactionDialog=new Panel();
        if(dialogTitle=="Deposit"){
            transactionDialog.setLayout(new GridLayout(3,2,0,0));

            String[] currency={"USD","CNY","INR"};
            JComboBox currencies=new JComboBox(currency);
            Panel currenciesPanel=new Panel();
            currenciesPanel.add(new Label("Currency of deposit"));
            currenciesPanel.add(currencies);

            transactionDialog.add(accNo);
            transactionDialog.add(currenciesPanel);
            transactionDialog.add(amount);
            transactionDialog.add(new Panel());
        }
        else if(dialogTitle=="Withdraw"){
            transactionDialog.setLayout(new GridLayout(2,2,0,0));

            transactionDialog.add(accNo);
            transactionDialog.add(amount);
        }
        else if(dialogTitle=="Account Transfer"){
            transactionDialog.setLayout(new GridLayout(3,2,0,0));
            if(user.hasSaving()&&
                    !user.getSaving().getId().toString().equals(from.getId().toString()))
                toAccounts.add("SAVING");
            if(user.hasChecking()&&
                    !user.getChecking().getId().toString().equals(from.getId().toString()))
                toAccounts.add("CHECKING");
            if(user.hasSecurities()&&
                    !user.getSecurities().getId().toString().equals(from.getId().toString()))
                toAccounts.add("SECURITY");
            if(toAccounts.isEmpty()){
                transactionDialog.add(new Label("You have no accounts to transfer to!!"));
                transactionDialog.add(new Panel());
                transactionDialog.add(new Panel());
                transactionDialog.add(new Panel());
            }
            else {
                String[] arr = new String[toAccounts.size()];
                //Converting List to Array
                toAccounts.toArray(arr);

                accounts=new JComboBox(arr);
                accounts.addItemListener(this);

                Panel toAccountsPanel = new Panel();
                toAccountsPanel.add(new Label("Account To transfer to"));
                toAccountsPanel.add(accounts);

                transactionDialog.add(accNo);
                transactionDialog.add(toAccountsPanel);
                transactionDialog.add(amount);
                transactionDialog.add(new Panel());
            }
        }
        else if(dialogTitle=="Buy Stocks"){
            transactionDialog.setLayout(new GridLayout(3,2,0,0));

            for(int i=0;i<Reader.L2.size();i++){
                stock.add(Reader.L2.get(i).split(" ")[0]+" - "+Reader.L2.get(i).split(" ")[1]);
            }
            stocks=new JComboBox(stock.toArray(new String[stock.size()]));
            Panel stocksPanel=new Panel();
            stocksPanel.add(new Label("Stocks available"));
            stocksPanel.add(stocks);

            transactionDialog.add(accNo);
            transactionDialog.add(stocksPanel);
            transactionDialog.add(noOfStocksPanel);
            transactionDialog.add(new Panel());
        }
        else if(dialogTitle=="Sell Stocks"){
            transactionDialog.setLayout(new GridLayout(3,2,0,0));

            List<String> stock=new ArrayList<>();
            for(int i=0;i< file.size();i++){
                if(i!=0)
                    stock.add(file.get(i).split(" ")[2]+" "+file.get(i).split(" ")[3]);
            }

            stocks=new JComboBox();
            Panel stocksPanel=new Panel();
            stocksPanel.add(new Label("Stocks available to sell"));
            stocksPanel.add(stocks);

            transactionDialog.add(accNo);
            transactionDialog.add(stocksPanel);
            transactionDialog.add(noOfStocksPanel);
            transactionDialog.add(new Panel());
        }
        else if(dialogTitle=="Loan installment"){
            transactionDialog.setLayout(new GridLayout(2,2,0,0));
            transactionDialog.add(amount);
            transactionDialog.add(new Panel());
        }
        addSub=dialogTitle;
        cancel.textButton("red");
        approve.textButton("blue");
        transactionDialog.add(cancel);
        transactionDialog.add(approve);
        dialog.add(transactionDialog);
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==deposit){
            openDialog("Deposit");
        }
        else if(e.getSource()==withdraw){
            openDialog("Withdraw");
        }
        else if(e.getSource()==accountToAccount){
            openDialog("Account Transfer");
        }
        else if(e.getSource()==buy){
            openDialog("Buy Stocks");
        }
        else if(e.getSource()==sell){
            openDialog("Sell Stocks");
        }
        else if(e.getSource()==loanPayment){
            openDialog("Loan installment");
        }
        else if(e.getSource()==viewAllTransactions){
        }
        else if(e.getSource()==cancel){
            dialog.dispose();
        }
        else if(e.getSource()==backButton){
            accountFrame.dispose();
            new CustomerDashboard(user,true);
        }
        else if(e.getSource()==approve){
            MakeTransactions makeTransactions
                    =new MakeTransactions(user, new Manager( Constants.managerUserName,
                    Constants.managerFirstName,
                    Constants.managerLastName,
                    Constants.managerPassword,
                    Constants.managerDateOfBirth));
            if(accountName=="SECURITY ACC"){
                if(addSub=="Sell Stocks"){
                    try {
                        Integer.parseInt(noOfStocksTextField.getText());
                        JOptionPane.showConfirmDialog(accountFrame,
                                "Your Total amount is " +
                                        Double.parseDouble(stocks.getSelectedItem().toString().split(" - ")[1]) * Double.parseDouble(noOfStocksTextField.getText()));
                        if (Double.compare(Double.parseDouble(stocks.getSelectedItem().toString().split(" - ")[1])
                                        * Double.parseDouble(noOfStocksTextField.getText()),
                                user.getSecurities().getBalance().getValue()) <= 0) {
                            user.getSecurities().sellStock(user.getId().toString(), stocks.getSelectedItem().toString().split(" - ")[0], Integer.parseInt(noOfStocksTextField.getText()));
                            JOptionPane.showMessageDialog(accountFrame, "Transaction successfully");
                            dialog.dispose();
                            accountFrame.dispose();
                            new AccountFrame("SECURITY ACC", user);
                        } else
                            JOptionPane.showMessageDialog(accountFrame, "Insufficient funds");

                    } catch (NumberFormatException error) {
                        JOptionPane.showMessageDialog(accountFrame, "enter a valid no of stocks");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(accountFrame, "Unsuccessfully try again");
                        throw new RuntimeException(ex);
                    }
                }
                else {
                    lable:
                    try {
                        Integer.parseInt(noOfStocksTextField.getText());
                        JOptionPane.showConfirmDialog(accountFrame,
                                "Your Total amount is " +
                                        Double.parseDouble(stocks.getSelectedItem().toString().split(" - ")[1]) * Double.parseDouble(noOfStocksTextField.getText()));
                        if (Double.compare(Double.parseDouble(stocks.getSelectedItem().toString().split(" - ")[1])
                                        * Double.parseDouble(noOfStocksTextField.getText()),
                                user.getSecurities().getBalance().getValue()) <= 0) {
                            user.getSecurities().buyStock(user.getId(), stocks.getSelectedItem().toString().split(" - ")[0], Integer.parseInt(noOfStocksTextField.getText()));
                            JOptionPane.showMessageDialog(accountFrame, "Transaction successfully");
                            dialog.dispose();
                            accountFrame.dispose();
                            new AccountFrame("SECURITY ACC", user);
                        } else
                            JOptionPane.showMessageDialog(accountFrame, "Insufficient funds");

                    } catch (NumberFormatException error) {
                        JOptionPane.showMessageDialog(accountFrame, "enter a valid no of stocks");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(accountFrame, "Unsuccessfully try again");
                        throw new RuntimeException(ex);
                    }
                }
            }
            else if(accountName=="SAVING ACC"){
                if(addSub=="Deposit"){
                    try {
                        if(Double.parseDouble(amountTextField.getText())<0)
                            throw new NumberFormatException();

                        makeTransactions.deposit(user.getSaving(),Double.parseDouble(amountTextField.getText()));
                        JOptionPane.showMessageDialog(accountFrame,"Transaction successfully");
                        dialog.dispose();
                        accountFrame.dispose();
                        new AccountFrame("SAVING ACC",user);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(accountFrame,"try again");
                        throw new RuntimeException(ex);
                    }
                    catch (NumberFormatException error) {
                        JOptionPane.showMessageDialog(accountFrame,"enter a valid amount");
                    }
                }
                else if (addSub=="Withdraw"){
                    label:try {
                        if(Double.compare(Double.parseDouble(amountTextField.getText()),user.getSaving().getBalance().getValue())>0){
                            JOptionPane.showMessageDialog(accountFrame,"Insufficient funds");
                            break label;
                        }
                        if(Double.parseDouble(amountTextField.getText())<0)
                            throw new NumberFormatException();

                        makeTransactions.withdraw(user.getSaving(),Double.parseDouble(amountTextField.getText()));
                        JOptionPane.showMessageDialog(accountFrame,"Transaction successfully");
                        dialog.dispose();
                        accountFrame.dispose();
                        new AccountFrame("SAVING ACC",user);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(accountFrame,"try again");
                        throw new RuntimeException(ex);
                    }
                    catch (NumberFormatException error) {
                        JOptionPane.showMessageDialog(accountFrame,"enter a valid amount");
                    }
                }
                else{
                    if(toAccounts.isEmpty()){
                        JOptionPane.showMessageDialog(accountFrame,"Invalid transaction!");
                        dialog.dispose();
                    }
                    else{
                        label:try {
                            if(Double.compare(Double.parseDouble(amountTextField.getText()),user.getSaving().getBalance().getValue())>0){
                                JOptionPane.showMessageDialog(accountFrame,"Insufficient funds");
                                break label;
                            }
                            if(Double.parseDouble(amountTextField.getText())<0)
                                throw new NumberFormatException();

                            if(accounts.getSelectedItem().equals("CHECKING"))
                                makeTransactions.transfer(user.getSaving(),user.getChecking(),Double.parseDouble(amountTextField.getText()));
                            else
                                makeTransactions.transfer(user.getSaving(),user.getSecurities(),Double.parseDouble(amountTextField.getText()));
                            JOptionPane.showMessageDialog(accountFrame,"Transaction successfully");
                            dialog.dispose();
                            accountFrame.dispose();
                            new AccountFrame("SAVING ACC",user);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(accountFrame,"try again");
                            throw new RuntimeException(ex);
                        }
                        catch (NumberFormatException error) {
                            JOptionPane.showMessageDialog(accountFrame,"enter a valid amount");
                        }
                    }
                }
            }
            else if(accountName=="CHECKING ACC"){
                if(addSub=="Deposit"){
                    try {
                        if(Double.parseDouble(amountTextField.getText())<0)
                            throw new NumberFormatException();

                        makeTransactions.deposit(user.getChecking(),Double.parseDouble(amountTextField.getText()));
                        JOptionPane.showMessageDialog(accountFrame,"Transaction successfully");
                        dialog.dispose();
                        accountFrame.dispose();
                        new AccountFrame("SAVING ACC",user);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(accountFrame,"try again");
                        throw new RuntimeException(ex);
                    }
                    catch (NumberFormatException error) {
                        JOptionPane.showMessageDialog(accountFrame,"enter a valid amount");
                    }
                }
                else if (addSub=="Withdraw"){

                    label:try {
                        if(Double.compare(Double.parseDouble(amountTextField.getText()),user.getChecking().getBalance().getValue())>0){
                            JOptionPane.showMessageDialog(accountFrame,"Insufficient funds");
                            break label;
                        }
                        if(Double.parseDouble(amountTextField.getText())<0)
                            throw new NumberFormatException();
                        makeTransactions.withdraw(user.getChecking(),Double.parseDouble(amountTextField.getText()));
                        JOptionPane.showMessageDialog(accountFrame,"Transaction successfully");
                        dialog.dispose();
                        accountFrame.dispose();
                        new AccountFrame("SAVING ACC",user);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(accountFrame,"try again");
                        throw new RuntimeException(ex);
                    }
                    catch (NumberFormatException error) {
                        JOptionPane.showMessageDialog(accountFrame,"enter a valid amount");
                    }
                }
                else{
                    if(toAccounts.isEmpty()){
                        JOptionPane.showMessageDialog(accountFrame,"Invalid transaction!");
                        dialog.dispose();
                    }
                    else{
                        label:try {
                            if(Double.compare(Double.parseDouble(amountTextField.getText()),user.getChecking().getBalance().getValue())>0){
                                JOptionPane.showMessageDialog(accountFrame,"Insufficient funds");
                                break label;
                            }
                            if(Double.parseDouble(amountTextField.getText())<0)
                                throw new NumberFormatException();

                            if(accounts.getSelectedItem().equals("SAVING"))
                                makeTransactions.transfer(user.getChecking(),user.getSaving(),Double.parseDouble(amountTextField.getText()));
                            else
                                makeTransactions.transfer(user.getChecking(),user.getSecurities(),Double.parseDouble(amountTextField.getText()));
                            JOptionPane.showMessageDialog(accountFrame,"Transaction successfully");
                            dialog.dispose();
                            accountFrame.dispose();
                            new AccountFrame("SAVING ACC",user);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(accountFrame,"try again");
                            throw new RuntimeException(ex);
                        }
                        catch (NumberFormatException error) {
                            JOptionPane.showMessageDialog(accountFrame,"enter a valid amount");
                        }
                    }
                }
            }
            else if(accountName=="LOAN ACC"){
                label:try {
                    if(Double.parseDouble(amountTextField.getText())<0) {
                        JOptionPane.showMessageDialog(accountFrame,"Enter a valid amount");
                        break label;
                    }
                    if(Double.compare(Double.parseDouble(amountTextField.getText())
                            ,user.getSaving().getUnpaidLoan().getValue())>0){
                        JOptionPane.showMessageDialog(accountFrame,"Amount you entered is more than your remaining loan");
                        break label;
                    }

                    makeTransactions.payLoan(user.getLoan(),Double.parseDouble(amountTextField.getText()));
                    JOptionPane.showMessageDialog(accountFrame,"Installment paid");
                    dialog.dispose();
                    accountFrame.dispose();
                    new AccountFrame("LOAN ACC",user);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(accountFrame,"try again");
                    throw new RuntimeException(ex);
                }
                catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(accountFrame,"enter a valid amount");
                }
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED){
            //System.out.println(e.getItem().toString());
            //to=toAccounts.get(0);
        }
    }
}
