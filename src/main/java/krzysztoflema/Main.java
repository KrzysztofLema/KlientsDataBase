package krzysztoflema;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import krzysztoflema.controllers.clientsControllers.ClientsAddController;
import krzysztoflema.controllers.clientsControllers.ClientsShowAllController;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainView.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 640, 480));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
