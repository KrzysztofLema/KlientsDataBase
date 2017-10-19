package krzysztoflema.controllers.stockControllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import krzysztoflema.models.ProductModel;
import krzysztoflema.models.SupplyModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class stockController implements Initializable {


    @FXML
    JFXButton buttonBack, buttonAddSupply;
    @FXML
    JFXTreeTableView treeTableStock;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showTable();
        buttonBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainView.fxml"));
                    Stage stageRoot = (Stage) buttonBack.getScene().getWindow();
                    stageRoot.setScene(new Scene(root, 640, 420));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonAddSupply.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Stage newStage = new Stage();
                AnchorPane anchorPane = new AnchorPane();
                anchorPane.setPrefWidth(400.0);
                anchorPane.setPrefHeight(400.0);
                JFXComboBox<String> comboBox = new JFXComboBox<>();
                JFXSlider jfxSlider = new JFXSlider();
                jfxSlider.setMin(1);
                jfxSlider.setMax(100);
                jfxSlider.valueProperty().addListener((observable, oldValue, newValue) -> jfxSlider.setValue(newValue.intValue()));
                JFXButton buttonAdd = new JFXButton("Dodaj");
                anchorPane.getChildren().addAll(comboBox, buttonAdd, jfxSlider);
                AnchorPane.setTopAnchor(comboBox, 50.0);
                AnchorPane.setBottomAnchor(buttonAdd, 100.0);
                AnchorPane.setTopAnchor(jfxSlider, 150.0);

                buttonAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println(comboBox.getValue() + " " + jfxSlider.getValue());
                    }
                });
                comboBox.getItems().addAll(
                        new ProductModel("Butla NB. 11 KG PB").getName(),
                        new ProductModel("Butla NB. 11 KG P").getName(),
                        new ProductModel("Butla NB. 11 KG SYFON P").getName());

                Scene stageScene = new Scene(anchorPane, 400, 500);
                newStage.setScene(stageScene);
                newStage.show();

            }
        });
    }

    private void showTable() {
        JFXTreeTableColumn<SupplyModel, String> dateColumn = new JFXTreeTableColumn<>("Data Dostawy");
        dateColumn.setPrefWidth(100.0);
        dateColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SupplyModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SupplyModel, String> param) {
                return param.getValue().getValue();
            }
        });

        JFXTreeTableColumn<SupplyModel, String> numberOfDeliverColumn = new JFXTreeTableColumn<>("Nr dostawy");
        numberOfDeliverColumn.setPrefWidth(100.0);
        dateColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SupplyModel, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SupplyModel, String> param) {
                return param.getValue().getValue();
            }
        });

        treeTableStock.getColumns().setAll(dateColumn, numberOfDeliverColumn);

        ObservableList<ProductModel> productModels = FXCollections.observableArrayList();


        final TreeItem<ProductModel> root = new RecursiveTreeItem<ProductModel>(productModels, RecursiveTreeObject::getChildren);
        treeTableStock.setRoot(root);
        treeTableStock.setShowRoot(false);


    }
}
