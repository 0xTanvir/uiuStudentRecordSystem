package recordSystemMain;

/**
 * Created by Tanvir on 7/22/2016.
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainLogin extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainLogin.fxml"));
        primaryStage.setTitle("UIU Student Record System");
        primaryStage.setScene(new Scene(root, 650, 400));
        primaryStage.show();
    }



}
