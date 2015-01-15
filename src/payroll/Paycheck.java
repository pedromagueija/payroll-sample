package payroll;

import java.util.Date;

/**
 * Represents a paycheck of an employee.
 *
 * @author Pedro
 */
public class Paycheck {
    private static Paycheck empty = new Paycheck("", new Date(Long.MIN_VALUE), 0);
    private final String employeeId;
    private final Date date;
    private final double amount;

    public Paycheck(String employeeId, Date date, double amount) {
        this.employeeId = employeeId;
        this.date = date;
        this.amount = amount;
    }

    public static Paycheck Empty() {
        return empty;
    }

    public String employeeId() {
        return employeeId;
    }

    public Date date() {
        return date;
    }

    public double amount() {
        return amount;
    }
}
