package ucf.assignments;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


class ToDoAppControllerTest{

    private DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    void addTask_inserts_into_table() {

    }

    @Test
    void delTask_removes_from_table() {
    }

    @Test
    void showAllTasks_resets_table_to_include_all_tasks() {
    }

    @Test
    void showComOnlyTasks_removes_all_inc_tasks() {
    }

    @Test
    void saveOperation_writes_data_to_file_correctly() {
    }

    @Test
    void loadOperation_reads_data_from_file_and_inserts_into_table_correctly() {
    }
}