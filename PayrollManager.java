import java.util.ArrayList;
import java.util.List;

/**
 * PayrollManager.java
 * Manages a list of employees. Handles add, update, delete, search.
 * Demonstrates: ArrayList, Methods, Loops
 */
public class PayrollManager {

    private ArrayList<Employee> employees;

    // \u2500\u2500 Constructor \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public PayrollManager() {
        this.employees = new ArrayList<>();
    }

    // \u2500\u2500 Add Employee \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    // \u2500\u2500 Update Employee \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public void updateEmployee(int index, Employee emp) {
        if (index >= 0 && index < employees.size()) {
            employees.set(index, emp);
        }
    }

    // \u2500\u2500 Delete Employee \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public void deleteEmployee(int index) {
        if (index >= 0 && index < employees.size()) {
            employees.remove(index);
        }
    }

    // \u2500\u2500 Get Employee \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public Employee getEmployee(int index) {
        if (index >= 0 && index < employees.size()) {
            return employees.get(index);
        }
        return null;
    }

    // \u2500\u2500 Get All \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public List<Employee> getAllEmployees() {
        return employees;
    }

    // \u2500\u2500 Count \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public int getCount() {
        return employees.size();
    }

    // \u2500\u2500 Search by Name or ID \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public int findByName(String name) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equalsIgnoreCase(name)) return i;
        }
        return -1;
    }

    // \u2500\u2500 Aggregate Stats \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public double getTotalGross() {
        double total = 0;
        for (Employee e : employees) total += e.getGrossSalary();
        return total;
    }

    public double getTotalDeductions() {
        double total = 0;
        for (Employee e : employees) total += e.getTotalDeductions();
        return total;
    }

    public double getTotalNet() {
        double total = 0;
        for (Employee e : employees) total += e.getNetSalary();
        return total;
    }

    public double getHighestSalary() {
        if (employees.isEmpty()) return 0;
        double max = employees.get(0).getNetSalary();
        for (Employee e : employees) if (e.getNetSalary() > max) max = e.getNetSalary();
        return max;
    }

    public double getLowestSalary() {
        if (employees.isEmpty()) return 0;
        double min = employees.get(0).getNetSalary();
        for (Employee e : employees) if (e.getNetSalary() < min) min = e.getNetSalary();
        return min;
    }

    public double getAverageSalary() {
        if (employees.isEmpty()) return 0;
        return getTotalNet() / employees.size();
    }
}
