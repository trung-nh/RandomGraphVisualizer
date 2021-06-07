package sample.Algorithm.RN;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.Algorithm.Element.Edge;
import sample.Algorithm.Element.Graph;
import sample.Algorithm.Element.Vertex;
import sample.Algorithm.RandomGraphStrategy;
import java.util.Collections;

public class RNGraph extends RandomGraphStrategy {

	@Override
	public void initGraph(int vCount, AnchorPane pane) {
		setGraph(new Graph(vCount));
		int V = getGraph().getInitVCount();
		float x, y;
		// generate vertices
		for (int i = 0; i < V; i++) {
			x = (float) (Math.random() * SEED_X + 1);
			y = (float) (Math.random() * SEED_Y + 1);
			Vertex newNode = new Vertex(x, y);
			getGraph().addVertex(newNode);
			pane.getChildren().add(newNode.getNode());
		}
	}

	@Override
	public void execAlgorithm(AnchorPane pane, double edge) {
		int V = getGraph().getVCount();
		int edgeCount = (int) edge;
		for (int i = 0; i < V - 1; i++) {
			for (int j = i + 1; j < V; j++) {
				Edge newEdge = new Edge();
				newEdge.draw(getGraph().getVList().get(i), getGraph().getVList().get(j));
				getGraph().addEdge(newEdge);
			}
		}
		// Randomly distribute the number of edges all over the graph
		Collections.shuffle(getGraph().getEList());

		// Display
		final int[] i = {0};
		final Timeline timeline = new Timeline();
		timeline.setCycleCount(edgeCount);
		timeline.setDelay(Duration.millis(350));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(350), actionEvent -> {
			pane.getChildren().add(getGraph().getEList().get(i[0]).getEdge());
			i[0]++;
		}));
		timeline.play();
	}
}