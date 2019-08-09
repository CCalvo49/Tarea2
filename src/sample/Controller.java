package sample;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private Button btn1;
    @FXML public TableView<List<String>> tableView;
    BufferedReader br = null;
    Integer ColumnSize = 0;

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
            if (seletedFile.length() != 0){
                String rute = seletedFile.getAbsolutePath();
                System.out.println(rute);
                System.out.println(seletedFile.length());
                br =new BufferedReader(new FileReader(rute));
                String line = br.readLine();
                boolean first = true;
                while (null!=line) {
                    if (line.contains(";")) {
                        try{
                            throw new FileisnotCSV("El archivo no esta separado por comas");
                        }catch(FileisnotCSV ex){
                            System.out.println(ex.getMessage());
                            JOptionPane.showMessageDialog(null,ex.getMessage());
                            clearTable();
                            break;
                        }

                    } else {
                    List<String> fields = Arrays.asList(line.split(","));
                    if (first) {
                        ColumnSize = fields.size();

                        createColumns(fields);
                        first = false;
                    } else {
                        try {
                            if (ColumnSize == fields.size()) {
                                tableView.getItems().add(fields);
                            } else {
                                throw new MismatchException("Las columnas no son todas iguales");
                            }
                        } catch (MismatchException ex) {
                            System.out.println(ex.getMessage());
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                            clearTable();
                            break;
                        }
                    }

                    line = br.readLine();
                }
                }
            }else {
                throw new emptyException("El archivo se encuenta vacio, intente con otro");
            }


        } catch (emptyException | IOException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }
}
