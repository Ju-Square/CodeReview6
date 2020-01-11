// Julia Hinterecker

package jdbc.codereview6;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class TeacherApp extends Application {
    private ListView<Teacher> listView;
    private ObservableList<Teacher> data;

    private ListView<Classes> listView2;
    private ObservableList<Classes> data2;

    private TextField idtxt;
    private TextField nametxt;
    private TextArea surnametxt;
    private Text emailtxt;

    private  TeacherDataAccess dbaccess;

    public  static void main(String [] args) {

        Application.launch(args);
    }
    @Override
    public  void init() {

        try  {
            dbaccess = new TeacherDataAccess();
        }
        catch (Exception e) {

            displayException(e);
        }
    }
    @Override
    public void stop() {

        try {
            dbaccess.closeDb();
        }
        catch (Exception e) {

            displayException(e);
        }
    }
    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        Label lblTop = new Label("School Statistics of Teachers (1)");
        Label lblId = new Label("ID");
        Label lblName = new Label("Name");
        Label lblSurname = new Label("Surname");
        Label lblEmail = new Label("Email");
        Label teachers = new Label("Teachers");
        Label wichClasses = new Label("Teaches this classes");
        Label thisTeacher = new Label("This teacher");
        idtxt = new TextField();
        nametxt = new TextField();
        surnametxt = new TextArea();
        emailtxt = new Text();

        HBox idData = new HBox(lblId, idtxt);
        HBox nameData = new HBox(lblName, nametxt);
        HBox surnameData = new HBox(lblSurname, surnametxt);
        HBox emailData = new HBox(lblEmail, emailtxt);

        idData.setSpacing(10);
        nameData.setSpacing(10);
        surnameData.setSpacing(10);
        emailData.setSpacing(10);

        HBox hBoxLblTop = new HBox(lblTop);

        VBox vBoxDataCenter = new VBox(thisTeacher, idData, nameData, surnameData, emailData);
        vBoxDataCenter.setSpacing(35);
        vBoxDataCenter.setAlignment(Pos.BASELINE_CENTER);

        VBox vBoxListView = new VBox();

        VBox vBoxListView2 = new VBox();

        root.setCenter(vBoxDataCenter);
        // hBoxLblTop.setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));

        root.setPadding(new Insets(15, 20, 10, 10));

        // View

        listView = new ListView<>();

        listView.getSelectionModel().selectedIndexProperty().addListener(
                new ListSelectChangeListener());
        data = getDbData();
        listView.setItems(data);

        vBoxListView.getChildren().addAll(teachers, listView);

        listView2 = new ListView<>();

        vBoxListView2.getChildren().addAll(wichClasses, listView2);



        root.setLeft(vBoxListView);
        root.setRight(listView2);


        // TOP
        String cssLayoutHboxTop = "-fx-border-color: green;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-radius: 10 10 10 10;\n" +
                "-fx-background-radius: 10 10 0 0;" +
                "-fx-border-width: 3;\n" +
                "-fx-padding: 15;";

        hBoxLblTop.setAlignment(Pos.TOP_CENTER);
        hBoxLblTop.setStyle(cssLayoutHboxTop);
        root.setTop(hBoxLblTop);


        // Scene View

        Scene scene = new Scene(root, 800, 400);

        primaryStage.setTitle("School Statistics of Teachers (1)");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private class ListSelectChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number old_val, Number new_val) {

            if ((new_val.intValue() < 0) || (new_val.intValue() >= data.size())) {

                return; // invalid data
            }

            // set id, name, surname and email fields for the selected teacher
            Teacher teacher = data.get(new_val.intValue());
            idtxt.setText(Integer.toString(teacher.getId()));
            nametxt.setText(teacher.getName());
            surnametxt.setText(teacher.getSurname());
            emailtxt.setText(teacher.getEmail());

            data2 = getDbData2(Integer.valueOf(idtxt.getText()));

            listView2.setItems(data2);

        }
    }

    private ObservableList<Teacher> getDbData() {

        List<Teacher> list = null;

        try {
            list = dbaccess.getAllRows();
        }
        catch (Exception e) {

            displayException(e);
        }

        ObservableList<Teacher> dbData = FXCollections.observableList(list);
        return dbData;
    }

    private ObservableList<Classes> getDbData2(int i) {

        List<Classes> list2 = null;

        try {
            list2 = dbaccess.getAllRows2(i);
        }
        catch (Exception e) {

            displayException(e);
        }

        ObservableList<Classes> dbData = FXCollections.observableList(list2);
        return dbData;
    }

    private void displayException(Exception e) {

        System.out.println("###### Exception ######");
        e.printStackTrace();
        System.exit(0);
    }
    }
