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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ToDoAppController implements Initializable {

    // fileChooser
    @FXML
    SplitPane splitPane;

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
    @FXML
    MenuItem helpButton;


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

    // data
    private ObservableList<TaskTDA> listData = FXCollections.observableArrayList();
    private ObservableList<TaskTDA> toInsert = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb){

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
        // remove highlighted task(s) from to-do list
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

        // add to list for removal
        for(TaskTDA task : listData){
            if(!(task.getIsComplete().isSelected())){
                toRemove.add(task);
            }
        }

        // remove; cache what was removed for reinsertion
        listData.removeAll(toRemove);
        toInsert.addAll(toRemove);

        // update user's view to only completed
        updateListView();

        // clear removal cache
        toRemove.removeAll(toRemove);
    }

    @FXML
    public void showIncOnlyTasks(ActionEvent actionEvent){
        // same as complete

        ObservableList<TaskTDA> toRemove = FXCollections.observableArrayList();

        for(TaskTDA task : listData){
            if(task.getIsComplete().isSelected()){
                toRemove.add(task);
            }
        }

        listData.removeAll(toRemove);
        toInsert.addAll(toRemove);

        updateListView();

        toRemove.removeAll(toRemove);
    }





    // file operations
    @FXML
    public void importToDoList(ActionEvent actionEvent) throws IOException {

        // setup fileChooser
        FileChooser fileChooser = fileChooserSetup();

        // window stuff
        fileChooser.setTitle("Open .txt File");
        Stage stage = (Stage)splitPane.getScene().getWindow();

        File file = fileChooser.showOpenDialog(stage);

        if(file != null){
            loadOperation(file);
        }
    }

    @FXML
    public void exportToDoList(ActionEvent actionEvent) throws IOException {

        FileChooser fileChooser = fileChooserSetup();

        // window stuff
        fileChooser.setTitle("Save List");
        Stage stage = (Stage) splitPane.getScene().getWindow();

        // create text file
        File fileSave = fileChooser.showSaveDialog(stage);

        if(fileSave != null){
            saveOperation(fileSave);
        }
    }




    // for using fileChooser
    public FileChooser fileChooserSetup(){
        // setup fileChooser
        FileChooser fileChooser = new FileChooser();

        // set extension to .txt
        FileChooser.ExtensionFilter extensionFilter =
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");

        fileChooser.getExtensionFilters().add(extensionFilter);

        return fileChooser;
    }





    // does the saving
    public void saveOperation(File fileToSave) throws IOException{
        // set up text to write
        String fileText = "";
        for (TaskTDA task:listData) {
            fileText += task.getDate() + "#" +
                    task.getTaskAction() + "#" +
                    task.getIsCompleteAsString() + "\n";
        }

        // write all the data to file
        FileWriter fileWriter = new FileWriter(fileToSave);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.write(fileText);

        // close writers
        printWriter.close();
        fileWriter.close();

    }

    // does the loading
    public void loadOperation(File fileToLoad) throws IOException{
        // assuming we got the file, start reading data
        Scanner reader = new Scanner(fileToLoad);

        // clear current table
        listData.removeAll(listData);

        while (reader.hasNextLine()){
            // read current line from file; split using "#"
            String[] currentLine = reader.nextLine().split("#");

            // create task using date from file
            TaskTDA taskAdd = new TaskTDA(currentLine[0], currentLine[1], currentLine[2]);

            // now add
            listData.add(taskAdd);
        }
    }





    // help dialog
    @FXML
    public void showHelpScreen(ActionEvent actionEvent){

        // text
        Label helpLabel = new Label(
                "Add:\nSet text in details box, set date, then click \"Add\".\n\n"
                +   "Remove:\nSelect either by clicking, or click, then use the arrow keys\n"
                +   "while holding SHIFT to select multiple, then click the arrow next to \"Add\", \nthen click " +
                        "\"Remove\".\n\n"
                +   "Mark As Complete:\nClick the checkbox next to a task to mark it as complete.\n\n"
                +   "Save File:\n Click File, then \"Save As\", then type in the filename.\n\n"
                +   "Load File:\n Click File, then \"Open\", then select select .txt file generated by app.\n\n"
                +   "Show/Hide:\n Each option will show only the desired tasks."
        );

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(helpLabel);

        Scene secondScene = new Scene(secondaryLayout, 600, 500);

        // the new window
        Stage newWindow = new Stage();
        newWindow.setTitle("Help");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(splitPane.getScene().getWindow().getX() + 200);
        newWindow.setY(splitPane.getScene().getWindow().getY() + 100);

        newWindow.show();
    }

    // updates GUI
    public void updateListView(){
        tableView.setItems(listData);

    }
}
