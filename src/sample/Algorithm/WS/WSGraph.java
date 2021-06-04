package sample.Algorithm.WS;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import sample.Algorithm.Element.Edge;
import sample.Algorithm.Element.Graph;
import sample.Algorithm.Element.Vertex;
import sample.Algorithm.RandomGraphStrategy;

import java.util.Random;

public class WSGraph extends RandomGraphStrategy {
	final double RADIUS = 300;
	final double CENTER_X = 358;
	final double CENTER_Y = 316;
	private int K;

	@Override
	public void initGraph(int vCount, AnchorPane pane) throws InterruptedException {
		final int[] id = {0};
		setGraph(new Graph(vCount));
		int V = getGraph().getInitVCount();

		// form vertices
		final Timeline timelineV = new Timeline();
		final int[] iV = {0};
		timelineV.setCycleCount(V);
		timelineV.getKeyFrames().add(new KeyFrame(Duration.millis(500), actionEvent -> {
			double angle = 2 * iV[0] * Math.PI / V;
			double xOffset = RADIUS * Math.cos(angle);
			double yOffset = RADIUS * Math.sin(angle);
			int x = (int) (CENTER_X + xOffset);
			int y = (int) (CENTER_Y + yOffset);
			Vertex newNode = new Vertex((float) x, (float) y);
			getGraph().addVertex(newNode);
			pane.getChildren().add(newNode.getNode());
			iV[0]++;
		}));
		timelineV.play();

		// draw edges
		final Timeline timeline = new Timeline();
		final int[] i = {0};
		final int[] j = {1};
		timeline.setCycleCount(V * K / 2);
		timeline.setDelay(Duration.millis((V + 1) * 500));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), actionEvent -> {
			int neighbor = (i[0] + j[0]) % V;
			Edge newEdge = new Edge();
			newEdge.draw(getGraph().getVList().get(i[0]), getGraph().getVList().get(neighbor));
			pane.getChildren().add(newEdge.getEdge());
			getGraph().addEdge(newEdge);
			j[0]++;
			if (j[0] == K / 2 + 1) {
				i[0]++;
				j[0] = 1;
			}
		}));
		timeline.play();
	}

	@Override
	public void execAlgorithm(AnchorPane pane, double prob) {
		System.out.println(getGraph().getEList());
		int V = getGraph().getVCount();
		final Timeline timeline = new Timeline();
		final int[] i = {0};
		timeline.setCycleCount(V);
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500), actionEvent -> {
			double probRandom = Math.random();
			// if rewiring probability is reached
			if (probRandom <= prob) {
				// first, remove the edge connecting consecutive vertices to the right of vertex id = i
				int id = i[0] * K / 2;
				System.out.println("id = "+id);
				pane.getChildren().remove(getGraph().getEList().get(id).getEdge());
				getGraph().removeEdge(id);

				// next, randomly choose a vertex to rewire to
//				int seed = V - K - 1;
//				Random rand = new Random();
//				int R = rand.nextInt(seed);
//				int rewiredV = (i[0] + K / 2) % V + 1 + R;

				// finally, rewire
//				Edge newEdge = new Edge();
//				newEdge.draw(getGraph().getVList().get(i[0]), getGraph().getVList().get(rewiredV));
//				pane.getChildren().add(newEdge.getEdge());
//				getGraph().addEdge(newEdge);
			}
			i[0]++;
		}));
		timeline.play();

	}


	public void setK(int k) {
		K = k;
	}
}










//		for (int i = 0; i < V; i++) {
//			double probRandom = Math.random();
//			// if rewiring probability is reached
//			if (probRandom <= prob) {
//				// first, remove the edge connecting consecutive vertices to the right of vertex id = i
//				int id = i * K / 2;
//				pane.getChildren().remove(getGraph().getEList().get(id).getEdge());
//				getGraph().removeEdge(id);
//
//				// next, randomly choose a vertex to rewire to
//				int seed = V - K - 1;
//				Random rand = new Random();
//				int R = rand.nextInt(seed);
//				int rewiredV = (i + K / 2) % V + 1 + R;
//
//				// finally, rewire
//				Edge newEdge = new Edge();
//				newEdge.draw(getGraph().getVList().get(i), getGraph().getVList().get(rewiredV));
//				pane.getChildren().add(newEdge.getEdge());
//				getGraph().addEdge(newEdge);
//			}