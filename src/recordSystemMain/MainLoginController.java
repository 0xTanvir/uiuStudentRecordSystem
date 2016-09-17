package recordSystemMain;


import database.DBConnection;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import student.Student;
import teacher.TeacherController;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Tanvir on 7/22/2016.
 */


public class MainLoginController {

    @FXML
    private TextField tfEmailID;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private ChoiceBox cbUser;

    @FXML
    private void loginButtonClick(Event event) throws SQLException{
        if(isAllFieldFillup()){
            String userName = tfEmailID.getText().trim();
            String password = pfPassword.getText();
            String userType = cbUser.getValue().toString().trim();




            switch (userType){
                case "Admin":
                    if (isValidCredentials(userType,userName,password,"Email")){
                        try {
                            Parent adminParent = FXMLLoader.load(getClass().getResource("/admin/Admin.fxml"));
                            Scene adminScene = new Scene(adminParent);
                            Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            adminStage.hide();
                            adminStage.setScene(adminScene);
                            adminStage.setTitle("Admin Panel");
                            adminStage.show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
                case "Guardian":
                    System.out.println("I am now on Guardian");
                    break;
                case "Student":
                    if(isValidCredentials(userType,userName,password,"ID")){
                        try {
                            Student student = new Student();
                            student.setStudentId(userName);
                            Parent studentParent = FXMLLoader.load(getClass().getResource("/student/Student.fxml"));
                            Scene studentScene = new Scene(studentParent);
                            Stage studentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            studentStage.hide();
                            studentStage.setScene(studentScene);
                            studentStage.setTitle("Student Panel");
                            studentStage.show();

                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    break;
                case "Teacher":
                    if (isValidCredentials(userType,userName,password,"Email")){
                        try {
                            TeacherController teacherController = new TeacherController();
                            teacherController.setEmail(userName);
                            Parent teacherParent = FXMLLoader.load(getClass().getResource("/teacher/Teacher.fxml"));
                            Scene teacherScene = new Scene(teacherParent);
                            Stage teacherStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            teacherStage.hide();
                            teacherStage.setScene(teacherScene);
                            teacherStage.setTitle("Teacher Panel");
                            teacherStage.show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    break;

            }

        }

    }


    private boolean isValidCredentials(String userType, String userName, String password,String loginType) throws SQLException {
        boolean userPassOk = false;

        DBConnection database = new DBConnection();
        Connection connection = database.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from "+userType.toLowerCase()+" where db"+userType
                +loginType+" = '"+userName+"' and db"+userType+"Password = '"+password+"';");


        while (resultSet.next()){

            if(resultSet.getString("db"+userType+loginType)!=null && resultSet.getString("db"+userType+"Password")!=null){
                userPassOk = true;
            }

        }

        if(!userPassOk) {

            NotificationType notificationType = NotificationType.ERROR;
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Wrong Information");
            tray.setMessage("Incorrect email or password");
            tray.setNotificationType(notificationType);
            tray.showAndDismiss(Duration.millis(3000));

            tfEmailID.clear();
            pfPassword.clear();

            userPassOk = false;

        }


        return userPassOk;
    }



    private boolean isAllFieldFillup(){
        boolean fillup;
        if(tfEmailID.getText().trim().isEmpty()||pfPassword.getText().isEmpty()){

            NotificationType notificationType = NotificationType.INFORMATION;
            TrayNotification tray = new TrayNotification();
            tray.setTitle("Attention!!!");
            tray.setMessage("Email or Password should not Empty.");
            tray.setNotificationType(notificationType);
            tray.showAndDismiss(Duration.millis(3000));

            fillup = false;
        }
        else fillup = true;
        return fillup;
    }
}
