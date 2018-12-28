package sample;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PayrollController implements Initializable {


    @FXML
    public AnchorPane anchor;


    @FXML
    private JFXTextField payrollSearch;

    @FXML
    private JFXTreeTableView<Payroll> payrollTreeView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addPayrollData();
    }


    public void addPayrollData(){

        UtilityClass.payrollData();

        JFXTreeTableColumn<Payroll, String> employeeId = new JFXTreeTableColumn<>("Employee ID");
        employeeId.setPrefWidth(150);
        employeeId.setCellValueFactory(param -> param.getValue().getValue().empIdProperty());


        JFXTreeTableColumn<Payroll, String> full_name = new JFXTreeTableColumn<>("Full Name");
        full_name.setPrefWidth(150);
        full_name.setCellValueFactory(param -> param.getValue().getValue().empNameProperty());


        JFXTreeTableColumn<Payroll, String> grade_level = new JFXTreeTableColumn<>("Grade Level");
        grade_level.setPrefWidth(150);
        grade_level.setCellValueFactory(param -> param.getValue().getValue().payrollIdProperty());

        JFXTreeTableColumn<Payroll, String> payable_amount= new JFXTreeTableColumn<>("Payable Amount");
        payable_amount.setPrefWidth(150);
        payable_amount.setCellValueFactory(param -> param.getValue().getValue().payAmtProperty());


        ObservableList<Payroll> pays = FXCollections.observableArrayList();

        List<Payroll> payList = UtilityClass.getPays();

        pays.addAll(payList);


        final TreeItem<Payroll> root = new RecursiveTreeItem<>(pays, RecursiveTreeObject::getChildren);
        //noinspection unchecked
        payrollTreeView.getColumns().setAll(employeeId, full_name, grade_level, payable_amount);
        payrollTreeView.setRoot(root);
        payrollTreeView.setColumnResizePolicy(TreeTableView.UNCONSTRAINED_RESIZE_POLICY);
        payrollTreeView.setShowRoot(false);
        payrollTreeView.setEditable(true);


    }

}
