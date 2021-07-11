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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ToDoAppController implements Initializable {

    // buttons
    @FXML
    SplitMenuButton addButton;
    @FXML
    MenuItem removeButton;
    @FXML
    MenuItem importButton;
    @FXML
    MenuItem exportButton;
    @FXML
    MenuItem showAllButton;
    @FXML
    MenuItem comOnlyButton;
    @FXML
    MenuItem incOnlyButton;

    // input fields
    @FXML
    DatePicker addDate;
    @FXML
    TextField addTaskDetail;

    // table stuff
    @FXML
    TableView<TaskTDA> tableView;
    @FXML
    TableColumn<TaskTDA, String> dateColumn;
    @FXML
    TableColumn<TaskTDA, String> textColumn;
    @FXML
    TableColumn<TaskTDA, CheckBox> isComCol;

    private ObservableList<TaskTDA> listData = FXCollections.observableArrayList();
    private ObservableList<TaskTDA> toInsert = FXCollections.observableArrayList();


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

        // make date column editable
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // make text column editable
        textColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // resizable
        tableView.getColumns().get(0).prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        tableView.getColumns().get(1).prefWidthProperty().bind(tableView.widthProperty().multiply(0.70));
        tableView.getColumns().get(2).prefWidthProperty().bind(tableView.widthProperty().multiply(0.05));


        // set up date picker for date program is ran
        addDate.setValue(LocalDate.now());
    }


    // add and delete
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


    // when user updates cell with new info
    @FXML
    public void changeDateCellEvent(TableColumn.CellEditEvent editEvent){
        TaskTDA taskTDA = tableView.getSelectionModel().getSelectedItem();
        taskTDA.setDate(editEvent.getNewValue().toString());
        updateListView();
    }

    @FXML
    public void changeTextCellEvent(TableColumn.CellEditEvent editEvent){
        TaskTDA taskTDA = tableView.getSelectionModel().getSelectedItem();
        taskTDA.setTaskAction(editEvent.getNewValue().toString());
        updateListView();
    }


    // show only
    @FXML
    public void showAllTasks(ActionEvent actionEvent){

        // put data back in
        listData.addAll(toInsert);

        // update
        updateListView();

        // clear cached insert list
        toInsert.removeAll(toInsert);
    }

    @FXML
    public void showComOnlyTasks(ActionEvent actionEvent){

        // make list for removing unwanted tasks and showing updated GUI
        ObservableList<TaskTDA> toRemove = FXCollections.observableArrayList();

        // add list for removal
        for(TaskTDA task : listData){
            if(!(task.getIsComplete().isSelected())){
                toRemove.add(task);
            }
        }

        listData.removeAll(toRemove);
        toInsert.addAll(toRemove);

        // update user's view to only completed
        updateListView();

        // clear cache
        toRemove.removeAll(toRemove);
    }

    @FXML
    public void showIncOnlyTasks(ActionEvent actionEvent){

        ObservableList<TaskTDA> toRemove = FXCollections.observableArrayList();

        for(TaskTDA task : listData){
            if(task.getIsComplete().isSelected()){
                toRemove.add(task);
            }
        }

        listData.removeAll(toRemove);
        toInsert.addAll(toRemove);

        // update user's view to only completed
        updateListView();

        // clear cache
        toRemove.removeAll(toRemove);
    }


    // file operations
    @FXML
    public void importToDoList(ActionEvent actionEvent) {
        // read list from JSON

        // convert to List/Map

        // update GUI
    }

    @FXML
    public void exportToDoList(ActionEvent actionEvent) {
        // Convert to JSON

        // Prompt to save JSON
    }


    // updates GUI
    public void updateListView(){
        tableView.setItems(listData);

    }
}
