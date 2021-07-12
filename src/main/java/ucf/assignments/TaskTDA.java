/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Jonas Turner
 */

package ucf.assignments;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class TaskTDA {

    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private String date;     // date task is due
    private String taskAction;  // item description
    private CheckBox isComplete; // flag for sorting

    public TaskTDA(LocalDate date, String action){
        // set date as date of input
        this.date = date.format(formatter);

        // use string for task action
        this.taskAction = action;

        // set isComplete checkbox
        this.isComplete = new CheckBox();
    }

    // Slightly different constructor, date entered as string
    public TaskTDA(String date, String action, String state){
        this.date = date;
        this.taskAction = action;
        this.isComplete = new CheckBox();

        // for reading the completion status
        if(state.equalsIgnoreCase("0")){
            this.isComplete.setSelected(false);

        }else{
            this.isComplete.setSelected(true);
        }

    }

    @Override
    public String toString(){
        return String.format("%-10s %s", getDate(), getTaskAction());
    }


    // setters and getters
    public String getTaskAction() {
        return taskAction;
    }

    public void setTaskAction(String taskAction) {

        this.taskAction = taskAction;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String inputDate) {
        this.date = inputDate;
    }

    public CheckBox getIsComplete(){
        return isComplete;
    }

    public String getIsCompleteAsString(){

        if(getIsComplete().isSelected()){
            return "1";
        }else{
            return "0";
        }
    }

    public void setComplete(CheckBox complete) {
        isComplete = complete;
    }
}
