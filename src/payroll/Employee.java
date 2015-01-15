package payroll;

import java.util.Date;

/**
 * Represents an employee.
 *
 * @author Pedro
 */
public class Employee {
    private final PaymentType paymentType;
    private final PaymentSchedule paymentSchedule;
    private String id;

    public Employee(String employeeId, PaymentType paymentType, PaymentSchedule paymentSchedule) {
        this.id = employeeId;
        this.paymentType = paymentType;
        this.paymentSchedule = paymentSchedule;
    }

    public String id() {
        return id;
    }

    public boolean isPayDate(Date payDate) {
        return this.paymentSchedule.isPayDate(payDate);
    }

    public double calculatePay(Date payDate) {
        return this.paymentType.calculatePay(payDate);
    }

    public PaymentType paymentType() {
        return paymentType;
    }
}
