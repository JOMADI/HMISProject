package sample;

import com.jfoenix.controls.*;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class AddEmployee implements Initializable {

    @FXML
    private AnchorPane addEmployee;

    @FXML
    private JFXComboBox<String> title;

    @FXML
    private JFXTextField fName;

    @FXML
    private JFXTextField mName;

    @FXML
    private JFXTextField lName;

    @FXML
    private JFXComboBox<String> bGroup;

    @FXML
    private JFXDatePicker bDate;

    @FXML
    private JFXComboBox<String> gender;

    @FXML
    private JFXTextField nationality;

    @FXML
    private JFXTextArea address;

    @FXML
    private JFXTextField state;

    @FXML
    private JFXTextField city;

    @FXML
    private JFXTextField phoneNum;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXRadioButton active;

    @FXML
    private JFXRadioButton inactive;

    @FXML
    private JFXTextField supervisor;


    @FXML
    private JFXComboBox<String> dept;

    @FXML
    private JFXTextField mStatus;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton addButton;

    @FXML
    private ImageView image;



    private String selected;

    private String dept_id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        final JFXSnackbar jfxSnackbar = new JFXSnackbar(addEmployee);
//        EventHandler eventHandler = event -> jfxSnackbar.unregisterSnackbarContainer(addEmployee);
//
//        jfxSnackbar.show("Successfully Added Employee: " + fName.getText() + " " + lName.getText(), " CLOSE ",6000, eventHandler);

        ToggleGroup toggleGroup = new ToggleGroup();
        active.setToggleGroup(toggleGroup);
        inactive.setToggleGroup(toggleGroup);

        title.getItems().addAll(UtilityClass.getTitleData());

        bGroup.getItems().addAll(UtilityClass.getBloodData());

        gender.getItems().addAll(UtilityClass.getGenderData());

        dept.getItems().addAll(UtilityClass.getDepartmentData());
        dept.valueProperty().addListener((observable, oldValue, newValue) -> {
          dept_id = UtilityClass.getDepartmentId(newValue);
          System.out.format("dept_name: %s depT_id: %s%n", newValue, dept_id);
        });

        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {

            JFXRadioButton radioButton = (JFXRadioButton) newValue.getToggleGroup().getSelectedToggle();
            selected = radioButton.getText();
        });

        cancelButton.setOnMouseClicked(event -> {

            Stage stage = (Stage) addEmployee.getScene().getWindow();
            stage.close();

        });

        addButton.setOnMouseClicked(event -> {
            cancelButton.setDisable(true);
            insertData();
            addButton.setDisable(true);

        });


    }

    void insertData(){

//        java.sql.Date date = java.sql.Date.valueOf(bDate.getValue());
//        Date date1 = new Date(date.getTime());
        UtilityClass.insertData(
                title.getValue(),
                fName.getText(),
                mName.getText(),
                lName.getText(),
                bGroup.getValue(),
                bDate.getValue() != null ? bDate.getValue().toString() : " ",
                gender.getValue(),
                nationality.getText(),
                address.getText(),
                state.getText(),
                city.getText(),
                phoneNum.getText(),
                email.getText(),
                selected,
                mStatus.getText(),
                dept_id);

        System.out.format("%s, %s %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n",
                title.getValue(),
                fName.getText(),
                mName.getText(),
                lName.getText(),
                bGroup.getValue(),
                bDate.getValue() != null ? bDate.getValue().toString() : " ",
                gender.getValue(),
                nationality.getText(),
                address.getText(),
                state.getText(),
                city.getText(),
                phoneNum.getText(),
                email.getText(),
                selected,
                mStatus.getText(),
                dept_id);


        new EmployeeDetailsController().addTableDetails();


        final JFXSnackbar jfxSnackbar = new JFXSnackbar(addEmployee);
        EventHandler eventHandler = event -> {
            jfxSnackbar.unregisterSnackbarContainer(addEmployee);
            Stage stage = (Stage) addEmployee.getScene().getWindow();
            stage.close();
        };

        jfxSnackbar.show("Successfully Added Employee: " + fName.getText() + " " + lName.getText(), " CLOSE ",20000, eventHandler);
    }

}

