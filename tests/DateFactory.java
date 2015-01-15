import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Pedro
 */
public class DateFactory {
    public static Date create(int year, int month, int day) {
        Calendar calendar = new GregorianCalendar(year, month - 1, day);
        return calendar.getTime();
    }
}
