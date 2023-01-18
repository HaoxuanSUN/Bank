package frames.manager;

import components.swingComponents.Button;
import components.swingComponents.Frame;
import components.swingComponents.Label;
import components.swingComponents.LogoutButton;
import components.swingComponents.Panel;
import data.Constants;
import frames.Login;
import frames.UserType;
import frames.customer.CustomerDashboard;
import model.users.Manager;
import util.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ManagerDashboard implements ActionListener {
    Frame managerdashboard;
    Panel managerdashboardPanel=new Panel();
    Manager manager;
    JDialog dialog;
    LogoutButton logoutButton=new LogoutButton();
    Button logoutEntered;
    Button viewAllTransactions=new Button("View all Transactions",100,20);
    List<Button> stockUpdateButton=new ArrayList<>();
    Button addStock=new Button("Add Stock",100,20);

    Button updateButton=new Button("Update",100,20);
    Button addButton=new Button("Add",100,20);
    Button cancleButton=new Button("Cancel",100,20);
    JTextField updateField=new JTextField(10);
    JTextField nameField=new JTextField(10);
    JTextField companyField=new JTextField(10);
    JTextField priceField=new JTextField(10);
    Button customersInfo=new Button("Customers Info",300,20);
    int indexOfStockBeingUpdated;

    public ManagerDashboard(){
        manager = new Manager(Constants.managerUserName,
                Constants.managerFirstName,
                Constants.managerLastName,
                Constants.managerPassword,
                Constants.managerDateOfBirth);
        managerdashboard=new Frame();

        logoutOption();
        managerInfo();
        addDailyTransactions();
        addstocks();
        custerLookup();

        managerdashboard.add(managerdashboardPanel);
        managerdashboard.setVisible(true);
    }
    void logoutOption(){
        Panel logoutPanel=new Panel();
        logoutPanel.setLayout(new BorderLayout());
        logoutPanel.setPreferredSize(new Dimension(400,30));
        logoutButton.addActionListener(this);
        logoutPanel.add(logoutButton, BorderLayout.EAST);
        managerdashboardPanel.add(logoutPanel);
    }
    void openDialog(){
        Panel panel=new Panel();
        dialog=new JDialog(managerdashboard,"Loggin out");
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

    void managerInfo(){
        Panel temp=new Panel();
        Panel gap1=new Panel();
        gap1.setPreferredSize( new Dimension( 380, 20 ) );
        Panel gap2=new Panel();
        gap2.setPreferredSize( new Dimension( 380, 20 ) );
        temp.add(gap1);
        temp.add(new Label("Hi, "+ manager.getFirstName()+" "+manager.getLastName()));
        temp.add(gap2);
        managerdashboardPanel.add(temp);
    }

    void addDailyTransactions(){


        Panel transactionsPanel=new Panel();
        transactionsPanel.setPreferredSize(new Dimension(350,1000));

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
        Calendar cal = Calendar.getInstance();
        Date ydate = null;
        for(int i=0;i<Reader.L8.size();i++){


            /* system date */
            String systemdate = sdf.format(cal.getTime());
            /* the date you want to compare in string format */
            String yourdate = Reader.L8.get(i).split(" ")[6]
                    +" "+
                    Reader.L8.get(i).split(" ")[5]+" "+
                    Reader.L8.get(i).split(" ")[9];

            try {
                ydate = sdf.parse(yourdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            yourdate = sdf.format(ydate);
            if(systemdate.equals(yourdate)){
                Panel temp=new Panel();
            temp.setLayout(new GridLayout(2,2,50,10));
            temp.add(new Label(Reader.L8.get(i).split(" ")[6]
                    +" "+
                    Reader.L8.get(i).split(" ")[5]+" "+
                    Reader.L8.get(i).split(" ")[9]
                    ));
            Label tAmount;
            if(Reader.L8.get(i).split(" ")[12].equals("DEPOSIT")){
                tAmount=new Label("+$ "+Reader.L8.get(i).split(" ")[11]);
                tAmount.setForeground(Color.green);
            }
            else{
                tAmount=new Label("-$ "+Reader.L8.get(i).split(" ")[11]);
                tAmount.setForeground(Color.red);
            }
            temp.add(tAmount);
            if(Reader.L8.get(i).split(" ")[12].equals("DEPOSIT"))
                temp.add(new Label("deposited-acc"));
            else if(Reader.L8.get(i).split(" ")[12].equals("WITHDRAW"))
                temp.add(new Label("acc-withdraw"));
            else
                temp.add(new Label("acc-acc"));
            temp.add(new Panel());
            transactionsPanel.add(temp);
            }
        }
        viewAllTransactions.textButton("blue");
        viewAllTransactions.addActionListener(this);
        //transactionsPanel.add(viewAllTransactions);
        managerdashboardPanel.add(transactionsPanel);


        JScrollPane scrollFrame = new JScrollPane(transactionsPanel);
        transactionsPanel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 380,200));
        scrollFrame.setBorder(BorderFactory.createTitledBorder("Today's transactions"));
        scrollFrame.setOpaque(false);
        scrollFrame.getViewport().setOpaque(false);
        managerdashboardPanel.add(scrollFrame);
    }

    void addstocks(){
        Panel stocksPanel=new Panel();
        stocksPanel.setLayout(new GridLayout(Reader.L2.size()+1,3,0,10));
        for(int i = 0; i< Reader.L2.size(); i++){
            stocksPanel.add(new Label(Reader.L2.get(i).split(" ")[0]));
            stocksPanel.add(new Label(Reader.L2.get(i).split(" ")[1]));
            stockUpdateButton.add(new Button("Update",100,20));
            stockUpdateButton.get(i).addActionListener(this);
            stockUpdateButton.get(i).textButton("blue");
            stocksPanel.add(stockUpdateButton.get(i));
        }
        stocksPanel.add(new Panel());
        addStock.addActionListener(this);
        addStock.textButton("blue");
        stocksPanel.add(addStock);
        stocksPanel.add(new Panel());

        JScrollPane scrollFrame = new JScrollPane(stocksPanel);
        stocksPanel.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 380,200));
        scrollFrame.setBorder(BorderFactory.createTitledBorder("Stocks"));
        scrollFrame.setOpaque(false);
        scrollFrame.getViewport().setOpaque(false);
        managerdashboardPanel.add(scrollFrame);
    }

    void updateStock(String stock,int index){
        indexOfStockBeingUpdated=index;
        Panel panel=new Panel();
        dialog=new JDialog(managerdashboard,"Updating "+stock.split(" ")[0]);
        dialog.getContentPane().setBackground(new Color(0xe0fbfc));
        dialog.setSize(300,300);

        panel.setLayout(new GridLayout(5,2,0,0));
        panel.add(new Label("Stock Name: "));
        panel.add(new Label(stock.split(" ")[0]));
        panel.add(new Label("Stock Company: "));
        panel.add(new Label(stock.split(" ")[2]));
        panel.add(new Label(("Current price")));
        panel.add(new Label(stock.split(" ")[1]));
        panel.add(new Label("Updated price"));
        panel.add(updateField);

        cancleButton.addActionListener(this);
        cancleButton.textButton("red");
        updateButton.addActionListener(this);
        updateButton.textButton("blue");

        panel.add(cancleButton);
        panel.add(updateButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    void addNewStock(){
        Panel panel=new Panel();
        dialog=new JDialog(managerdashboard,"Add new stock");
        dialog.getContentPane().setBackground(new Color(0xe0fbfc));
        dialog.setSize(300,300);

        panel.setLayout(new GridLayout(4,2,0,0));
        panel.add(new Label("Stock Name: "));
        panel.add(nameField);
        panel.add(new Label("Stock Company: "));
        panel.add(companyField);
        panel.add(new Label(("Price: ")));
        panel.add(priceField);

        cancleButton.addActionListener(this);
        cancleButton.textButton("red");
        addButton.addActionListener(this);
        addButton.textButton("blue");

        panel.add(cancleButton);
        panel.add(addButton);

        dialog.add(panel);
        dialog.setVisible(true);
    }

    void custerLookup(){
        Panel gap1=new Panel();
        gap1.setPreferredSize( new Dimension( 380, 20 ) );
        Panel temp=new Panel();
        customersInfo.addActionListener(this);
        customersInfo.textButton("blue");
        temp.add(customersInfo);
        managerdashboardPanel.add(gap1);
        managerdashboardPanel.add(temp);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==logoutButton)
            openDialog();
        else if(e.getSource()==addStock)
            addNewStock();
        else if(e.getSource()==logoutEntered){
            dialog.dispose();
            managerdashboard.dispose();
            new Login("Manager");
        }
        else if(e.getSource()==cancleButton)
            dialog.dispose();
        else if(e.getSource()==updateButton){
            if(updateField.getText().isBlank())
                JOptionPane.showMessageDialog(managerdashboard,"Empty fields");
            else{
                try{
                    Double.parseDouble(updateField.getText());
                    manager.updateStockPrice(
                            Reader.L2.get(indexOfStockBeingUpdated).split(" ")[0],
                            Double.parseDouble(updateField.getText()));
                    JOptionPane.showMessageDialog(managerdashboard,"Successfully Updated");
                    dialog.dispose();
                    managerdashboard.dispose();
                    new ManagerDashboard();
                }
                catch (NumberFormatException error){
                    JOptionPane.showMessageDialog(managerdashboard,"Invalid input");
                }
            }
        }
        else if(e.getSource()==addButton){
            if(nameField.getText().isBlank() ||
                    companyField.getText().isBlank() ||
                    priceField.getText().isBlank())
                JOptionPane.showMessageDialog(managerdashboard,"Empty fields");
            else{
                try{
                    Double.parseDouble(priceField.getText());
                    manager.addNewStock(nameField.getText(),
                            Double.parseDouble(priceField.getText()),
                            companyField.getText());
                    JOptionPane.showMessageDialog(managerdashboard,"Successfully Updated");
                    dialog.dispose();
                    managerdashboard.dispose();
                    new ManagerDashboard();
                }
                catch (NumberFormatException error){
                    JOptionPane.showMessageDialog(managerdashboard,"Invalid price value");
                }
            }
        }
        else if(e.getSource()==customersInfo){
            managerdashboard.dispose();
            new CustomerLookUp(manager);
        }
        for(int i=0;i<Reader.L2.size();i++){
            if(e.getSource()==stockUpdateButton.get(i)){
                updateStock(Reader.L2.get(i),i);
            }
        }
    }
}
