package frames;

import components.swingComponents.Button;
import components.swingComponents.Frame;
import frames.customer.StartFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserType implements ActionListener {
    Frame userTypeFrame;
    Button managerButton;
    Button customerButton;
    Panel mangerPanel=new Panel();
    Panel customerPanel=new Panel();

    ImageIcon managerLogo=new ImageIcon(new ImageIcon("src/images/manager-logo.jpeg")
            .getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
    ImageIcon customerLogo=new ImageIcon(new ImageIcon("src/images/customer-logo.png")
            .getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
    public UserType(){
        userTypeFrame=new Frame();
        userTypeFrame.setLayout(new GridLayout(2,1,0,50));
        addingManagerButton();
        addingCustomerButton();
        userTypeFrame.setVisible(true);
    }
    void addingManagerButton(){
        managerButton=new Button("Manager",150,50);
        managerButton.setIcon(managerLogo);
        managerButton.addActionListener(this);
        mangerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.weighty = 1;
        mangerPanel.add(managerButton,gbc);
        userTypeFrame.add(mangerPanel);
    }

    void addingCustomerButton(){
        customerButton=new Button("Customer",150,50);
        customerButton.setIcon(customerLogo);
        customerButton.addActionListener(this);
        customerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        customerPanel.add(customerButton,gbc);
        userTypeFrame.add(customerPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==customerButton){
            userTypeFrame.dispose();
            new StartFrame();
        }
        else if(e.getSource()==managerButton){
            userTypeFrame.dispose();
            new Login("Manager");
        }
    }
}
