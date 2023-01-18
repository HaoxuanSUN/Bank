package frames.customer;

import components.swingComponents.BackButton;
import components.swingComponents.Frame;
import components.swingComponents.Label;
import components.swingComponents.Panel;
import components.swingComponents.Button;
import frames.Login;
import model.users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PersonalInfo implements ActionListener {
    Frame personalInfo;
    Panel personalInfoPanel;
    User user;
    BackButton backButton=new BackButton();
    Button changePassword;
    Button edit=new Button("Edit",100,30);

    JDialog editDialog;
    JDialog passwordChangeDialog;
    JTextField username = new JTextField(15);
    JTextField firstname = new JTextField(6);
    JTextField lastname = new JTextField(6);
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
    JFormattedTextField date_of_birth = new JFormattedTextField(dateFormat);
    JPasswordField passwordField = new JPasswordField(10);
    JPasswordField re_passwordField = new JPasswordField(10);

    Button editAtDialog=new Button("Edit",100,30);

    Button passwordChangeAtDialog=new Button("change",100,30);
    public PersonalInfo(User user){
        this.user=user;
        personalInfo=new Frame();
        personalInfoPanel=new Panel();
        username.setText(user.getUsername());
        firstname.setText(user.getFirstName());
        lastname.setText(user.getLastName());
        date_of_birth.setText(user.getBirth());

        addBackButton();
        //addEditButton();
        addUserInfo();

        personalInfo.add(personalInfoPanel);
        personalInfo.setVisible(true);
    }

    void addEditButton(){
        Panel editPanel=new Panel();
        editPanel.setLayout(new BorderLayout());
        editPanel.setPreferredSize(new Dimension(400,30));
        edit.addActionListener(this);
        edit.textButton("blue");
        editPanel.add(edit, BorderLayout.EAST);
        personalInfoPanel.add(editPanel);
    }

    void addBackButton(){
        Panel backPanel=new Panel();
        backPanel.setLayout(new BorderLayout());
        backPanel.setPreferredSize(new Dimension(400,30));
        backButton.addActionListener(this);
        backPanel.add(backButton, BorderLayout.WEST);
        personalInfoPanel.add(backPanel);
    }

    void addUserInfo(){
        Panel userInfo=new Panel();
        userInfo.setLayout(new GridLayout(3,2,10,10));

        changePassword=new Button("Click here", 100,30);
        changePassword.textButton("blue");
        changePassword.addActionListener(this);

        userInfo.add(new Label("username: "));
        userInfo.add(new Label(user.getUsername()));
        userInfo.add(new Label("Name: "));
        userInfo.add(new Label(user.getFirstName()+" "+user.getLastName()));
        userInfo.add(new Label("Date of birth: "));
        userInfo.add(new Label(user.getBirth()));
        //userInfo.add(new Label("Change password"));
        //userInfo.add(changePassword);

        personalInfoPanel.add(userInfo);
    }

    void editDialog(){
        editDialog=new JDialog(personalInfo,"Edit your personal Information");
        editDialog.setSize(400,250);
        editDialog.getContentPane().setBackground(new Color(0xe0fbfc));

        Panel editPanel=new Panel();
        editPanel.setLayout(new GridLayout(4,1,10,10));
        Panel usernamePanel=new Panel();
        usernamePanel.add(new Label("username"));
        usernamePanel.add(username);
        Panel namePanel=new Panel();
        namePanel.add(new Label("Name"));
        namePanel.add(firstname);
        namePanel.add(lastname);
        Panel DOBPanel=new Panel();
        DOBPanel.add(new Label("Date Of Birth"));
        DOBPanel.add(date_of_birth);

        editAtDialog.addActionListener(this);
        editAtDialog.textButton("blue");

        editPanel.add(usernamePanel);
        editPanel.add(namePanel);
        editPanel.add(DOBPanel);
        editPanel.add(editAtDialog);

        editDialog.add(editPanel);
        editDialog.setVisible(true);
    }

    void passwordChangeDialog(){
        passwordChangeDialog=new JDialog(personalInfo,"Edit your personal Information");
        passwordChangeDialog.setSize(400,250);
        passwordChangeDialog.getContentPane().setBackground(new Color(0xe0fbfc));

        Panel passwordChangePanel=new Panel();
        passwordChangePanel.setLayout(new GridLayout(4,1,10,10));

        Panel passwordPanel=new Panel();
        passwordPanel.add(new Label("password"));
        passwordPanel.add(passwordField);
        Panel re_passwordPanel=new Panel();
        re_passwordPanel.add(new Label("re enter password"));
        re_passwordPanel.add(re_passwordField);
        Panel passwordComment=new Panel();
        Label passwordsCommentLabel1 = new Label("The two passwords should match.");
        Label passwordsCommentLabel2 = new Label("The password should contain at least 1 alphabet");
        passwordsCommentLabel1.setTextAsCaption();
        passwordsCommentLabel2.setTextAsCaption();
        passwordComment.add(passwordsCommentLabel1);
        passwordComment.add(passwordsCommentLabel2);

        passwordChangeAtDialog.addActionListener(this);
        passwordChangeAtDialog.textButton("blue");

        passwordChangePanel.add(passwordPanel);
        passwordChangePanel.add(re_passwordPanel);
        passwordChangePanel.add(passwordComment);
        passwordChangePanel.add(passwordChangeAtDialog);

        passwordChangeDialog.add(passwordChangePanel);
        passwordChangeDialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==backButton){
            personalInfo.dispose();
            new CustomerDashboard(user,true);
        }
        else if(e.getSource()==edit){
            editDialog();
        }
        else if(e.getSource()==editAtDialog){
            if(true) {
                editDialog.dispose();
                personalInfo.dispose();
                new Login("Customer");
            }
            else
                JOptionPane.showMessageDialog(personalInfo,"invalid edit");
        }
        else if(e.getSource()==changePassword){
            passwordChangeDialog();
        }
        else if(e.getSource()==passwordChangeAtDialog){
            if(new String(passwordField.getPassword()).isBlank() ||
                    new String(passwordField.getPassword()).isBlank() )
                JOptionPane.showMessageDialog(personalInfo,"Enter all fields");
            else if(new String(passwordField.getPassword()).equals(new String(re_passwordField.getPassword())))
                JOptionPane.showMessageDialog(personalInfo,"Passwords not matching");
            else{
                if(true) {
                    passwordChangeDialog.dispose();
                    personalInfo.dispose();
                    new Login("Customer");
                }
                else
                    JOptionPane.showMessageDialog(personalInfo,"invalid password");
            }
        }
    }
}
