package student;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Tanvir on 8/25/2016.
 */
public class resultHistoryTableData {
    private final SimpleStringProperty resultHistoryTableCourseCode;
    private final SimpleStringProperty resultHistoryTableSec;
    private final SimpleStringProperty resultHistoryTableGPA;

    public resultHistoryTableData(String resultHistoryTableCourseCode, String resultHistoryTableSec, String resultHistoryTableGPA) {
        this.resultHistoryTableCourseCode = new SimpleStringProperty(resultHistoryTableCourseCode);
        this.resultHistoryTableSec = new SimpleStringProperty(resultHistoryTableSec);
        this.resultHistoryTableGPA = new SimpleStringProperty(resultHistoryTableGPA);
    }

    public String getResultHistoryTableCourseCode() {
        return resultHistoryTableCourseCode.get();
    }

    public SimpleStringProperty resultHistoryTableCourseCodeProperty() {
        return resultHistoryTableCourseCode;
    }

    public void setResultHistoryTableCourseCode(String resultHistoryTableCourseCode) {
        this.resultHistoryTableCourseCode.set(resultHistoryTableCourseCode);
    }

    public String getResultHistoryTableSec() {
        return resultHistoryTableSec.get();
    }

    public SimpleStringProperty resultHistoryTableSecProperty() {
        return resultHistoryTableSec;
    }

    public void setResultHistoryTableSec(String resultHistoryTableSec) {
        this.resultHistoryTableSec.set(resultHistoryTableSec);
    }

    public String getResultHistoryTableGPA() {
        return resultHistoryTableGPA.get();
    }

    public SimpleStringProperty resultHistoryTableGPAProperty() {
        return resultHistoryTableGPA;
    }

    public void setResultHistoryTableGPA(String resultHistoryTableGPA) {
        this.resultHistoryTableGPA.set(resultHistoryTableGPA);
    }
}
