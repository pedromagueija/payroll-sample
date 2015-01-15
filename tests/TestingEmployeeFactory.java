import payroll.Employee;
import payroll.TimeCardRepository;

/**
 * Creates employees instances for testing purposes.
 *
 * @author Pedro
 */
public class TestingEmployeeFactory {

    private static TestingPaymentTypeFactory paymentTypeFactory = new TestingPaymentTypeFactory();

    public static Employee createHourlyEmployee(String employeeId, double rate, TimeCardRepository timeCardRepository) {
        return new Employee(employeeId,
                paymentTypeFactory.createHourlyPayment(employeeId, rate, timeCardRepository)
        );
    }

    public static Employee createSalariedEmployee(String employeeId, double salary) {
        return new Employee(employeeId,
                paymentTypeFactory.createSalariedPayment(salary)
        );
    }

}
