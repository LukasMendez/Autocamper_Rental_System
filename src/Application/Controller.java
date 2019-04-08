package Application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Lukas
 * 08-04-2019.
 */
public class Controller extends Application {

    // CONTAINERS
    BorderPane root;
    VBox vBox;

    // BUTTONS
    Button rentAutoCamperButton = new Button("Rent an Auto Camper");



    @Override
    public void start(Stage primaryStage) throws Exception {

        root = new BorderPane();
        vBox = new VBox();

        root.setLeft(vBox);
        vBox.setStyle("-fx-background-color: GREY");


        vBox.getChildren().addAll(rentAutoCamperButton);



















        Scene scene = new Scene(root, 700,600);
        primaryStage.setScene(scene);
        primaryStage.show();





    }





}
