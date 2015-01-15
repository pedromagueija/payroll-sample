package payroll;

import java.util.Date;

/**
 * Represents an employee.
 *
 * @author Pedro
 */
public class Employee {
    private final PaymentType payment;
    private String id;

    public Employee(String employeeId, PaymentType paymentType) {
        this.id = employeeId;
        this.payment = paymentType;
    }

    public String getId() {
        return id;
    }

    public boolean isPayDate(Date payDate) {
        return this.payment.isPayDate(payDate);
    }

    public double calculatePay(Date payDate) {
        return this.payment.calculatePay(payDate);
    }
}
