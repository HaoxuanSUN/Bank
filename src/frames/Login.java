package frames;

import components.swingComponents.BackButton;
import components.swingComponents.Button;
import components.swingComponents.Frame;
import components.swingComponents.Label;
import data.Constants;
import frames.customer.CustomerDashboard;
import frames.customer.StartFrame;
import frames.manager.ManagerDashboard;
import model.users.User;
import service.SignIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {
    Frame loginFrame;
    JTextField textField = new JTextField(15);
    JPasswordField passwordField = new JPasswordField(15);
    Label usernameLabel = new Label("Username");
    Label passwordLabel = new Label("Password");
    Panel headingPanel=new Panel();
    Panel formPanel=new Panel();
    Panel userNamePanel=new Panel();
    Panel passwordPanel=new Panel();
    Panel submitPanel=new Panel();
    Button login_button;
    BackButton back=new BackButton();
    String userType;
    ImageIcon logo=new ImageIcon(new ImageIcon("src/images/bank-logo.png")
            .getImage().getScaledInstance(70, 70,Image.SCALE_DEFAULT));
    JLabel logoLabel = new JLabel(logo);
    Panel logoPanel=new Panel();
    Panel backPanel=new Panel();

    SignIn singIn;
    public Login(String login_of){

        userType=login_of;
        loginFrame=new Frame();
        loginFrame.setLayout(new GridLayout(3,1,0,50));

        headingPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,30));
        backPanel.setLayout(new BorderLayout());
        logoLabel.setLayout(new GridBagLayout());
        backPanel.setPreferredSize(new Dimension(420,30));
        backPanel.add(back, BorderLayout.WEST);
        back.addActionListener(this);
        logoPanel.setPreferredSize(new Dimension(420,70));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.weighty = 1;
        logoPanel.add(logoLabel,gbc);
        headingPanel.add(backPanel);
        headingPanel.add(logoPanel);
        loginFrame.add(headingPanel);

        login_button= new Button(login_of+" Login",200,50);
        formPanel.setLayout(new GridLayout(3,1,10,20));

        userNamePanel.setLayout(new GridBagLayout());
        userNamePanel.add(usernameLabel);
        userNamePanel.add(textField);
        formPanel.add(userNamePanel);

        passwordPanel.setLayout(new GridBagLayout());
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        formPanel.add(passwordPanel);

        submitPanel.setLayout(new GridBagLayout());
        submitPanel.add(login_button);
        login_button.addActionListener(this);
        formPanel.add(submitPanel);

        loginFrame.add(formPanel);

        loginFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            if(userType.equalsIgnoreCase("Manager")){
                loginFrame.dispose();
                new UserType();
            }
            else{
                loginFrame.dispose();
                new StartFrame();
            }
        }
        else if(e.getSource()==login_button) {
            if(userType.equalsIgnoreCase("Manager")){
                if(textField.getText().equals(Constants.managerUserName)
                && new String(passwordField.getPassword()).equals(Constants.managerPassword)){
                    loginFrame.dispose();
                    new ManagerDashboard();
                }
                else
                    JOptionPane.showMessageDialog(login_button,"incorrect username or password try again");
            }
            else{
                singIn=new SignIn(textField.getText(),new String(passwordField.getPassword()));
                User user = singIn.signIn();
                if(user!=null){
                    loginFrame.dispose();
                    new CustomerDashboard(user,true);
                }
                else
                    JOptionPane.showMessageDialog(login_button,"incorrect username or password try again");
            }
        }
    }
}
