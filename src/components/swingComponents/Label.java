package components.swingComponents;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {
    public Label(String label_text){
        this.setText(label_text);
    }
    public void setTextAsCaption(){
        this.setFont(new Font("Serif", Font.PLAIN, 11));
        this.setForeground(Color.GRAY);
    }
}
