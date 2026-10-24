/**
 * Employee.java
 * Stores all employee details and salary components.
 * Demonstrates: Encapsulation, Constructors, Getters/Setters
 */
public class Employee {

    // \u2500\u2500 Private fields (Encapsulation) \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    private String empId;
    private String name;
    private String department;
    private String designation;
    private String month;
    private String bankAccount;

    // Salary components
    private double basicPay;
    private double specialAllowance;
    private double hra;
    private double da;
    private double ta;
    private double grossSalary;
    private double providentFund;
    private double professionalTax;
    private double totalDeductions;
    private double netSalary;

    // \u2500\u2500 Constructor \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public Employee(String empId, String name, String department,
                    String designation, String month, String bankAccount,
                    double basicPay, double specialAllowance) {
        this.empId           = empId;
        this.name            = name;
        this.department      = department;
        this.designation     = designation;
        this.month           = month;
        this.bankAccount     = bankAccount;
        this.basicPay        = basicPay;
        this.specialAllowance = specialAllowance;
        calculateSalary();
    }

    // \u2500\u2500 Salary Calculation (Business Logic) \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public void calculateSalary() {
        // Earnings
        this.hra          = basicPay * 0.40;   // 40% of Basic
        this.da           = basicPay * 0.10;   // 10% of Basic
        this.ta           = basicPay * 0.05;   // 5%  of Basic
        this.grossSalary  = basicPay + hra + da + ta + specialAllowance;

        // Deductions
        this.providentFund    = basicPay * 0.12;   // 12% PF
        this.professionalTax  = calculateProfessionalTax(basicPay);
        this.totalDeductions  = providentFund + professionalTax;

        // Net Pay
        this.netSalary = grossSalary - totalDeductions;
    }

    // Professional Tax: slab-based calculation
    private double calculateProfessionalTax(double basic) {
        if (basic <= 10000) return 0;
        else if (basic <= 20000) return 150;
        else return 200;
    }

    // \u2500\u2500 Getters \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public String getEmpId()           { return empId; }
    public String getName()            { return name; }
    public String getDepartment()      { return department; }
    public String getDesignation()     { return designation; }
    public String getMonth()           { return month; }
    public String getBankAccount()     { return bankAccount; }
    public double getBasicPay()        { return basicPay; }
    public double getSpecialAllowance(){ return specialAllowance; }
    public double getHra()             { return hra; }
    public double getDa()              { return da; }
    public double getTa()              { return ta; }
    public double getGrossSalary()     { return grossSalary; }
    public double getProvidentFund()   { return providentFund; }
    public double getProfessionalTax() { return professionalTax; }
    public double getTotalDeductions() { return totalDeductions; }
    public double getNetSalary()       { return netSalary; }

    // \u2500\u2500 Setters (with recalculation) \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public void setEmpId(String empId)         { this.empId = empId; }
    public void setName(String name)           { this.name = name; }
    public void setDepartment(String dept)     { this.department = dept; }
    public void setDesignation(String desig)   { this.designation = desig; }
    public void setMonth(String month)         { this.month = month; }
    public void setBankAccount(String bank)    { this.bankAccount = bank; }

    public void setBasicPay(double basicPay) {
        this.basicPay = basicPay;
        calculateSalary();
    }
    public void setSpecialAllowance(double special) {
        this.specialAllowance = special;
        calculateSalary();
    }

    // \u2500\u2500 toString (for debugging) \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    @Override
    public String toString() {
        return String.format("Employee[%s | %s | %s | Net: \u20b9%.0f]",
                empId, name, department, netSalary);
    }
}
