package payroll;

import java.util.Date;

/**
 * Stores paychecks.
 *
 * @author Pedro
 */
public interface PaycheckRepository {
    Paycheck find(String employeeId, Date date);

    void add(Paycheck paycheck);
}
