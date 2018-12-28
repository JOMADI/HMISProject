package sample;

import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeDetailsController implements Initializable {

    @FXML
    public AnchorPane anchor;

    @FXML
    public JFXButton addReport;

    @FXML
    private JFXTreeTableView<Employee> employeesTreeView;

    @FXML
    private JFXButton addEmployee;

    @FXML
    private JFXTextField searchEmployee;

    @FXML
    private ScrollPane scroll;

    @FXML
    private FlowPane flow;


    @FXML
    private JFXTreeTableView<Leave> employeesLeaveTreeView;






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addTableDetails();

        setUpReportCreation();

        addLeaveData();


//        addReport.setOnMouseClicked(event -> {
//            Task<Void> task = new Task<Void>() {
//                @Override
//                protected Void call() {
//                    return null;
//                }
//
//                @Override
//                public void run() {
//                    try{
//                        UtilityClass.makePdf();
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }
//                }
//            };
//
//            Thread thread = new Thread(task);
//            thread.setDaemon(true);
//
//            thread.run();
//        });
    }


//    private void changed(ObservableValue<? extends String> o, String oldVal, String newVal) {
//        employeesLeaveTreeView.setPredicate(user -> user.getValue().age.get().toLowerCase().contains(newVal.toLowerCase())
//                || user.getValue().department.get().toLowerCase().contains(newVal.toLowerCase())
//                || user.getValue().userName.get().toLowerCase().contains(newVal.toLowerCase())
//                || user.getValue().empId.get().toLowerCase().contains(newVal.toLowerCase()));
//    }
//
//
//

    private void setUpReportCreation(){

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Horizontal scroll bar
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);    // Vertical scroll bar
        scroll.setContent(flow);
        scroll.viewportBoundsProperty().addListener((ov, oldBounds, bounds) -> {
            flow.setPrefWidth(bounds.getWidth());
            flow.setPrefHeight(bounds.getHeight());
        });

        flow.setStyle("-fx-background-color: #ffffff;");

        for (int i = 0; i < 10; i++) {
            flow.getChildren().add(generateRectangle("Employee Emails " + i));
        }

    }

    private StackPane generateRectangle(String s) {

        Rectangle rect2 = new Rectangle(10, 10, 10, 10);
        rect2.setId("app");
        rect2.setArcHeight(8);
        rect2.setArcWidth(8);
        rect2.setStyle("-fx-fill: #007cff; -jfx-button-type:RAISED;");
        //rect2.setX(10);
        //rect2.setY(160);
        rect2.setStrokeWidth(1);
        rect2.setStroke(Color.WHITE);
        rect2.setWidth(280);
        rect2.setHeight(200);

        Text text = new Text(s);
        text.setStyle("-fx-fill:#ffffff; -fx-font-size:20px;");

        StackPane stackPane = new StackPane(rect2, text);
        stackPane.setLayoutX(10);
        stackPane.setLayoutY(10);
        return stackPane;
    }

    private void createPdf(){

    }


    public void addLeaveData(){
        UtilityClass.leaveData();

        JFXTreeTableColumn<Leave, String> employeeId = new JFXTreeTableColumn<>("Employee ID");
        employeeId.setPrefWidth(150);
        employeeId.setCellValueFactory(param -> param.getValue().getValue().empIdProperty());


        JFXTreeTableColumn<Leave, String> full_name = new JFXTreeTableColumn<>("Full Name");
        full_name.setPrefWidth(150);
        full_name.setCellValueFactory(param -> param.getValue().getValue().fNameProperty());


        JFXTreeTableColumn<Leave, String> start_date = new JFXTreeTableColumn<>("Start Date");
        start_date.setPrefWidth(150);
        start_date.setCellValueFactory(param -> param.getValue().getValue().startDateProperty());

        JFXTreeTableColumn<Leave, String> end_date= new JFXTreeTableColumn<>("End Date");
        end_date.setPrefWidth(150);
        end_date.setCellValueFactory(param -> param.getValue().getValue().startDateProperty());


        JFXTreeTableColumn<Leave, String> cat= new JFXTreeTableColumn<>("Leave Category");
        cat.setPrefWidth(150);
        cat.setCellValueFactory(param -> param.getValue().getValue().categoryProperty());

        JFXTreeTableColumn<Leave, String> approved= new JFXTreeTableColumn<>("Approved");
        approved.setPrefWidth(150);
        approved.setCellValueFactory(param -> param.getValue().getValue().approvedProperty());

        ObservableList<Leave> leaves = FXCollections.observableArrayList();

        List<Leave> leaveList = UtilityClass.getLeaves();

        leaves.addAll(leaveList);


        final TreeItem<Leave> root = new RecursiveTreeItem<>(leaves, RecursiveTreeObject::getChildren);
        //noinspection unchecked
        employeesLeaveTreeView.getColumns().setAll(employeeId, full_name, start_date, end_date, cat, approved);
        employeesLeaveTreeView.setRoot(root);
        employeesLeaveTreeView.setColumnResizePolicy(TreeTableView.UNCONSTRAINED_RESIZE_POLICY);
        employeesLeaveTreeView.setShowRoot(false);
        employeesLeaveTreeView.setEditable(true);

        employeesLeaveTreeView.setOnMouseClicked(event -> {
            System.out.println("Clicked on " + employeesLeaveTreeView.getSelectionModel().getSelectedItem());
        });


    }

   public void addTableDetails(){

        UtilityClass.initDatabaseData();
        JFXTreeTableColumn<Employee, String> employeeId = new JFXTreeTableColumn<>("Employee ID");
        employeeId.setPrefWidth(150);
        employeeId.setCellValueFactory(param -> param.getValue().getValue().empIdProperty());
       employeeId.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       employeeId.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().empIdProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> title = new JFXTreeTableColumn<>("Title");
        title.setPrefWidth(150);
        title.setCellValueFactory(param -> param.getValue().getValue().titleProperty());
        title.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
        title.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().titleProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> fName = new JFXTreeTableColumn<>("First Name");
        fName.setPrefWidth(250);
        fName.setCellValueFactory(param -> param.getValue().getValue().fNameProperty());
        fName.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
        fName.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().fNameProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> mName = new JFXTreeTableColumn<>("Middle Name");
        mName.setPrefWidth(250);
        mName.setCellValueFactory(param -> param.getValue().getValue().mNameProperty());
       mName.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       mName.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().mNameProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> lName = new JFXTreeTableColumn<>("Last Name");
        lName.setPrefWidth(250);
        lName.setCellValueFactory(param -> param.getValue().getValue().lNameProperty());
       lName.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       lName.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().lNameProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> bDate = new JFXTreeTableColumn<>("Birth Date");
        bDate.setPrefWidth(250);
        bDate.setCellValueFactory(param -> param.getValue().getValue().birthDateProperty());
        bDate.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
        bDate.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().birthDateProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> bGroup = new JFXTreeTableColumn<>("Blood Group");
        bGroup.setPrefWidth(250);
        bGroup.setCellValueFactory(param -> param.getValue().getValue().bloodGroupProperty());
       bGroup.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       bGroup.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().bloodGroupProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> gender = new JFXTreeTableColumn<>("Gender");
        gender.setPrefWidth(250);
        gender.setCellValueFactory(param -> param.getValue().getValue().genderProperty());
       gender.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       gender.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().genderProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> nationality = new JFXTreeTableColumn<>("Nationality");
        nationality.setPrefWidth(250);
        nationality.setCellValueFactory(param -> param.getValue().getValue().nationalityProperty());
       nationality.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       nationality.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().nationalityProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> address = new JFXTreeTableColumn<>("Address");
        address.setPrefWidth(250);
        address.setCellValueFactory(param -> param.getValue().getValue().addressProperty());
       address.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       address.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().titleProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> state = new JFXTreeTableColumn<>("State");
        state.setPrefWidth(250);
        state.setCellValueFactory(param -> param.getValue().getValue().stateProperty());
        state.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       state.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().stateProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> city = new JFXTreeTableColumn<>("City");
        city.setPrefWidth(250);
        city.setCellValueFactory(param -> param.getValue().getValue().cityProperty());
       city.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       city.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().cityProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> phoneNum = new JFXTreeTableColumn<>("Phone Number");
        phoneNum.setPrefWidth(250);
        phoneNum.setCellValueFactory(param -> param.getValue().getValue().phoneNumberProperty());
       phoneNum.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       phoneNum.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().phoneNumberProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> email = new JFXTreeTableColumn<>("Email");
        email.setPrefWidth(250);
        email.setCellValueFactory(param -> param.getValue().getValue().emailProperty());
       email.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       email.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().emailProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> status = new JFXTreeTableColumn<>("Status");
        status.setPrefWidth(250);
        status.setCellValueFactory(param -> param.getValue().getValue().statusProperty());
       status.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       status.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().statusProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> mStatus = new JFXTreeTableColumn<>("Marital Status");
        mStatus.setPrefWidth(250);
        mStatus.setCellValueFactory(param -> param.getValue().getValue().maritalStatusProperty());
       mStatus.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       mStatus.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().maritalStatusProperty().setValue(event.getNewValue()));


        JFXTreeTableColumn<Employee, String> supervisor = new JFXTreeTableColumn<>("Supervisor");
        supervisor.setPrefWidth(250);
        supervisor.setCellValueFactory(param -> param.getValue().getValue().supervisorProperty());
       supervisor.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       supervisor.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().supervisorProperty().setValue(event.getNewValue()));

        JFXTreeTableColumn<Employee, String> payroll = new JFXTreeTableColumn<>("Payroll");
        payroll.setPrefWidth(250);
        payroll.setCellValueFactory(param -> param.getValue().getValue().supervisorProperty());
       payroll.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       payroll.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().payrollProperty().setValue(event.getNewValue()));


        JFXTreeTableColumn<Employee, String> dept = new JFXTreeTableColumn<>("Department");
        dept.setPrefWidth(250);
        dept.setCellValueFactory(param -> param.getValue().getValue().departmentProperty());
       dept.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
       dept.setOnEditCommit(event -> event.getTreeTableView().getTreeItem(event.getTreeTablePosition().getRow()).getValue().departmentProperty().setValue(event.getNewValue()));


        ObservableList<Employee> employees = FXCollections.observableArrayList();

        List<Employee> employeeList = UtilityClass.getEmployees();

        employees.addAll(employeeList);



        final TreeItem<Employee> root = new RecursiveTreeItem<>(employees, RecursiveTreeObject::getChildren);
        //noinspection unchecked
        employeesTreeView.getColumns().setAll(employeeId, title, fName, mName, lName, bGroup, bDate, gender, nationality, address, state, city, phoneNum, email, status, mStatus, supervisor, dept, payroll);
        employeesTreeView.setRoot(root);
        employeesTreeView.setColumnResizePolicy(TreeTableView.UNCONSTRAINED_RESIZE_POLICY);
        employeesTreeView.setShowRoot(false);
        employeesTreeView.setEditable(true);

        employeesTreeView.setOnMouseClicked(event -> {
            System.out.println("Clicked on " + employeesTreeView.getSelectionModel().getSelectedItem());
        });

      // searchEmployee.textProperty().addListener(this::changed);


        addEmployee.setOnMouseClicked(event -> {
            //if(event.isPrimaryButtonDown()){
            AnchorPane anchorPane = (AnchorPane) UtilityClass.loadClass("addEmployeeDialog.fxml");
            assert anchorPane != null;


            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();

//            JFXButton jfxButton = (JFXButton) anchorPane.lookup("#cancelButton");
//            jfxButton.setOnMouseClicked(event2 -> {
//                stage.close();
//            });

//            JFXButton addButton = (JFXButton) anchorPane.lookup("#addButton");
//            addButton.setOnMouseClicked(event1 -> {
//                new AddEmployee().insertData();
//            });

        });

    }
}
