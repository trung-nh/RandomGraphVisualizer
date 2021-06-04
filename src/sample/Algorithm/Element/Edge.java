package sample.Algorithm.Element;

import javafx.scene.shape.Line;

public class Edge {
	private Vertex from;
	private Vertex to;
	Line edge;

	public void draw(Vertex from, Vertex to) {
		// draw a line between 2 given vertices
		this.from = from;
		this.to = to;
		edge = new Line(from.getX(), from.getY(), to.getX(), to.getY());
	}

	public Line getEdge() {
		return edge;
	}

	public Vertex getFrom() {
		return from;
	}

	public Vertex getTo() {
		return to;
	}
}