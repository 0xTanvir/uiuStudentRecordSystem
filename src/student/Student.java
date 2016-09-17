package student;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import menuBar.MenuBarControl;
import profileView.Info;
import profileView.ProfileView;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Tanvir on 8/3/2016.
 */
public class Student {

    @FXML
    TableView<RegistrationTableData> studentRunningCourseTableView;
    @FXML
    TableColumn<RegistrationTableData,String> studentRCourseColumnCode;
    @FXML
    TableColumn<RegistrationTableData,String> studentRCourseColumnTitle;
    @FXML
    TableColumn<RegistrationTableData,Integer> studentRCourseColumnCredit;
    @FXML
    TableColumn<RegistrationTableData,String> studentRCourseColumnSec;


    @FXML
    TableView<resultHistoryTableData> resultHistoryTableView;
    @FXML
    TableColumn<resultHistoryTableData,String> resultHistoryColumnCode;
    @FXML
    TableColumn<resultHistoryTableData,String> resultHistoryColumnSec;
    @FXML
    TableColumn<resultHistoryTableData,String> resultHistoryColumnGPA;

    @FXML
    TextField studentTFFname;
    @FXML
    TextField studentTFLname;
    @FXML
    TextField studentTFID;
    @FXML
    TextField studentTFEmail;
    @FXML
    TextField studentTFPhone;
    @FXML
    TextField studentTFGFname;
    @FXML
    TextField studentTFGLname;
    @FXML
    TextField studentTFAddress;
    @FXML
    TextField studentTFPassword;
    @FXML
    TextField studentTFPicChooser;

    @FXML
    Button studentSaveClick;
    @FXML
    Button studentCancelClick;


    private DBConnection database = new DBConnection();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    RegistrationController registrationController;

    private MenuBarControl menuBarControl = new MenuBarControl();

    static String ID;
    public void setStudentId(String ID){
        this.ID = ID;
    }



    private ObservableList getDataFromResultHistoryAndAddToObservableList(String query) {
        ObservableList<resultHistoryTableData> resultHistoryTableData = FXCollections.observableArrayList();



        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            String columnNo = null;
            String[] devide = null,partPart = null;
            while (resultSet.next()){
                for (int i = 1; i < 16; i++) {
                    columnNo = resultSet.getString("dbstudentgpa"+i+"thSem");

                    String temp = "a"+columnNo;
                    temp = temp.substring(1);
                    if (temp.equals("null")||temp.isEmpty()){
                        break;
                    }
                    else {
                        devide = columnNo.split(",", 0);
                        for (String s : devide) {
                            partPart = s.split(":", 0);
                            resultHistoryTableData.add(new resultHistoryTableData(partPart[0], partPart[1], partPart[2]));
                        }
                    }

                }
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }



        return resultHistoryTableData;
    }



    private void setAllEnable(){
        studentTFFname.setDisable(false);
        studentTFLname.setDisable(false);
        //studentTFID.setDisable(false);
        studentTFEmail.setDisable(false);
        studentTFPhone.setDisable(false);
        studentTFGFname.setDisable(false);
        studentTFGLname.setDisable(false);
        studentTFAddress.setDisable(false);
        studentTFPassword.setDisable(false);
        studentTFPicChooser.setDisable(false);

        studentSaveClick.setDisable(false);
        studentCancelClick.setDisable(false);
    }

    private void setAllDisable(){
        studentTFFname.setDisable(true);
        studentTFLname.setDisable(true);
        //studentTFID.setDisable(true);
        studentTFEmail.setDisable(true);
        studentTFPhone.setDisable(true);
        studentTFGFname.setDisable(true);
        studentTFGLname.setDisable(true);
        studentTFAddress.setDisable(true);
        studentTFPassword.setDisable(true);
        studentTFPicChooser.setDisable(true);

        studentSaveClick.setDisable(true);
        studentCancelClick.setDisable(true);
    }

    private void setAllClear(){
        studentTFFname.clear();
        studentTFLname.clear();
        studentTFID.clear();
        studentTFEmail.clear();
        studentTFPhone.clear();
        studentTFGFname.clear();
        studentTFGLname.clear();
        studentTFAddress.clear();
        studentTFPassword.clear();
        studentTFPassword.clear();
        studentTFPicChooser.clear();
    }

    @FXML
    private void setStudentEditProfileClick(Event event){
        setAllEnable();
        String sqlQuery = "select * FROM student where dbStudentID = '"+ID+"';";
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()) {
                studentTFFname.setText(resultSet.getString("dbStudentFname"));
                studentTFLname.setText(resultSet.getString("dbStudentLname"));
                studentTFID.setText(resultSet.getString("dbStudentID"));
                studentTFEmail.setText(resultSet.getString("dbStudentEmail"));
                studentTFPassword.setText(resultSet.getString("dbStudentPassword"));
                studentTFPhone.setText(resultSet.getString("dbStudentPhone"));
                studentTFAddress.setText(resultSet.getString("dbStudentAddress"));
                studentTFGFname.setText(resultSet.getString("dbGuardianFname"));
                studentTFGLname.setText(resultSet.getString("dbGuardianLname"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void setStudentViewProfileClick(Event event) throws IOException {

        Info info = new Info(ID);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/profileView/profileView.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));

        ProfileView profileView = loader.getController();
        profileView.setCurrentInfo(info);
        stage.show();
    }

    @FXML
    private void setStudentCancelClick(Event event){
        setAllDisable();
        setAllClear();
    }

    @FXML
    private void setStudentSaveClick(Event event){
        try {
            connection = database.getConnection();
            statement = connection.createStatement();

            int rowsAffected = statement.executeUpdate("update student set "+
                    "dbStudentFname = '"+studentTFFname.getText()+"',"+
                    "dbStudentLname = '"+studentTFLname.getText()+"',"+
                    "dbStudentID = '"+studentTFID.getText()+"',"+
                    "dbStudentEmail = '"+studentTFEmail.getText()+"',"+
                    "dbStudentPassword = '"+studentTFPassword.getText()+"',"+
                    "dbStudentPhone = '"+studentTFPhone.getText()+"',"+
                    "dbStudentAddress = '"+studentTFAddress.getText()+"',"+
                    "dbGuardianFname = '"+studentTFGFname.getText()+"',"+
                    "dbGuardianLname = '"+studentTFGLname.getText()+
                    "' where dbStudentID = '"+
                    ID+"';");

            connection.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        setAllClear();
        setAllDisable();

    }

    @FXML
    private void setStudentRegistrationClick(Event event)throws IOException{
        RegistrationController re = new RegistrationController();
        re.setID(ID);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/student/Registration.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        RegistrationController registrationController = new RegistrationController();
        registrationController.setStage(stage);
        registrationController.setTanother(studentRunningCourseTableView);
        stage.show();

    }

    @FXML
    private void initialize(){
        studentRCourseColumnCode.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,String>("courseTableDataCode"));
        studentRCourseColumnTitle.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,String>("courseTableDataTitle"));
        studentRCourseColumnCredit.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,Integer>("courseTableDataCredit"));
        studentRCourseColumnSec.setCellValueFactory(new PropertyValueFactory<RegistrationTableData,String>("courseTableDataSec"));

        registrationController = new RegistrationController();
        studentRunningCourseTableView.setItems(registrationController.getDataFromCurrentCourseAndAddToObservableList("SELECT * FROM studentgpa WHERE dbstudentgpaID = '"+ID+"';"));


        resultHistoryColumnCode.setCellValueFactory(new PropertyValueFactory<resultHistoryTableData,String>("resultHistoryTableCourseCode"));
        resultHistoryColumnSec.setCellValueFactory(new PropertyValueFactory<resultHistoryTableData,String>("resultHistoryTableSec"));
        resultHistoryColumnGPA.setCellValueFactory(new PropertyValueFactory<resultHistoryTableData,String>("resultHistoryTableGPA"));

        resultHistoryTableView.setItems(getDataFromResultHistoryAndAddToObservableList("SELECT * FROM studentgpa WHERE dbstudentgpaID = '"+ID+"';"));

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
