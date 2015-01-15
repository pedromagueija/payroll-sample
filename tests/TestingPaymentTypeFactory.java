import payroll.HourlyPayment;
import payroll.SalariedPayment;
import payroll.TimeCardRepository;

/**
 * @author Pedro
 */
public class TestingPaymentTypeFactory {
    public HourlyPayment createHourlyPayment(String employeeId, double rate, TimeCardRepository timeCardRepository) {
        return new HourlyPayment(employeeId, rate, timeCardRepository);
    }

    public SalariedPayment createSalariedPayment(double salary) {
        return new SalariedPayment(salary);
    }
}
