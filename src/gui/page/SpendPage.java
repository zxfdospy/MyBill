package gui.page;

import util.ToolUtil;

public class SpendPage {

    public String thisMonthSpend;
    public String todaySpend;
    public String avgSpendPerDay;
    public String monthAvailable;
    public String dayAvgAvailable;
    public String monthLeftDay;

    public int usagePercentage;

    public boolean isOverSpend=false;

    public SpendPage(double thisMonthSpend, double todaySpend, double avgSpendPerDay, double monthAvailable, double dayAvgAvailable,
                     int monthLeftDay, int usagePercentage) {
        this.thisMonthSpend = "¥" + ToolUtil.formatDouble(thisMonthSpend);
        this.todaySpend = "¥" + ToolUtil.formatDouble(todaySpend);
        this.avgSpendPerDay = "¥" + ToolUtil.formatDouble(avgSpendPerDay);
        if (monthAvailable < 0)
            isOverSpend = true;

        if (!isOverSpend) {
            this.monthAvailable = "¥" + ToolUtil.formatDouble(monthAvailable);
            this.dayAvgAvailable = "¥" + ToolUtil.formatDouble(dayAvgAvailable);
        } else {
            this.monthAvailable = "超支" + ToolUtil.formatDouble((0 - monthAvailable));
            this.dayAvgAvailable = "¥0";
        }

        this.monthLeftDay = monthLeftDay + "天";
        this.usagePercentage = usagePercentage;
    }

}
