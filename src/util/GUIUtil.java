package util;

import com.alee.laf.WebLookAndFeel;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;

public class GUIUtil {
    private static String ImageFolder="d:/project/MyBill/img";

    //打包成jar后资源文件加载不能用File形式
//    public static void setImageIcon(JButton b,String fileName,String tip,int fontsize,int width,int height){
//        try(InputStream resource = ClassLoader.class.getResourceAsStream("/img/"+fileName)){
//            byte[] read=new byte[2048];
//            resource.read(read);
//            ImageIcon i=new ImageIcon(read);
//            b.setIcon(i);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        b.setPreferredSize(new Dimension(width,height));
//        b.setToolTipText(tip);
//        b.setVerticalTextPosition(JButton.BOTTOM);
//        b.setHorizontalTextPosition(JButton.CENTER);
//        b.setText(tip);
//        b.setFont(new FontUIResource("微软雅黑",0,fontsize));
//    }

    //文件形式访问imf资源
    public static void setImageIcon(JButton b,String fileName,String tip,int fontsize,int width,int height){
        ImageIcon i=new ImageIcon(new File(ImageFolder,fileName).getAbsolutePath());
        b.setIcon(i);
        b.setPreferredSize(new Dimension(width,height));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
        b.setFont(new FontUIResource("微软雅黑",0,fontsize));
    }

    public static void setImageIcon(JButton b,String fileName,String tip){
        ImageIcon i=new ImageIcon(new File(ImageFolder,fileName).getAbsolutePath());
        b.setIcon(i);
        b.setPreferredSize(new Dimension(70,80));
        b.setToolTipText(tip);
        b.setVerticalTextPosition(JButton.BOTTOM);
        b.setHorizontalTextPosition(JButton.CENTER);
        b.setText(tip);
    }

    public static void setColor(Color color,JComponent...cs){
        for(JComponent c:cs){
            c.setForeground(color);
        }
    }


    public static void useLNF(){
        try {

            //苹果风格
            useBeautyEye();

//            useWeblaf();

            //Win10风格
//            UIManager.setLookAndFeel(com.sun.java.swing.plaf.windows.WindowsLookAndFeel.class.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void showPanel(JPanel p,double strechRate){
            GUIUtil.useLNF();
            JFrame f=new JFrame("测试");
            f.setSize(678,550);
            f.setLocationRelativeTo(null);
            CenterPanel cp=new CenterPanel(strechRate);
            f.setContentPane(cp);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
            cp.show(p);
    }

    public static void showPanel(JPanel p){
        showPanel(p,0.8);
    }

    public static boolean checkNumber(JTextField tf, String input) {
        if (checkEmpty(tf, input))
            return false;
        String text = tf.getText().trim();
        int count=0;
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e1) {
            count++;
        }
        try{
            Double.parseDouble(text);
        }catch (NumberFormatException e1){
            count++;
        }
        if(count>1){
            JOptionPane.showMessageDialog(null, input + " 需要是数字");
            tf.grabFocus();
            return false;
        }
        return true;
    }

    public static boolean checkZero(JTextField tf, String input) {
        if (!checkNumber(tf, input))
            return false;
        String text = tf.getText().trim();
        if (0==Double.parseDouble(text)) {
            JOptionPane.showMessageDialog(null, input + " 不能为零");
            tf.grabFocus();
            return true;
        }
        return false;
    }

    public static boolean checkEmpty(JTextField tf, String input) {
        String text = tf.getText().trim();
        if (0 == text.length()) {
            JOptionPane.showMessageDialog(null, input + " 不能为空");
            tf.grabFocus();
            return true;
        }
        return false;
    }

    public static int getInt(JTextField tf) {
        return Integer.parseInt(tf.getText());
    }

    public static double getDouble(JTextField tf){
        return Double.parseDouble(tf.getText());
    }

    public static void setLableCenter(JLabel...labels){
        for(JLabel a:labels){
            a.setHorizontalAlignment(JLabel.CENTER);
        }
    }

    public static void useBeautyEye() {
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        UIManager.put("RootPane.setupButtonVisible",false);
        String[] DEFAULT_FONT = new String[]{
                "Table.font", "TableHeader.font"
                , "CheckBox.font", "Tree.font", "Viewport.font", "ProgressBar.font", "RadioButtonMenuItem.font", "ToolBar.font", "ColorChooser.font", "ToggleButton.font", "Panel.font", "TextArea.font", "Menu.font", "TableHeader.font", "TextField.font"
                , "OptionPane.font", "MenuBar.font", "Button.font", "Label.font", "PasswordField.font", "ScrollPane.font", "MenuItem.font", "ToolTip.font", "List.font", "EditorPane.font", "Table.font", "TabbedPane.font", "RadioButton.font", "CheckBoxMenuItem.font", "TextPane.font", "PopupMenu.font", "TitledBorder.font", "ComboBox.font"
        };
        // 调整默认字体
        for (int i = 0; i < DEFAULT_FONT.length; i++)
            UIManager.put(DEFAULT_FONT[i], new Font("微软雅黑", 0, 14));
    }

    public static void useWeblaf(){
        FontUIResource wryh=new FontUIResource("微软雅黑",0,14);
        WebLookAndFeel.globalControlFont=wryh;
        WebLookAndFeel.globalTextFont=wryh;
        WebLookAndFeel.globalAcceleratorFont=wryh;
        WebLookAndFeel.globalAlertFont=wryh;
        WebLookAndFeel.globalMenuFont=wryh;
        WebLookAndFeel.globalTitleFont=wryh;
        WebLookAndFeel.globalTooltipFont=wryh;
        WebLookAndFeel.install ();
    }

    public static void setFontSize(int type,int size,JComponent...cp){
        for(JComponent c:cp){
            c.setFont(new FontUIResource("微软雅黑",type,size));
        }
    }



}
