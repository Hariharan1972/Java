============================================================
  SALARY PAYROLL PROCESSING & PAYSLIP GENERATION SYSTEM
  Java Swing Desktop Application
============================================================

HOW TO RUN
──────────────────────────────────────────────────────────

STEP 1: Make sure Java JDK is installed.
  Check: Open Command Prompt / Terminal and type:
         java -version
         javac -version
  Both should show version 8 or above.
  Download JDK from: https://www.oracle.com/java/technologies/downloads/

STEP 2: Open Command Prompt / Terminal.
  Navigate to the folder containing all .java files:
  Example:  cd C:\Users\You\PayrollSystem\src

STEP 3: Compile all Java files:
  javac *.java

STEP 4: Run the application:
  java PayrollApp

QUICK OPTION (from the PayrollSystem folder, not src):
  Windows : double-click run.bat
  Mac/Linux: ./run.sh

──────────────────────────────────────────────────────────

FILES IN THIS PROJECT
──────────────────────────────────────────────────────────

  Employee.java         - Employee data model (OOP class)
  PayrollManager.java   - Manages list of employees (ArrayList)
  PayslipPrinter.java   - Generates payslip text and HTML
  PayrollApp.java       - Main GUI (Java Swing)

──────────────────────────────────────────────────────────

FEATURES
──────────────────────────────────────────────────────────

  ✅ Add, Edit, Delete employees
  ✅ Live salary calculation as you type:
       HRA  = 40% of Basic Pay
       DA   = 10% of Basic Pay
       TA   =  5% of Basic Pay
       PF   = 12% of Basic Pay
       PT   = Slab-based (₹0 / ₹150 / ₹200)
  ✅ View all employees in a table
  ✅ Preview payslip with professional layout
  ✅ Print payslip directly from the application
  ✅ Summary statistics (Total Gross, Deductions, Net)
  ✅ 3 sample employees loaded on startup

──────────────────────────────────────────────────────────

JAVA CONCEPTS USED (for Viva)
──────────────────────────────────────────────────────────

  OOP         : Classes, Objects, Encapsulation (Employee.java)
  Inheritance : JFrame extended in PayrollApp
  Abstraction : PayrollManager hides list operations
  Methods     : calculateSalary(), generatePayslip(), etc.
  Collections : ArrayList<Employee> in PayrollManager
  Loops       : for loops in manager stats methods
  Conditions  : if-else for Professional Tax slabs
  Exception   : try-catch for number parsing
  GUI         : JFrame, JPanel, JTable, JTabbedPane, JDialog
  Events      : ActionListener, DocumentListener, MouseListener
  Swing       : JButton, JTextField, JComboBox, JLabel

──────────────────────────────────────────────────────────
  KG College of Arts and Science | Java Capstone | 2026
──────────────────────────────────────────────────────────
