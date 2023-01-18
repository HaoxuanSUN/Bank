package frames.customer;

import components.swingComponents.*;
import components.swingComponents.Button;
import components.swingComponents.Frame;
import components.swingComponents.Label;
import components.swingComponents.Panel;
import frames.UserType;
import frames.customer.account.AccountFrame;
import model.users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class CustomerDashboard implements ActionListener {
    Frame dashboard;
    Panel dashboardPanel=new Panel();
    Panel personalInfoPanel=new Panel();
    Panel accountsInfoPanel=new Panel();
    Panel transactionPanel=new Panel();

    Panel gapPanel=new Panel();
    Label personalInfoLabel;

    Button personalInfoButton=new Button("View Profile", 150,20);
    Button createAccount;
    Button viewAllTransactions=new Button("View all Transactions",100,20);

    List<Button> acccountButtons=new ArrayList<Button>();

    LogoutButton logoutButton=new LogoutButton();

    User user;

    Button logoutEntered;

    JDialog dialog;

    Button savingsAccountButton=new Button("View",100,30);
    Button checkingAccountButton=new Button("View",100,30);
    Button loanAccountButton=new Button("View",100,30);
    Button securitiesAccountButton=new Button("View",100,30);

    Boolean view=false;

    public CustomerDashboard(User user,Boolean view) {
        this.view=view;
        this.user=user;
        dashboard=new Frame();
        if(!view){
            dashboard.setLocationRelativeTo(null);
            dashboard.setSize(420,300);
            dashboard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        personalInfoLabel=new Label("Hi, "+user.getFirstName()+" "+user.getLastName());
        gapPanel.setPreferredSize( new Dimension( 380, 20 ) );
        Panel gap=new Panel();
        gap.setPreferredSize( new Dimension( 380, 20 ) );
        dashboardPanel.setBounds(20,10,380,820);

        personalInfoPanel.setLayout(new GridLayout(1,2,0,0));
        personalInfoPanel.add(personalInfoLabel);
        personalInfoButton.textButton("blue");
        personalInfoButton.addActionListener(this);
        if(view) personalInfoPanel.add(personalInfoButton);

        addingAccounts();
        //addTransactions();

        if(view) logoutOption();

        dashboardPanel.add(personalInfoPanel);
        dashboardPanel.add(gap);
        dashboardPanel.add(accountsInfoPanel);
        dashboardPanel.add(gapPanel);
        dashboardPanel.add(transactionPanel);

        dashboard.setLayout(null);
        dashboard.add(dashboardPanel);
        dashboard.setVisible(true);
    }

    void logoutOption(){
        Panel logoutPanel=new Panel();
        logoutPanel.setLayout(new BorderLayout());
        logoutPanel.setPreferredSize(new Dimension(400,30));
        logoutButton.addActionListener(this);
        logoutPanel.add(logoutButton, BorderLayout.EAST);
        dashboardPanel.add(logoutPanel);
    }

    void addingAccounts(){
        Boolean saving=user.hasSaving();
        Boolean checking=user.hasChecking();
        Boolean loan=user.hasLoan();
        Boolean security=user.hasSecurities();

        BitSet bs = new BitSet(4);
        if(saving)  bs.set(0);
        if(checking) bs.set(1);
        if(loan) bs.set(2);
        if(security) bs.set(3);
        List<Panel> accountsPanel=new ArrayList<Panel>();

        if(bs.cardinality()==4) {
            if(view) accountsInfoPanel.setLayout(new GridLayout(4, 1, 0, 0));
            else accountsInfoPanel.setLayout(new GridLayout(4, 1, 0, 10));
        }
        else
            accountsInfoPanel.setLayout(new GridLayout(bs.cardinality()+1,1,0,0));

        if(saving){
            Panel savingAccountPanel=new Panel();
            savingAccountPanel.setLayout(new GridLayout(2,2,20,0));
            savingsAccountButton.addActionListener(this);
            savingsAccountButton.textButton("blue");
            savingAccountPanel.add(new Label("SAVINGS"));
            if(view) savingAccountPanel.add(savingsAccountButton);
            else savingAccountPanel.add(new Panel());
            savingAccountPanel.add(new Label("$ "+Double.toString(user.getSaving().getBalance().getValue())));
            accountsInfoPanel.add(savingAccountPanel);
        }
        if(checking){
            Panel checkingAccountPanel=new Panel();
            checkingAccountPanel.setLayout(new GridLayout(2,2,20,0));
            checkingAccountButton.addActionListener(this);
            checkingAccountButton.textButton("blue");
            checkingAccountPanel.add(new Label("CHECKING"));
            if(view) checkingAccountPanel.add(checkingAccountButton);
            else checkingAccountPanel.add(new Panel());
            checkingAccountPanel.add(new Label("$ "+Double.toString(user.getChecking().getBalance().getValue())));
            accountsInfoPanel.add(checkingAccountPanel);
        }
        if(loan){
            Panel loanAccountPanel=new Panel();
            loanAccountPanel.setLayout(new GridLayout(2,2,20,0));
            loanAccountButton.addActionListener(this);
            loanAccountButton.textButton("blue");
            loanAccountPanel.add(new Label("LOAN"));
            if(view) loanAccountPanel.add(loanAccountButton);
            else loanAccountPanel.add(new Panel());
            loanAccountPanel.add(new Label("$ "+Double.toString(user.getLoan().getUnpaidLoan().getValue())));
            accountsInfoPanel.add(loanAccountPanel);
        }
        if(security){
            Panel securityAccountPanel=new Panel();
            securityAccountPanel.setLayout(new GridLayout(2,2,20,0));
            securitiesAccountButton.addActionListener(this);
            securitiesAccountButton.textButton("blue");
            securityAccountPanel.add(new Label("SECURITY"));
            if (view) securityAccountPanel.add(securitiesAccountButton);
            else securityAccountPanel.add(new Panel());
            securityAccountPanel.add(new Label("$ "+Double.toString(user.getSecurities().getBalance().getValue())));
            accountsInfoPanel.add(securityAccountPanel);
        }

        accountsInfoPanel.setBorder(BorderFactory.createTitledBorder("Accounts"));
        if(!loan && bs.cardinality()<=2){
            createAccount=new Button("Create Account / request a loan", 330,20);
            createAccount.textButton("blue");
            accountsInfoPanel.add(createAccount);
            createAccount.addActionListener(this);
        }
        else if(loan && checking && saving && !security){
            if(Double.compare(user.getSaving().getBalance().getValue(),5000)>=0){
                createAccount=new Button("Create Security account", 330,20);
                createAccount.textButton("blue");
                accountsInfoPanel.add(createAccount);
                createAccount.addActionListener(this);
            }
        }
        else if(!loan && bs.cardinality()==3){
            createAccount=new Button("Request a loan", 330,20);
            createAccount.textButton("blue");
            accountsInfoPanel.add(createAccount);
            createAccount.addActionListener(this);
        }
        else if(loan && (!saving||!checking)){
            createAccount=new Button("Create Account", 330,20);
            createAccount.textButton("blue");
            accountsInfoPanel.add(createAccount);
            createAccount.addActionListener(this);
        }


    }

    void addTransactions(){
        List<Panel> transactionsPanel=new ArrayList<Panel>();
        transactionPanel.setLayout(new GridLayout(6,1,0,10));
        transactionPanel.setBorder(BorderFactory.createTitledBorder("Transaction"));
        for(int i=0;i<5;i++){
            transactionsPanel.add(new Panel());
            transactionsPanel.get(i).setLayout(new GridLayout(2,3,20,0));
            transactionsPanel.get(i).add(new Label("MM/dd/yyyy"));
            transactionsPanel.get(i).add(new Label("$50"));
            transactionsPanel.get(i).add(new Label("From acc-1234 to where"));
            transactionsPanel.get(i).add(new Label("$500"));
            transactionPanel.add(transactionsPanel.get(i));
        }
        viewAllTransactions.textButton("blue");
        viewAllTransactions.addActionListener(this);
        transactionPanel.add(viewAllTransactions);
    }

    void openDialog(){
        Panel panel=new Panel();
        dialog=new JDialog(dashboard,"Loggin out");
        dialog.getContentPane().setBackground(new Color(0xe0fbfc));
        dialog.setSize(350,70);
        logoutEntered=new Button("Log out",100,20);
        logoutEntered.textButton("red");
        logoutEntered.addActionListener(this);
        panel.add(new Label("Are you sure you want to logout?"));
        panel.add(logoutEntered);
        dialog.add(panel);

        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==logoutButton){
            openDialog();
        }
        else if(e.getSource()==logoutEntered){
            dialog.dispose();
            dashboard.dispose();
            new UserType();
        }
        else if(e.getSource()==personalInfoButton){
            dashboard.dispose();
            new PersonalInfo(user);
        }
        else if(e.getSource()==createAccount){
            dashboard.dispose();
            new CreateAccount(user);
        }
        else if(e.getSource()==savingsAccountButton){
            dashboard.dispose();
            //new AccountFrame("SAVING ACC",user, user.getSaving().getId().toString());
            new AccountFrame("SAVING ACC",user);
        }
        else if(e.getSource()==checkingAccountButton){
            dashboard.dispose();
            //new AccountFrame("CHECKING ACC",user, user.getChecking().getId().toString());
            new AccountFrame("CHECKING ACC",user);
        }
        else if(e.getSource()==loanAccountButton){
            dashboard.dispose();
            //new AccountFrame("LOAN ACC",user, user.getLoan().getId().toString());
            new AccountFrame("LOAN ACC",user);
        }
        else if(e.getSource()==securitiesAccountButton){
            dashboard.dispose();
            //new AccountFrame("SECURITY ACC",user, user.getSecurities().getId().toString());
            new AccountFrame("SECURITY ACC",user);
        }
    }
}
