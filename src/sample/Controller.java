package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Controller {
        public ImageView exitButtonImage;
        Stage stage;

        @FXML
        private AnchorPane scenePane;

        @FXML
        private AnchorPane backScene2;

        @FXML
        private AnchorPane backScene1;

        //Designed exit button
        @FXML
        public void exitMenu() {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("You are about to exit!");
            if (alert.showAndWait().get() == ButtonType.OK) {
                stage = (Stage) scenePane.getScene().getWindow();
                stage.close();
            }
        }

        //Access to 4 algo scene
        @FXML
        private Button RN;

        @FXML
        private Button WS;

        @FXML
        private Button ER;

        @FXML
        private Button BA;

        @FXML
        public void ERscene(ActionEvent event) {
            try {
                Parent RNroot = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Algorithm/ER/ER.fxml"));
                Scene RNscene = new Scene(RNroot);
                Stage RNstage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                //RNstage.setTitle("RN");
                RNstage.setScene(RNscene);
                RNstage.centerOnScreen();
                RNstage.show();
                RNscene.getStylesheets().add("sample/Algorithm/style.css");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        @FXML
        public void RNscene (ActionEvent event) throws IOException {
                try {
                    Parent RNroot = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Algorithm/RN/RN.fxml"));
                    Scene RNscene = new Scene(RNroot);
                    Stage RNstage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    //RNstage.setTitle("RN");
                    RNstage.setScene(RNscene);
                    RNstage.centerOnScreen();
                    RNstage.show();
                    RNscene.getStylesheets().add("sample/Algorithm/style.css");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
        }

        @FXML
        public void WSscene(ActionEvent event) {
            try {
                Parent RNroot = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Algorithm/WS/WS.fxml"));
                Scene RNscene = new Scene(RNroot);
                Stage RNstage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                //RNstage.setTitle("RN");
                RNstage.setScene(RNscene);
                RNstage.centerOnScreen();
                RNstage.show();
                RNscene.getStylesheets().add("sample/Algorithm/style.css");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        @FXML
        public void BAscene(ActionEvent event) {
            try {
                Parent RNroot = FXMLLoader.load(getClass().getClassLoader().getResource("sample/Algorithm/BA/BA.fxml"));
                Scene RNscene = new Scene(RNroot);
                Stage RNstage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                //RNstage.setTitle("RN");
                RNstage.setScene(RNscene);
                RNstage.centerOnScreen();
                RNstage.show();
                RNscene.getStylesheets().add("sample/Algorithm/style.css");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
}