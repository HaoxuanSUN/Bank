package frames.customer;

import components.swingComponents.*;
import components.swingComponents.Button;
import components.swingComponents.Frame;
import components.swingComponents.Label;
import components.swingComponents.Panel;
import data.Constants;
import frames.Login;
import model.users.Manager;
import model.users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CreateAccount implements ActionListener {
    Frame createAccount;
    Panel createAccountPanel=new Panel();
    User user;
    Button savingsAccount=new Button("Open a savings account", 250,30);
    Button checkingAccount=new Button("Open a checking account", 250,30);
    Button loanRequest=new Button("Request a loan", 250,30);
    Button securityAccount=new Button("Open a security account", 250,30);

    JDialog dialog;

    JTextField startingAmount=new JTextField(10);
    JTextField creditScore=new JTextField(10);

    Button savingsAccountAtDialog=new Button("Open savings", 250,30);
    Button checkingAccountAtDialog=new Button("Open checking", 250,30);
    Button loanRequestAtDialog=new Button("Request loan", 250,30);
    Button securityAccountAtDialog=new Button("Open security ", 250,30);
    BackButton backButton=new BackButton();
    public CreateAccount(User user){
        this.user=user;
        createAccount=new Frame();
        addBackButton();
        checkWhichAccount();
        createAccount.add(createAccountPanel);
        createAccount.setVisible(true);
    }

    void checkWhichAccount(){
        Boolean saving=false;
        Boolean checking=false;
        Boolean loan=false;
        Boolean security=false;
        if(user.getSaving()!=null)
            saving=true;
        if(user.getChecking()!=null)
            checking=true;
        if(user.getLoan()!=null)
            loan=true;
        if(user.getSecurities()!=null)
            security=true;

        savingsAccount.addActionListener(this);
        savingsAccount.textButton("blue");
        checkingAccount.addActionListener(this);
        checkingAccount.textButton("blue");
        loanRequest.addActionListener(this);
        loanRequest.textButton("blue");
        securityAccount.addActionListener(this);
        securityAccount.textButton("blue");

        Panel buttonsPanel=new Panel();

        if(!saving && !checking){
            buttonsPanel.setLayout(new GridLayout(2,1,0,20));
            buttonsPanel.add(savingsAccount);
            buttonsPanel.add(checkingAccount);
        }
        else if(!saving) {
            if(!loan){
                buttonsPanel.setLayout(new GridLayout(2, 1, 0, 20));
                buttonsPanel.add(savingsAccount);
                buttonsPanel.add(loanRequest);
            }
        }
        else if(!checking){
            if (!loan && !security) {
                if (Double.compare(user.getSaving().getBalance().getValue(), 5000) >= 0) {
                    buttonsPanel.setLayout(new GridLayout(3, 1, 0, 20));
                    buttonsPanel.add(checkingAccount);
                    buttonsPanel.add(loanRequest);
                    buttonsPanel.add(securityAccount);
                } else {
                    buttonsPanel.setLayout(new GridLayout(2, 1, 0, 20));
                    buttonsPanel.add(checkingAccount);
                    buttonsPanel.add(loanRequest);
                }
            }
            else if(!loan){
                buttonsPanel.setLayout(new GridLayout(2, 1, 0, 20));
                buttonsPanel.add(checkingAccount);
                buttonsPanel.add(loanRequest);
            }
            else if(!security){
                if (Double.compare(user.getSaving().getBalance().getValue(), 5000) >= 0) {
                    buttonsPanel.setLayout(new GridLayout(2, 1, 0, 20));
                    buttonsPanel.add(checkingAccount);
                    buttonsPanel.add(securityAccount);
                }
                else{
                    buttonsPanel.setLayout(new GridLayout(1, 1, 0, 20));
                    buttonsPanel.add(checkingAccount);
                }
            }
        }
        else{
            if (!loan && !security) {
                if (Double.compare(user.getSaving().getBalance().getValue(), 5000) >= 0) {
                    buttonsPanel.setLayout(new GridLayout(2, 1, 0, 20));
                    buttonsPanel.add(loanRequest);
                    buttonsPanel.add(securityAccount);
                } else {
                    buttonsPanel.setLayout(new GridLayout(1, 1, 0, 20));
                    buttonsPanel.add(loanRequest);
                }
            }
            else if(!loan){
                buttonsPanel.setLayout(new GridLayout(1, 1, 0, 20));
                buttonsPanel.add(loanRequest);
            }
            else if(!security){
                if (Double.compare(user.getSaving().getBalance().getValue(), 5000) >= 0) {
                    buttonsPanel.setLayout(new GridLayout(1, 1, 0, 20));
                    buttonsPanel.add(securityAccount);
                }
            }
        }
        createAccountPanel.add(buttonsPanel);
    }

    void addBackButton(){
        Panel backPanel=new Panel();
        backPanel.setLayout(new BorderLayout());
        backPanel.setPreferredSize(new Dimension(400,30));
        backButton.addActionListener(this);
        backPanel.add(backButton, BorderLayout.WEST);
        createAccountPanel.add(backPanel);
    }

    void openingSavings(){
        dialog=new JDialog(createAccount,"Opening Savings");
        dialog.setSize(450,200);
        dialog.getContentPane().setBackground(new Color(0xe0fbfc));

        Panel dialogPanel=new Panel();
        dialogPanel.setLayout(new GridLayout(4,1,20,0));

        Panel minimumAmountLable1=new Panel();
        Panel minimumAmountLable2=new Panel();
        minimumAmountLable1.add(new Label("Fee for opening a savings account is $10. "));
        minimumAmountLable2.add(new Label("And, the minimum for the first deposit is $100"));

        Panel amountPanel=new Panel();
        amountPanel.add(new Label("Amount to be added"));
        amountPanel.add(startingAmount);

        savingsAccountAtDialog.addActionListener(this);
        savingsAccountAtDialog.textButton("blue");

        dialogPanel.add(minimumAmountLable1);
        dialogPanel.add(minimumAmountLable2);
        dialogPanel.add(amountPanel);
        dialogPanel.add(savingsAccountAtDialog);

        dialog.add(dialogPanel);
        dialog.setVisible(true);
    }
    void openingChecking(){
        dialog=new JDialog(createAccount,"Opening Checking");
        dialog.setSize(450,200);
        dialog.getContentPane().setBackground(new Color(0xe0fbfc));

        Panel dialogPanel=new Panel();
        dialogPanel.setLayout(new GridLayout(4,1,20,0));

        Panel minimumAmountLable1=new Panel();
        Panel minimumAmountLable2=new Panel();
        minimumAmountLable1.add(new Label("Fee for opening a checking account is $10."));
        minimumAmountLable2.add(new Label("And, the minimum for the first deposit is $100"));

        Panel amountPanel=new Panel();
        amountPanel.add(new Label("Amount to be added"));
        amountPanel.add(startingAmount);

        checkingAccountAtDialog.addActionListener(this);
        checkingAccountAtDialog.textButton("blue");

        dialogPanel.add(minimumAmountLable1);
        dialogPanel.add(minimumAmountLable2);
        dialogPanel.add(amountPanel);
        dialogPanel.add(checkingAccountAtDialog);


        dialog.add(dialogPanel);
        dialog.setVisible(true);
    }
    void openingSecurity(){
        dialog=new JDialog(createAccount,"Opening Security");
        dialog.setSize(450,200);
        dialog.getContentPane().setBackground(new Color(0xe0fbfc));

        Panel dialogPanel=new Panel();
        dialogPanel.setLayout(new GridLayout(4,1,20,0));

        Panel minimumAmountLable1=new Panel();
        Panel minimumAmountLable2=new Panel();
        minimumAmountLable1.add(new Label("Fee for opening a checking account is $10."));
        minimumAmountLable2.add(new Label("And, the minimum for the first deposit is $1000"));
        Panel amountPanel=new Panel();
        amountPanel.add(new Label("Amount to be added"));
        amountPanel.add(startingAmount);

        securityAccountAtDialog.addActionListener(this);
        securityAccountAtDialog.textButton("blue");

        dialogPanel.add(minimumAmountLable1);
        dialogPanel.add(minimumAmountLable2);
        dialogPanel.add(amountPanel);
        dialogPanel.add(securityAccountAtDialog);

        dialog.add(dialogPanel);
        dialog.setVisible(true);
    }

    void requestingLoan(){
        dialog=new JDialog(createAccount,"Loan request");
        dialog.setSize(450,200);
        dialog.getContentPane().setBackground(new Color(0xe0fbfc));

        Panel dialogPanel=new Panel();
        dialogPanel.setLayout(new GridLayout(3,1,20,0));

        Panel loanLabel=new Panel();
        loanLabel.add(new Label("Enter the loan amount required."));
        Panel amountPanel=new Panel();
        amountPanel.add(new Label("Loan amount"));
        amountPanel.add(startingAmount);

        loanRequestAtDialog.addActionListener(this);
        loanRequestAtDialog.textButton("blue");

        dialogPanel.add(loanLabel);
        dialogPanel.add(amountPanel);
        dialogPanel.add(loanRequestAtDialog);

        dialog.add(dialogPanel);
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){
            createAccount.dispose();
            new CustomerDashboard(user,true);
        }
        else if(e.getSource()==savingsAccount)
            openingSavings();
        else if(e.getSource()==checkingAccount)
            openingChecking();
        else if(e.getSource()==securityAccount)
            openingSecurity();
        else if(e.getSource()==loanRequest)
            requestingLoan();
        else if (e.getSource()==checkingAccountAtDialog) {
            try{
                Double.parseDouble(startingAmount.getText());
                service.CreateAccount createAccount1 =
                        new service.CreateAccount(user,
                                new Manager(
                                        Constants.managerUserName,
                                        Constants.managerFirstName,
                                        Constants.managerLastName,
                                        Constants.managerPassword,
                                        Constants.managerDateOfBirth
                                ));
                if(createAccount1.createCheckingA(Double.parseDouble(startingAmount.getText()))){
                    JOptionPane.showMessageDialog(createAccount, "successfully created a checking account");
                    dialog.dispose();
                    createAccount.dispose();
                    new CustomerDashboard(user,true);
                }
                else
                    JOptionPane.showMessageDialog(createAccount, "enter an amount greater to $100");
            }
            catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(createAccount, "enter a valid amount");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource()==savingsAccountAtDialog) {
            try {
                Double.parseDouble(startingAmount.getText());
                service.CreateAccount createAccount1 =
                        new service.CreateAccount(user,
                                new Manager(
                                        Constants.managerUserName,
                                        Constants.managerFirstName,
                                        Constants.managerLastName,
                                        Constants.managerPassword,
                                        Constants.managerDateOfBirth
                                ));
                if(createAccount1.createSavingA(Double.parseDouble(startingAmount.getText()))){
                    JOptionPane.showMessageDialog(createAccount, "successfully created a savings account");
                    dialog.dispose();
                    createAccount.dispose();
                    new CustomerDashboard(user,true);
                }
                else
                    JOptionPane.showMessageDialog(createAccount, "enter an amount greater to $100");
            }
            catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(createAccount, "enter a valid amount");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource()==securityAccountAtDialog) {
            try {
                Double.parseDouble(startingAmount.getText());
                service.CreateAccount createAccount1 =
                        new service.CreateAccount(user,
                                new Manager(
                                        Constants.managerUserName,
                                        Constants.managerFirstName,
                                        Constants.managerLastName,
                                        Constants.managerPassword,
                                        Constants.managerDateOfBirth
                                ));
                if(createAccount1.createSecuritiesA(Double.parseDouble(startingAmount.getText()))){
                    JOptionPane.showMessageDialog(createAccount, "successfully created a security account");
                    dialog.dispose();
                    createAccount.dispose();
                    new CustomerDashboard(user,true);
                }
                else
                    JOptionPane.showMessageDialog(createAccount, "enter an amount greater to $1000");
            }
            catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(createAccount, "enter a valid amount");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(createAccount, "try again");
                throw new RuntimeException(ex);
            }
        }
        else if (e.getSource()==loanRequestAtDialog) {
            try {
                Double.parseDouble(startingAmount.getText());
                service.CreateAccount createAccount1 =
                        new service.CreateAccount(user,
                                new Manager(
                                        Constants.managerUserName,
                                        Constants.managerFirstName,
                                        Constants.managerLastName,
                                        Constants.managerPassword,
                                        Constants.managerDateOfBirth
                                ));
                if(createAccount1.createLoanA(Double.parseDouble(startingAmount.getText()))){
                    JOptionPane.showMessageDialog(createAccount, "successfully got a loan");
                    dialog.dispose();
                    createAccount.dispose();
                    new CustomerDashboard(user,true);
                }
                else {
                    JOptionPane.showMessageDialog(createAccount, "You are not qualified for a loan because you have under $1000 in savings");
                }
            }
            catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(createAccount, "enter a valid amount");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(createAccount, "entered amount not valid for a loan");
            }

        }
        else if(e.getSource()==securityAccountAtDialog){

        }
    }
}
