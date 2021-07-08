/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jonas Turner
 */

package ucf.assignments;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ToDoAppController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb){
        addDate.setValue(LocalDate.now());
    }

    @FXML
    Button addButton;
    @FXML
    DatePicker addDate;
    @FXML
    TextField addTaskDetail;
    @FXML
    ListView<TaskTDA> taskList;

    ObservableList<TaskTDA> list = FXCollections.observableArrayList();

    @FXML
    public void addTask(ActionEvent actionEvent) {
        // add task to to do list
        list.add(new TaskTDA(addDate.getValue(), addTaskDetail.getText()));
        taskList.setItems(list);
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



    public void editTask(ActionEvent actionEvent) {
        // open new window

        // configure task details using text input

        // update GUI
    }

    public void delTask(ActionEvent actionEvent) {
        // remove task from to do list
    }
}
