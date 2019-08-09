package sample;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private Button btn1;
    @FXML public TableView<List<String>> tableView;
    BufferedReader br = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void createColumns(List<String> lista){
        clearTable();
        for (int i = 0; i < lista.size(); i++){
            final int finalI = i;
            TableColumn<List<String>,String> tableColumn = new TableColumn<>();
            tableColumn.setText(lista.get(i));
            tableColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(finalI)));
            tableView.getColumns().add(tableColumn);
        }
    }

    private void clearTable(){
        tableView.getColumns().clear();
        tableView.getItems().clear();
    }

    public void Button1Action(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("D:\\Documentos"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files","*.csv"));
        File seletedFile = fc.showOpenDialog(null);
        try {
            String rute = seletedFile.getAbsolutePath();
            System.out.println(rute);
            br =new BufferedReader(new FileReader(rute));
            String line = br.readLine();
            boolean first = true;
            while (null!=line) {
                List<String> fields = Arrays.asList(line.split(","));
                if (first){
                    createColumns(fields);
                    first = false;
                } else {
                    tableView.getItems().add(fields);
                }

                line = br.readLine();
            }

        } catch (Exception ex) {
            System.out.println("Ha ocurrido un error fatal");
        }
    }
}
