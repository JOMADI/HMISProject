package sample;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee  extends RecursiveTreeObject<Employee> {

    private StringProperty empId;
    private StringProperty title;
    private StringProperty fName;
    private StringProperty mName;
    private StringProperty lName;
    private StringProperty bloodGroup;
    private StringProperty birthDate;
    private StringProperty gender;
    private StringProperty nationality;
    private StringProperty address;
    private StringProperty state;
    private StringProperty city;
    private StringProperty phone;
    private StringProperty email;
    private StringProperty status;
    private StringProperty maritalStatus;



    private StringProperty supervisor;



    private StringProperty image;
    private StringProperty department;


    private StringProperty payroll;

    private StringProperty nullValue = new SimpleStringProperty(" ");


    public Employee(String empId, String title, String fName, String mName, String lName, String bloodGroup, String birthDate, String gender, String nationality, String address, String state, String city, String phone, String email, String status, String maritalStatus, String image, String supervisor, String department, String payroll) {
        this.empId = new SimpleStringProperty(empId);
        this.title = new SimpleStringProperty(title);
        this.fName = new SimpleStringProperty(fName);
        this.mName = new SimpleStringProperty(mName);
        this.lName = new SimpleStringProperty(lName);
        this.bloodGroup = new SimpleStringProperty(bloodGroup);
        this.birthDate = new SimpleStringProperty(birthDate);
        this.gender = new SimpleStringProperty(gender);
        this.nationality = new SimpleStringProperty(nationality);
        this.address = new SimpleStringProperty(address);
        this.state = new SimpleStringProperty(state);
        this.city = new SimpleStringProperty(city);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.status = new SimpleStringProperty(status);
        this.maritalStatus = new SimpleStringProperty(maritalStatus);
        this.image = new SimpleStringProperty(image);
        this.supervisor = new SimpleStringProperty(supervisor);
        this.department = new SimpleStringProperty(department);
        this.payroll = new SimpleStringProperty(payroll);
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

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
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

    public String getmName() {
        return mName.get();
    }

    public StringProperty mNameProperty() {
        return mName == null ? nullValue : mName;
    }

    public void setmName(String mName) {
        this.mName.set(mName);
    }

    public String getlName() {
        return lName.get();
    }

    public StringProperty lNameProperty() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName.set(lName);
    }

    public String getBloodGroup() {
        return bloodGroup.get();
    }

    public StringProperty bloodGroupProperty() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup.set(bloodGroup);
    }

    public String getBirthDate() {
        return birthDate.get();
    }

    public StringProperty birthDateProperty() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate.set(birthDate);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getNationality() {
        return nationality.get();
    }

    public StringProperty nationalityProperty() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality.set(nationality);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getState() {
        return state.get();
    }

    public StringProperty stateProperty() {
        return state;
    }

    public void setState(String state) {
        this.state.set(state);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getPhoneNumber() {
        return phone.get();
    }

    public StringProperty phoneNumberProperty() {
        return phone;
    }

    public void setPhoneNumber(String mobilePhone) {
        this.phone.set(mobilePhone);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getMaritalStatus() {
        return maritalStatus.get();
    }

    public StringProperty maritalStatusProperty() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus.set(maritalStatus);
    }

    public String getSupervisor() {
        return supervisor.get();
    }

    public StringProperty supervisorProperty() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor.set(supervisor);
    }

    public String getDepartment() {
        return department.get();
    }

    public StringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public String getImage() {
        return image.get();
    }

    public StringProperty imageProperty() {
        return image;
    }

    public void setImage(String image) {
        this.image.set(image);
    }

    public String getPayroll() {
        return payroll.get();
    }

    public StringProperty payrollProperty() {
        return payroll;
    }

    public void setPayroll(String payroll) {
        this.payroll.set(payroll);
    }



    @Override
    public String toString() {
        return "Employee{" +
                "EmpId=" + empId +
                "title=" + title +
                ", fName=" + fName +
                ", mName=" + mName +
                ", lName=" + lName +
                ", bloodGroup=" + bloodGroup +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", nationality=" + nationality +
                ", address=" + address +
                ", state=" + state +
                ", city=" + city +
                ", phone=" + phone +
                ", email=" + email +
                ", status=" + status +
//                ", supervisor=" + supervisor +
//                ", hiredDate=" + hiredDate +
//                ", department=" + department +
                ", maritalStatus=" + maritalStatus +
                '}';
    }


}
