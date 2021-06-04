package sample.Algorithm.ER;

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
import javafx.stage.Stage;

import javax.swing.*;


public class Controller {
	// variables, constants
	int vCount;
	double prob;
	ERGraph myGraph = new ERGraph();


	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@FXML
	private Button GoBack;

	@FXML
	private Button nextButton;

	@FXML
	private Button execButton;

	@FXML
	private TextField VerticesID;

	@FXML
	private TextField probID;

	@FXML
	private Button enterButton;

	@FXML
	void getData(ActionEvent event) {
		String verticestxt = VerticesID.getText();
		String probtxt = probID.getText();
		if (verticestxt.isEmpty()) verticestxt = "0";
		if (probtxt.isEmpty()) probtxt = "0";
		if (isInteger(verticestxt)) {
			vCount = Integer.parseInt(verticestxt);
			if (vCount >= 0) {
				JOptionPane.showMessageDialog(null, "Number of vertices is:" + vCount);
			} else {
				JOptionPane.showMessageDialog(null, "Invalid input! Input must be an Integer ");
				//return false
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid input! Input must be an Integer ");
			//return false
		}

		if (isDouble(probtxt)) {
			prob = Double.parseDouble(probtxt);
			if (0 <= prob && prob <= 1) {
				JOptionPane.showMessageDialog(null, "Probability is: " + prob);
			} else {
				JOptionPane.showMessageDialog(null, "Invalid input! Number must between 0 and 1");
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid input! Number must between 0 and 1");
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

	//Create random nodes
	@FXML
	private AnchorPane pane;


	@FXML
	private Button generateButton;

	@FXML
	void generate(ActionEvent event) {
		myGraph.initGraph(vCount, pane);
		myGraph.execAlgorithm(pane, prob);
	}

	//Reset all nodes
	@FXML
	private Button resetButton;

	@FXML
	void reset(ActionEvent event) {
		myGraph.resetStrategy(pane);
		JOptionPane.showMessageDialog(null, "Successfully removed!");
	}
}