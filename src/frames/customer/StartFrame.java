package frames.customer;

import components.swingComponents.BackButton;
import components.swingComponents.Button;
import components.swingComponents.Frame;
import frames.UserType;
import frames.Login;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame implements ActionListener {
    Frame customerStartFrame;
    Button existingCustomer;
    Button newCustomer;
    Panel existingCustomerPanel=new Panel();
    Panel newCustomerPanel=new Panel();
    BackButton back=new BackButton();
    Panel customerButtonPanel=new Panel();
    Panel backPanel=new Panel();
    Panel borderPanel=new Panel();
    public StartFrame(){
        customerStartFrame=new Frame();
        customerStartFrame.setLayout(new GridLayout(2,1,0,50));
        addingExistingCustomer();
        addingNewCustomer();
        customerStartFrame.setVisible(true);
    }
    void addingExistingCustomer(){
        borderPanel.setLayout(new BorderLayout());
        borderPanel.setPreferredSize(new Dimension(420,30));

        backPanel.setLayout(new BorderLayout());
        backPanel.setPreferredSize(new Dimension(420,60));
        back.addActionListener(this);
        backPanel.add(borderPanel, BorderLayout.NORTH);
        backPanel.add(back, BorderLayout.WEST);

        existingCustomer=new Button("Existing Customer",200,50);
        existingCustomer.addActionListener(this);
        existingCustomerPanel.setLayout(new BorderLayout());
        existingCustomerPanel.add(backPanel, BorderLayout.NORTH);

        customerButtonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weighty = 1;
        customerButtonPanel.add(existingCustomer,gbc);
        existingCustomerPanel.add(customerButtonPanel, BorderLayout.SOUTH);
        customerStartFrame.add(existingCustomerPanel);
    }

    void addingNewCustomer(){
        newCustomer=new Button("New Customer",200,50);
        newCustomer.addActionListener(this);
        newCustomerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        newCustomerPanel.add(newCustomer,gbc);
        customerStartFrame.add(newCustomerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==newCustomer){
            customerStartFrame.dispose();
            new CustomerRegistration();
        }
        else if(e.getSource()==existingCustomer){
            customerStartFrame.dispose();
            new Login("Customer");
        }
        else if(e.getSource()==back){
            customerStartFrame.dispose();
            new UserType();
        }
    }
}
