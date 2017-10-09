package krzysztoflema;

import javafx.scene.control.Alert;

public class Utils {

    public static void createSimpleDialog(String title,String header, String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

}
