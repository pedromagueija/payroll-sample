package payroll;

import java.util.Date;
import java.util.List;

/**
 * Stores time cards.
 *
 * @author Pedro
 */
public interface TimeCardRepository {
    void add(TimeCard timeCard);

    List<TimeCard> find(String employeeId, Date payDate);
}
