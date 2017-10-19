package krzysztoflema.models;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

public class SupplyModel {

    private IntegerProperty index;
    LocalDateTime localDate;
    private StringProperty carDriverName;

    public SupplyModel(IntegerProperty index, LocalDateTime localDate, StringProperty carDriverName) {
        this.index = index;
        this.localDate = localDate;
        this.carDriverName = carDriverName;
    }
}
