import payroll.Employee;
import payroll.MonthlyPaymentSchedule;
import payroll.WeeklyPaymentSchedule;

/**
 * Creates employees instances for testing purposes.
 *
 * @author Pedro
 */
class TestingEmployeeFactory {

    private static TestingPaymentTypeFactory paymentTypeFactory = new TestingPaymentTypeFactory();

    public static Employee createHourlyEmployee(String employeeId, double rate) {
        return new Employee(employeeId,
                paymentTypeFactory.createHourlyPayment(rate),
                new WeeklyPaymentSchedule()
        );
    }

    public static Employee createSalariedEmployee(String employeeId, double salary) {
        return new Employee(employeeId,
                paymentTypeFactory.createSalariedPayment(salary),
                new MonthlyPaymentSchedule()
        );
    }

}
