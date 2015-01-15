package payroll;

import java.util.ArrayList;
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
    private List<TimeCard> timeCards;

    public HourlyPayment(double rate) {
        this.rate = rate;
        this.timeCards = new ArrayList<TimeCard>();
    }

    @Override
    public boolean isPayDate(Date payDate) {
        return isFriday(payDate);
    }

    @Override
    public double calculatePay(Date payDate) {
        double pay = 0;

        for (TimeCard timeCard : timeCards) {
            if (isPayWeek(payDate, timeCard))
                pay += hoursPay(timeCard) + overtimePay(timeCard);
        }

        return pay;
    }

    private boolean isPayWeek(Date payDate, TimeCard timeCard) {
        int payWeek = getWeek(payDate);
        int timeCardWeek = getWeek(timeCard.date());
        return payWeek == timeCardWeek;
    }

    private int getWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    private double overtimePay(TimeCard timeCard) {
        return timeCard.overtime() * (rate * 1.5);
    }

    private double hoursPay(TimeCard timeCard) {
        return timeCard.hours() * rate;
    }

    private boolean isFriday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        return cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
    }

    public void addTimeCard(TimeCard timeCard) {
        this.timeCards.add(timeCard);
    }
}
