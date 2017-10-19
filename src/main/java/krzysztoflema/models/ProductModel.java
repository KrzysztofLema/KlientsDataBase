package krzysztoflema.models;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductModel extends RecursiveTreeObject<ProductModel> {
    private StringProperty index;
    private StringProperty name;

    public ProductModel(String index, String name) {
        this.index = new  SimpleStringProperty(index);
        this.name = new SimpleStringProperty(name);
    }

    public ProductModel(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getIndex() {
        return index.get();
    }

    public StringProperty indexProperty() {
        return index;
    }

    public void setIndex(String index) {
        this.index.set(index);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }


}
