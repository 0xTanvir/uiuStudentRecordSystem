package student;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Created by Tanvir on 8/20/2016.
 */
public class ChooseASectionController {
    static String allSection;
    static Stage stage;
    public void setStage(Stage stage){
        this.stage=stage;
    }

    public void setAllSection(String s){
        this.allSection = s;
    }

    @FXML
    ComboBox<String> chooseASection;
    @FXML
    private void setTakeButtonClick(Event event) throws SQLException {
        RegistrationController registrationController = new RegistrationController();
        registrationController.setChoosingSec(chooseASection.getValue());
        stage.close();
        registrationController.studentRegistrationSectionUpdate();
    }

    @FXML
    private void initialize(){
        chooseASection.getItems().addAll(allSection.split(",",0));
    }
}
