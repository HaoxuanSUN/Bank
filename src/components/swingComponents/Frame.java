package components.swingComponents;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame(){
        this.setTitle("My Fancy Bank");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(420,700);
        this.getContentPane().setBackground(new Color(0xe0fbfc));
    }
}
