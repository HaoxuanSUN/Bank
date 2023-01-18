package frames.manager;

import components.swingComponents.BackButton;
import components.swingComponents.Button;
import components.swingComponents.Frame;
import components.swingComponents.Label;
import components.swingComponents.Panel;
import data.UserDao;
import frames.customer.CustomerDashboard;
import model.users.Manager;
import model.users.User;
import util.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CustomerLookUp implements ActionListener {
    Frame customerLookUp;
    Panel customerLookUpPanel=new Panel();
    Manager manager;
    BackButton backButton=new BackButton();
    JTextField usernameField=new JTextField(10);

    Button usernameEntered=new Button("View",100,20);

    List<Button> customersButtons=new ArrayList<>();
    public CustomerLookUp(Manager manager){
        customerLookUp=new Frame();
        this.manager=manager;

        addBackButton();
        customerlookUpWithUserName();
        customerWithLoanAndLessSavings();

        customerLookUp.add(customerLookUpPanel);
        customerLookUp.setVisible(true);
    }
    void addBackButton(){
        Panel backPanel=new Panel();
        backPanel.setLayout(new BorderLayout());
        backPanel.setPreferredSize(new Dimension(400,30));
        backButton.addActionListener(this);
        backPanel.add(backButton, BorderLayout.WEST);
        customerLookUpPanel.add(backPanel);
    }
    void customerlookUpWithUserName(){
        Panel loopUp=new Panel();
        loopUp.add(new Label("Enter contermer username"));
        loopUp.add(usernameField);
        usernameEntered.addActionListener(this);
        usernameEntered.textButton("blue");
        loopUp.add(usernameEntered);

        customerLookUpPanel.add(loopUp);

    }

    void customerWithLoanAndLessSavings(){
        Panel customers=new Panel();
        customers.setPreferredSize(new Dimension(350,1000));

        for (String key : manager.getWhoOweMeMost().keySet()){
            Panel temp=new Panel();
            temp.setLayout(new GridLayout(2,2,50,0));
            temp.add(new Label(key));
            customersButtons.add(new Button("view",100,20));
            customersButtons.get(new ArrayList(manager.getWhoOweMeMost().keySet()).indexOf(key)).addActionListener(this);
            customersButtons.get(new ArrayList(manager.getWhoOweMeMost().keySet()).indexOf(key)).textButton("blue");
            temp.add(customersButtons.get(new ArrayList(manager.getWhoOweMeMost().keySet()).indexOf(key)));
            temp.add(new Label("Loan amount: $"+manager.getWhoOweMeMost().get(key)));
            temp.add(new Panel());

            customers.add(temp);
        }

        JScrollPane scrollFrame = new JScrollPane(customers);
        customers.setAutoscrolls(true);
        scrollFrame.setPreferredSize(new Dimension( 380,200));
        scrollFrame.setBorder(BorderFactory.createTitledBorder("Customer with loans and no savings"));
        scrollFrame.setOpaque(false);
        scrollFrame.getViewport().setOpaque(false);
        customerLookUpPanel.add(scrollFrame);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){
            customerLookUp.dispose();
            new ManagerDashboard();
        }
        else if(e.getSource()==usernameEntered){
            UserDao userDao=new UserDao();
            User user=userDao.getCustomerByName(usernameField.getText());
            if(user!=null) new CustomerDashboard(user,false);
            else JOptionPane.showMessageDialog(customerLookUp, "Username does not exist");
        }
        for(int i=0;i<customersButtons.size();i++){
            if(e.getSource()==customersButtons.get(i)){
                UserDao userDao=new UserDao();
                User user=userDao.getCustomerByName(new ArrayList(manager.getWhoOweMeMost().keySet()).get(i).toString());
                if(user!=null) new CustomerDashboard(user,false);

            }
        }
    }
}
