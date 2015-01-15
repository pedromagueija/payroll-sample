import payroll.TimeCard;
import payroll.TimeCardRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Runtime time cards store (for testing purposes).
 *
 * @author Pedro
 */
public class RuntimeTimeCardStore implements TimeCardRepository {
    List<TimeCard> store = new ArrayList<TimeCard>(10);

    @Override
    public void add(TimeCard timeCard) {
        store.add(timeCard);
    }

    @Override
    public List<TimeCard> find(String employeeId, Date payDate) {
        List<TimeCard> employeeTimeCards = new ArrayList<TimeCard>();

        for (TimeCard timeCard : store) {
            if (timeCardBelongsTo(employeeId, timeCard) && isPayMonthTimeCard(payDate, timeCard)) {
                employeeTimeCards.add(timeCard);
            }
        }

        return employeeTimeCards;
    }

    private boolean isPayMonthTimeCard(Date payDate, TimeCard timeCard) {
        int payMonth = getMonth(payDate);
        int timeCardMonth = getMonth(timeCard.date());

        return payMonth == timeCardMonth;
    }

    private boolean timeCardBelongsTo(String employeeId, TimeCard timeCard) {
        return timeCard.employeeId().equals(employeeId);
    }

    private int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }
}
