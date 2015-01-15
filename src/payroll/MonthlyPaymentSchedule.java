package payroll;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Pedro
 */
public class MonthlyPaymentSchedule implements PaymentSchedule {
    @Override
    public boolean isPayDate(Date payDate) {
        return isLastDayMonth(payDate);
    }

    private boolean isLastDayMonth(Date payDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(payDate);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        return cal.get(Calendar.DAY_OF_MONTH) == lastDay;
    }
}
