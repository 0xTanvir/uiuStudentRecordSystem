package teacher;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Tanvir on 8/21/2016.
 */
public class TeacherTableData {
    private final SimpleStringProperty teacherTableDataID;
    private final SimpleStringProperty teacherTableDataName;
    private final SimpleStringProperty teacherTableDataGPA;
    private final SimpleStringProperty teacherTableDataPhone;
    private final SimpleStringProperty teacherTableDataEmail;

    public TeacherTableData(String teacherTableDataID, String teacherTableDataName, String teacherTableDataGPA, String teacherTableDataPhone, String teacherTableDataEmail) {
        this.teacherTableDataID = new SimpleStringProperty(teacherTableDataID);
        this.teacherTableDataName = new SimpleStringProperty(teacherTableDataName);
        this.teacherTableDataGPA = new SimpleStringProperty(teacherTableDataGPA);
        this.teacherTableDataPhone = new SimpleStringProperty(teacherTableDataPhone);
        this.teacherTableDataEmail = new SimpleStringProperty(teacherTableDataEmail);
    }

    public String getTeacherTableDataID() {
        return teacherTableDataID.get();
    }

    public SimpleStringProperty teacherTableDataIDProperty() {
        return teacherTableDataID;
    }

    public void setTeacherTableDataID(String teacherTableDataID) {
        this.teacherTableDataID.set(teacherTableDataID);
    }

    public String getTeacherTableDataName() {
        return teacherTableDataName.get();
    }

    public SimpleStringProperty teacherTableDataNameProperty() {
        return teacherTableDataName;
    }

    public void setTeacherTableDataName(String teacherTableDataName) {
        this.teacherTableDataName.set(teacherTableDataName);
    }

    public String getTeacherTableDataGPA() {
        return teacherTableDataGPA.get();
    }

    public SimpleStringProperty teacherTableDataGPAProperty() {
        return teacherTableDataGPA;
    }

    public void setTeacherTableDataGPA(String teacherTableDataGPA) {
        this.teacherTableDataGPA.set(teacherTableDataGPA);
    }

    public String getTeacherTableDataPhone() {
        return teacherTableDataPhone.get();
    }

    public SimpleStringProperty teacherTableDataPhoneProperty() {
        return teacherTableDataPhone;
    }

    public void setTeacherTableDataPhone(String teacherTableDataPhone) {
        this.teacherTableDataPhone.set(teacherTableDataPhone);
    }

    public String getTeacherTableDataEmail() {
        return teacherTableDataEmail.get();
    }

    public SimpleStringProperty teacherTableDataEmailProperty() {
        return teacherTableDataEmail;
    }

    public void setTeacherTableDataEmail(String teacherTableDataEmail) {
        this.teacherTableDataEmail.set(teacherTableDataEmail);
    }
}
