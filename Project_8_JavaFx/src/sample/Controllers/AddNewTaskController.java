package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import sample.NewTask.Priority;
import sample.NewTask.TaskNew;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static sample.Controllers.Controller.addNewTask;

public class AddNewTaskController implements Initializable {

    @FXML
    public ComboBox<Priority> prio;
    public Button btnApply;
    public TextField title;
    public DatePicker date;
    public TextField description;
    public Button Update;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prio.getItems().addAll(Priority.VERY_HIGH, Priority.HIGH, Priority.MEDIUM,Priority.LOW);

        btnApply.setOnMouseClicked(event -> {
            applyTask();
        });

        Update.setOnMouseClicked(event -> {
            applyTask();
        });
    }

    public void applyTask(){
        String text = title.getText();
        Priority priority = prio.getValue();
        LocalDate data = date.getValue();
        String describe = description.getText();
        Controller.toDo.add(new TaskNew(text,priority,data,describe));
        addNewTask.close();

    }


}
