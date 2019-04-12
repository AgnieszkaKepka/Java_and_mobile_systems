package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.stage.Stage;
import sample.NewTask.Priority;
import sample.NewTask.TaskNew;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public static ObservableList<TaskNew> toDo = FXCollections.observableArrayList();
    public static ObservableList<TaskNew> inProgress = FXCollections.observableArrayList();
    public static ObservableList<TaskNew> doneObs = FXCollections.observableArrayList();

    @FXML
    public Button btn;
    public static Stage addNewTask = new Stage();
    public  ListView<TaskNew> toDoList = new ListView<>(toDo);
    public  ListView<TaskNew> progress = new ListView<>(inProgress);
    public  ListView<TaskNew> done = new ListView<>(doneObs);
    public ContextMenu menu;
    public MenuItem DeleteTODO;
    public MenuItem EditTODO;
    public MenuItem DeleteDONE;
    public MenuItem EditDONE;
    public MenuItem DeleteINPROGRESS;
    public MenuItem EditINPROGRESS;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toDoList.setItems(toDo);
        progress.setItems(inProgress);
        done.setItems(doneObs);


        btn.setOnMouseClicked(mouseEvent -> {      //add task
            try {
                createAddWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        DeleteTODO.setOnAction(event -> {
            toDoList.getItems().remove(toDoList.getItems().get(toDoList.getFocusModel().getFocusedIndex()));
        });

        DeleteINPROGRESS.setOnAction(event -> {
            progress.getItems().remove(progress.getItems().get(progress.getFocusModel().getFocusedIndex()));
        });

        DeleteDONE.setOnAction(event -> {
            done.getItems().remove(done.getItems().get(done.getFocusModel().getFocusedIndex()));
        });



        EditTODO.setOnAction(actionEvent -> {
            addNewTask.show();
            toDoList.getItems().remove(toDoList.getItems().get(toDoList.getFocusModel().getFocusedIndex()));


        });

        EditINPROGRESS.setOnAction(actionEvent -> {
            addNewTask.show();
            progress.getItems().remove(progress.getItems().get(progress.getFocusModel().getFocusedIndex()));
        });
        EditDONE.setOnAction(actionEvent -> {
            addNewTask.show();
            done.getItems().remove(done.getItems().get(done.getFocusModel().getFocusedIndex()));
        });


        //DRAG-AND-DROP (From ToDO to Progress)
        toDoList.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (toDoList.getSelectionModel().getSelectedItem() == null) {
                    return;
                }
                Dragboard dragboard = toDoList.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(toDoList.getSelectionModel().getSelectedItem().getTitle());
                dragboard.setContent(content);
            }});

        progress.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
        });

        progress.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                String text = dragEvent.getDragboard().getString();
                String describe = dragEvent.getDragboard().getString();
                Priority priority = TaskNew.getPriority();
                LocalDate data = TaskNew.getLocalDate();
                progress.getItems().add(new TaskNew(text,priority,data,describe));
                toDo.remove(toDoList.getItems().get(toDoList.getFocusModel().getFocusedIndex()));
                toDoList.setItems(toDo);
                dragEvent.setDropCompleted(true);
            }
        });




        //DRAG-AND_DROP (From Progress to Done)
        progress.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (progress.getSelectionModel().getSelectedItem() == null) {
                    return;
                }
                Dragboard dragboard = progress.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(progress.getSelectionModel().getSelectedItem().getTitle());
                dragboard.setContent(content);
            }});

        done.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                dragEvent.acceptTransferModes(TransferMode.MOVE);
            }
        });

        done.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                String text = dragEvent.getDragboard().getString();
                String describe = dragEvent.getDragboard().getString();
                Priority priority = TaskNew.getPriority();
                LocalDate data = TaskNew.getLocalDate();
                done.getItems().add(new TaskNew(text,priority,data,describe));
                inProgress.remove(progress.getItems().get(progress.getFocusModel().getFocusedIndex()));
                progress.setItems(inProgress);
                dragEvent.setDropCompleted(true);
            }
        });



        //tooltip
        toDoList.setCellFactory(p -> new ListCell<TaskNew>()
        {
            @Override
            protected void updateItem(TaskNew item, boolean empty) {
                super.updateItem(item, empty);
                if(empty)
                    setText(null);
                else
                {
                    setText(getItem().getTitle());
                    Tooltip tooltip = new Tooltip();
                    tooltip.setText(getItem().getDescribe());
                    setTooltip(tooltip);
                }
            }
        });

        progress.setCellFactory(p -> new ListCell<TaskNew>()
        {
            @Override
            protected void updateItem(TaskNew item, boolean empty) {
                super.updateItem(item, empty);
                if(empty)
                    setText(null);
                else
                {
                    setText(getItem().getTitle());
                    Tooltip tooltip = new Tooltip();
                    tooltip.setText(getItem().getDescribe());
                    setTooltip(tooltip);
                }
            }
        });

        done.setCellFactory(p -> new ListCell<TaskNew>()
        {
            @Override
            protected void updateItem(TaskNew item, boolean empty) {
                super.updateItem(item, empty);
                if(empty)
                    setText(null);
                else
                {
                    setText(getItem().getTitle());
                    Tooltip tooltip = new Tooltip();
                    tooltip.setText(getItem().getDescribe());
                    setTooltip(tooltip);
                }
            }
        });


    }

    @FXML
    public void createAddWindow() throws IOException {
        FXMLLoader loaderAddTask = new FXMLLoader();
        loaderAddTask.setLocation(Controller.class.getResource("AddNewTask.fxml"));
        Parent root1 = loaderAddTask.load();
        addNewTask.setTitle("Add New Task");
        addNewTask.setScene(new Scene(root1));
        addNewTask.show();
    }


}
