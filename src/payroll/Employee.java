package payroll;

import java.util.Date;

/**
 * Represents an employee.
 *
 * @author Pedro
 */
public class Employee {
    private final PaymentType paymentType;
    private String id;

    public Employee(String employeeId, PaymentType paymentType) {
        this.id = employeeId;
        this.paymentType = paymentType;
    }

    public String getId() {
        return id;
    }

    public boolean isPayDate(Date payDate) {
        return this.paymentType.isPayDate(payDate);
    }

    public double calculatePay(Date payDate) {
        return this.paymentType.calculatePay(payDate);
    }

    public PaymentType paymentType() {
        return paymentType;
    }
}
