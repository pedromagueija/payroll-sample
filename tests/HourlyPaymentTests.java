import org.junit.Before;
import org.junit.Test;
import payroll.HourlyPayment;
import payroll.TimeCard;

import java.util.Date;

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
        payDate = DateFactory.create(2015, 1, 9);
        employeeId = "1";
        hourlyPayment = new HourlyPayment(10.0);
    }

    @Test
    public void shouldCalculatePayRegularHours() {
        TimeCard mondayTimeCard = new TimeCard(employeeId, DateFactory.create(2015, 1, 5), 8.0);
        TimeCard tuesdayTimeCard = new TimeCard(employeeId, DateFactory.create(2015, 1, 6), 8.0);

        hourlyPayment.addTimeCard(mondayTimeCard);
        hourlyPayment.addTimeCard(tuesdayTimeCard);
        double amount = hourlyPayment.calculatePay(payDate);

        assertEquals(160.0, amount, 0.001);
    }

    @Test
    public void shouldCalculatePayOvertime() {
        TimeCard mondayTimeCard = new TimeCard(employeeId, DateFactory.create(2015, 1, 5), 10.0);
        TimeCard tuesdayTimeCard = new TimeCard(employeeId, DateFactory.create(2015, 1, 6), 10.0);

        hourlyPayment.addTimeCard(mondayTimeCard);
        hourlyPayment.addTimeCard(tuesdayTimeCard);

        double amount = hourlyPayment.calculatePay(payDate);

        assertEquals(220.0, amount, 0.001);
    }

    @Test
    public void shouldIncludeOnlyTimeCardsOfTheWeek() {
        TimeCard mondayTimeCard = new TimeCard(employeeId, DateFactory.create(2015, 1, 5), 8.0);
        TimeCard nextWeekMondayTimeCard = new TimeCard(employeeId, DateFactory.create(2015, 1, 12), 8.0);

        hourlyPayment.addTimeCard(mondayTimeCard);
        hourlyPayment.addTimeCard(nextWeekMondayTimeCard);

        double amount = hourlyPayment.calculatePay(payDate);

        assertEquals(80.0, amount, 0.001);
    }
}
