package admin;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Tanvir on 7/25/2016.
 */
public class AdminTable {
    private final SimpleIntegerProperty adminTableDataNo;
    private final SimpleStringProperty adminTableDataStudentName;
    private final SimpleStringProperty adminTableDataStudentID;
    private final SimpleStringProperty adminTableDataStudentDepartment;
    private final SimpleDoubleProperty adminTableDataStudentCGPA;
    private final SimpleStringProperty adminTableDataStudentPhone;
    private final SimpleStringProperty adminTableDataStudentDOB;
    private final SimpleStringProperty adminTableDataGuardianName;
    private final SimpleStringProperty adminTableDataGuardianEmail;
    private final SimpleStringProperty adminTableDataGuardianPhone;
    private final SimpleStringProperty adminTableDataStudentAddress;

    public AdminTable(int adminTableDataNo, String adminTableDataStudentName, String adminTableDataStudentID, String adminTableDataStudentDepartment, Double adminTableDataStudentCGPA, String adminTableDataStudentPhone, String adminTableDataStudentDOB, String adminTableDataGuardianName, String adminTableDataGuardianEmail, String adminTableDataGuardianPhone, String adminTableDataStudentAddress) {
        this.adminTableDataNo = new SimpleIntegerProperty(adminTableDataNo);
        this.adminTableDataStudentName = new SimpleStringProperty(adminTableDataStudentName);
        this.adminTableDataStudentID = new SimpleStringProperty(adminTableDataStudentID);
        this.adminTableDataStudentDepartment = new SimpleStringProperty(adminTableDataStudentDepartment);
        this.adminTableDataStudentCGPA = new SimpleDoubleProperty(adminTableDataStudentCGPA);
        this.adminTableDataStudentPhone = new SimpleStringProperty(adminTableDataStudentPhone);
        this.adminTableDataStudentDOB = new SimpleStringProperty(adminTableDataStudentDOB);
        this.adminTableDataGuardianName = new SimpleStringProperty(adminTableDataGuardianName);
        this.adminTableDataGuardianEmail = new SimpleStringProperty(adminTableDataGuardianEmail);
        this.adminTableDataGuardianPhone = new SimpleStringProperty(adminTableDataGuardianPhone);
        this.adminTableDataStudentAddress = new SimpleStringProperty(adminTableDataStudentAddress);
    }




    public Integer getAdminTableDataNo() {

        return adminTableDataNo.get();
    }

    public SimpleIntegerProperty adminTableDataNoProperty() {
        return adminTableDataNo;
    }

    public void setAdminTableDataNo(Integer adminTableDataNo) {
        this.adminTableDataNo.set(adminTableDataNo);
    }

    public String getAdminTableDataStudentName() {
        return adminTableDataStudentName.get();
    }

    public SimpleStringProperty adminTableDataStudentNameProperty() {
        return adminTableDataStudentName;
    }

    public void setAdminTableDataStudentName(String adminTableDataStudentName) {
        this.adminTableDataStudentName.set(adminTableDataStudentName);
    }

    public String getAdminTableDataStudentID() {
        return adminTableDataStudentID.get();
    }

    public SimpleStringProperty adminTableDataStudentIDProperty() {
        return adminTableDataStudentID;
    }

    public void setAdminTableDataStudentID(String adminTableDataStudentID) {
        this.adminTableDataStudentID.set(adminTableDataStudentID);
    }

    public String getAdminTableDataStudentDepartment() {
        return adminTableDataStudentDepartment.get();
    }

    public SimpleStringProperty adminTableDataStudentDepartmentProperty() {
        return adminTableDataStudentDepartment;
    }

    public void setAdminTableDataStudentDepartment(String adminTableDataStudentDepartment) {
        this.adminTableDataStudentDepartment.set(adminTableDataStudentDepartment);
    }

    public Double getAdminTableDataStudentCGPA() {
        return adminTableDataStudentCGPA.get();
    }

    public SimpleDoubleProperty adminTableDataStudentCGPAProperty() {
        return adminTableDataStudentCGPA;
    }

    public void setAdminTableDataStudentCGPA(Double adminTableDataStudentCGPA) {
        this.adminTableDataStudentCGPA.set(adminTableDataStudentCGPA);
    }

    public String getAdminTableDataStudentPhone() {
        return adminTableDataStudentPhone.get();
    }

    public SimpleStringProperty adminTableDataStudentPhoneProperty() {
        return adminTableDataStudentPhone;
    }

    public void setAdminTableDataStudentPhone(String adminTableDataStudentPhone) {
        this.adminTableDataStudentPhone.set(adminTableDataStudentPhone);
    }

    public String getAdminTableDataStudentDOB() {
        return adminTableDataStudentDOB.get();
    }

    public SimpleStringProperty adminTableDataStudentDOBProperty() {
        return adminTableDataStudentDOB;
    }

    public void setAdminTableDataStudentDOB(String adminTableDataStudentDOB) {
        this.adminTableDataStudentDOB.set(adminTableDataStudentDOB);
    }

    public String getAdminTableDataGuardianName() {
        return adminTableDataGuardianName.get();
    }

    public SimpleStringProperty adminTableDataGuardianNameProperty() {
        return adminTableDataGuardianName;
    }

    public void setAdminTableDataGuardianName(String adminTableDataGuardianName) {
        this.adminTableDataGuardianName.set(adminTableDataGuardianName);
    }

    public String getAdminTableDataGuardianEmail() {
        return adminTableDataGuardianEmail.get();
    }

    public SimpleStringProperty adminTableDataGuardianEmailProperty() {
        return adminTableDataGuardianEmail;
    }

    public void setAdminTableDataGuardianEmail(String adminTableDataGuardianEmail) {
        this.adminTableDataGuardianEmail.set(adminTableDataGuardianEmail);
    }

    public String getAdminTableDataGuardianPhone() {
        return adminTableDataGuardianPhone.get();
    }

    public SimpleStringProperty adminTableDataGuardianPhoneProperty() {
        return adminTableDataGuardianPhone;
    }

    public void setAdminTableDataGuardianPhone(String adminTableDataGuardianPhone) {
        this.adminTableDataGuardianPhone.set(adminTableDataGuardianPhone);
    }

    public String getAdminTableDataStudentAddress() {
        return adminTableDataStudentAddress.get();
    }

    public SimpleStringProperty adminTableDataStudentAddressProperty() {
        return adminTableDataStudentAddress;
    }

    public void setAdminTableDataStudentAddress(String adminTableDataStudentAddress) {
        this.adminTableDataStudentAddress.set(adminTableDataStudentAddress);
    }
}
