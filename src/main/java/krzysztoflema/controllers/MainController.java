package krzysztoflema.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import krzysztoflema.models.MySqlConnector;
import krzysztoflema.models.dao.ClientDao;
import krzysztoflema.models.dao.daoImpl.ClientDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    ClientDao clientDao = new ClientDaoImpl();
    MySqlConnector connector = MySqlConnector.getInstace();

    @FXML
    private JFXButton buttonClients, buttonWarehouse;

    public void initialize(URL location, ResourceBundle resources) {

        buttonClients.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientsViews/clientsShowAllView11.fxml"));
                    Stage stageRoot = (Stage) buttonClients.getScene().getWindow();
                    stageRoot.setScene(new Scene(root,1000, 740));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        buttonWarehouse.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Parent root =FXMLLoader.load(getClass().getClassLoader().getResource("warehouseViews/stockView.fxml"));
                    Stage stageRoot=(Stage) buttonWarehouse.getScene().getWindow();
                    stageRoot.setScene(new Scene(root,1000,740));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
