package sample.Algorithm.WS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
	public TextField log_r_edges;
	public TextField log_prob;
	int k, vCount;
	float prob;
	WSGraph myGraph = new WSGraph();

	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isFloat(String s) {
		try {
			Float.parseFloat(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@FXML
	private TextField probID;
	@FXML
	private Button GoBack;
	@FXML
	private Button enterButton;
	@FXML
	private TextField kID;
	@FXML
	private TextField verticesID;

	@FXML
	void getData(ActionEvent event) {
		String verticestxt = verticesID.getText();
		String ktxt = kID.getText();
		String probtxt = probID.getText();
		if (verticestxt.isEmpty()) verticestxt = "0";
		if (ktxt.isEmpty()) ktxt = "0";
		if (probtxt.isEmpty()) probtxt = "0";
		if (isInteger(verticestxt)) {
			vCount = Integer.parseInt(verticestxt);
			if (vCount >= 3) {
				JOptionPane.showMessageDialog(null, "Number of vertices is:" + vCount);
			} else {
				JOptionPane.showMessageDialog(null, "Invalid input! Input must be an Integer larger than 3");
				//return false
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid input! Input must be an Integer larger than 3");
			//return false
		}
		if (isInteger(ktxt)) {
			k = Integer.parseInt(ktxt);
			if (k % 2 == 0 && 0 <= k) {
				if ((vCount % 2 == 1 && k <= vCount - 1) || (vCount % 2 == 0 && k <= vCount - 2)) {
					JOptionPane.showMessageDialog(null, "Number of mean degree is:" + k);
				} else {
					JOptionPane.showMessageDialog(null, "Invalid mean degree input!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Invalid input! Input must be an Integer divide by 2");
				//return false
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid input! Input must be an Integer divide by 2");
			//return false
		}

		if (isFloat(probtxt)) {
			prob = Float.parseFloat(probtxt);
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


	//addCircles
	@FXML
	private Circle circle;

	@FXML

	private AnchorPane pane;

	@FXML
	private Button setUp;

	@FXML
	void setUp(ActionEvent Event) throws InterruptedException {
		myGraph.setK(k);
		myGraph.initGraph(vCount, pane);
	}

	@FXML
	private Button generate;

	@FXML
	void generate(ActionEvent Event) {
		myGraph.execAlgorithm(pane, prob);
	}

	@FXML
	private Button resetButton;

	@FXML
	void reset(ActionEvent event) {
		myGraph.resetStrategy(pane);
		JOptionPane.showMessageDialog(null, "Successfully removed!");
		log_prob.setText(null);
		log_r_edges.setText(null);
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
	public void logRes(ActionEvent actionEvent) {
		myGraph.logResult(log_r_edges, log_prob);
	}
}