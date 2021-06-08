package sample.Algorithm;

import javafx.scene.layout.AnchorPane;
import sample.Algorithm.Element.Graph;

public abstract class RandomGraphStrategy {
	private Graph graph;
	private float prob;
	public final int SEED_X = 737;
	public final int SEED_Y = 631;
	public double interval;

	// abstract methods
	public abstract void initGraph(int vCount, AnchorPane pane) throws InterruptedException;

	public abstract void execAlgorithm(AnchorPane pane, double prob);

	protected abstract void  setInterval(int V);
	// regular methods
	public void resetStrategy(AnchorPane pane) {
		// Remove vertices
		for (int i = 0; i < this.graph.getVCount(); i++) {
			pane.getChildren().remove(graph.getVList().get(i).getNode());
		}
		// Remove edges
		for (int i = 0; i < this.graph.getECount(); i++) {
			pane.getChildren().remove(this.graph.getEList().get(i).getEdge());
		}
		// Clear graph class
		this.graph.resetGraph();
	}

	public Graph getGraph() {
		return this.graph;
	}

	public float getProb() {
		return prob;
	}

	public void setProb(float prob) {
		this.prob = prob;
	}

	public void setGraph(Graph graph) {
		this.graph = graph;
	}

}