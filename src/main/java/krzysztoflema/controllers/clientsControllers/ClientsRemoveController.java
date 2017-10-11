package krzysztoflema.controllers.clientsControllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import krzysztoflema.models.ClientModel;
import krzysztoflema.models.dao.ClientDao;
import krzysztoflema.models.dao.daoImpl.ClientDaoImpl;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ClientsRemoveController implements Initializable {

    ClientDao clientDao = new ClientDaoImpl();

    @FXML
    JFXTreeTableView<ClientModel> treeView;
    @FXML
    JFXButton buttonDelate, buttonShowAllClients;
    @FXML
    JFXTextField textFieldFilter;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showTreeTable();
        removeContact();
        searchName();
        loadContacts();

    }

    private void searchName() {
        textFieldFilter.textProperty().addListener((observable, oldValue, newValue) -> treeView.setPredicate(new Predicate<TreeItem<ClientModel>>() {
            @Override
            public boolean test(TreeItem<ClientModel> clientModelTreeItem) {
                boolean flag = clientModelTreeItem.getValue().getName().contains(newValue);
                return flag;
            }
        }));
    }

    private void showTreeTable() {
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

    private void loadContacts() {
        ObservableList<ClientModel> clients = FXCollections.observableArrayList(clientDao.getAllClients());
        final TreeItem<ClientModel> root = new RecursiveTreeItem<ClientModel>(clients, RecursiveTreeObject::getChildren);
        treeView.setRoot(root);
        treeView.setShowRoot(false);
    }

    private void removeContact() {
        buttonDelate.setOnMouseClicked(event -> {
            clientDao.removeClient(treeView.getSelectionModel().getSelectedItem().getValue().getName(), treeView.getSelectionModel().getSelectedItem().getValue().getNip());
            loadContacts();
        });
    }
}
