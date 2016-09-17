package admin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Tanvir on 8/19/2016.
 */
public class AdminTeacherTable {

    private final SimpleIntegerProperty AdminTeacherTableDataNo;
    private final SimpleStringProperty AdminTeacherTableDataName;
    private final SimpleStringProperty AdminTeacherTableDataID;
    private final SimpleStringProperty AdminTeacherTableDataEmail;
    private final SimpleStringProperty AdminTeacherTableDataDepartment;
    private final SimpleStringProperty AdminTeacherTableDataSec;

    public AdminTeacherTable(int adminTeacherTableDataNo, String adminTeacherTableDataName, String adminTeacherTableDataID, String adminTeacherTableDataEmail, String adminTeacherTableDataDepartment, String adminTeacherTableDataSec) {
        AdminTeacherTableDataNo = new SimpleIntegerProperty(adminTeacherTableDataNo);
        AdminTeacherTableDataName = new SimpleStringProperty(adminTeacherTableDataName);
        AdminTeacherTableDataID = new SimpleStringProperty(adminTeacherTableDataID);
        AdminTeacherTableDataEmail = new SimpleStringProperty(adminTeacherTableDataEmail);
        AdminTeacherTableDataDepartment = new SimpleStringProperty(adminTeacherTableDataDepartment);
        AdminTeacherTableDataSec = new SimpleStringProperty(adminTeacherTableDataSec);
    }

    public int getAdminTeacherTableDataNo() {
        return AdminTeacherTableDataNo.get();
    }

    public SimpleIntegerProperty adminTeacherTableDataNoProperty() {
        return AdminTeacherTableDataNo;
    }

    public void setAdminTeacherTableDataNo(int adminTeacherTableDataNo) {
        this.AdminTeacherTableDataNo.set(adminTeacherTableDataNo);
    }

    public String getAdminTeacherTableDataName() {
        return AdminTeacherTableDataName.get();
    }

    public SimpleStringProperty adminTeacherTableDataNameProperty() {
        return AdminTeacherTableDataName;
    }

    public void setAdminTeacherTableDataName(String adminTeacherTableDataName) {
        this.AdminTeacherTableDataName.set(adminTeacherTableDataName);
    }

    public String getAdminTeacherTableDataID() {
        return AdminTeacherTableDataID.get();
    }

    public SimpleStringProperty adminTeacherTableDataIDProperty() {
        return AdminTeacherTableDataID;
    }

    public void setAdminTeacherTableDataID(String adminTeacherTableDataID) {
        this.AdminTeacherTableDataID.set(adminTeacherTableDataID);
    }

    public String getAdminTeacherTableDataEmail() {
        return AdminTeacherTableDataEmail.get();
    }

    public SimpleStringProperty adminTeacherTableDataEmailProperty() {
        return AdminTeacherTableDataEmail;
    }

    public void setAdminTeacherTableDataEmail(String adminTeacherTableDataEmail) {
        this.AdminTeacherTableDataEmail.set(adminTeacherTableDataEmail);
    }

    public String getAdminTeacherTableDataDepartment() {
        return AdminTeacherTableDataDepartment.get();
    }

    public SimpleStringProperty adminTeacherTableDataDepartmentProperty() {
        return AdminTeacherTableDataDepartment;
    }

    public void setAdminTeacherTableDataDepartment(String adminTeacherTableDataDepartment) {
        this.AdminTeacherTableDataDepartment.set(adminTeacherTableDataDepartment);
    }

    public String getAdminTeacherTableDataSec() {
        return AdminTeacherTableDataSec.get();
    }

    public SimpleStringProperty adminTeacherTableDataSecProperty() {
        return AdminTeacherTableDataSec;
    }

    public void setAdminTeacherTableDataSec(String adminTeacherTableDataSec) {
        this.AdminTeacherTableDataSec.set(adminTeacherTableDataSec);
    }
}
