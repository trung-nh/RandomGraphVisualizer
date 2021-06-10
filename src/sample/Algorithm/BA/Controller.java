package sample.Algorithm.BA;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Controller {
	public TextField log_max_deg;
	public TextField log_avg_deg;
	int vCount;
	BAGraph myGraph = new BAGraph();

	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@FXML
	private Button enterButton;

	@FXML
	private TextField verticesID;

	@FXML
	private Button GoBack;

	@FXML
	private Button nextButton;

	@FXML
	private Button execButton;


	@FXML
	void getData(ActionEvent event) {
		String verticestxt = verticesID.getText();
		if (verticestxt.isEmpty()) verticestxt = "0";
		if (isInteger(verticestxt)) {
			vCount = Integer.parseInt(verticestxt);
			if (vCount >= 2) {
				JOptionPane.showMessageDialog(null, "Number of vertices is: " + vCount);
			} else {
				JOptionPane.showMessageDialog(null, "Invalid input! Input must be an Integer larger than 2");
				//return false
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid input! Input must be an Integer larger than 2");
			//return false
		}
	}

	//return to main scene
	@FXML
	void Return(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample/sample.fxml"));
			Scene scene = new Scene(root);
			scene.setFill(Color.TRANSPARENT);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.centerOnScreen();
			stage.show();
			scene.getStylesheets().add("sample/style.css");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private AnchorPane pane;

	@FXML
	private Button generateButton;

	@FXML
	void setUp(ActionEvent actionEvent) {
		myGraph.initGraph(vCount, pane);
	}

	@FXML
	void generate(ActionEvent event) {
		myGraph.execAlgorithm(pane, 0);
	}

	//Reset all nodes
	@FXML
	private Button resetButton;

	@FXML
	void reset(ActionEvent event) {
		myGraph.resetStrategy(pane);
		JOptionPane.showMessageDialog(null, "Successfully removed!");
		log_avg_deg.setText(null);
		log_max_deg.setText(null);
	}

	@FXML
	void logRes(ActionEvent actionEvent) {
		myGraph.logRes(log_max_deg, log_avg_deg);
	}
}