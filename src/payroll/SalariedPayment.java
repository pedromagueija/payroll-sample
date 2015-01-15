package payroll;

import java.util.Calendar;
import java.util.Date;

/**
 * Salary payment type.
 *
 * @author Pedro
 */
public class SalariedPayment implements PaymentType {
    private final double salary;

    public SalariedPayment(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean isPayDate(Date payDate) {
        return isLastDayOfMonth(payDate);
    }

    @Override
    public double calculatePay(Date payDate) {
        return salary;
    }

    private boolean isLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        return cal.get(Calendar.DAY_OF_MONTH) == lastDay;
    }
}
