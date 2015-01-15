package payroll;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Pedro
 */
public class WeeklyPaymentSchedule implements PaymentSchedule {
    @Override
    public boolean isPayDate(Date payDate) {
        return isFriday(payDate);
    }

    private boolean isFriday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
    }

}
