package frames.customer;

import components.swingComponents.BackButton;
import components.swingComponents.Label;
import components.swingComponents.Frame;
import components.swingComponents.Button;
import model.users.User;
import service.Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CustomerRegistration implements ActionListener {
    String[] prefix={"Mr.","Mrs.","Ms."};
    String[] accounts={"Savings Acc","Checking Acc"};
    String[] states={
            "	Alabama	",
            "	Alaska	",
            "	Arizona	",
            "	Arkansas	",
            "	California	",
            "	Colorado	",
            "	Connecticut	",
            "	Delaware	",
            "	Florida	",
            "	Georgia	",
            "	Hawaii	",
            "	Idaho	",
            "	Illinois	",
            "	Indiana	",
            "	Iowa	",
            "	Kansas	",
            "	Kentucky	",
            "	Louisiana	",
            "	Maine	",
            "	Maryland	",
            "	Massachusetts	",
            "	Michigan	",
            "	Minnesota	",
            "	Mississippi	",
            "	Missouri	",
            "	Montana	",
            "	Nebraska	",
            "	Nevada	",
            "	New Hampshire	",
            "	New Jersey	",
            "	New Mexico	",
            "	New York	",
            "	North Carolina	",
            "	North Dakota	",
            "	Ohio	",
            "	Oklahoma	",
            "	Oregon	",
            "	Pennsylvania	",
            "	Rhode Island	",
            "	South Carolina	",
            "	South Dakota	",
            "	Tennessee	",
            "	Texas	",
            "	Utah	",
            "	Vermont	",
            "	Virginia	",
            "	Washington	",
            "	West Virginia	",
            "	Wisconsin	",
            "	Wyoming	",
    };


    Frame registrationFrame;
    Label usernameLabel = new Label("Username:");
    Label fullNameLabel = new Label("Full Name:");
    Label passwordLabel = new Label("Password:");
    Label dateOfBirthLabel = new Label("Date Of Birth:");
    Label IDLabel = new Label("ID Card Number:");
    Label accountTypeLabel = new Label("Account Type:");
    Label phoneLabel=new Label("Phone Number:");
    Label citizenshipLabel=new Label("Citizenship:");
    Label addressLabel=new Label("Residental Address:");
    Label customerRegistrationLabel=new Label("Registration Form");


    Panel headingPanel=new Panel();
    Panel backPanel=new Panel();
    Panel formPanel=new Panel();
    Panel userNamePanel=new Panel();
    Panel passwordPanel=new Panel();
    Panel namePanel=new Panel();
    Panel dateOfBirthPanel=new Panel();
    Panel accountTypePanel=new Panel();
    Panel IDPanel=new Panel();
    Panel phonePanel=new Panel();
    Panel citizenshipPanel=new Panel();
    Panel addressPanel=new Panel();
    Panel addressWithLabelPanel=new Panel();
    Panel submitPanel=new Panel();
    Panel formNamePanel=new Panel();

    JTextField usernameTextField = new JTextField(15);
    JTextField firstNameTextField = new JTextField(5);
    JTextField lastNameTextField = new JTextField(5);
    JTextField IDTextField=new JTextField(15);
    JTextField phoneAreaTextField=new JTextField(3);
    JTextField phoneNumTextField=new JTextField(10);
    JTextField citizenshipTextField=new JTextField(10);
    JTextField streetTextField=new JTextField();
    JTextField line2TextField=new JTextField();
    JTextField cityTextField=new JTextField();
    JTextField date_of_birth = new JTextField("MM/dd/YYYY");

    JPasswordField passwordField = new JPasswordField(10);
    JPasswordField re_passwordField = new JPasswordField(10);

    JComboBox namePrefix=new JComboBox(prefix);
    JComboBox accountType=new JComboBox(accounts);
    JComboBox state=new JComboBox(states);

    Button register;

    BackButton back=new BackButton();

    Register registerUser;

    public CustomerRegistration(){
        registrationFrame=new Frame();
        formPanel.setLayout(new GridLayout(8,1,0,0));
        Panel passwordComment=new Panel();

        headingPanel.setLayout(new FlowLayout(FlowLayout.LEFT,0,30));
        backPanel.setLayout(new BorderLayout());
        customerRegistrationLabel.setLayout(new GridBagLayout());
        backPanel.setPreferredSize(new Dimension(420,30));
        backPanel.add(back, BorderLayout.WEST);
        back.addActionListener(this);
        formNamePanel.setPreferredSize(new Dimension(420,70));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.weighty = 1;
        formNamePanel.add(customerRegistrationLabel,gbc);
        headingPanel.add(backPanel);
        headingPanel.add(formNamePanel);

        namePanel.setLayout(new GridBagLayout());
        namePanel.add(fullNameLabel);
        namePanel.add(namePrefix);
        firstNameTextField.setText("First");
        namePanel.add(firstNameTextField);
        lastNameTextField.setText("Last");
        namePanel.add(lastNameTextField);

        dateOfBirthPanel.setLayout(new GridBagLayout());
        dateOfBirthPanel.add(dateOfBirthLabel);
        date_of_birth.setColumns(15);
        date_of_birth.setText("MM/dd/YYYY");
        dateOfBirthPanel.add(date_of_birth);

        IDPanel.setLayout(new GridBagLayout());
        IDPanel.add(IDLabel);
        IDPanel.add(IDTextField);

        phonePanel.setLayout(new GridBagLayout());
        phonePanel.add(phoneLabel);
        phonePanel.add(phoneAreaTextField);
        phonePanel.add(phoneNumTextField);

        citizenshipPanel.setLayout(new GridBagLayout());
        citizenshipPanel.add(citizenshipLabel);
        citizenshipPanel.add(citizenshipTextField);

        userNamePanel.setLayout(new GridBagLayout());
        userNamePanel.add(usernameLabel);
        userNamePanel.add(usernameTextField);

        passwordPanel.setLayout(new GridBagLayout());
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        passwordPanel.add(re_passwordField);

        accountTypePanel.setLayout(new GridBagLayout());
        accountTypePanel.add(accountTypeLabel);
        accountTypePanel.add(accountType);

        addressPanel.setLayout(new GridLayout(2,2,10,0));
        streetTextField.setLayout(new GridBagLayout());
        addressPanel.add(streetTextField);
        addressPanel.add(line2TextField);
        addressPanel.add(cityTextField);
        addressPanel.add(state);

        Label passwordsCommentLabel1 = new Label("The two passwords should match.");
        Label passwordsCommentLabel2 = new Label("The password should contain at least 1 alphabet");
        passwordsCommentLabel1.setTextAsCaption();
        passwordsCommentLabel2.setTextAsCaption();
        passwordComment.add(passwordsCommentLabel1);
        passwordComment.add(passwordsCommentLabel2);

        addressWithLabelPanel.setLayout(new GridBagLayout());
        addressWithLabelPanel.add(addressLabel);
        addressWithLabelPanel.add(addressPanel);

        register= new Button("register",200,50);
        submitPanel.setLayout(new GridBagLayout());
        submitPanel.add(register);
        register.addActionListener(this);

        formPanel.add(headingPanel);
        formPanel.add(namePanel);
        formPanel.add(dateOfBirthPanel);
        formPanel.add(IDPanel);
        //formPanel.add(phonePanel);
        formPanel.add(userNamePanel);
        formPanel.add(passwordPanel);
        //formPanel.add(passwordComment);
        //formPanel.add(addressWithLabelPanel);
        formPanel.add(submitPanel);

        registrationFrame.add(formPanel);
        registrationFrame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
            registrationFrame.dispose();
            new StartFrame();
        }
        else if(e.getSource()==register){
            if(firstNameTextField.getText().isBlank() ||
                    lastNameTextField.getText().isBlank() ||
                    new String(passwordField.getPassword()).isBlank() ||
                    new String(re_passwordField.getPassword()).isBlank() ||
                    date_of_birth.getText().isBlank() ||
                    IDTextField.getText().isBlank())
            {
                JOptionPane.showMessageDialog(registrationFrame,"Enter all fields");
            }
            else if(new String(passwordField.getPassword()).equals(new String(re_passwordField.getPassword()))){
                registerUser=new Register(usernameTextField.getText(),
                        firstNameTextField.getText(),
                        lastNameTextField.getText(),
                        new String(passwordField.getPassword()),
                        date_of_birth.getText(),
                        IDTextField.getText());
                try {
                    User temp= registerUser.register();
                    JOptionPane.showMessageDialog(registrationFrame,"Successfully register");
                    registrationFrame.dispose();
                    new StartFrame();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(registrationFrame,"registration unsuccessful try again");
                    throw new RuntimeException(ex);
                }
            }
            else {
                JOptionPane.showMessageDialog(registrationFrame,"Passwords do not match!");
            }
        }

    }
}
