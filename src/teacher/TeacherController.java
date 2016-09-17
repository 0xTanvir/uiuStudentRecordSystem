package teacher;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.StringUtils;
import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Duration;
import menuBar.MenuBarControl;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import static jdk.nashorn.internal.objects.NativeMath.round;

/**
 * Created by Tanvir on 8/21/2016.
 */
public class TeacherController {

    @FXML
    ComboBox<String> teacherTableSecChoose;

    @FXML
    TableView<TeacherTableData> teacherTableView;
    @FXML
    TableColumn<TeacherTableData,String> teacherColumnID;
    @FXML
    TableColumn<TeacherTableData,String> teacherColumnName;
    @FXML
    TableColumn<TeacherTableData,String> teacherColumnGPA;
    @FXML
    TableColumn<TeacherTableData,String> teacherColumnPhone;
    @FXML
    TableColumn<TeacherTableData,String> teacherEmail;

    @FXML
    TextField teacherTFAttendance;
    @FXML
    TextField teacherTFAssignment;
    @FXML
    TextField teacherTFPresentation;
    @FXML
    TextField teacherTFClassTest;
    @FXML
    TextField teacherTFMidterm;
    @FXML
    TextField teacherTFFinal;

    @FXML
    Button teacherCancelButton;
    @FXML
    Button teacherAddGPAButton;
    @FXML
    Button teacherLoadButton;

    @FXML
    Text name;
    @FXML
    Text id;


    private DBConnection database = new DBConnection();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet,resultSet1;
    private MenuBarControl menuBarControl = new MenuBarControl();
    private static String email;

    private String selectedID;
    private String courseID;

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }


    private void setSelectedID(String selectedID){
        this.selectedID = selectedID;
    }

    public static void setEmail(String email) {
        TeacherController.email = email;
    }

    private ObservableList getDataFromTeacherTableDataAndAddToObservableList(String query) {
        ObservableList<TeacherTableData> teacherTableDatas = FXCollections.observableArrayList();
        try {
            String id = null,name =null,phone=null,email = null,thisCourseGpa=null;
            String idList[][] = new String[50][2];
            int i = 0;
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet1 = statement.executeQuery("SELECT * FROM studentgpa where  dbstudentgpaCurrentCourse LIKE '%"+query+"%'");

            while (resultSet1.next()){
                idList[i][0] = resultSet1.getString("dbstudentgpaID");
                idList[i][1] = resultSet1.getString("dbstudentgpaCurrentCourseGPA");
                i++;
            }


            for (int j=0;idList[j][0]!=null&&!idList[j][0].isEmpty();j++) {

                if (idList[j][1]!=null&&!idList[j][1].isEmpty()){

                    if (idList[j][1].contains(",")){

                        String gpaAllSecCut[] = idList[j][1].split(",",0);
                        for (String s:gpaAllSecCut) {

                            if(s.contains(query)){
                                thisCourseGpa = s.substring(s.lastIndexOf(":")+1);
                            }
                            else thisCourseGpa = null;
                        }

                    }
                    else {
                        if(idList[j][1].contains(query)) {
                            thisCourseGpa = idList[j][1].substring(idList[j][1].lastIndexOf(":") + 1);
                        }
                        else thisCourseGpa = null;
                    }
                }
                else thisCourseGpa = null;
                resultSet = statement.executeQuery("SELECT * FROM student where  dbStudentID = '"+idList[j][0]+"'");/////student.dbStudentID
                while (resultSet.next()){
                    name=resultSet.getString("dbStudentFname")+" "+resultSet.getString("dbStudentLname");
                    phone = resultSet.getString("dbStudentPhone");
                    email = resultSet.getString("dbStudentEmail");
                }

                teacherTableDatas.add(new TeacherTableData(idList[j][0],name,thisCourseGpa,phone,email));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teacherTableDatas;
    }

    @FXML
    private void initialize(){

        teacherColumnID.setCellValueFactory(new PropertyValueFactory<TeacherTableData,String>("teacherTableDataID"));
        teacherColumnName.setCellValueFactory(new PropertyValueFactory<TeacherTableData,String>("teacherTableDataName"));
        teacherColumnGPA.setCellValueFactory(new PropertyValueFactory<TeacherTableData,String>("teacherTableDataGPA"));
        teacherColumnPhone.setCellValueFactory(new PropertyValueFactory<TeacherTableData,String>("teacherTableDataPhone"));
        teacherEmail.setCellValueFactory(new PropertyValueFactory<TeacherTableData,String>("teacherTableDataEmail"));

        String teacherSecs[]=null,teacherSec=null;

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM teacher WHERE dbTeacherEmail = '"+email+"';");
            while (resultSet.next()){
                teacherSec=resultSet.getString("dbTeacherCourse");
            }
            if (teacherSec.contains(",")){
                teacherSecs = teacherSec.split(",",0);
            }
            else if (!teacherSec.isEmpty()){
                teacherSecs[0] = teacherSec;
            }
            else {
                System.out.println("There is no teacher sec");
            }

            ObservableList<String> sec = FXCollections.observableArrayList(teacherSecs);
            teacherTableSecChoose.setItems(sec);
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        teacherTableSecChoose.getSelectionModel().select(teacherSecs[0]);
        setCourseID(teacherSecs[0]);

        teacherTableView.setItems(getDataFromTeacherTableDataAndAddToObservableList(teacherSecs[0]));


    }

    @FXML
    private void setTeacherLoadClick(Event event) throws SQLException {

        setCourseID(teacherTableSecChoose.getValue());
        teacherTableView.setItems(getDataFromTeacherTableDataAndAddToObservableList(teacherTableSecChoose.getValue()));
    }

    @FXML
    private void setTeacherSelectClick(Event event){

        name.setText(teacherTableView.getSelectionModel().getSelectedItem().getTeacherTableDataName());
        id.setLayoutX(name.getLayoutX()+10+name.getBoundsInParent().getWidth());
        id.setText("("+teacherTableView.getSelectionModel().getSelectedItem().getTeacherTableDataID()+")");

        setSelectedID(teacherTableView.getSelectionModel().getSelectedItem().getTeacherTableDataID());
        setAllEnable();

    }

    private void setAllDisable(){
        teacherTFAttendance.setDisable(true);
        teacherTFAssignment.setDisable(true);
        teacherTFPresentation.setDisable(true);
        teacherTFClassTest.setDisable(true);
        teacherTFMidterm.setDisable(true);
        teacherTFFinal.setDisable(true);
        teacherTFFinal.setDisable(true);

        teacherTableSecChoose.setDisable(false);
        teacherLoadButton.setDisable(false);

        teacherCancelButton.setDisable(true);
        teacherAddGPAButton.setDisable(true);
    }

    private void setAllEnable(){
        teacherTFAttendance.setDisable(false);
        teacherTFAssignment.setDisable(false);
        teacherTFPresentation.setDisable(false);
        teacherTFClassTest.setDisable(false);
        teacherTFMidterm.setDisable(false);
        teacherTFFinal.setDisable(false);
        teacherTFFinal.setDisable(false);

        teacherTableSecChoose.setDisable(true);
        teacherLoadButton.setDisable(true);

        teacherCancelButton.setDisable(false);
        teacherAddGPAButton.setDisable(false);
    }

    private void setAllClear(){
        teacherTFAttendance.clear();
        teacherTFAssignment.clear();
        teacherTFPresentation.clear();
        teacherTFClassTest.clear();
        teacherTFMidterm.clear();
        teacherTFFinal.clear();
        teacherTFFinal.clear();
    }


    @FXML
    private void setTeacherCancelClick(Event event){
        setAllDisable();
        setAllClear();
        name.setText("Name");
        id.setLayoutX(name.getLayoutX()+10+name.getBoundsInParent().getWidth());
        id.setText("(ID)");
    }


    @FXML
    private void setTeacherAddGPAClick(Event event){
        name.setText("Name");
        id.setLayoutX(name.getLayoutX()+10+name.getBoundsInParent().getWidth());
        id.setText("(ID)");

        Double attendance = 0.00,assignment,presentation,classTest,midterm,finalm,total,totalGpa = 0.00;
        attendance = Double.parseDouble(teacherTFAttendance.getText().trim());
        assignment = Double.parseDouble(teacherTFAssignment.getText().trim());
        presentation = Double.parseDouble(teacherTFPresentation.getText().trim());
        classTest = Double.parseDouble(teacherTFClassTest.getText().trim());
        midterm = Double.parseDouble(teacherTFMidterm.getText().trim());
        finalm = Double.parseDouble(teacherTFFinal.getText().trim());


        if (!teacherTFAttendance.getText().isEmpty()&& attendance <=5.0&&
                !teacherTFAssignment.getText().isEmpty()&& assignment <=5.0&&
                !teacherTFPresentation.getText().isEmpty()&& presentation <=5.0&&
                !teacherTFClassTest.getText().isEmpty()&& classTest <=15.0&&
                !teacherTFMidterm.getText().isEmpty()&& midterm <=30.0&&
                !teacherTFFinal.getText().isEmpty()&& finalm <=40.0){


            total = attendance+assignment+presentation+classTest+midterm+finalm;

            if (total>=90.0&&total<=100.0){
                totalGpa=4.0;
            }
            else if (total>=86.0&&total<90.0){
                totalGpa=3.67;
            }
            else if (total>=82.0&&total<86.0){
                totalGpa=3.33;
            }
            else if (total>=78.0&&total<82.0){
                totalGpa=3.00;
            }
            else if (total>=74.0&&total<78.0){
                totalGpa=2.67;
            }
            else if (total>=70.0&&total>74.0){
                totalGpa=2.33;
            }
            else if (total>=66.0&&total<70.0){
                totalGpa=2.00;
            }
            else if (total>=62.0&&total<66.0){
                totalGpa=1.67;
            }
            else if (total>=58.0&&total>62.0){
                totalGpa=1.33;
            }
            else if (total>=55.0&&total>58.0){
                totalGpa=1.00;
            }


            try {
                String currentGpa = null,updatedGpa,cCorce = null;
                connection = database.getConnection();
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM studentgpa where  dbstudentgpaID = '"+selectedID+"';");
                while (resultSet.next()){
                    currentGpa = "a"+resultSet.getString("dbstudentgpaCurrentCourseGPA");
                    cCorce = resultSet.getString("dbstudentgpaCurrentCourse");
                }

                currentGpa = currentGpa.substring(1);
                if ((!currentGpa.isEmpty())&&(currentGpa != null)&&(!currentGpa.equals("null"))&&(!currentGpa.contains(courseID))){//&&currentGpa != null

                    updatedGpa = currentGpa+","+courseID+":"+totalGpa;
                }
                else {
                    updatedGpa = courseID+":"+totalGpa;
                }
                statement.executeUpdate("UPDATE studentgpa set dbstudentgpaCurrentCourseGPA = '"+updatedGpa+"' where dbstudentgpaID = '"+selectedID+"';");


                if(cCorce.split(",").length==updatedGpa.split(",").length){


                    String columnNo=null,courseCredit = null;


                    double completedCredit = 0.00,previouseCGPA = 0.00,currentTotal = 0.00,currentCompleteCredit = 0.00,updatedCGPA;
                    double previousCCredit = 0.00;


                    resultSet = statement.executeQuery("SELECT * FROM studentgpa where  dbstudentgpaID = '"+selectedID+"';");
                    String dbStudentColumnNo = null;
                    while (resultSet.next()){
                        for (int i = 1; i <= 16; i++) {
                            columnNo = "dbstudentgpa"+i+"thSem";
                            dbStudentColumnNo = "dbStudent"+i+"thSemGpa";
                            String temp = "a"+resultSet.getString(columnNo);
                            temp = temp.substring(1);
                            if (temp.equals("null")||temp.isEmpty()){
                                break;
                            }
                        }

                        completedCredit = resultSet.getDouble("dbstudentgpaCCredit");
                        previousCCredit = completedCredit;
                        previouseCGPA = resultSet.getDouble("dbstudentgpaCGPA");
                    }



                    //dbStudentgpaCurrentCoursegpaString modify it for calculate gpa and cgpa;
                    String[] courseList = updatedGpa.split(",",0);
                    for (String co:courseList) {
                        String[] splitCourse = co.split(":",0);
                        resultSet = statement.executeQuery("SELECT * FROM course where  dbCourseCode = '"+splitCourse[0]+"';");
                        while (resultSet.next()){
                            courseCredit = resultSet.getString("dbCourseCredit");

                        }

                        currentTotal = currentTotal+Double.parseDouble(courseCredit)* Double.parseDouble(splitCourse[2]);
                        currentCompleteCredit = currentCompleteCredit + Double.parseDouble(courseCredit);

                    }

                    //Previous Completed credit * previous cgpa + currentcredit*current GPA  /TOTAL CREDIT
                    completedCredit = completedCredit+currentCompleteCredit;

                    updatedCGPA = ((previouseCGPA* previousCCredit)+currentTotal)/(completedCredit);

                    //System.out.println("Current sum = "+currentTotal+"Previous Total = "+(previouseCGPA* previousCCredit));

                    statement.executeUpdate("UPDATE studentgpa set "+columnNo+" = '"+updatedGpa+"', dbstudentgpaCGPA = "+ updatedCGPA+",dbstudentgpaCCredit = "+completedCredit+" where dbstudentgpaID = '"+selectedID+"';");


                    double thisSemGpa  = currentTotal/currentCompleteCredit;
                    //thisSemGpa = round(thisSemGpa, 2);

                    DecimalFormat df = new DecimalFormat("#.##");
                    thisSemGpa = Double.valueOf(df.format(thisSemGpa));

                    //This line will be update gpa on student profile

                    statement.executeUpdate("UPDATE student set "+ dbStudentColumnNo+" = '"+Double.toString(thisSemGpa)+"', dbStudentCgpa = "+ updatedCGPA+" where dbStudentID = '"+selectedID+"';");



                    statement.executeUpdate("UPDATE studentgpa set dbstudentgpaCurrentCourse = '' , dbstudentgpaCurrentCourseGPA = '' where dbstudentgpaID = '"+selectedID+"';");

                }




            } catch (SQLException e) {
                e.printStackTrace();
            }


            System.out.println("Cgpa add Successfully");

        }
        else{
            NotificationType notificationType = NotificationType.ERROR;
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Wrong Information");
            tray.setMessage("Something is Wrong Please Check it again.");
            tray.setNotificationType(notificationType);
            tray.showAndDismiss(Duration.millis(3000));
        }


        setAllDisable();
        setAllClear();
    }

    @FXML
    private void setCourseAboutButtonClick(Event event) throws IOException {
        menuBarControl.about();
    }

    @FXML
    private void setCourseCloseButtonClick(Event event){
        menuBarControl.close();
    }



}
