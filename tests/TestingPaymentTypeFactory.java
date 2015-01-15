import payroll.HourlyPayment;
import payroll.SalariedPayment;

/**
 * @author Pedro
 */
class TestingPaymentTypeFactory {
    public HourlyPayment createHourlyPayment(double rate) {
        return new HourlyPayment(rate);
    }

    public SalariedPayment createSalariedPayment(double salary) {
        return new SalariedPayment(salary);
    }
}
