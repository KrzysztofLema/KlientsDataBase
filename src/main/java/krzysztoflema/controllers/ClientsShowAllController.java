package krzysztoflema.controllers;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import krzysztoflema.models.ClientModel;
import krzysztoflema.models.dao.ClientDao;
import krzysztoflema.models.dao.daoImpl.ClientDaoImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsShowAllController implements Initializable {

    ClientModel client;
    ClientDao clientDao= new ClientDaoImpl();
    @FXML
    JFXTreeTableView<ClientModel> treeView;

    private ObservableList<ClientModel> observableList;

    public void initialize(URL location, ResourceBundle resources) {

        JFXTreeTableColumn<ClientModel, String> nameColumn = new JFXTreeTableColumn<>("name");
        nameColumn.setPrefWidth(100.0);
        nameColumn.setCellValueFactory(param -> param.getValue().getValue().nameProperty());

        JFXTreeTableColumn<ClientModel,String> surnameColumn = new JFXTreeTableColumn<>("surname");
        surnameColumn.setPrefWidth(100);
        surnameColumn.setCellValueFactory(param -> param.getValue().getValue().surnameProperty());

        JFXTreeTableColumn<ClientModel,String> nipColumn=new JFXTreeTableColumn<>("nip");
        nipColumn.setPrefWidth(100);
        nipColumn.setCellValueFactory(param -> param.getValue().getValue().nipProperty());



        ObservableList<ClientModel> clients = FXCollections.observableArrayList(clientDao.getAllClients());




        final TreeItem<ClientModel> root = new RecursiveTreeItem<ClientModel>(clients, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(nameColumn,surnameColumn,nipColumn);
        treeView.setRoot(root);
        treeView.setShowRoot(false);
    }


    }
