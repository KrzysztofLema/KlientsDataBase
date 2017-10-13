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
import javafx.stage.Stage;
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
    JFXButton buttonDeleteClient, buttonAddClient, buttonBack, buttonClientEdit;


    public void initialize(URL location, ResourceBundle resources) {
        showTable();
        searchName();
        loadContacts();
        deleteContact();


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
        buttonAddClient.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("clientsViews/clientsAddView.fxml"));
                    Scene scene = new Scene(root, 430, 550);
                    Stage secondStage = new Stage();
                    secondStage.setScene(scene);
                    secondStage.show();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
    private void deleteContact() {
        buttonDeleteClient.setOnMouseClicked(event -> {
            clientDao.removeClient(treeView.getSelectionModel().getSelectedItem().getValue().getName(), treeView.getSelectionModel().getSelectedItem().getValue().getNip());
            loadContacts();
        });
    }

    public  void loadContacts() {
        ObservableList<ClientModel> clients = FXCollections.observableArrayList(clientDao.getAllClients());
        final TreeItem<ClientModel> root = new RecursiveTreeItem<ClientModel>(clients, RecursiveTreeObject::getChildren);
        treeView.setRoot(root);
        treeView.setShowRoot(false);

    }

    private void searchName() {
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

        JFXTreeTableColumn<ClientModel, String> cityCodeColumn = new JFXTreeTableColumn<>("Miasto");
        cityCodeColumn.setPrefWidth(100);
        cityCodeColumn.setCellValueFactory(param -> param.getValue().getValue().cityProperty());

        JFXTreeTableColumn<ClientModel, String> phoneNumberColumn = new JFXTreeTableColumn<>("Numer Telefonu");
        phoneNumberColumn.setPrefWidth(100);
        phoneNumberColumn.setCellValueFactory(param -> param.getValue().getValue().phoneNumberProperty());


        treeView.getColumns().setAll(nameColumn, surnameColumn, nipColumn, streetColumn, cityColumn, cityCodeColumn, phoneNumberColumn);

    }


}
