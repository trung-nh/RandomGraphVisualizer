package sample.Algorithm.ER;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.Algorithm.Element.Edge;
import sample.Algorithm.Element.Graph;
import sample.Algorithm.Element.Vertex;
import sample.Algorithm.RandomGraphStrategy;

public class ERGraph extends RandomGraphStrategy {

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
	public void execAlgorithm(AnchorPane pane, double prob) {

		// generate edges
		int V = getGraph().getVCount();
		setInterval(V);
		final int[] i = {0};
		final Timeline timeline = new Timeline();
		timeline.setCycleCount(V - 1);
		timeline.setDelay(Duration.millis(500));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(interval), actionEvent -> {
			for (int j = i[0] + 1; j < getGraph().getVCount(); j++) {
				double probRandom = Math.random();
				if (probRandom <= prob) {
					Edge newEdge = new Edge();
					newEdge.draw(getGraph().getVList().get(i[0]), getGraph().getVList().get(j));
					pane.getChildren().add(newEdge.getEdge());
					getGraph().addEdge(newEdge);
				}
			}
			i[0]++;
		}));
		timeline.play();
	}
	@Override
	protected void setInterval(int V) {
		if (V >= 40) {
			this.interval = 300;
		} else if (V >= 20) {
			this.interval = 450;
		} else if (V >= 8) {
			this.interval = 600;
		} else {
			this.interval = 900;
		}
	}
}