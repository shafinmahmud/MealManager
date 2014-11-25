
package mealmanager;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author SHAFIN
 */
public class DateAndTime {
    
    /**
     *
     * @param m
     * @return
     */
    public String monthName(int m) {
        String[] monthname
                = {
                    "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
                };
        return monthname[m];
    }

    /**
     *
     * @return
     */
    public String currentTime() {
        Calendar cal = new GregorianCalendar();
        String[] dayname
                = {
                    "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"
                };

        int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);

        String time = dayname[day] + " " + hour + ":" + minute + ":" + second;
        return time;
    }
}
