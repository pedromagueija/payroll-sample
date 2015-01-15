package payroll;

import java.util.Date;

/**
 * Represents the payment type of an employee.
 *
 * @author Pedro
 */
public interface PaymentType {
    boolean isPayDate(Date payDate);

    double calculatePay(Date payDate);
}
