package gui.frame;
 
import javax.swing.JFrame;

import gui.panel.*;
import util.GUIUtil;
import util.ToolUtil;

import java.awt.*;

public class MainFrame extends JFrame{
    public static MainFrame instance = new MainFrame();
     
    private MainFrame(){

        this.setTitle("一本糊涂账");
        this.setContentPane(MainPanel.instance);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(580,500));
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
     
    public static void main(String[] args) {
        instance.setVisible(true);

    }
     
}