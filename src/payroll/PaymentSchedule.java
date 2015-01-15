package payroll;

import java.util.Date;

/**
 * @author Pedro
 */
public interface PaymentSchedule {
    boolean isPayDate(Date payDate);
}
