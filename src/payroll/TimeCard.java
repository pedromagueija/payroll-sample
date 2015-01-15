package payroll;

import java.util.Date;

/**
 * Represents a employee time card.
 *
 * @author Pedro
 */
public class TimeCard {
    private String employeeId;
    private Date date;
    private double hours;

    public TimeCard(String employeeId, Date date, double hours) {
        this.employeeId = employeeId;
        this.date = date;
        this.hours = hours;
    }

    public String employeeId() {
        return employeeId;
    }

    public Date date() {
        return date;
    }

    public double hours() {
        return hours > 8 ? 8 : hours;
    }

    public double overtime() {
        double overtime = hours - 8;
        return overtime > 0 ? overtime : 0;
    }
}
