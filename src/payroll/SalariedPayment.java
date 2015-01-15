package payroll;

import java.util.Date;

/**
 * Salary payment type.
 *
 * @author Pedro
 */
public class SalariedPayment implements PaymentType {
    private final double salary;

    public SalariedPayment(double salary) {
        this.salary = salary;
    }

    @Override
    public double calculatePay(Date payDate) {
        return salary;
    }
}
