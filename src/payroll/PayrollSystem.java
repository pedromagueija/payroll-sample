package payroll;

import java.util.Date;

/**
 * Represents the payroll system.
 *
 * @author Pedro
 */
public class PayrollSystem {

    private EmployeeRepository employeeRepository;
    private TimeCardRepository timeCardRepository;
    private PaycheckRepository paycheckRepository;


    public PayrollSystem(EmployeeRepository employeeRepository,
                         TimeCardRepository timeCardRepository,
                         PaycheckRepository paycheckRepository) {
        this.employeeRepository = employeeRepository;
        this.timeCardRepository = timeCardRepository;
        this.paycheckRepository = paycheckRepository;
    }

    public void addEmployee(Employee employee) {
        employeeRepository.add(employee);
    }

    public Employee findEmployee(String employeeId) {
        return employeeRepository.find(employeeId);
    }

    public void deleteEmployee(String employeeId) {
        employeeRepository.delete(employeeId);
    }

    public boolean employeeExists(String employeeId) {
        return employeeRepository.exists(employeeId);
    }

    public void pay(Date date) {
        for (Employee employee : this.employeeRepository.all()) {
            if (employee.isPayDate(date)) {
                addPaycheck(date, employee);
            }
        }
    }

    private void addPaycheck(Date date, Employee employee) {
        double amount = employee.calculatePay(date);
        Paycheck paycheck = new Paycheck(employee.getId(), date, amount);

        paycheckRepository.add(paycheck);
    }

    public Paycheck findPaycheckFor(String employeeId, Date date) {
        return paycheckRepository.find(employeeId, date);
    }

    public void addTimeCard(TimeCard timeCard) {
        timeCardRepository.add(timeCard);
    }
}
