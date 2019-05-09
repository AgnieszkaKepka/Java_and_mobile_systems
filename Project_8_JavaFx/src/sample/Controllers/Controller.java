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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.NewTask.Priority;
import sample.NewTask.TaskNew;
import sample.Serialize;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;


public class Controller implements Initializable, Serializable {

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
    public ContextMenu menuProgress;
    public ContextMenu menuDone;
    public MenuItem DeleteProgress;
    public MenuItem EditProgress;
    public MenuItem DeleteDone;
    public MenuItem EditDone;
    public MenuItem Delete;
    public MenuItem Edit;

    //------------------------------------------LAB8-------------------------------------------//
    public MenuItem open;
    public MenuItem save;
    public MenuItem export;
    public MenuItem impor;
    public AnchorPane anchorPane;

   Serialize serialize = new Serialize();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toDoList.setItems(toDo);
        progress.setItems(inProgress);
        done.setItems(doneObs);

        //-------------------------------LAB8-------------------------------------//

        save.setOnAction(event -> {

            serialize.firstList = new ArrayList<>(toDo);  //rzutuje na arraylist
            serialize.secondList = new ArrayList<>(inProgress);
            serialize.thirdList = new ArrayList<>(doneObs);

            try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("TestObject.ser"))){
                outputStream.writeObject(serialize);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        open.setOnAction(event -> {

            try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("TestObject.ser"))){
                serialize = (Serialize) inputStream.readObject();
            } catch (FileNotFoundException e){
                System.err.println("File not found "+e);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            toDo= FXCollections.observableArrayList(serialize.firstList);
            toDoList.setItems(toDo);

            inProgress= FXCollections.observableArrayList(serialize.secondList);
            progress.setItems(inProgress);

            doneObs= FXCollections.observableArrayList(serialize.thirdList);
            done.setItems(doneObs);

        });

        export.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser(); //okienko
            Stage FCstage = (Stage) anchorPane.getScene().getWindow();
            File workingDirectory = new File(System.getProperty("user.dir"));
            fileChooser.setInitialDirectory(workingDirectory);
            fileChooser.setTitle("Choose file to export data");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV", "*.csv")
            );
            File file = fileChooser.showSaveDialog(FCstage);

            if (file != null) {
                serialize.firstList = new ArrayList<>(toDo);
                serialize.secondList = new ArrayList<>(inProgress);
                serialize.thirdList = new ArrayList<>(doneObs);

                generateCSVfile(file);

            }
        });

        impor.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            Stage FCstage = (Stage) anchorPane.getScene().getWindow();
            File workingDirectory = new File(System.getProperty("user.dir"));
            fileChooser.setInitialDirectory(workingDirectory);
            fileChooser.setTitle("Choose file to import data");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("CSV", "*.csv")
            );

            File file = fileChooser.showOpenDialog(FCstage);
            serialize.firstList = new ArrayList<>(toDo);
            serialize.secondList = new ArrayList<>(inProgress);
            serialize.thirdList = new ArrayList<>(doneObs);
            if (file != null) {
                try(Reader reader = new FileReader(file.getAbsolutePath())){

                    loadFromCSVfile(file);


                    toDo= FXCollections.observableArrayList(serialize.firstList);
                    toDoList.setItems(toDo);

                    inProgress= FXCollections.observableArrayList(serialize.secondList);
                    progress.setItems(inProgress);

                    doneObs= FXCollections.observableArrayList(serialize.thirdList);
                    done.setItems(doneObs);
                } catch (FileNotFoundException | NullPointerException e){
                    System.err.println("File not found "+e);
                } catch (IOException e) { }
            }
        });

        //-------------------------------END-------------------------------------//

        btn.setOnMouseClicked(mouseEvent -> {
            try {
                createAddWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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


        Delete.setOnAction(event -> {
            toDoList.getItems().remove(toDoList.getItems().get(toDoList.getFocusModel().getFocusedIndex()));
        });

        DeleteProgress.setOnAction(event -> {
            progress.getItems().remove(progress.getItems().get(progress.getFocusModel().getFocusedIndex()));
        });

        DeleteDone.setOnAction(event -> {
            done.getItems().remove(done.getItems().get(done.getFocusModel().getFocusedIndex()));
        });

        Edit.setOnAction(event -> {
          addNewTask.show();
          toDoList.getItems().remove(toDoList.getItems().get(toDoList.getFocusModel().getFocusedIndex()));

        });

        EditProgress.setOnAction(event -> {
            addNewTask.show();
            progress.getItems().remove(progress.getItems().get(progress.getFocusModel().getFocusedIndex()));

        });

        EditDone.setOnAction(event -> {
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


    }

    @FXML
    public void createAddWindow() throws IOException {
        FXMLLoader loaderAddTask = new FXMLLoader();
        loaderAddTask.setLocation(Controller.class.getResource("AddNewTask.fxml"));
        Parent root1 = loaderAddTask.load();
        addNewTask.setTitle("Add New Task");
        addNewTask.setScene(new Scene(root1,600,400));
        addNewTask.show();
    }

    public void generateCSVfile(File file){
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("title", "description", "priority", "localDate", "typeOfList"));
        ) {

            for (int i = 0; i < serialize.firstList.size(); i++){
                csvPrinter.printRecord(serialize.firstList.get(i).getTitle(),
                        serialize.firstList.get(i).getDescribe().replace("\n"," "),
                        serialize.firstList.get(i).getPriority(),
                        serialize.firstList.get(i).getLocalDate(),
                        "First List");
            }
            for (int i = 0; i < serialize.secondList.size(); i++){
                csvPrinter.printRecord(serialize.secondList.get(i).getTitle(),
                        serialize.secondList.get(i).getDescribe().replace("\n"," "),
                        serialize.secondList.get(i).getPriority(),
                        serialize.secondList.get(i).getLocalDate(),
                        "Second List");
            }

            for (int i = 0; i < serialize.thirdList.size(); i++){
                csvPrinter.printRecord(serialize.thirdList.get(i).getTitle(),
                        serialize.thirdList.get(i).getDescribe().replace("\n"," "),
                        serialize.thirdList.get(i).getPriority(),
                        serialize.thirdList.get(i).getLocalDate(),
                        "Third List");
            }

            csvPrinter.flush(); //oproznia strumien bazowy
        } catch (IOException e){ }
    }

    public void loadFromCSVfile(File file){
        try (
                Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT    //analizuje pliki csv
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        ) {
            serialize.firstList.clear();
            serialize.secondList.clear();
            serialize.thirdList.clear();
            for (CSVRecord csvRecord : csvParser) {
                String title = csvRecord.get("title");
                String describe = csvRecord.get("description");
                String priority = csvRecord.get("priority");
                LocalDate date = LocalDate.parse(csvRecord.get("localDate"));
                String typeOfList = csvRecord.get("typeOfList");

                Priority priority1;
                if(priority.equals("VERY_HIGH")) {priority1=Priority.VERY_HIGH;}
                else if(priority.equals("HIGH")){priority1=Priority.HIGH;}
                else if(priority.equals("MEDIUM")){priority1=Priority.MEDIUM;}
                else {priority1=Priority.LOW;}

                System.out.println(priority);
                switch (typeOfList) {
                    case "First List":
                        serialize.firstList.add(new TaskNew(title, priority1, date, describe));
                        break;
                    case "Second List":
                        serialize.secondList.add(new TaskNew(title, priority1, date, describe));
                        break;
                    case "Third List":
                        serialize.thirdList.add(new TaskNew(title, priority1, date, describe));
                        break;
                }
            }
        } catch (IOException e){ }
    }


}
