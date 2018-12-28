package sample;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Leave extends RecursiveTreeObject<Leave> {

    private StringProperty empId;
    private StringProperty startDate;
    private StringProperty endDate;
    private StringProperty fName;
    private StringProperty category;
    private StringProperty approved;


    public Leave(String empId, String fName, String startDate, String endDate,  String category, String approved) {
        this.empId = new SimpleStringProperty(empId);
        this.startDate = new SimpleStringProperty(startDate);
        this.endDate = new SimpleStringProperty(endDate);
        this.fName = new SimpleStringProperty(fName);
        this.category = new SimpleStringProperty(category);
        this.approved = new SimpleStringProperty(approved);
    }


    public String getEmpId() {
        return empId.get();
    }

    public StringProperty empIdProperty() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId.set(empId);
    }

    public String getStartDate() {
        return startDate.get();
    }

    public StringProperty startDateProperty() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

    public String getEndDate() {
        return endDate.get();
    }

    public StringProperty endDateProperty() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate.set(endDate);
    }

    public String getfName() {
        return fName.get();
    }

    public StringProperty fNameProperty() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName.set(fName);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getApproved() {
        return approved.get();
    }

    public StringProperty approvedProperty() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved.set(approved);
    }

}
