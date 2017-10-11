package krzysztoflema.controllers.clientsControllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {

    @FXML
    JFXButton buttonAddClient, buttonShowAllClients,buttonDeleteClient;

    public void initialize(URL location, ResourceBundle resources) {

      buttonAddClient.setOnMouseClicked(new EventHandler<MouseEvent>() {
          public void handle(MouseEvent event) {
              try {
                  Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientsViews/clientsAddView.fxml"));
                  Stage stageRoot=(Stage) buttonAddClient.getScene().getWindow();
                  stageRoot.setScene(new Scene(root,1000, 700));
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      });
      buttonShowAllClients.setOnMouseClicked(new EventHandler<MouseEvent>() {
          public void handle(MouseEvent event) {
              try {
                  Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientsViews/clientsShowAllView.fxml"));
                  Stage stageRoot=(Stage) buttonShowAllClients.getScene().getWindow();
                  stageRoot.setScene(new Scene(root,1000,700));
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      });

      buttonDeleteClient.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
              try {
                  Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientsViews/clientsDeleteView.fxml"));
                  Stage stageRoot=(Stage) buttonDeleteClient.getScene().getWindow();
                  stageRoot.setScene(new Scene(root,1000,700));
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      });
    }
}