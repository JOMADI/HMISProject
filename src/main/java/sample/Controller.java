package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableListValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private AnchorPane bodyAnchor;

    @FXML
    private AnchorPane drawerAnchor;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private AnchorPane detailsContainerPane;



    public void initialize(URL location, ResourceBundle resources) {

        try {
            Pane pane = FXMLLoader.load(getClass().getResource("/drawerContent.fxml"));
            ImageView profileImage = (ImageView) pane.getChildren().get(0);
            circledImage(profileImage);
            drawer.setSidePane(pane);

            drawerAction();//open or close drawer action;
            UtilityClass.loadAnother(detailsContainerPane, "employees.fxml");//Loading test Anchor

            for (Node node : pane.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                        switch (node.getAccessibleText()) {
                            case "profile_picture":
                                largeImage();//make image larger
                                break;
                            case "employee_details":
                                UtilityClass.loadAnother(detailsContainerPane, "employees.fxml");
                                break;
                            case "payroll":
                                UtilityClass.loadAnother(detailsContainerPane, "payroll.fxml");
                                break;
                            case "training":

                                break;
                            case "performance":

                                break;
                            case "resignation":

                                break;
                            case "resume_tracking":

                                break;
                            case "toggle":

                                break;
                            case "logout":
                                UtilityClass.CloseHikari();
                                System.exit(0);

                        }
                    });
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    private void drawerAction(){
        if(drawer.isOpened()) {
            drawer.close();
            AnchorPane.setLeftAnchor(detailsContainerPane, 0.0);
        }else{
            AnchorPane.setLeftAnchor(detailsContainerPane, 400.0);
            drawer.open();
        }
    }
    private void circledImage(ImageView profileImage){
        Rectangle clip = new Rectangle(profileImage.getFitWidth(), profileImage.getFitHeight());
        clip.setArcWidth(180);
        clip.setArcHeight(180);
        profileImage.setClip(clip);

        // snapshot the rounded image.
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = profileImage.snapshot(parameters, null);

        // remove the rounding clip so that our effect can show through.
        profileImage.setClip(null);

        // apply a shadow effect.
        profileImage.setEffect(new DropShadow(20, Color.BLACK));

        // store the rounded image in the imageView.
        profileImage.setImage(image);
    }

    private void largeImage(){
        VBox vBox = (VBox) UtilityClass.loadClass("profileImageLarge.fxml");
        assert vBox != null;
        ImageView large = (ImageView) vBox.getChildren().get(0);
        large.setImage(new Image("/Folder.jpg"));
        Scene scene = new Scene(vBox);
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        JFXButton jfxButton = (JFXButton) vBox.getChildren().get(1);
        jfxButton.setOnMouseClicked(event1 -> stage.close());
    }

}
