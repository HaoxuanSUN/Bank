package components.swingComponents;

import javax.swing.*;
import java.awt.*;

public class LogoutButton extends JButton{
    ImageIcon logo=new ImageIcon(new ImageIcon("src/images/logout.jpg")
            .getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
    public LogoutButton(){
        this.setFocusable(false);
        this.setPreferredSize(new Dimension(50,30));
        this.setIcon(logo);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
