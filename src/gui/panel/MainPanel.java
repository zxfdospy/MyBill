package gui.panel;

import gui.listener.ToolBarListener;
import util.CenterPanel;
import util.GUIUtil;


import javax.swing.*;

import java.awt.*;

import static com.sun.javafx.fxml.expression.Expression.add;

public class MainPanel extends JPanel{
    static {
        GUIUtil.useLNF();
    }
    public static MainPanel instance=new MainPanel();

    public JToolBar tb=new JToolBar();
    public JButton bSpend=new JButton();
    public JButton bRecord=new JButton();
    public JButton bCategoryPreview=new JButton();
    public JButton bExpenseRecord=new JButton();
    public JButton bReport=new JButton();
    public JButton bConfig=new JButton();
    public JButton bBackup=new JButton();
    public JButton bRecover=new JButton();

    public CenterPanel workingPanel;

    private MainPanel(){
        GUIUtil.setImageIcon(bSpend,"home.png","消费一览",12,80,80);
        GUIUtil.setImageIcon(bRecord,"record.png","记一笔",12,80,80);
        GUIUtil.setImageIcon(bCategoryPreview,"category2.png","分类预览",12,80,80);
        GUIUtil.setImageIcon(bExpenseRecord,"category1.png","消费记录",12,80,80);
        GUIUtil.setImageIcon(bReport,"report.png","月度报表",12,80,80);
        GUIUtil.setImageIcon(bConfig,"config.png","设置",12,80,80);
        GUIUtil.setImageIcon(bBackup,"backup.png","备份记录",12,80,80);
        GUIUtil.setImageIcon(bRecover,"restore.png","恢复记录",12,80,80);

        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bCategoryPreview);
        tb.add(bExpenseRecord);
        tb.add(bReport);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);
        tb.setFloatable(false);

        workingPanel=new CenterPanel(0.95);
//        workingPanel.setPreferredSize(new Dimension(610,400));

        this.setLayout(new BorderLayout());
        add(tb, BorderLayout.NORTH);
        add(workingPanel,BorderLayout.CENTER);

        addlistener();
    }

    private  void addlistener(){
        ToolBarListener toolBarListener=new ToolBarListener();
        bSpend.addActionListener(toolBarListener);
        bRecord.addActionListener(toolBarListener);
        bCategoryPreview.addActionListener(toolBarListener);
        bExpenseRecord.addActionListener(toolBarListener);
        bReport.addActionListener(toolBarListener);
        bConfig.addActionListener(toolBarListener);
        bBackup.addActionListener(toolBarListener);
        bRecover.addActionListener(toolBarListener);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(MainPanel.instance,1);
    }
}
