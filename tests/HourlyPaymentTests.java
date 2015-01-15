import org.junit.Before;
import org.junit.Test;
import payroll.HourlyPayment;
import payroll.TimeCard;
import payroll.TimeCardRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * @author Pedro
 */
public class HourlyPaymentTests {

    private Date payDate;
    private TimeCardRepository timeCardRepository;
    private HourlyPayment hourlyPayment;
    private String employeeId;

    @Before
    public void setUp() throws Exception {
        payDate = onDate(2015, 1, 9);
        timeCardRepository = new RuntimeTimeCardStore();
        employeeId = "1";
        hourlyPayment = new HourlyPayment(employeeId, 10.0, timeCardRepository);
    }

    @Test
    public void shouldCalculatePayRegularHours() {
        TimeCard mondayTimeCard = new TimeCard(employeeId, onDate(2015, 1, 5), 8.0);
        TimeCard tuesdayTimeCard = new TimeCard(employeeId, onDate(2015, 1, 6), 8.0);

        timeCardRepository.add(mondayTimeCard);
        timeCardRepository.add(tuesdayTimeCard);

        double amount = hourlyPayment.calculatePay(payDate);

        assertEquals(160.0, amount, 0.001);
    }

    @Test
    public void shouldCalculatePayOvertime() {
        TimeCard mondayTimeCard = new TimeCard(employeeId, onDate(2015, 1, 5), 10.0);
        TimeCard tuesdayTimeCard = new TimeCard(employeeId, onDate(2015, 1, 6), 10.0);

        timeCardRepository.add(mondayTimeCard);
        timeCardRepository.add(tuesdayTimeCard);

        double amount = hourlyPayment.calculatePay(payDate);

        assertEquals(220.0, amount, 0.001);
    }

    private Date onDate(int y, int m, int d) {
        Calendar calendar = new GregorianCalendar(y, m - 1, d);
        return calendar.getTime();
    }
}
