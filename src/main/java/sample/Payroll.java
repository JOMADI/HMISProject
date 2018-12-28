package sample;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Payroll extends RecursiveTreeObject<Payroll> {

    private StringProperty empId;
    private StringProperty payrollId;
    private StringProperty empName;
    private StringProperty payAmt;

    public String getEmpId() {
        return empId.get();
    }

    public StringProperty empIdProperty() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId.set(empId);
    }

    public String getPayrollId() {
        return payrollId.get();
    }

    public StringProperty payrollIdProperty() {
        return payrollId;
    }

    public void setPayrollId(String payrollId) {
        this.payrollId.set(payrollId);
    }

    public String getEmpName() {
        return empName.get();
    }

    public StringProperty empNameProperty() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName.set(empName);
    }

    public String getPayAmt() {
        return payAmt.get();
    }

    public StringProperty payAmtProperty() {
        return payAmt;
    }

    public void setPayAmt(String payAmt) {
        this.payAmt.set(payAmt);
    }



    public Payroll(String empId, String payrollId, String empName, String payAmt) {
        this.empId = new SimpleStringProperty(empId);
        this.payrollId = new SimpleStringProperty(payrollId);
        this.empName = new SimpleStringProperty(empName);
        this.payAmt = new SimpleStringProperty(payAmt);
    }



}
