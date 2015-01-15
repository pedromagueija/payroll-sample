package payroll;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Hourly payment type.
 *
 * @author Pedro
 */
public class HourlyPayment implements PaymentType {
    private final double rate;
    private final String employeeId;
    private final TimeCardRepository timeCardRepository;

    public HourlyPayment(String employeeId, double rate, TimeCardRepository timeCardRepository) {
        this.employeeId = employeeId;
        this.rate = rate;
        this.timeCardRepository = timeCardRepository;
    }

    @Override
    public boolean isPayDate(Date payDate) {
        return isFriday(payDate);
    }

    @Override
    public double calculatePay(Date payDate) {
        List<TimeCard> employeeTimeCards = timeCardRepository.find(employeeId, payDate);
        double pay = 0;

        for (TimeCard timeCard : employeeTimeCards) {
            pay += timeCard.hours() * rate + timeCard.overtime() * (rate * 1.5);
        }

        return pay;
    }

    private boolean isFriday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
    }
}
