package profileView;

import database.DBConnection;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import sun.tracing.NullProviderFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Tanvir on 8/13/2016.
 */
public class ProfileView {

    @FXML
    private ImageView profilePic;

    @FXML
    Text tID;
    @FXML
    Text tName;
    @FXML
    Text tDepartment;
    @FXML
    Text tCGPA;
    @FXML
    Text tDOB;
    @FXML
    Text tEmail;
    @FXML
    Text tVEmail;
    @FXML
    Text tPhone;
    @FXML
    Text tAddress;
    @FXML
    Text tGName;
    @FXML
    Text tGEmail;
    @FXML
    Text tGPhone;

    DBConnection database = new DBConnection();
    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @FXML
    private LineChart<String,Number> gpaVisualization;



    private Info currentInfo;

    public void setCurrentInfo(Info currentInfo) {
        tID.setText(currentInfo.getId());
        tName.setText(currentInfo.getName());
        tDepartment.setLayoutX(tName.getLayoutX()+10+tName.getBoundsInParent().getWidth());//Adjust Layout with name
        tDepartment.setText(currentInfo.getDepartment());
        tCGPA.setText(currentInfo.getCgpa());
        tVEmail.setText(currentInfo.getVemail());
        tDOB.setText(currentInfo.getDob());
        tEmail.setText(currentInfo.getEmail());
        tPhone.setText(currentInfo.getPhone());
        tAddress.setText(currentInfo.getAddress());


        tGName.setText(currentInfo.getGname());
        tGEmail.setText(currentInfo.getGemail());
        tGPhone.setText(currentInfo.getGphone());



        try {
            Image image = new Image("/image/"+currentInfo.getfullId()+".jpg");
            profilePic.setImage(image);
        }
        catch (Exception e){
            profilePic.setImage(new Image("/image/default-user-icon.png"));
        }





        this.currentInfo = currentInfo;

    }

    @FXML
    private void setLoadGpaButtonClcik(Event event){
        gpaVisualization.getData().clear();
        XYChart.Series<String,Number> gpaLineChart = new XYChart.Series<String,Number>();

        try {
            connection = database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM student where dbStudentID = '"+currentInfo.getfullId()+"';");

            while (resultSet.next()){

                for (int i = 1; i <12 ; i++) {
                    if(resultSet.getDouble("dbStudent"+i+"thSemGpa")!=0.00){
                        gpaLineChart.getData().add(new XYChart.Data<String,Number>(i+"th", resultSet.getDouble("dbStudent"+i+"thSemGpa")));
                    }
                }

                gpaLineChart.setName("All GPA");
                gpaVisualization.getData().add(gpaLineChart);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
