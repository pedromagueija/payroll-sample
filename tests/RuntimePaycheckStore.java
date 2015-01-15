import payroll.Paycheck;
import payroll.PaycheckRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Runtime paycheck store (for testing purposes).
 *
 * @author Pedro
 */
public class RuntimePaycheckStore implements PaycheckRepository {
    private List<Paycheck> store = new ArrayList<Paycheck>(10);

    @Override
    public Paycheck find(String employeeId, Date date) {
        for (Paycheck paycheck : store) {
            if (paycheck.employeeId().equals(employeeId) && paycheck.date() == date) {
                return paycheck;
            }
        }

        return Paycheck.Empty();
    }

    @Override
    public void add(Paycheck paycheck) {
        this.store.add(paycheck);
    }
}
