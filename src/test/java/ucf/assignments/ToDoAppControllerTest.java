package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoAppControllerTest {

    @Test
    void importToDoList_reads_data_from_JSON_and_adds_accordingly() {
        // create a JSON with multiple lists of tasks

        // read from JSON

        // parse each list object with their own tasks

        // now check if each list contains each intended task; flag if not found

        // check flag
    }

    @Test
    void exportToDoList_writes_data_to_JSON_file_and_saves() {
        // create some lists with tasks

        // write a JSON that contains this data

        // read from the JSON

        // compare if data parsed from JSON is the same as what was written to it;
        // flag if match not found

        // check flag
    }

    @Test
    void addToDoList_adds_a_list_with_specified_content() {
        // create a list

        // check the contents of the list;
        // flag if data is not what is intended

        // check flag
    }

    @Test
    void editToDoList_changes_name_to_desired_input() {
        // create a list

        // now change the list name

        // check if name of list successfully changed
    }

    @Test
    void delToDoList_deletes_target_list() {
        // create a list

        // now remove the list

        // search if list is still there; flag if found

        // check flag
    }

    @Test
    void addTask_adds_a_task_to_a_specific_list() {
        // create list

        // now add a new task to that list

        // check if task is found
    }

    @Test
    void editTask_changes_task_attributes_to_specified_attributes() {
        // create list with two tasks

        // add the list

        // now edit 1 task in that list to have different details

        // check if the details of the tasks match
    }

    @Test
    void delTask_removes_task_from_list_and_updates_current_list() {
        // create list with two tasks

        // now remove one of the tasks

        // check if task was removed
    }
}