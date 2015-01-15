import org.junit.Before;
import org.junit.Test;
import payroll.Employee;
import payroll.Paycheck;
import payroll.PayrollSystem;
import payroll.TimeCard;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Test the behavior of the payroll system.
 *
 * @author Pedro
 */
public class PayrollSystemTests {

    private Employee salariedEmployee;
    private PayrollSystem payrollSystem;
    private String employeeId;
    private Employee hourlyEmployee;
    private TimeCard timeCard;
    private double salary;
    private double rate;

    @Before
    public void setUp() throws Exception {
        RuntimeEmployeeStore employeeRepository = new RuntimeEmployeeStore();
        RuntimePaycheckStore paycheckRepository = new RuntimePaycheckStore();

        employeeId = "100";
        salary = 1000.0;
        rate = 10.0;

        salariedEmployee = TestingEmployeeFactory.createSalariedEmployee(employeeId, salary);
        payrollSystem = new PayrollSystem(employeeRepository, paycheckRepository);
        hourlyEmployee = TestingEmployeeFactory.createHourlyEmployee(employeeId, rate);
        timeCard = new TimeCard(employeeId, DateFactory.create(2015, 1, 5), 8.0);
    }

    @Test
    public void shouldAddEmployee() {
        payrollSystem.addEmployee(salariedEmployee);
        Employee storedEmployee = payrollSystem.findEmployee(employeeId);

        assertEquals(salariedEmployee, storedEmployee);
    }

    @Test
    public void shouldDeleteEmployee() {
        payrollSystem.addEmployee(salariedEmployee);
        payrollSystem.deleteEmployee(employeeId);

        assertFalse(payrollSystem.employeeExists(employeeId));
    }

    @Test
    public void shouldPayHourlyEmployee() {
        Date friday = DateFactory.create(2015, 1, 9);
        double amount = rate * timeCard.hours();

        payrollSystem.addEmployee(hourlyEmployee);

        payrollSystem.addTimeCard(timeCard);

        payrollSystem.pay(friday);

        Paycheck paycheck = payrollSystem.findPaycheckFor(employeeId, friday);

        checkIsValidPaycheck(friday, amount, paycheck);
    }

    @Test
    public void shouldPaySalariedEmployee() {
        Date lastDayMonth = DateFactory.create(2015, 1, 31);

        payrollSystem.addEmployee(salariedEmployee);

        payrollSystem.pay(lastDayMonth);

        Paycheck paycheck = payrollSystem.findPaycheckFor(employeeId, lastDayMonth);

        checkIsValidPaycheck(lastDayMonth, salary, paycheck);
    }

    @Test
    public void shouldNotPaySalariedEmployeeIfNotEndOfMonth() {
        Date notEndOfMonth = DateFactory.create(2015, 1, 15);

        payrollSystem.addEmployee(salariedEmployee);

        payrollSystem.pay(notEndOfMonth);

        Paycheck paycheck = payrollSystem.findPaycheckFor(employeeId, notEndOfMonth);

        assertEquals("Paycheck should be empty.", paycheck, Paycheck.Empty());
    }

    private void checkIsValidPaycheck(Date payDate, double amount, Paycheck paycheck) {
        assertNotEquals("Paycheck is empty.", paycheck, Paycheck.Empty());
        assertEquals("Paycheck date is wrong.", payDate, paycheck.date());
        assertEquals("Pay amount is wrong.", amount, paycheck.amount(), 0.001);
    }
}
