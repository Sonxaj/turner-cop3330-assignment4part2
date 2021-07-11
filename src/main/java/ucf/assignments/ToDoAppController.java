/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jonas Turner
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
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
    ListView<TaskTDA> taskText;
    @FXML
    ListView<TaskTDA> taskDate;

    @FXML
    TableView<TaskTDA> tableView;
    @FXML
    TableColumn<TaskTDA, String> dateColumn;
    @FXML
    TableColumn<TaskTDA, String> textColumn;

    private ObservableList<TaskTDA> listData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb){
        // the user can see 2 lists with divider, so both must be initialized

        // text half initializer
        taskText.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        taskText.setEditable(true);
        taskText.setCellFactory(listView -> {
            TextFieldListCell<TaskTDA> cell = new TextFieldListCell<>();
            cell.setConverter(new StringConverter<TaskTDA>() {
                @Override
                public String toString(TaskTDA object) {
                    return object.getTaskAction();
                }

                @Override
                public TaskTDA fromString(String string) {
                    TaskTDA task = cell.getItem();
                    task.setTaskAction(string);
                    return task;
                }
            });
            return cell;
        });

        // task date initializer
        taskDate.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        taskDate.setEditable(true);
        taskDate.setCellFactory(listView -> {
            TextFieldListCell<TaskTDA> cell = new TextFieldListCell<>();
            cell.setConverter(new StringConverter<TaskTDA>() {
                @Override
                public String toString(TaskTDA object) {
                    return object.getDate().toString();
                }

                @Override
                public TaskTDA fromString(String string) {
                    TaskTDA task = cell.getItem();
                    task.setDate(string);
                    return task;
                }
            });
            return cell;
        });

        addDate.setValue(LocalDate.now());
    }


    @FXML
    public void addTask(ActionEvent actionEvent) {
        // add task to to do list
        listData.add(
                new TaskTDA(addDate.getValue(), addTaskDetail.getText())
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


    @FXML
    public void delTask(ActionEvent actionEvent) {
        // remove task(s) from to do list highlighted either from text half or date half
        listData.removeAll(
                taskText.getSelectionModel().getSelectedItems()
        );

        listData.removeAll(
                taskDate.getSelectionModel().getSelectedItems()
        );

        updateListView();
    }

    // updates GUI
    public void updateListView(){
        taskText.setItems(listData);
        taskDate.setItems(listData);
    }
}
