package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private CheckBox checkBox;
    @FXML public TableView<String> tableView;

    public void press(){
        checkBox.setSelected(true);
        System.out.println("presionado");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < 3; i++){
            TableColumn tableColumn = new TableColumn();
            tableColumn.setText("Col"+i);
            tableView.getColumns().add(tableColumn);
        }
    }
}
