package krzysztoflema.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import krzysztoflema.models.dao.ClientDao;
import krzysztoflema.models.dao.daoImpl.ClientDaoImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsAddController implements Initializable {

    @FXML
    JFXTextField textFieldName, textFieldSurname, textFieldNip, textFieldStreet, textFieldCity, textFieldCityCode, textFieldPhone;

    @FXML
    JFXTextArea textAreaComments;

    @FXML
    JFXButton buttonClear, buttonSave;

    ClientDao clientDao = new ClientDaoImpl();

    public void initialize(URL location, ResourceBundle resources) {

        buttonSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
               addClient();
            }
        });


        buttonClear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                clearAll();
            }
        });

    }

    private void addClient() {
        clientDao.addClient(textFieldName.getText(), textFieldSurname.getText(), textFieldNip.getText(), textFieldStreet.getText(),
                textFieldCity.getText(), textFieldCityCode.getText(), textFieldPhone.getText(), textAreaComments.getText());
    }

    private void clearAll() {
        textFieldName.setText(null);
        textFieldSurname.setText(null);
        textFieldNip.setText(null);
        textFieldStreet.setText(null);
        textFieldCity.setText(null);
        textFieldCityCode.setText(null);
        textFieldPhone.setText(null);
        textAreaComments.setText(null);
    }
}
