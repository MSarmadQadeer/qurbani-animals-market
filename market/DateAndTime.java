package market;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface DateAndTime {
    public static String getCurrentDate() {
        SimpleDateFormat simple = new SimpleDateFormat("MMM dd, yyyy");
        return simple.format(new Date());
    }

    public static String getCurrentTime() {
        SimpleDateFormat simple = new SimpleDateFormat("hh:mm a");
        return simple.format(new Date());
    }
}
