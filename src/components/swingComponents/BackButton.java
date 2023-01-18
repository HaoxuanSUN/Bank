package components.swingComponents;

import javax.swing.*;
import java.awt.*;

public class BackButton  extends JButton {
    ImageIcon logo=new ImageIcon(new ImageIcon("src/images/back.jpg")
            .getImage().getScaledInstance(15, 15,Image.SCALE_DEFAULT));
    public BackButton(){
        this.setFocusable(false);
        this.setPreferredSize(new Dimension(50,30));
        this.setIcon(logo);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
