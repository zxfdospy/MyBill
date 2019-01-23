package util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ToolUtil {


    //List去重
    public static void removeDuplicate(List list) {
        List result = new ArrayList(list.size());
        for (Object str : list) {
            if (!result.contains(str)) {
                result.add(str);
            }
        }
        list.clear();
        list.addAll(result);
    }

    //  double保留两位小数，保存为String，用于显示
    public static String formatDouble(double d) {
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(d);
    }





}
