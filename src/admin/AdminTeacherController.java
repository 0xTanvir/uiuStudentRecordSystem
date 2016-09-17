package admin;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import menuBar.MenuBarControl;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * Created by Tanvir on 8/19/2016.
 */
public class AdminTeacherController implements Initializable {

    private DBConnection database = new DBConnection();
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private boolean isAdminTeacherAddNewButtonClick;
    private boolean isAdminTeacherEditButtonClick;
    private MenuBarControl menuBarControl = new MenuBarControl();
    private String temp ;



    @FXML
    TableView<AdminTeacherTable> adminTeacherTableView;
    @FXML
    TableColumn<AdminTeacherTable,Integer> adminTeacherColumnNo;
    @FXML
    TableColumn<AdminTeacherTable,String> adminTeacherColumnName;
    @FXML
    TableColumn<AdminTeacherTable,String> adminTeacherColumnID;
    @FXML
    TableColumn<AdminTeacherTable,String> adminTeacherColumnEmail;
    @FXML
    TableColumn<AdminTeacherTable,String> adminTeacherColumnDepartment;
    @FXML
    TableColumn<AdminTeacherTable,String> adminTeacherColumnRunningCourse;


    @FXML
    private TextField adminTeacherTFFname;
    @FXML
    private TextField adminTeacherTFLname;
    @FXML
    private TextField adminTeacherTFID;
    @FXML
    private TextField adminTeacherTFEmail;
    @FXML
    private TextField adminTeacherTFDepartment;
    @FXML
    private TextField adminTeacherTFSec;
    @FXML
    private TextField adminTeacherTFSearch;


    @FXML
    private Button adminTeacherClearButtonClick;
    @FXML
    private Button adminTeacherSaveButtonClick;


    private ObservableList getDataFromTeacherAndAddToObservableList(String query){
        ObservableList<AdminTeacherTable> adminTeacherTableData = FXCollections.observableArrayList();
        try {

            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);//"SELECT * FROM teacher;"
            while(resultSet.next()){
                adminTeacherTableData.add(new AdminTeacherTable(
                        resultSet.getInt("dbTeacherSerialNo"),
                        resultSet.getString("dbTeacherFname")+" "+resultSet.getString("dbTeacherLname"),
                        resultSet.getString("dbTeacherID"),
                        resultSet.getString("dbTeacherEmail"),
                        resultSet.getString("dbTeacherDepartment"),
                        resultSet.getString("dbTeacherCourse")
                ));
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminTeacherTableData;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adminTeacherColumnNo.setCellValueFactory(new PropertyValueFactory<AdminTeacherTable,Integer>("AdminTeacherTableDataNo"));
        adminTeacherColumnName.setCellValueFactory(new PropertyValueFactory<AdminTeacherTable,String>("AdminTeacherTableDataName"));
        adminTeacherColumnID.setCellValueFactory(new PropertyValueFactory<AdminTeacherTable,String>("AdminTeacherTableDataID"));
        adminTeacherColumnEmail.setCellValueFactory(new PropertyValueFactory<AdminTeacherTable,String>("AdminTeacherTableDataEmail"));
        adminTeacherColumnDepartment.setCellValueFactory(new PropertyValueFactory<AdminTeacherTable,String>("AdminTeacherTableDataDepartment"));
        adminTeacherColumnRunningCourse.setCellValueFactory(new PropertyValueFactory<AdminTeacherTable,String>("AdminTeacherTableDataSec"));

        adminTeacherTableView.setItems(getDataFromTeacherAndAddToObservableList("SELECT * FROM teacher;"));

    }

    @FXML
    private void setAdminTeacherAddNewButtonClick(Event event){
        adminTeacherSetAllEnable();
        isAdminTeacherAddNewButtonClick = true;
    }

    private void adminTeacherSetAllEnable(){
        adminTeacherTFFname.setDisable(false);
        adminTeacherTFLname.setDisable(false);
        adminTeacherTFID.setDisable(false);
        adminTeacherTFEmail.setDisable(false);
        adminTeacherTFDepartment.setDisable(false);
        adminTeacherTFSec.setDisable(false);

        adminTeacherClearButtonClick.setDisable(false);
        adminTeacherSaveButtonClick.setDisable(false);
    }

    private void adminTeacherSetAllDisable(){
        adminTeacherTFFname.setDisable(true);
        adminTeacherTFLname.setDisable(true);
        adminTeacherTFID.setDisable(true);
        adminTeacherTFEmail.setDisable(true);
        adminTeacherTFDepartment.setDisable(true);
        adminTeacherTFSec.setDisable(true);

        adminTeacherClearButtonClick.setDisable(true);
        adminTeacherSaveButtonClick.setDisable(true);
    }

    private void adminTeacherSetAllClear(){
        adminTeacherTFFname.clear();
        adminTeacherTFLname.clear();
        adminTeacherTFID.clear();
        adminTeacherTFDepartment.clear();
        adminTeacherTFEmail.clear();
        adminTeacherTFSec.clear();

    }

    @FXML
    private void setAdminTeacherEditButtonClick(Event event){
        AdminTeacherTable getSelectedRow = adminTeacherTableView.getSelectionModel().getSelectedItem();


        String sqlQuery = "select * FROM teacher where dbTeacherID = '"+getSelectedRow.getAdminTeacherTableDataID()+"';";

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            adminTeacherSetAllEnable();
            while(resultSet.next()) {
                adminTeacherTFFname.setText(resultSet.getString("dbTeacherFname"));
                adminTeacherTFLname.setText(resultSet.getString("dbTeacherLname"));
                adminTeacherTFID.setText(resultSet.getString("dbTeacherID"));
                adminTeacherTFEmail.setText(resultSet.getString("dbTeacherEmail"));
                adminTeacherTFDepartment.setText(resultSet.getString("dbTeacherDepartment"));
                adminTeacherTFSec.setText(resultSet.getString("dbTeacherCourse"));

            }

            temp =adminTeacherTFID.getText();
            isAdminTeacherEditButtonClick = true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void setAdminTeacherSaveButtonClick(Event event){

        try{
            connection = database.getConnection();
            statement = connection.createStatement();
            if(isAdminTeacherAddNewButtonClick){
                int rowsAffected = statement.executeUpdate("insert into `teacher` (`dbTeacherFname`,`dbTeacherLname`,`dbTeacherID`,`dbTeacherEmail`,`dbTeacherDepartment`,`dbTeacherCourse`) values ('"+
                        adminTeacherTFFname.getText()+"','"+adminTeacherTFLname.getText()+"','"+adminTeacherTFID.getText()+"','"+adminTeacherTFEmail.getText()
                        +"','"+adminTeacherTFDepartment.getText()+"','"+adminTeacherTFSec.getText()+"') ;");
            }
            else if (isAdminTeacherEditButtonClick){
                int rowsAffected = statement.executeUpdate("update teacher set "+
                        "dbTeacherFname = '"+adminTeacherTFFname.getText()+"',"+
                        "dbTeacherLname = '"+adminTeacherTFLname.getText()+"',"+
                        "dbTeacherID = '"+adminTeacherTFID.getText()+"',"+
                        "dbTeacherEmail = '"+adminTeacherTFEmail.getText()+"',"+
                        "dbTeacherDepartment = '"+adminTeacherTFDepartment.getText()+"',"+
                        "dbTeacherCourse = '"+adminTeacherTFSec.getText()+

                        "' where dbTeacherID = '"+
                        temp+"';");
            }


            connection.close();
            statement.close();
            resultSet.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        adminTeacherSetAllClear();
        adminTeacherSetAllDisable();
        adminTeacherTableView.setItems(getDataFromTeacherAndAddToObservableList("SELECT * FROM teacher;"));
        isAdminTeacherAddNewButtonClick=false;
        isAdminTeacherEditButtonClick = false;

    }

    @FXML
    private void setAdminTeacherClearButtonClick(Event event){
        adminTeacherSetAllClear();
        adminTeacherSetAllDisable();
        isAdminTeacherAddNewButtonClick = false;
        isAdminTeacherEditButtonClick = false;
    }

    @FXML
    private void setAdminTeacherDeleteButtonClick(Event event){
        AdminTeacherTable getSelectedRow = adminTeacherTableView.getSelectionModel().getSelectedItem();
        String sqlQuery = "delete from teacher where dbTeacherID = '"+getSelectedRow.getAdminTeacherTableDataID()+"';";
        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(sqlQuery);
            adminTeacherTableView.setItems(getDataFromTeacherAndAddToObservableList("SELECT * FROM teacher;"));

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setAdminTeacherRefreshButtonClick(Event event){
       adminTeacherTableView.setItems(getDataFromTeacherAndAddToObservableList("SELECT * FROM teacher;"));//sql Query
        adminTeacherTFSearch.clear();
    }

    @FXML
    private void setAdminTeacherSearchButtonClick(Event event){
        String sqlQuery = "select * FROM teacher where dbTeacherID = '"+adminTeacherTFSearch.getText()+"';";
        adminTeacherTableView.setItems(getDataFromTeacherAndAddToObservableList(sqlQuery));
        adminTeacherTFSearch.clear();
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
