package components.swingComponents;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    public Button(String button_text,int x,int y){
        this.setFocusable(false);
        this.setPreferredSize(new Dimension(x,y));
        this.setText(button_text);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    public void textButton(String color){
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        if(color=="red"){
            this.setForeground(Color.RED);
        }
        else if(color=="blue"){
            this.setForeground(Color.BLUE);
        }
    }
}
