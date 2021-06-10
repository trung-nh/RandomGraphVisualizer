package sample.Algorithm.WS;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
	private int rewiredECount = 0;

	@Override
	public void initGraph(int vCount, AnchorPane pane) throws InterruptedException {
		setGraph(new Graph(vCount));
		rewiredECount = 0;
		int V = getGraph().getInitVCount();
		// Set time for each interval
		setInterval(V);
		// form vertices
		final Timeline timelineV = new Timeline();
		final int[] iV = {0};
		timelineV.setCycleCount(V);
		timelineV.getKeyFrames().add(new KeyFrame(Duration.millis(interval), actionEvent -> {
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
		timeline.setDelay(Duration.millis((V + 1) * interval));
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(interval), actionEvent -> {
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
		int V = getGraph().getVCount();
		// Define rewired edge's color
		final Paint col = Color.rgb(224, 57, 230);
		final Timeline timeline = new Timeline();
		final int[] i = {0};
		timeline.setCycleCount(V);
		timeline.getKeyFrames().add(new KeyFrame(Duration.millis(interval), actionEvent -> {
			double probRandom = Math.random();
			int id = (i[0] * K) / 2;
			// if rewiring probability is reached
			if (probRandom <= prob) {
				// first, remove the edge connecting consecutive vertices to the right of vertex having index = i
				Edge target = getGraph().getEList().get(id);
				pane.getChildren().remove(target.getEdge());

				// next, randomly choose a vertex to rewire to
				int seed = V - K - 1;
				Random rand = new Random();
				int R = rand.nextInt(seed);
				int rewiredV = (((i[0] + (K / 2)) % V) + 1 + R) % V;

				// finally, rewire
				Edge newEdge = new Edge();
				newEdge.setColor(col);
				newEdge.draw(getGraph().getVList().get(i[0]), getGraph().getVList().get(rewiredV));
				pane.getChildren().add(newEdge.getEdge());
				getGraph().addEdge(newEdge);

				// update rewired edges count
				rewiredECount++;
			}
			i[0]++;
		}));
		timeline.play();
	}

	@Override
	protected void setInterval(int V) {
		if (V >= 40) {
			this.interval = 50;
		} else if (V >= 20) {
			this.interval = 100;
		} else if (V >= 8) {
			this.interval = 250;
		} else {
			this.interval = 400;
		}
	}

	public void setK(int k) {
		K = k;
	}

	public void logResult(TextField rEdges, TextField prob) {
		rEdges.setText(String.valueOf(rewiredECount));
		System.out.println(getGraph().getVCount());
		System.out.println(rewiredECount / getGraph().getVCount());
		prob.setText(String.valueOf((float) rewiredECount / (float) getGraph().getVCount()));
	}
}