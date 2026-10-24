import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * PayrollApp.java  \u2014 Main GUI Application
 * Salary Payroll Processing and Payslip Generation System
 *
 * HOW TO RUN:
 *   1. Place all 4 .java files in the same folder
 *   2. Compile:  javac *.java
 *   3. Run:      java PayrollApp
 *
 * Demonstrates: Swing GUI, JTabbedPane, JTable, JPanel, ActionListeners,
 *               Inner Classes, OOP (Employee, PayrollManager, PayslipPrinter)
 */
public class PayrollApp extends JFrame {
    private static final long serialVersionUID = 1L;

    // \u2500\u2500 Color Palette \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    static final Color NAVY      = new Color(0x0D, 0x1B, 0x2A);
    static final Color NAVY2     = new Color(0x13, 0x24, 0x38);
    static final Color TEAL      = new Color(0x0D, 0x94, 0x88);
    static final Color TEAL2     = new Color(0x14, 0xB8, 0xA6);
    static final Color TEAL_LIGHT= new Color(0xCC, 0xFB, 0xF1);
    static final Color AMBER     = new Color(0xF5, 0x9E, 0x0B);
    static final Color AMBER_LIGHT=new Color(0xFE, 0xF3, 0xC7);
    static final Color WHITE     = Color.WHITE;
    static final Color OFF_WHITE = new Color(0xF8, 0xFF, 0xFE);
    static final Color LIGHT_GRAY= new Color(0xE2, 0xE8, 0xF0);
    static final Color GRAY      = new Color(0x64, 0x74, 0x8B);
    static final Color RED       = new Color(0xEF, 0x44, 0x44);
    static final Color GREEN     = new Color(0x10, 0xB9, 0x81);
    static final Color TEXT      = new Color(0x1E, 0x29, 0x3B);

    // \u2500\u2500 Fonts \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    static final Font FONT_TITLE  = new Font("SansSerif", Font.BOLD,   20);
    static final Font FONT_HEAD   = new Font("SansSerif", Font.BOLD,   14);
    static final Font FONT_BODY   = new Font("SansSerif", Font.PLAIN,  13);
    static final Font FONT_SMALL  = new Font("SansSerif", Font.PLAIN,  11);
    static final Font FONT_MONO   = new Font("Monospaced",Font.BOLD,   13);
    static final Font FONT_BIG    = new Font("SansSerif", Font.BOLD,   28);

    // \u2500\u2500 Data \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    private PayrollManager manager = new PayrollManager();
    private int selectedIndex = -1;

    // \u2500\u2500 UI Components \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    private JTabbedPane tabbedPane;
    private DefaultTableModel tableModel;
    private JTable empTable;
    private JEditorPane payslipPane;

    // Form fields
    private JTextField fEmpId, fName, fDesig, fMonth, fBank;
    private JComboBox<String> fDept;
    private JTextField fBasic, fSpecial;

    // Stat labels
    private JLabel lblTotal, lblGross, lblDed, lblNet;

    // Salary preview labels
    private JLabel lHra, lDa, lTa, lGross, lPf, lPt, lDeduct, lNet;
    private JPanel calcPanel;

    // \u2500\u2500 Constructor \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public PayrollApp() {
        setTitle("PaySlip Pro \u2014 Salary Payroll System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 720);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);

        buildUI();
        refreshAll();
        setVisible(true);
    }

    // \u2500\u2500 Build UI \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    private void buildUI() {
        setLayout(new BorderLayout());

        // Header
        add(buildHeader(), BorderLayout.NORTH);

        // Main content
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(OFF_WHITE);

        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(FONT_HEAD);
        tabbedPane.setBackground(OFF_WHITE);

        tabbedPane.addTab("\ud83d\udcdd  Process Payroll",  buildFormTab());
        tabbedPane.addTab("\ud83d\udc65  All Employees",    buildListTab());
        tabbedPane.addTab("\ud83e\uddfe  Payslip",          buildPayslipTab());

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    // \u2500\u2500 HEADER \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(NAVY);
        header.setPreferredSize(new Dimension(0, 62));
        header.setBorder(BorderFactory.createMatteBorder(0,0,2,0, TEAL));

        // Left: logo
        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 12));
        left.setBackground(NAVY);

        JLabel icon = new JLabel("\ud83d\udcbc");
        icon.setFont(new Font("SansSerif", Font.PLAIN, 24));

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(NAVY);
        JLabel title = new JLabel("PaySlip Pro");
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        title.setForeground(WHITE);
        JLabel sub = new JLabel("SALARY PAYROLL SYSTEM");
        sub.setFont(FONT_SMALL);
        sub.setForeground(TEAL2);
        textPanel.add(title);
        textPanel.add(sub);

        left.add(icon);
        left.add(textPanel);

        // Right: stats + button
        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 14));
        right.setBackground(NAVY);

        lblTotal = makePill("0 Employees");
        lblGross = makePill("Gross: \u20b90");
        lblNet   = makePill("Net: \u20b90");

        JButton addBtn = makeButton("+ Add Employee", TEAL, WHITE);
        addBtn.addActionListener(e -> openAddDialog());

        right.add(lblTotal);
        right.add(lblGross);
        right.add(lblNet);
        right.add(addBtn);

        header.add(left,  BorderLayout.WEST);
        header.add(right, BorderLayout.EAST);
        return header;
    }

    // \u2500\u2500 FORM TAB \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    private JPanel buildFormTab() {
        JPanel outer = new JPanel(new BorderLayout(12, 12));
        outer.setBackground(OFF_WHITE);
        outer.setBorder(new EmptyBorder(16, 16, 16, 16));

        // Scroll wrapper
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(OFF_WHITE);

        // \u2500\u2500 Employee Info Card \u2500\u2500
        content.add(buildCard("Employee Information", buildEmpInfoPanel()));
        content.add(Box.createVerticalStrut(12));

        // \u2500\u2500 Salary Card \u2500\u2500
        content.add(buildCard("Salary Components & Live Preview", buildSalaryPanel()));
        content.add(Box.createVerticalStrut(12));

        // \u2500\u2500 Actions Card \u2500\u2500
        content.add(buildCard("Actions", buildActionsPanel()));

        JScrollPane scroll = new JScrollPane(content);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        outer.add(scroll, BorderLayout.CENTER);
        return outer;
    }

    private JPanel buildEmpInfoPanel() {
        JPanel p = new JPanel(new GridLayout(3, 4, 10, 10));
        p.setBackground(WHITE);

        String[] depts = {"", "IT", "HR", "Finance", "Operations", "Marketing", "Sales", "Admin", "Legal"};
        fDept = new JComboBox<>(depts);
        styleCombo(fDept);

        fEmpId  = makeField("EMP001");
        fName   = makeField("Full Name");
        fDesig  = makeField("Software Engineer");
        fMonth  = makeField("April 2026");
        fBank   = makeField("XXXX (last 4 digits)");
        fBasic  = makeField("50000");
        fSpecial= makeField("5000");

        // Add salary listeners
        DocumentListener dl = new DocumentListener() {
            public void insertUpdate(DocumentEvent e)  { updateCalcPreview(); }
            public void removeUpdate(DocumentEvent e)  { updateCalcPreview(); }
            public void changedUpdate(DocumentEvent e) { updateCalcPreview(); }
        };
        fBasic.getDocument().addDocumentListener(dl);
        fSpecial.getDocument().addDocumentListener(dl);

        p.add(labeledField("Employee ID",  fEmpId));
        p.add(labeledField("Full Name",    fName));
        p.add(labeledField("Department",   fDept));
        p.add(labeledField("Designation",  fDesig));
        p.add(labeledField("Month / Year", fMonth));
        p.add(labeledField("Bank (last 4)",fBank));
        p.add(labeledField("Basic Pay (\u20b9)",fBasic));
        p.add(labeledField("Special Allow.",fSpecial));
        p.add(new JLabel()); // spacer
        p.add(new JLabel()); // spacer
        p.add(new JLabel()); // spacer
        p.add(new JLabel()); // spacer

        return p;
    }

    private JPanel buildSalaryPanel() {
        JPanel outer = new JPanel(new BorderLayout(16, 0));
        outer.setBackground(WHITE);

        // \u2500\u2500 Left: Earnings \u2500\u2500
        JPanel earn = new JPanel();
        earn.setLayout(new BoxLayout(earn, BoxLayout.Y_AXIS));
        earn.setBackground(WHITE);

        earn.add(sectionTag("\ud83d\udcc8  EARNINGS", TEAL, TEAL_LIGHT));
        earn.add(Box.createVerticalStrut(6));
        lHra    = new JLabel("\u20b90");
        lDa     = new JLabel("\u20b90");
        lTa     = new JLabel("\u20b90");
        lGross  = new JLabel("\u20b90");
        earn.add(salRow("Basic Pay",          fBasic, null,  false));
        earn.add(salRow("HRA",               "(40%)",lHra,  false));
        earn.add(salRow("DA",                "(10%)",lDa,   false));
        earn.add(salRow("TA",                "(5%)", lTa,   false));
        earn.add(salRow("Special Allowance", "",     null,  false));
        earn.add(salRow("Gross Salary",       "",    lGross, true));

        // \u2500\u2500 Right: Deductions + Net \u2500\u2500
        JPanel ded = new JPanel();
        ded.setLayout(new BoxLayout(ded, BoxLayout.Y_AXIS));
        ded.setBackground(WHITE);

        ded.add(sectionTag("\ud83d\udcc9  DEDUCTIONS", AMBER, AMBER_LIGHT));
        ded.add(Box.createVerticalStrut(6));
        lPf     = new JLabel("\u20b90");
        lPt     = new JLabel("\u20b90");
        lDeduct = new JLabel("\u20b90");
        lNet    = new JLabel("\u20b90");
        ded.add(salRow("Provident Fund", "(12%)", lPf,     false));
        ded.add(salRow("Professional Tax","",     lPt,     false));
        ded.add(salRow("Total Deductions","",     lDeduct,  true));
        ded.add(Box.createVerticalStrut(10));

        // Net salary big box
        JPanel netBox = new JPanel(new BorderLayout());
        netBox.setBackground(NAVY);
        netBox.setBorder(new RoundedBorder(NAVY, 10));
        JLabel netLabel = new JLabel("NET SALARY");
        netLabel.setFont(FONT_SMALL);
        netLabel.setForeground(new Color(255,255,255,150));
        netLabel.setBorder(new EmptyBorder(14,16,0,16));
        lNet.setFont(FONT_BIG);
        lNet.setForeground(TEAL2);
        lNet.setBorder(new EmptyBorder(4,16,14,16));
        netBox.add(netLabel, BorderLayout.NORTH);
        netBox.add(lNet,     BorderLayout.CENTER);
        ded.add(netBox);

        outer.add(earn, BorderLayout.WEST);
        outer.add(new JSeparator(JSeparator.VERTICAL), BorderLayout.CENTER);
        outer.add(ded,  BorderLayout.EAST);
        return outer;
    }

    private JPanel buildActionsPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        p.setBackground(WHITE);

        JButton btnSave    = makeButton("\ud83d\udcbe  Save Employee",    TEAL,  WHITE);
        JButton btnPayslip = makeButton("\ud83e\uddfe  Preview Payslip",  NAVY,  WHITE);
        JButton btnPrint   = makeButton("\ud83d\udda8\ufe0f  Print Payslip",    AMBER, WHITE);
        JButton btnClear   = makeButton("\u21ba  Clear Form",        LIGHT_GRAY, TEXT);

        btnSave.addActionListener(e    -> saveEmployee());
        btnPayslip.addActionListener(e -> previewPayslip());
        btnPrint.addActionListener(e   -> printPayslip());
        btnClear.addActionListener(e   -> clearForm());

        p.add(btnSave);
        p.add(btnPayslip);
        p.add(btnPrint);
        p.add(btnClear);
        return p;
    }

    // \u2500\u2500 LIST TAB \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    private JPanel buildListTab() {
        JPanel p = new JPanel(new BorderLayout(0, 12));
        p.setBackground(OFF_WHITE);
        p.setBorder(new EmptyBorder(16, 16, 16, 16));

        // Summary row
        JPanel summary = new JPanel(new GridLayout(1, 4, 12, 0));
        summary.setBackground(OFF_WHITE);
        lblTotal = makeStatCard("Total Employees",  "0",    NAVY);
        lblGross = makeStatCard("Total Gross",       "\u20b90",  TEAL);
        lblDed   = makeStatCard("Total Deductions",  "\u20b90",  AMBER);
        lblNet   = makeStatCard("Total Net Pay",     "\u20b90",  GREEN);
        summary.add(lblTotal.getParent());
        summary.add(lblGross.getParent());
        summary.add(lblDed.getParent());
        summary.add(lblNet.getParent());
        p.add(summary, BorderLayout.NORTH);

        // Table
        String[] cols = {"Emp ID","Name","Department","Designation","Basic Pay","Gross Salary","Deductions","Net Salary"};
        tableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        empTable = new JTable(tableModel);
        empTable.setFont(FONT_BODY);
        empTable.setRowHeight(34);
        empTable.setShowGrid(false);
        empTable.setIntercellSpacing(new Dimension(0, 0));
        empTable.setSelectionBackground(TEAL_LIGHT);
        empTable.setSelectionForeground(TEXT);
        empTable.getTableHeader().setFont(FONT_SMALL);
        empTable.getTableHeader().setBackground(OFF_WHITE);
        empTable.getTableHeader().setForeground(GRAY);
        empTable.getTableHeader().setPreferredSize(new Dimension(0, 34));

        // Alternating row colors
        empTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable t, Object val,
                    boolean sel, boolean foc, int row, int col) {
                Component c = super.getTableCellRendererComponent(t, val, sel, foc, row, col);
                if (!sel) c.setBackground(row % 2 == 0 ? WHITE : OFF_WHITE);
                setBorder(new EmptyBorder(0, 12, 0, 12));
                // Right-align money columns
                if (col >= 4) setHorizontalAlignment(SwingConstants.RIGHT);
                else setHorizontalAlignment(SwingConstants.LEFT);
                return c;
            }
        });

        empTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = empTable.getSelectedRow();
                if (row >= 0) { selectedIndex = row; loadToForm(row); }
                if (e.getClickCount() == 2 && row >= 0) previewPayslipForIndex(row);
            }
        });

        JScrollPane scroll = new JScrollPane(empTable);
        scroll.setBorder(BorderFactory.createLineBorder(LIGHT_GRAY));

        // Bottom buttons
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        btns.setBackground(OFF_WHITE);
        JButton btnEdit = makeButton("\u270f\ufe0f  Edit Selected",   TEAL,  WHITE);
        JButton btnDel  = makeButton("\ud83d\uddd1\ufe0f  Delete Selected", RED,   WHITE);
        JButton btnSlip = makeButton("\ud83e\uddfe  View Payslip",    NAVY,  WHITE);
        btnEdit.addActionListener(e -> editSelected());
        btnDel .addActionListener(e -> deleteSelected());
        btnSlip.addActionListener(e -> { if (selectedIndex >= 0) previewPayslipForIndex(selectedIndex); });
        btns.add(btnEdit); btns.add(btnDel); btns.add(btnSlip);

        p.add(scroll, BorderLayout.CENTER);
        p.add(btns,   BorderLayout.SOUTH);
        return p;
    }

    // \u2500\u2500 PAYSLIP TAB \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    private JPanel buildPayslipTab() {
        JPanel p = new JPanel(new BorderLayout(0, 10));
        p.setBackground(OFF_WHITE);
        p.setBorder(new EmptyBorder(16, 16, 16, 16));

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        topBar.setBackground(OFF_WHITE);
        JButton btnPrint = makeButton("\ud83d\udda8\ufe0f  Print Payslip",  AMBER, WHITE);
        JButton btnBack  = makeButton("\u2190 Back to Form",      LIGHT_GRAY, TEXT);
        btnPrint.addActionListener(e -> printCurrentPayslip());
        btnBack .addActionListener(e -> tabbedPane.setSelectedIndex(0));
        topBar.add(btnPrint); topBar.add(btnBack);

        payslipPane = new JEditorPane();
        payslipPane.setContentType("text/html");
        payslipPane.setEditable(false);
        payslipPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
        payslipPane.setFont(FONT_BODY);
        payslipPane.setText("<html><body style='font-family:sans-serif;padding:40px;text-align:center;color:#94a3b8'>"
            + "<h2>No payslip generated yet</h2>"
            + "<p>Fill in employee details and click <b>Preview Payslip</b></p></body></html>");

        JScrollPane scroll = new JScrollPane(payslipPane);
        scroll.setBorder(BorderFactory.createLineBorder(LIGHT_GRAY));

        p.add(topBar, BorderLayout.NORTH);
        p.add(scroll, BorderLayout.CENTER);
        return p;
    }

    // \u2500\u2500 SAVE / LOAD \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    private void saveEmployee() {
        try {
            String name  = fName.getText().trim();
            String basic = fBasic.getText().trim();
            if (name.isEmpty())  { showError("Name is required."); return; }
            if (basic.isEmpty()) { showError("Basic Pay is required."); return; }

            double basicVal   = Double.parseDouble(basic);
            double specialVal = fSpecial.getText().isEmpty() ? 0 : Double.parseDouble(fSpecial.getText().trim());
            if (basicVal <= 0) { showError("Basic Pay must be greater than 0."); return; }

            Employee emp = new Employee(
                fEmpId.getText().trim(),
                name,
                (String) fDept.getSelectedItem(),
                fDesig.getText().trim(),
                fMonth.getText().trim(),
                fBank.getText().trim(),
                basicVal,
                specialVal
            );

            if (selectedIndex >= 0 && selectedIndex < manager.getCount()) {
                manager.updateEmployee(selectedIndex, emp);
                showInfo("Employee updated successfully!");
            } else {
                manager.addEmployee(emp);
                selectedIndex = manager.getCount() - 1;
                showInfo("Employee saved successfully!");
            }
            refreshAll();

        } catch (NumberFormatException ex) {
            showError("Please enter valid numbers for Basic Pay and Special Allowance.");
        }
    }

    private void loadToForm(int index) {
        Employee emp = manager.getEmployee(index);
        if (emp == null) return;
        fEmpId  .setText(emp.getEmpId());
        fName   .setText(emp.getName());
        fDept   .setSelectedItem(emp.getDepartment());
        fDesig  .setText(emp.getDesignation());
        fMonth  .setText(emp.getMonth());
        fBank   .setText(emp.getBankAccount());
        fBasic  .setText(String.valueOf((int) emp.getBasicPay()));
        fSpecial.setText(String.valueOf((int) emp.getSpecialAllowance()));
        updateCalcPreview();
        tabbedPane.setSelectedIndex(0);
    }

    private void editSelected() {
        if (selectedIndex < 0 || selectedIndex >= manager.getCount()) {
            showError("Please select an employee first."); return;
        }
        loadToForm(selectedIndex);
    }

    private void deleteSelected() {
        if (selectedIndex < 0 || selectedIndex >= manager.getCount()) {
            showError("Please select an employee first."); return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
            "Delete " + manager.getEmployee(selectedIndex).getName() + "?",
            "Confirm Delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            manager.deleteEmployee(selectedIndex);
            selectedIndex = -1;
            clearForm();
            refreshAll();
        }
    }

    private void clearForm() {
        fEmpId.setText("");  fName.setText("");   fDesig.setText("");
        fMonth.setText("");  fBank.setText("");   fBasic.setText("");
        fSpecial.setText(""); fDept.setSelectedIndex(0);
        selectedIndex = -1;
        updateCalcPreview();
    }

    // \u2500\u2500 LIVE CALCULATION PREVIEW \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    private void updateCalcPreview() {
        try {
            double basic   = fBasic.getText().isEmpty()   ? 0 : Double.parseDouble(fBasic.getText().trim());
            double special = fSpecial.getText().isEmpty() ? 0 : Double.parseDouble(fSpecial.getText().trim());
            if (basic < 0 || special < 0) return;

            double hra    = basic * 0.40;
            double da     = basic * 0.10;
            double ta     = basic * 0.05;
            double gross  = basic + hra + da + ta + special;
            double pf     = basic * 0.12;
            double pt     = basic <= 10000 ? 0 : basic <= 20000 ? 150 : 200;
            double deduct = pf + pt;
            double net    = gross - deduct;

            lHra.setText(PayslipPrinter.fmt(hra));
            lDa .setText(PayslipPrinter.fmt(da));
            lTa .setText(PayslipPrinter.fmt(ta));
            lGross.setText(PayslipPrinter.fmt(gross));
            lPf .setText(PayslipPrinter.fmt(pf));
            lPt .setText(PayslipPrinter.fmt(pt));
            lDeduct.setText(PayslipPrinter.fmt(deduct));
            lNet.setText(PayslipPrinter.fmt(net));

        } catch (NumberFormatException ignored) {}
    }

    // \u2500\u2500 PAYSLIP \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    private Employee buildCurrentEmployee() {
        try {
            String name  = fName.getText().trim();
            double basic = Double.parseDouble(fBasic.getText().trim());
            double spec  = fSpecial.getText().isEmpty() ? 0 : Double.parseDouble(fSpecial.getText().trim());
            return new Employee(
                fEmpId.getText().trim(), name,
                (String) fDept.getSelectedItem(),
                fDesig.getText().trim(),
                fMonth.getText().trim(),
                fBank.getText().trim(),
                basic, spec
            );
        } catch (Exception e) { return null; }
    }

    private void previewPayslip() {
        Employee emp = buildCurrentEmployee();
        if (emp == null || emp.getName().isEmpty()) {
            showError("Please enter Name and Basic Pay first."); return;
        }
        payslipPane.setText(PayslipPrinter.generateHtmlPayslip(emp));
        tabbedPane.setSelectedIndex(2);
    }

    private void previewPayslipForIndex(int index) {
        Employee emp = manager.getEmployee(index);
        if (emp == null) return;
        payslipPane.setText(PayslipPrinter.generateHtmlPayslip(emp));
        tabbedPane.setSelectedIndex(2);
    }

    private void printPayslip() {
        Employee emp = buildCurrentEmployee();
        if (emp == null) { showError("Enter Name and Basic Pay first."); return; }
        showPrintDialog(emp);
    }

    private void printCurrentPayslip() {
        // Print whatever is currently in the payslip pane
        try {
            MessageFormat header = new MessageFormat("Payslip");
            MessageFormat footer = new MessageFormat("Page {0}");
            payslipPane.print(header, footer);
        } catch (Exception e) {
            showError("Printing failed: " + e.getMessage());
        }
    }

    private void showPrintDialog(Employee emp) {
        JDialog dialog = new JDialog(this, "\ud83d\udda8\ufe0f  Print Preview \u2014 " + emp.getName(), true);
        dialog.setSize(600, 700);
        dialog.setLocationRelativeTo(this);

        JEditorPane preview = new JEditorPane("text/html", PayslipPrinter.generateHtmlPayslip(emp));
        preview.setEditable(false);
        preview.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
        JScrollPane scroll = new JScrollPane(preview);

        JPanel btns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        btns.setBackground(OFF_WHITE);
        JButton print = makeButton("\ud83d\udda8\ufe0f  Print", AMBER, WHITE);
        JButton close = makeButton("Close",      LIGHT_GRAY, TEXT);
        print.addActionListener(e -> {
            try {
                MessageFormat hdr = new MessageFormat("Payslip \u2014 " + emp.getName());
                MessageFormat ftr = new MessageFormat("Page {0}");
                preview.print(hdr, ftr);
            } catch (Exception ex) { showError("Print failed: " + ex.getMessage()); }
        });
        close.addActionListener(e -> dialog.dispose());
        btns.add(close); btns.add(print);

        dialog.setLayout(new BorderLayout());
        dialog.add(scroll, BorderLayout.CENTER);
        dialog.add(btns,   BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    // \u2500\u2500 ADD DIALOG \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    private void openAddDialog() {
        clearForm();
        tabbedPane.setSelectedIndex(0);
        fName.requestFocus();
    }

    // \u2500\u2500 REFRESH ALL \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    private void refreshAll() {
        // Header pills
        int count = manager.getCount();
        if (lblTotal != null) {
            // Find the stat labels in list tab and update
        }
        refreshTable();
        refreshStatCards();
    }

    private void refreshTable() {
        if (tableModel == null) return;
        tableModel.setRowCount(0);
        for (Employee emp : manager.getAllEmployees()) {
            tableModel.addRow(new Object[]{
                emp.getEmpId(),
                emp.getName(),
                emp.getDepartment(),
                emp.getDesignation(),
                PayslipPrinter.fmt(emp.getBasicPay()),
                PayslipPrinter.fmt(emp.getGrossSalary()),
                PayslipPrinter.fmt(emp.getTotalDeductions()),
                PayslipPrinter.fmt(emp.getNetSalary())
            });
        }
    }

    private void refreshStatCards() {
        if (lblTotal == null) return;
        lblTotal.setText(String.valueOf(manager.getCount()));
        lblGross.setText(PayslipPrinter.fmt(manager.getTotalGross()));
        lblDed  .setText(PayslipPrinter.fmt(manager.getTotalDeductions()));
        lblNet  .setText(PayslipPrinter.fmt(manager.getTotalNet()));
    }

    // \u2500\u2500 UI HELPERS \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    private JPanel buildCard(String title, JPanel content) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY),
            new EmptyBorder(0, 0, 0, 0)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, card.getPreferredSize().height + 200));

        // Card header
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        header.setBackground(OFF_WHITE);
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, LIGHT_GRAY));
        JLabel dot = new JLabel("\u25cf");
        dot.setForeground(TEAL);
        dot.setFont(new Font("SansSerif", Font.PLAIN, 10));
        JLabel lbl = new JLabel(title);
        lbl.setFont(FONT_HEAD);
        lbl.setForeground(TEXT);
        header.add(dot); header.add(lbl);

        content.setBorder(new EmptyBorder(16, 16, 16, 16));
        card.add(header,  BorderLayout.NORTH);
        card.add(content, BorderLayout.CENTER);
        return card;
    }

    private JLabel makeStatCard(String label, String val, Color valColor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY),
            new EmptyBorder(14, 18, 14, 18)
        ));
        JLabel lLbl = new JLabel(label);
        lLbl.setFont(FONT_SMALL);
        lLbl.setForeground(GRAY);
        JLabel lVal = new JLabel(val);
        lVal.setFont(FONT_BIG);
        lVal.setForeground(valColor);
        card.add(lLbl, BorderLayout.NORTH);
        card.add(lVal, BorderLayout.CENTER);
        return lVal; // return value label for updating
    }

    private JTextField makeField(String placeholder) {
        JTextField f = new JTextField();
        f.setFont(FONT_BODY);
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(LIGHT_GRAY),
            new EmptyBorder(6, 10, 6, 10)
        ));
        f.setForeground(TEXT);
        return f;
    }

    private void styleCombo(JComboBox<?> cb) {
        cb.setFont(FONT_BODY);
        cb.setBackground(WHITE);
        cb.setBorder(BorderFactory.createLineBorder(LIGHT_GRAY));
    }

    private JPanel labeledField(String label, JComponent field) {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.setBackground(WHITE);
        JLabel lbl = new JLabel(label.toUpperCase());
        lbl.setFont(new Font("SansSerif", Font.BOLD, 10));
        lbl.setForeground(GRAY);
        p.add(lbl,   BorderLayout.NORTH);
        p.add(field, BorderLayout.CENTER);
        return p;
    }

    private JPanel salRow(String label, Object middle, JLabel valueLabel, boolean bold) {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setBackground(OFF_WHITE);
        row.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, LIGHT_GRAY),
            new EmptyBorder(7, 10, 7, 10)
        ));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));

        JLabel lbl = new JLabel(label);
        lbl.setFont(bold ? new Font("SansSerif", Font.BOLD, 13) : FONT_BODY);
        lbl.setForeground(bold ? TEAL : TEXT);

        JLabel mid = new JLabel(middle instanceof String ? (String) middle : "");
        mid.setFont(FONT_SMALL);
        mid.setForeground(GRAY);

        JPanel leftSide = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftSide.setBackground(OFF_WHITE);
        leftSide.add(lbl);
        if (!mid.getText().isEmpty()) { leftSide.add(Box.createHorizontalStrut(4)); leftSide.add(mid); }

        if (valueLabel != null) {
            valueLabel.setFont(FONT_MONO);
            valueLabel.setForeground(bold ? TEAL : TEXT);
            row.add(leftSide,    BorderLayout.WEST);
            row.add(valueLabel,  BorderLayout.EAST);
        } else {
            // Use the field directly (for Basic and Special)
            JComponent field = label.equals("Basic Pay") ? fBasic : fSpecial;
            if (field == null) field = new JLabel();
            row.add(leftSide, BorderLayout.WEST);
        }
        return row;
    }

    private JLabel sectionTag(String text, Color fg, Color bg) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.BOLD, 11));
        l.setForeground(fg);
        l.setBackground(bg);
        l.setOpaque(true);
        l.setBorder(new EmptyBorder(4, 10, 4, 10));
        l.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        return l;
    }

    private JButton makeButton(String text, Color bg, Color fg) {
        JButton b = new JButton(text);
        b.setFont(new Font("SansSerif", Font.BOLD, 13));
        b.setBackground(bg);
        b.setForeground(fg);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setBorder(new EmptyBorder(9, 18, 9, 18));
        b.setFocusPainted(false);
        b.addMouseListener(new MouseAdapter() {
            Color orig = bg;
            public void mouseEntered(MouseEvent e) { b.setBackground(bg.darker()); }
            public void mouseExited(MouseEvent e)  { b.setBackground(orig); }
        });
        return b;
    }

    private JLabel makePill(String text) {
        JLabel l = new JLabel(text);
        l.setFont(FONT_SMALL);
        l.setForeground(TEAL2);
        l.setBackground(new Color(13, 148, 136, 40));
        l.setOpaque(true);
        l.setBorder(new EmptyBorder(4, 12, 4, 12));
        return l;
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showInfo(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // \u2500\u2500 Custom rounded border \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    static class RoundedBorder extends AbstractBorder {
        private static final long serialVersionUID = 1L;
        private Color color; private int radius;
        RoundedBorder(Color c, int r) { color = c; radius = r; }
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawRoundRect(x, y, w-1, h-1, radius, radius);
        }
        public Insets getBorderInsets(Component c) { return new Insets(radius+1,radius+1,radius+1,radius+1); }
        public Insets getBorderInsets(Component c, Insets i) { i.left=i.right=i.top=i.bottom=radius+1; return i; }
    }

    // \u2500\u2500 MAIN \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public static void main(String[] args) {
        // Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // Run on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            PayrollApp app = new PayrollApp();

            // Load sample employees
            app.manager.addEmployee(new Employee(
                "EMP001", "Rajesh Kumar", "IT", "Software Engineer",
                "April 2026", "7890", 50000, 5000));
            app.manager.addEmployee(new Employee(
                "EMP002", "Priya Sharma", "HR", "HR Manager",
                "April 2026", "1234", 45000, 3000));
            app.manager.addEmployee(new Employee(
                "EMP003", "Arun Mehta", "Finance", "Accountant",
                "April 2026", "5678", 35000, 2000));
            app.refreshAll();
        });
    }
}
