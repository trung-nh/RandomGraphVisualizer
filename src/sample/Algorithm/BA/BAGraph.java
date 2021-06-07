package sample.Algorithm.BA;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.Algorithm.Element.Edge;
import sample.Algorithm.Element.Graph;
import sample.Algorithm.Element.Vertex;
import sample.Algorithm.RandomGraphStrategy;

public class BAGraph extends RandomGraphStrategy {

	@Override
	public void initGraph(int vCount, AnchorPane pane) {
		setGraph(new Graph(vCount));
		// Init 2 vertices
		float x, y;
		for (int i = 0; i < 2; i++) {
			x = (float) (Math.random() * SEED_X + 1);
			y = (float) (Math.random() * SEED_Y + 1);
			Vertex newNode = new Vertex(x, y);
			// Init degree and prob of each vertex
			newNode.setDegree(1);
			getGraph().addVertex(newNode);
			pane.getChildren().add(newNode.getNode());
		}
		// Init first edge
		Edge newEdge = new Edge();
		newEdge.draw(getGraph().getVList().get(0), getGraph().getVList().get(1));
		pane.getChildren().add(newEdge.getEdge());
		getGraph().addEdge(newEdge);
		// Update graph's initial total degree
		getGraph().setTotalDegree(2);
	}

	@Override
	public void execAlgorithm(AnchorPane pane, double prob) {
		int V = getGraph().getInitVCount();
		final float[] x = new float[1];
		final float[] y = new float[1];
		// # of vertices that has been linked to new node so far
		final int[] connected = {0};
		// Degree of node already linked to new node
		final int[] excludedDeg = {0};
		// creating new nodes

		final int[] i = {0};
		final Timeline timeline = new Timeline();
		timeline.setCycleCount(V - 2);
		timeline.setDelay(Duration.millis(350));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(600), actionEvent -> {
			x[0] = (float) (Math.random() * SEED_X + 1);
			y[0] = (float) (Math.random() * SEED_Y + 1);
			Vertex newNode = new Vertex(x[0], y[0]);
			newNode.setDegree(2);
			getGraph().addVertex(newNode);
			pane.getChildren().add(newNode.getNode());
			int curV = getGraph().getVCount();
			// loop through all old nodes
			for (int j = 0; j < curV - 1; j++) {
				if (connected[0] == 2) {
					// Reset counters
					connected[0] = 0;
					excludedDeg[0] = 0;
					break;
				}
				Vertex oldNode = getGraph().getVList().get(j);
				int tmp = curV + connected[0] - j;
				if (curV + connected[0] - j == 3) {
					// if there are no more options, just link new node to remaining vertices
					for (int k = j; k < curV - 1; k++) {
						Vertex remNode = getGraph().getVList().get(k);
						Edge newEdge = new Edge();
						// increment degree of old node and total degree
						remNode.incrementDeg();
						getGraph().incrementDeg();

						newEdge.draw(newNode, remNode);
						pane.getChildren().add(newEdge.getEdge());
						getGraph().addEdge(newEdge);
						excludedDeg[0] += oldNode.getDegree();
						connected[0]++;
					}
					// Reset counters
					connected[0] = 0;
					excludedDeg[0] = 0;
					break;
				}
				float probRandom = (float) Math.random();
				float probDeg = getGraph().handleCalcProbDeg(oldNode, excludedDeg[0], connected[0]);
				if (probRandom <= probDeg) {
					// probability reached, let's link
					Edge newEdge = new Edge();
					newEdge.draw(newNode, oldNode);
					// update deg
					newNode.incrementDeg();
					oldNode.incrementDeg();
					getGraph().incrementDeg();
					// Render
					pane.getChildren().add(newEdge.getEdge());
					getGraph().addEdge(newEdge);
					connected[0]++;
				}
				excludedDeg[0] += oldNode.getDegree();
			}
		}));
		timeline.play();
	}
}