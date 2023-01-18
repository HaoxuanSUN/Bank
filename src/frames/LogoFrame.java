package frames;

import components.codeComponents.StaticMethods;
import components.swingComponents.Frame;
import components.swingComponents.Label;

import javax.swing.*;
import java.awt.*;

public class LogoFrame{
    Frame logoFrame;
    Label logoLabel;
    ImageIcon logo=new ImageIcon(new ImageIcon("src/images/bank-logo.png")
            .getImage().getScaledInstance(75, 75,Image.SCALE_DEFAULT));
    public LogoFrame(){
        logoFrame=new Frame();
        logoLabel=new Label("My Fancy Bank");
        logoLabel.setIcon(logo);
        logoLabel.setHorizontalTextPosition(JLabel.CENTER);
        logoLabel.setVerticalTextPosition(JLabel.BOTTOM);
        logoFrame.add(logoLabel);
        logoLabel.setIconTextGap(25);
        logoLabel.setFont(new Font("Arial",Font.PLAIN,20));
        logoLabel.setVerticalAlignment(JLabel.CENTER);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoFrame.setVisible(true);
        openNextWindow();
    }

    void openNextWindow(){
        StaticMethods.sleep(2000);
        logoFrame.dispose();
        new UserType();
    }
}
