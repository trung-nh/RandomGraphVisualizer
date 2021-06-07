package sample.Algorithm.Element;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class Vertex {
	// Vertex configurations
	final int RADIUS = 4;
	final Paint COLOR = Color.rgb(255, 20, 67);

	// Attributes
	private float x;
	private float y;
	private Circle node;
	private int degree = 0;

	// Methods
	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public int getDegree() {
		return degree;
	}

	public void incrementDeg() {
		degree++;
	}

	public Circle getNode() {
		return this.node;
	}

	// Constructor
	public Vertex(float x, float y) {
		node = new Circle(x, y, 4, COLOR);
		setX(x);
		setY(y);
	}

}