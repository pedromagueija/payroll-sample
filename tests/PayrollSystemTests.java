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
    private Employee hourlyEmployee;
    private TimeCard timeCard;
    private double salary;
    private double rate;

    @Before
    public void setUp() throws Exception {
        salary = 1000.0;
        rate = 10.0;

        salariedEmployee = TestingEmployeeFactory.createSalariedEmployee("1", salary);
        hourlyEmployee = TestingEmployeeFactory.createHourlyEmployee("2", rate);
        timeCard = new TimeCard(hourlyEmployee.id(), DateFactory.create(2015, 1, 5), 8.0);

        RuntimeEmployeeStore employeeRepository = new RuntimeEmployeeStore();
        RuntimePaycheckStore paycheckRepository = new RuntimePaycheckStore();
        payrollSystem = new PayrollSystem(employeeRepository, paycheckRepository);

        payrollSystem.addEmployee(salariedEmployee);
        payrollSystem.addEmployee(hourlyEmployee);
    }

    @Test
    public void shouldAddEmployee() {
        Employee storedEmployee = payrollSystem.findEmployee(salariedEmployee.id());

        assertEquals(salariedEmployee, storedEmployee);
    }

    @Test
    public void shouldDeleteEmployee() {
        payrollSystem.deleteEmployee(salariedEmployee.id());

        assertFalse(payrollSystem.employeeExists(salariedEmployee.id()));
    }

    @Test
    public void shouldPayHourlyEmployeeOnFriday() {
        Date friday = DateFactory.create(2015, 1, 9);
        double amount = rate * timeCard.hours();

        payrollSystem.addTimeCard(timeCard);

        payrollSystem.pay(friday);

        Paycheck paycheck = payrollSystem.findPaycheckFor(hourlyEmployee.id(), friday);

        checkIsValidPaycheck(friday, amount, paycheck);
    }

    @Test
    public void shouldNotPayHourlyEmployeeOnOtherThanFriday() {
        Date friday = DateFactory.create(2015, 1, 5);

        payrollSystem.addTimeCard(timeCard);

        payrollSystem.pay(friday);

        Paycheck paycheck = payrollSystem.findPaycheckFor(hourlyEmployee.id(), friday);

        assertEquals("Paycheck should be empty.", paycheck, Paycheck.Empty());
    }

    @Test
    public void shouldPaySalariedEmployee() {
        Date lastDayMonth = DateFactory.create(2015, 1, 31);

        payrollSystem.pay(lastDayMonth);

        Paycheck paycheck = payrollSystem.findPaycheckFor(salariedEmployee.id(), lastDayMonth);

        checkIsValidPaycheck(lastDayMonth, salary, paycheck);
    }

    @Test
    public void shouldNotPaySalariedEmployeeIfNotEndOfMonth() {
        Date notEndOfMonth = DateFactory.create(2015, 1, 15);

        payrollSystem.pay(notEndOfMonth);

        Paycheck paycheck = payrollSystem.findPaycheckFor(salariedEmployee.id(), notEndOfMonth);

        assertEquals("Paycheck should be empty.", paycheck, Paycheck.Empty());
    }

    private void checkIsValidPaycheck(Date payDate, double amount, Paycheck paycheck) {
        assertNotEquals("Paycheck is empty.", paycheck, Paycheck.Empty());
        assertEquals("Paycheck date is wrong.", payDate, paycheck.date());
        assertEquals("Pay amount is wrong.", amount, paycheck.amount(), 0.001);
    }
}
