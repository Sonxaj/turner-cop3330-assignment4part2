/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jonas Turner
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ToDoAppController implements Initializable {

    @FXML
    SplitMenuButton addButton;
    @FXML
    MenuItem removeButton;
    @FXML
    DatePicker addDate;
    @FXML
    TextField addTaskDetail;
    @FXML
    TableView<TaskTDA> tableView;
    @FXML
    TableColumn<TaskTDA, String> dateColumn;
    @FXML
    TableColumn<TaskTDA, String> textColumn;
    @FXML
    TableColumn<TaskTDA, CheckBox> isComCol;

    private ObservableList<TaskTDA> listData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // the user can see 2 lists with divider, so both must be initialized

        // set table columns for editing and multiple-item highlighting
        tableView.setEditable(true);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // set up columns
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        textColumn.setCellValueFactory(new PropertyValueFactory<>("taskAction"));
        isComCol.setCellValueFactory(new PropertyValueFactory<>("isComplete"));

        // set up data
        tableView.setItems(listData);


        // make cells editable
        // date column
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // text column
        textColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        // set up date picker for date program is ran
        addDate.setValue(LocalDate.now());
    }


    @FXML
    public void addTask(ActionEvent actionEvent) {
        // add task to to do list
        listData.add(new TaskTDA(addDate.getValue(), addTaskDetail.getText()));

        updateListView();
    }

    @FXML
    public void delTask(ActionEvent actionEvent) {
        // remove task(s) from to do list highlighted either from text half or date half
        listData.removeAll(
                tableView.getSelectionModel().getSelectedItems()
        );

        updateListView();
    }


    public void importToDoList(ActionEvent actionEvent) {
        // read list from JSON

        // convert to List/Map

        // update GUI
    }

    public void exportToDoList(ActionEvent actionEvent) {
        // Convert to JSON

        // Prompt to save JSON
    }

    // updates GUI
    public void updateListView(){
        tableView.setItems(listData);
    }
}
