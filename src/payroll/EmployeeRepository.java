package payroll;

/**
 * Stores employees.
 *
 * @author Pedro
 */
public interface EmployeeRepository {
    void add(Employee employee);

    Employee find(String employeeId);

    void delete(String employeeId);

    boolean exists(String employeeId);

    Employee[] all();
}
