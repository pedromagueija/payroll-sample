import payroll.Employee;
import payroll.EmployeeRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Runtime employee store (for testing purposes).
 *
 * @author Pedro
 */
public class RuntimeEmployeeStore implements EmployeeRepository {
    private Map<String, Employee> store;

    public RuntimeEmployeeStore() {
        this.store = new HashMap<String, Employee>(10);
    }

    @Override
    public void add(Employee employee) {
        store.put(employee.getId(), employee);
    }

    @Override
    public Employee find(String employeeId) {
        return store.get(employeeId);
    }

    @Override
    public void delete(String employeeId) {
        store.remove(employeeId);
    }

    @Override
    public boolean exists(String employeeId) {
        return store.containsKey(employeeId);
    }

    @Override
    public Employee[] all() {
        return store.values().toArray(new Employee[store.values().size()]);
    }
}
