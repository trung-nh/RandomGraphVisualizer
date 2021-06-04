package sample.Algorithm.ER;

import javafx.scene.layout.AnchorPane;
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
		for (int i = 0; i < getGraph().getVCount() - 1; i++) {
			for (int j = i + 1; j < getGraph().getVCount(); j++) {
				double probRandom = Math.random();
				if (probRandom <= prob) {
					Edge newEdge = new Edge();
					newEdge.draw(getGraph().getVList().get(i), getGraph().getVList().get(j));
					pane.getChildren().add(newEdge.getEdge());
					getGraph().addEdge(newEdge);
				}
			}
		}
	}

}