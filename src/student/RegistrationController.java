package student;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import menuBar.MenuBarControl;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Created by Tanvir on 8/20/2016.
 */
public class RegistrationController implements Initializable {

    private DBConnection database = new DBConnection();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet,resultSet1;
    private MenuBarControl menuBarControl = new MenuBarControl();


    private static String ID,ChoosingSec,finalAllCourse="empty",courseCode;
    static Stage stage;
    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void setID(String ID){
        this.ID = ID;
    }
    public void setChoosingSec(String ChoosingSec){
        this.ChoosingSec=ChoosingSec;
    }


    @FXML
    TextField registrationTFSearch;


    @FXML
    TableView<RegistrationTableData> studentCurrentCourseTableView;
    @FXML
    TableColumn<RegistrationTableData,String> studentCCourseColumnCode;
    @FXML
    TableColumn<RegistrationTableData,String> studentCCourseColumnTitle;
    @FXML
    TableColumn<RegistrationTableData,Integer> studentCCourseColumnCredit;
    @FXML
    TableColumn<RegistrationTableData,String> studentCCourseColumnSec;



    @FXML
    TableView<RegistrationTableData> studentAllCourseTableView;
    @FXML
    TableColumn<RegistrationTableData,String> studentACourseColumnCode;
    @FXML
    TableColumn<RegistrationTableData,String> studentACourseColumnTitle;
    @FXML
    TableColumn<RegistrationTableData,Integer> studentACourseColumnCredit;
    @FXML
    TableColumn<RegistrationTableData,String> studentACourseColumnSec;

    private static TableView tanother;
    public void setTanother(TableView a){
        this.tanother = a;
    }


    private ObservableList getDataFromAllCourseAndAddToObservableList(String query){
        ObservableList<RegistrationTableData> allCourseTableData = FXCollections.observableArrayList();
        try {

            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);//"SELECT * FROM cource;"
            while(resultSet.next()){
                allCourseTableData.add(new RegistrationTableData(
                        resultSet.getString("dbCourseCode"),
                        resultSet.getString("dbCourseTitle"),
                        resultSet.getInt("dbCourseCredit"),
                        resultSet.getString("dbCourseSec")

                ));
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCourseTableData;
    }

    public ObservableList getDataFromCurrentCourseAndAddToObservableList(String query){
        ObservableList<RegistrationTableData> currentCourseTableData = FXCollections.observableArrayList();
        try {

            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);//"SELECT * FROM cource;"
            String allCourse = null;

            while(resultSet.next()){
                allCourse = "a"+resultSet.getString("dbStudentgpaCurrentCourse");
            }

            if (!(allCourse.equals("anull"))&&!(allCourse.equals("a"))){
                allCourse=allCourse.substring(1);
                finalAllCourse = allCourse;
                String cCode = null,cSec = null,cName = null;
                int cCredit = 0;
                String[] Courses = allCourse.split(",", 0);
                for (String s:Courses) {
                    cCode=s.substring(0,s.indexOf(":"));
                    cSec=s.substring(s.indexOf(":")+1);
                    resultSet1 = statement.executeQuery("SELECT * FROM course WHERE dbCourseCode = '"+cCode+"';");
                    while (resultSet1.next()){
                        cName =  resultSet1.getString("dbCourseTitle");
                        cCredit =  resultSet1.getInt("dbCourseCredit");
                    }
                    currentCourseTableData.add(new RegistrationTableData(cCode,cName,cCredit,cSec));
                }
            }

            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentCourseTableData;
    }

    public void studentRegistrationSectionUpdate() throws SQLException {
        connection = database.getConnection();
        statement = connection.createStatement();

        if (!finalAllCourse.equals("empty")){
            finalAllCourse=finalAllCourse+","+courseCode+":"+ChoosingSec;

            int rowsAffected  = statement.executeUpdate("update studentgpa set "+"dbStudentgpaCurrentCourse"+
                    " ='"+finalAllCourse+"' where dbstudentgpaID = '"+ID+"';");
        }

        else {
            finalAllCourse = courseCode+":"+ChoosingSec;
            int rowsAffected  = statement.executeUpdate("update studentgpa set dbStudentgpaCurrentCourse ='"+finalAllCourse+"' where dbstudentgpaID = '"+ID+"';");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentACourseColumnCode.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,String>("courseTableDataCode"));
        studentACourseColumnTitle.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,String>("courseTableDataTitle"));
        studentACourseColumnCredit.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,Integer>("courseTableDataCredit"));
        studentACourseColumnSec.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,String>("courseTableDataSec"));

        studentAllCourseTableView.setItems(getDataFromAllCourseAndAddToObservableList("SELECT * FROM course;"));


        studentCCourseColumnCode.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,String>("courseTableDataCode"));
        studentCCourseColumnTitle.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,String>("courseTableDataTitle"));
        studentCCourseColumnCredit.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,Integer>("courseTableDataCredit"));
        studentCCourseColumnSec.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,String>("courseTableDataSec"));

        studentCurrentCourseTableView.setItems(getDataFromCurrentCourseAndAddToObservableList("SELECT * FROM studentgpa WHERE dbstudentgpaID = '"+ID+"';"));

    }

    @FXML
    private void setRegistrationRefreshClick(Event event){
        studentCurrentCourseTableView.setItems(getDataFromCurrentCourseAndAddToObservableList("SELECT * FROM studentgpa WHERE dbstudentgpaID = '"+ID+"';"));
        studentAllCourseTableView.setItems(getDataFromAllCourseAndAddToObservableList("SELECT * FROM course;"));
    }

    @FXML
    private void setRegistrationTakeClick(Event event) throws Exception{
        RegistrationTableData getSelectedCourse = studentAllCourseTableView.getSelectionModel().getSelectedItem();
        ChooseASectionController chooseASectionController = new ChooseASectionController();
        chooseASectionController.setAllSection(getSelectedCourse.getCourseTableDataSec());
        courseCode = getSelectedCourse.getCourseTableDataCode();


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/student/ChooseASection.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(new Scene(p));
        chooseASectionController.setStage(stage);
        stage.show();

    }

    @FXML
    private void setRegistrationDeleteClick(Event event) throws SQLException {
        RegistrationTableData getSelectedCourse = studentCurrentCourseTableView.getSelectionModel().getSelectedItem();
        String primaryCourse = null,finalCourse;
        connection = database.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM studentgpa where dbstudentgpaID = '"+ID+"';");
        while (resultSet.next()){
            primaryCourse = resultSet.getString("dbStudentgpaCurrentCourse");
        }
        finalCourse = getSelectedCourse.getCourseTableDataCode()+":"+getSelectedCourse.getCourseTableDataSec();

        if (!(primaryCourse.contains(","))){
            finalAllCourse="empty";
        }
        else if(primaryCourse.startsWith(finalCourse)){
            finalCourse=finalCourse+",";
        }
        else {
            finalCourse=","+finalCourse;
        }
        finalCourse = primaryCourse.replace(finalCourse,"");
        statement.executeUpdate("update studentgpa set dbStudentgpaCurrentCourse ='"+finalCourse+"' where dbstudentgpaID = '"+ID+"';");
        studentCurrentCourseTableView.setItems(getDataFromCurrentCourseAndAddToObservableList("SELECT * FROM studentgpa WHERE dbstudentgpaID = '"+ID+"';"));


    }

    @FXML
    private void setRegistrationDoneClick(Event event){
        stage.close();
        tanother.setItems(getDataFromCurrentCourseAndAddToObservableList("SELECT * FROM studentgpa WHERE dbstudentgpaID = '"+ID+"';"));
    }

    @FXML
    private void setRegistrationSearchClick(Event event){
        String sqlQuery = "select * FROM course where dbCourseCode = '"+registrationTFSearch.getText()+"';";
        studentAllCourseTableView.setItems(getDataFromAllCourseAndAddToObservableList(sqlQuery));
        registrationTFSearch.clear();
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
