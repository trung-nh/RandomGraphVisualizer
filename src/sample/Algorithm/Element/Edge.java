package sample.Algorithm.Element;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public class Edge {
	private Paint color = Color.rgb(38, 240, 240);
	private Vertex from;
	private Vertex to;
	Line edge;

	public void draw(Vertex from, Vertex to) {
		// draw a line between 2 given vertices
		this.from = from;
		this.to = to;
		edge = new Line(from.getX(), from.getY(), to.getX(), to.getY());
		edge.setStroke(color);
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