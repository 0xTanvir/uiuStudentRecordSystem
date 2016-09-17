package admin;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Tanvir on 8/18/2016.
 */
public class CourseTable {
    private final SimpleIntegerProperty courseTableDataNo;
    private final SimpleStringProperty courseTableDataCode;
    private final SimpleStringProperty courseTableDataTitle;
    private final SimpleIntegerProperty courseTableDataCredit;
    private final SimpleStringProperty courseTableDataSec;


    public CourseTable(int courseTableDataNo, String courseTableDataCode, String courseTableDataTitle, int courseTableDataCredit, String courseTableDataSec) {
        this.courseTableDataNo = new SimpleIntegerProperty(courseTableDataNo);
        this.courseTableDataCode = new SimpleStringProperty(courseTableDataCode);
        this.courseTableDataTitle = new SimpleStringProperty(courseTableDataTitle);
        this.courseTableDataCredit = new SimpleIntegerProperty(courseTableDataCredit);
        this.courseTableDataSec = new SimpleStringProperty(courseTableDataSec);
    }

    public int getCourseTableDataNo() {
        return courseTableDataNo.get();
    }

    public SimpleIntegerProperty courseTableDataNoProperty() {
        return courseTableDataNo;
    }

    public void setCourseTableDataNo(int courseTableDataNo) {
        this.courseTableDataNo.set(courseTableDataNo);
    }

    public String getCourseTableDataCode() {
        return courseTableDataCode.get();
    }

    public SimpleStringProperty courseTableDataCodeProperty() {
        return courseTableDataCode;
    }

    public void setCourseTableDataCode(String courseTableDataCode) {
        this.courseTableDataCode.set(courseTableDataCode);
    }

    public String getCourseTableDataTitle() {
        return courseTableDataTitle.get();
    }

    public SimpleStringProperty courseTableDataTitleProperty() {
        return courseTableDataTitle;
    }

    public void setCourseTableDataTitle(String courseTableDataTitle) {
        this.courseTableDataTitle.set(courseTableDataTitle);
    }

    public int getCourseTableDataCredit() {
        return courseTableDataCredit.get();
    }

    public SimpleIntegerProperty courseTableDataCreditProperty() {
        return courseTableDataCredit;
    }

    public void setCourseTableDataCredit(int courseTableDataCredit) {
        this.courseTableDataCredit.set(courseTableDataCredit);
    }

    public String getCourseTableDataSec() {
        return courseTableDataSec.get();
    }

    public SimpleStringProperty courseTableDataSecProperty() {
        return courseTableDataSec;
    }

    public void setCourseTableDataSec(String courseTableDataSec) {
        this.courseTableDataSec.set(courseTableDataSec);
    }
}
