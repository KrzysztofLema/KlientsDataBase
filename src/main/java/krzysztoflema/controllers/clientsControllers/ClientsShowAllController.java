package krzysztoflema.controllers.clientsControllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import krzysztoflema.models.ClientModel;
import krzysztoflema.models.dao.ClientDao;
import krzysztoflema.models.dao.daoImpl.ClientDaoImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ClientsShowAllController implements Initializable {

    ClientModel client;
    ClientDao clientDao = new ClientDaoImpl();


    @FXML
    JFXTreeTableView<ClientModel> treeView;
    @FXML
    JFXTextField textFieldFilter;
    @FXML
    JFXButton buttonDeleteClient, buttonAddClient, buttonBack, buttonEdit;


    public void initialize(URL location, ResourceBundle resources) {
        showTable();
        searchName();
        loadClient();
        deleteClient();


        buttonBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Parent parentRoot = FXMLLoader.load(getClass().getClassLoader().getResource("mainView.fxml"));
                    Stage stageRoot = (Stage) buttonBack.getScene().getWindow();
                    stageRoot.setScene(new Scene(parentRoot, 1000, 700));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonAddClient.setOnMouseClicked(event -> {
            Stage newStage = new Stage();
            newStage.setResizable(false);

            JFXTextField textFieldName = new JFXTextField();
            textFieldName.setPromptText("Imie");
            JFXTextField textFieldSurname = new JFXTextField();
            textFieldSurname.setPromptText("Nazwisko");
            JFXTextField textFieldNip = new JFXTextField();
            textFieldNip.setPromptText("Nip");
            JFXTextField textFieldStreet = new JFXTextField();
            textFieldStreet.setPromptText("Ulica");
            JFXTextField textFieldCity = new JFXTextField();
            textFieldCity.setPromptText("Miasto");
            JFXTextField textFieldPostCode = new JFXTextField();
            textFieldPostCode.setPromptText("Kod pocztowy");
            JFXTextField textFieldPhoneNumber = new JFXTextField();
            textFieldPhoneNumber.setPromptText("Numer Telefonu");

            JFXTextArea textAreaComments = new JFXTextArea();
            textAreaComments.setPromptText("Komentarz");
            textAreaComments.setMaxSize(130, 120);

            JFXButton buttonSave = new JFXButton("Zapisz");
            buttonSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    clientDao.addClient(textFieldName.getText(),
                            textFieldSurname.getText(),
                            textFieldNip.getText(),
                            textFieldCity.getText(),
                            textFieldCity.getText(),
                            textFieldPostCode.getText(),
                            textFieldPhoneNumber.getText(),
                            textAreaComments.getText());
                    loadClient();
                    newStage.close();
                }
            });

            AnchorPane anchorPane = new AnchorPane();

            anchorPane.setPrefHeight(500.0);
            anchorPane.setPrefWidth(400.0);
            anchorPane.getChildren().addAll(textFieldName, textFieldSurname, textFieldNip, textFieldStreet, textFieldCity, textFieldPostCode, textFieldPhoneNumber, textAreaComments, buttonSave);
            AnchorPane.setTopAnchor(textFieldName, 120.0);
            AnchorPane.setLeftAnchor(textFieldName, 50.0);


            AnchorPane.setTopAnchor(textFieldSurname, 120.0);
            AnchorPane.setRightAnchor(textFieldSurname, 50.0);


            AnchorPane.setTopAnchor(textFieldNip, 170.0);
            AnchorPane.setLeftAnchor(textFieldNip, 50.0);


            AnchorPane.setTopAnchor(textFieldStreet, 170.0);
            AnchorPane.setRightAnchor(textFieldStreet, 50.0);


            AnchorPane.setTopAnchor(textFieldCity, 220.0);
            AnchorPane.setLeftAnchor(textFieldCity, 50.0);


            AnchorPane.setTopAnchor(textFieldPostCode, 320.0);
            AnchorPane.setLeftAnchor(textFieldPostCode, 50.0);

            AnchorPane.setTopAnchor(textFieldPhoneNumber, 270.0);
            AnchorPane.setLeftAnchor(textFieldPhoneNumber, 50.0);

            AnchorPane.setTopAnchor(textAreaComments, 220.0);
            AnchorPane.setRightAnchor(textAreaComments, 50.0);

            AnchorPane.setBottomAnchor(buttonSave, 50.0);
            AnchorPane.setRightAnchor(buttonSave, 50.0);

            Scene stageScene = new Scene(anchorPane, 400, 500);
            newStage.setScene(stageScene);
            newStage.show();
        });
        buttonEdit.setOnMouseClicked(event -> {
            Stage newStage = new Stage();

            JFXTextField textFieldName = new JFXTextField();
            textFieldName.setPromptText(treeView.getSelectionModel().getSelectedItem().getValue().getName());
            JFXTextField textFieldSurname = new JFXTextField();
            textFieldSurname.setPromptText(treeView.getSelectionModel().getSelectedItem().getValue().getSurname());
            JFXTextField textFieldNip = new JFXTextField();
            textFieldNip.setPromptText(treeView.getSelectionModel().getSelectedItem().getValue().getNip());
            JFXTextField textFieldStreet = new JFXTextField();
            textFieldStreet.setPromptText(treeView.getSelectionModel().getSelectedItem().getValue().getStreet());
            JFXTextField textFieldCity = new JFXTextField();
            textFieldCity.setPromptText(treeView.getSelectionModel().getSelectedItem().getValue().getCity());
            JFXTextField textFieldPostCode = new JFXTextField();
            textFieldPostCode.setPromptText(treeView.getSelectionModel().getSelectedItem().getValue().getCityCode());
            JFXTextField textFieldPhoneNumber = new JFXTextField();
            textFieldPhoneNumber.setPromptText(treeView.getSelectionModel().getSelectedItem().getValue().getPhoneNumber());


            JFXTextArea textAreaComments = new JFXTextArea();
            textAreaComments.setMaxSize(130, 120);

            JFXButton buttonSave = new JFXButton("Zapisz");
            buttonSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    clientDao.editClient(textFieldName.getText(),
                            textFieldSurname.getText(),
                            textFieldNip.getText(),
                            textFieldStreet.getText(),
                            textFieldCity.getText(),
                            textFieldPostCode.getText(),
                            textFieldPhoneNumber.getText(),
                            textAreaComments.getText(),
                            treeView.getSelectionModel().getSelectedItem().getValue().getName(),
                            treeView.getSelectionModel().getSelectedItem().getValue().getNip());


                    loadClient();
                    newStage.close();
                }
            });


            AnchorPane anchorPane = new AnchorPane();

            anchorPane.setPrefHeight(500.0);
            anchorPane.setPrefWidth(400.0);
            anchorPane.getChildren().addAll(textFieldName, textFieldSurname, textFieldNip, textFieldStreet, textFieldCity, textFieldPostCode, textFieldPhoneNumber, textAreaComments, buttonSave);

            AnchorPane.setTopAnchor(textFieldName, 120.0);
            AnchorPane.setLeftAnchor(textFieldName, 50.0);

            AnchorPane.setTopAnchor(textFieldSurname, 120.0);
            AnchorPane.setRightAnchor(textFieldSurname, 50.0);

            AnchorPane.setTopAnchor(textFieldNip, 170.0);
            AnchorPane.setLeftAnchor(textFieldNip, 50.0);

            AnchorPane.setTopAnchor(textFieldStreet, 170.0);
            AnchorPane.setRightAnchor(textFieldStreet, 50.0);

            AnchorPane.setTopAnchor(textFieldCity, 220.0);
            AnchorPane.setLeftAnchor(textFieldCity, 50.0);

            AnchorPane.setTopAnchor(textFieldPostCode, 320.0);
            AnchorPane.setLeftAnchor(textFieldPostCode, 50.0);

            AnchorPane.setTopAnchor(textFieldPhoneNumber, 270.0);
            AnchorPane.setLeftAnchor(textFieldPhoneNumber, 50.0);

            AnchorPane.setTopAnchor(textAreaComments, 220.0);
            AnchorPane.setRightAnchor(textAreaComments, 50.0);

            AnchorPane.setBottomAnchor(buttonSave, 50.0);
            AnchorPane.setRightAnchor(buttonSave, 50.0);

            Scene stageScene = new Scene(anchorPane, 400, 500);
            newStage.setScene(stageScene);
            newStage.show();

        });
    }


    private void deleteClient() {
        buttonDeleteClient.setOnMouseClicked(event -> {
            clientDao.removeClient(treeView.getSelectionModel().getSelectedItem().getValue().getName(), treeView.getSelectionModel().getSelectedItem().getValue().getNip());
            loadClient();
        });
    }

    private void loadClient() {
        ObservableList<ClientModel> clients = FXCollections.observableArrayList(clientDao.getAllClients());
        final TreeItem<ClientModel> root = new RecursiveTreeItem<ClientModel>(clients, RecursiveTreeObject::getChildren);
        treeView.setRoot(root);
        treeView.setShowRoot(false);
    }

    private void searchName() {
        textFieldFilter.setPromptText("Wyszukaj");
        textFieldFilter.textProperty().addListener((observable, oldValue, newValue) -> treeView.setPredicate(client -> {
            Boolean flag = client.getValue().getName().contains(newValue);

            return flag;
        }));
    }

    public void showTable() {
        JFXTreeTableColumn<ClientModel, String> nameColumn = new JFXTreeTableColumn<>("Imie");
        nameColumn.setPrefWidth(100.0);
        nameColumn.setCellValueFactory(param -> param.getValue().getValue().nameProperty());

        JFXTreeTableColumn<ClientModel, String> surnameColumn = new JFXTreeTableColumn<>("Nazwisko");
        surnameColumn.setPrefWidth(100);
        surnameColumn.setCellValueFactory(param -> param.getValue().getValue().surnameProperty());

        JFXTreeTableColumn<ClientModel, String> nipColumn = new JFXTreeTableColumn<>("nip");
        nipColumn.setPrefWidth(100);
        nipColumn.setCellValueFactory(param -> param.getValue().getValue().nipProperty());

        JFXTreeTableColumn<ClientModel, String> streetColumn = new JFXTreeTableColumn<>("Ulica");
        streetColumn.setPrefWidth(100);
        streetColumn.setCellValueFactory(param -> param.getValue().getValue().streetProperty());

        JFXTreeTableColumn<ClientModel, String> cityColumn = new JFXTreeTableColumn<>("Miasto");
        cityColumn.setPrefWidth(100);
        cityColumn.setCellValueFactory(param -> param.getValue().getValue().cityProperty());

        JFXTreeTableColumn<ClientModel, String> cityCodeColumn = new JFXTreeTableColumn<>("Kod pocztowy");
        cityCodeColumn.setPrefWidth(100);
        cityCodeColumn.setCellValueFactory(param -> param.getValue().getValue().cityProperty());

        JFXTreeTableColumn<ClientModel, String> phoneNumberColumn = new JFXTreeTableColumn<>("Numer Telefonu");
        phoneNumberColumn.setPrefWidth(100);
        phoneNumberColumn.setCellValueFactory(param -> param.getValue().getValue().phoneNumberProperty());

        treeView.getColumns().setAll(nameColumn, surnameColumn, nipColumn, streetColumn, cityColumn, cityCodeColumn, phoneNumberColumn);

    }

}
