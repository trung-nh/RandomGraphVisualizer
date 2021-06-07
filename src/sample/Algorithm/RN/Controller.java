package sample.Algorithm.RN;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import sample.Algorithm.BA.BAGraph;

import javax.swing.*;

public class Controller {
	int node, edge;
	Double edgeDouble;

	public boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
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
	private TextField nodeID;

	@FXML
	private TextField edgeID;

	@FXML
	private Button enterButton;

	@FXML
	void getData(ActionEvent event) {
		String nodetxt = nodeID.getText();
		String edgetxt = edgeID.getText();
		if (nodetxt.isEmpty()) nodetxt = "0";
		if (edgetxt.isEmpty()) edgetxt = "0";
		if (isInteger(nodetxt)) {
			node = Integer.parseInt(nodetxt);
			JOptionPane.showMessageDialog(null, "Node input is: " + node);
		} else {
			JOptionPane.showMessageDialog(null, "Invalid Node Input! Input must be an integer");
			//return false;
		}
		if (isInteger(edgetxt)) {
			edge = Integer.parseInt(edgetxt);
			if (edge > node * (node - 1) / 2) {
				JOptionPane.showMessageDialog(null, "Invalid Edge Input! Edge input must be smaller than total edge");
				//return false;
			} else {
				JOptionPane.showMessageDialog(null, "Edge input is: " + edge);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Invalid Edge Input! Input must be an integer");
			//return false;
		}
	}

	@FXML
	private AnchorPane pane;
	RNGraph myGraph = new RNGraph();

	@FXML
	void generate(ActionEvent event) {
		edgeDouble = Double.valueOf(edge);
		myGraph.initGraph(node, pane);
		myGraph.execAlgorithm(pane, edgeDouble);
	}

	@FXML
	void reset(ActionEvent event) {
		myGraph.resetStrategy(pane);
		JOptionPane.showMessageDialog(null, "Successfully removed!");
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
}