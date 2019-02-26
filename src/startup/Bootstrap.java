package startup;
 
import javax.swing.*;

import gui.frame.MainFrame;
import gui.panel.MainPanel;
import gui.panel.SpendPanel;
import jdk.nashorn.internal.scripts.JO;
import util.DBUtil;
import util.GUIUtil;

import java.awt.*;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static java.awt.SystemColor.desktop;


public class Bootstrap {
    public static void main(String[] args) throws Exception{
        GUIUtil.useLNF();
//        if(!DBUtil.mySQLExist()){
//            if(JOptionPane.OK_OPTION==JOptionPane.showConfirmDialog(null,"无法连接本地数据库，请安装MySQL数据库，并设置账号为'root'，密码为'admin'。\r\n点击确认下载安装MySQL"))
//            {
//                Desktop openURL=Desktop.getDesktop();
//                openURL.browse(new URI("http://how2j.cn/k/mysql/mysql-install/377.html?p=64911"));
//            }
//            else
//                return;
//            return;
//        }
//        if(!DBUtil.hutubiliExist()){
//            if(JOptionPane.OK_OPTION==JOptionPane.showConfirmDialog(null,"成功连接本地数据库，但是没有检测到hutubill数据库，点击确认将会自动配置hutubill数据表，配置完成后方可运行")){
//                DBUtil.creatHutubill();
//                DBUtil.creatTable();
//            }else
//                return;
//            return;
//        }

        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                MainFrame.instance.setVisible(true);
                MainPanel.instance.workingPanel.show(SpendPanel.instance);

            }
        });
    }
}