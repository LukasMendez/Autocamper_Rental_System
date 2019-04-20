package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by Lukas
 * 09-04-2019.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.getIcons().add(new Image("Domain/Autocamper/Images/camper-pngrepo-com.png"));
        primaryStage.setTitle("Auto Camper Rental System");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();



    }


}
