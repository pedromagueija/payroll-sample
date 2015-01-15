import org.junit.Before;
import org.junit.Test;
import payroll.HourlyPayment;
import payroll.TimeCard;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * @author Pedro
 */
public class HourlyPaymentTests {

    private Date payDate;
    private HourlyPayment hourlyPayment;
    private String employeeId;

    @Before
    public void setUp() throws Exception {
        payDate = onDate(2015, 1, 9);
        employeeId = "1";
        hourlyPayment = new HourlyPayment(10.0);
    }

    @Test
    public void shouldCalculatePayRegularHours() {
        TimeCard mondayTimeCard = new TimeCard(employeeId, onDate(2015, 1, 5), 8.0);
        TimeCard tuesdayTimeCard = new TimeCard(employeeId, onDate(2015, 1, 6), 8.0);

        hourlyPayment.addTimeCard(mondayTimeCard);
        hourlyPayment.addTimeCard(tuesdayTimeCard);
        double amount = hourlyPayment.calculatePay(payDate);

        assertEquals(160.0, amount, 0.001);
    }

    @Test
    public void shouldCalculatePayOvertime() {
        TimeCard mondayTimeCard = new TimeCard(employeeId, onDate(2015, 1, 5), 10.0);
        TimeCard tuesdayTimeCard = new TimeCard(employeeId, onDate(2015, 1, 6), 10.0);

        hourlyPayment.addTimeCard(mondayTimeCard);
        hourlyPayment.addTimeCard(tuesdayTimeCard);

        double amount = hourlyPayment.calculatePay(payDate);

        assertEquals(220.0, amount, 0.001);
    }

    @Test
    public void shouldCalculatePayOfTheWeek() {
        TimeCard mondayTimeCard = new TimeCard(employeeId, onDate(2015, 1, 5), 8.0);
        TimeCard nextWeekMondayTimeCard = new TimeCard(employeeId, onDate(2015, 1, 12), 8.0);

        hourlyPayment.addTimeCard(mondayTimeCard);
        hourlyPayment.addTimeCard(nextWeekMondayTimeCard);

        double amount = hourlyPayment.calculatePay(payDate);

        assertEquals(80.0, amount, 0.001);
    }

    private Date onDate(int y, int m, int d) {
        Calendar calendar = new GregorianCalendar(y, m - 1, d);
        return calendar.getTime();
    }
}
