package util;

import java.awt.*;

public class ColorUtil {
    public static Color blueColor= Color.decode("#3399FF");
    public static Color grayColor=Color.decode("#999999");
    public static Color backgroundColor=Color.decode("#eeeeee");
    public static Color warningColor=Color.decode("#FF3333");

    public static Color getByPercentage(int per){
        if(per>100)
            per=100;
        int r=51,g=255,b=51;
        double rate=(double)per/100;
        r=(int)(51+rate*(255-51));
        g=255-r+51;
        Color color=new Color(r,g,b);
        return color;
    }
}
