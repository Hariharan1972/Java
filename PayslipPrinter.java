/**
 * PayslipPrinter.java
 * Generates formatted payslip as a String (for display/print).
 * Demonstrates: Static methods, String formatting
 */
public class PayslipPrinter {

    // \u2500\u2500 Generate Console-Style Payslip \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public static String generatePayslip(Employee emp) {
        StringBuilder sb = new StringBuilder();
        String line  = "\u2550".repeat(52);
        String dash  = "\u2500".repeat(52);

        sb.append("\n").append(line).append("\n");
        sb.append(center("YOUR COMPANY NAME", 52)).append("\n");
        sb.append(center("EMPLOYEE PAYSLIP", 52)).append("\n");
        sb.append(center("Month: " + (emp.getMonth().isEmpty() ? "April 2026" : emp.getMonth()), 52)).append("\n");
        sb.append(line).append("\n");

        sb.append(String.format("%-22s : %s%n", "Employee ID",   emp.getEmpId()));
        sb.append(String.format("%-22s : %s%n", "Name",          emp.getName()));
        sb.append(String.format("%-22s : %s%n", "Department",    emp.getDepartment()));
        sb.append(String.format("%-22s : %s%n", "Designation",   emp.getDesignation()));
        sb.append(String.format("%-22s : XXXX XXXX XXXX %s%n", "Bank Account", emp.getBankAccount()));
        sb.append(dash).append("\n");

        sb.append(center("EARNINGS", 52)).append("\n");
        sb.append(dash).append("\n");
        sb.append(salRow("Basic Pay",          emp.getBasicPay()));
        sb.append(salRow("HRA (40%)",          emp.getHra()));
        sb.append(salRow("DA  (10%)",          emp.getDa()));
        sb.append(salRow("TA  (5%)",           emp.getTa()));
        sb.append(salRow("Special Allowance",  emp.getSpecialAllowance()));
        sb.append(dash).append("\n");
        sb.append(salRow("GROSS SALARY",       emp.getGrossSalary()));
        sb.append(dash).append("\n");

        sb.append(center("DEDUCTIONS", 52)).append("\n");
        sb.append(dash).append("\n");
        sb.append(salRow("Provident Fund (12%)",   emp.getProvidentFund()));
        sb.append(salRow("Professional Tax",        emp.getProfessionalTax()));
        sb.append(dash).append("\n");
        sb.append(salRow("TOTAL DEDUCTIONS",        emp.getTotalDeductions()));
        sb.append(line).append("\n");
        sb.append(salRow("*** NET SALARY PAYABLE", emp.getNetSalary()));
        sb.append(line).append("\n");
        sb.append(center("Computer Generated \u2014 No Signature Required", 52)).append("\n");
        sb.append(line).append("\n");

        return sb.toString();
    }

    // \u2500\u2500 HTML Payslip for JEditorPane \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    public static String generateHtmlPayslip(Employee emp) {
        String month = emp.getMonth().isEmpty() ? "April 2026" : emp.getMonth();
        return "<html><body style='font-family:monospace;margin:0;background:#f0fafa'>"
            + "<table width='100%' cellpadding='0' cellspacing='0'>"
            + "<tr><td style='background:#0D1B2A;padding:18px 24px'>"
            + "<span style='font-size:18px;font-weight:bold;color:white'>YOUR COMPANY NAME</span><br>"
            + "<span style='color:#14B8A6;font-size:12px'>Payroll Department</span></td>"
            + "<td align='right' style='background:#0D1B2A;padding:18px 24px'>"
            + "<span style='color:rgba(255,255,255,0.5);font-size:11px'>PAY PERIOD</span><br>"
            + "<span style='color:white;font-weight:bold'>" + month + "</span></td></tr>"
            + "</table>"

            + "<table width='100%' cellpadding='10' cellspacing='0' style='background:#e8f7f5;border-bottom:2px solid #ccc'>"
            + "<tr>"
            + infoCell("Employee ID",  emp.getEmpId())
            + infoCell("Name",         emp.getName())
            + infoCell("Department",   emp.getDepartment())
            + infoCell("Designation",  emp.getDesignation())
            + "</tr></table>"

            + "<table width='100%' cellpadding='0' cellspacing='0'><tr>"
            + "<td width='50%' valign='top' style='padding:16px'>"
            + "<div style='background:#d1fae5;padding:5px 10px;font-size:11px;font-weight:bold;color:#065F46;text-transform:uppercase;border-radius:4px;margin-bottom:8px'>\ud83d\udcc8 Earnings</div>"
            + salHtml("Basic Pay",         fmt(emp.getBasicPay()),         false)
            + salHtml("HRA (40%)",         fmt(emp.getHra()),              false)
            + salHtml("DA (10%)",          fmt(emp.getDa()),               false)
            + salHtml("TA (5%)",           fmt(emp.getTa()),               false)
            + salHtml("Special Allowance", fmt(emp.getSpecialAllowance()), false)
            + "<div style='background:#d1fae5;padding:7px 10px;display:flex;justify-content:space-between;border-radius:4px;margin-top:4px;font-weight:bold;color:#065F46'>"
            + "<span>Gross Salary</span><span>" + fmt(emp.getGrossSalary()) + "</span></div>"
            + "</td>"

            + "<td width='50%' valign='top' style='padding:16px;border-left:1px solid #e2e8f0'>"
            + "<div style='background:#fef3c7;padding:5px 10px;font-size:11px;font-weight:bold;color:#92400E;text-transform:uppercase;border-radius:4px;margin-bottom:8px'>\ud83d\udcc9 Deductions</div>"
            + salHtml("Provident Fund (12%)", fmt(emp.getProvidentFund()),   false)
            + salHtml("Professional Tax",      fmt(emp.getProfessionalTax()), false)
            + "<div style='background:#fef3c7;padding:7px 10px;display:flex;justify-content:space-between;border-radius:4px;margin-top:4px;font-weight:bold;color:#92400E'>"
            + "<span>Total Deductions</span><span>" + fmt(emp.getTotalDeductions()) + "</span></div>"

            + "<div style='background:#0D1B2A;border-radius:8px;padding:14px 16px;margin-top:16px'>"
            + "<div style='color:rgba(255,255,255,0.6);font-size:11px'>NET SALARY PAYABLE</div>"
            + "<div style='color:#14B8A6;font-size:26px;font-weight:bold;margin-top:4px'>" + fmt(emp.getNetSalary()) + "</div>"
            + "<div style='color:rgba(255,255,255,0.4);font-size:10px;margin-top:4px'>XXXX XXXX XXXX " + emp.getBankAccount() + "</div>"
            + "</div></td></tr></table>"

            + "<div style='text-align:center;padding:10px;background:#f8fffe;font-size:10px;color:#94a3b8;border-top:1px solid #e2e8f0'>"
            + "Computer-generated payslip \u2014 No signature required</div>"
            + "</body></html>";
    }

    // \u2500\u2500 Helpers \u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500
    private static String salRow(String label, double val) {
        return String.format("  %-26s %20s%n", label, fmt(val));
    }

    private static String infoCell(String label, String val) {
        return "<td style='padding:10px 14px'><div style='font-size:10px;font-weight:bold;text-transform:uppercase;color:#64748B;letter-spacing:1px'>"
             + label + "</div><div style='font-size:13px;font-weight:600;color:#0D1B2A;margin-top:2px'>" + (val==null||val.isEmpty()?"\u2014":val) + "</div></td>";
    }

    private static String salHtml(String label, String val, boolean bold) {
        String style = bold ? "font-weight:bold;background:#f0fafa;" : "";
        return "<div style='display:flex;justify-content:space-between;padding:5px 0;border-bottom:1px dashed #e2e8f0;font-size:12px;" + style + "'>"
             + "<span>" + label + "</span><span style='font-family:monospace'>" + val + "</span></div>";
    }

    private static String center(String text, int width) {
        int pad = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, pad)) + text;
    }

    public static String fmt(double val) {
        return String.format("\u20b9%,.0f", val);
    }
}
