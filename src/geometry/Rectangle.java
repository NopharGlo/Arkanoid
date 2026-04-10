//322631458 Nophar Glotman
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import biuoop.DrawSurface;
/**
 * @author      Nophar Glotman
 * @version     1
 * @since       8.4.22
 */
public class Rectangle implements Sprite {
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;
    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft -the upper left point of the rectangle.
     * @param width -the width of the rectangle.
     * @param height - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = getRandomColor();
    }
    /**
     * Create a new rectangle with location and width/height.
     * @param upperLeft -the upper left point of the rectangle.
     * @param width -the width of the rectangle.
     * @param height - the height of the rectangle.
     * @param color - the color of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    /**
     * Create a new rectangle with location.
     * @param frame -the frame of the rectangle.
     * @param color - the color of the rectangle.
     */
    public Rectangle(Frame frame, Color color) {
        this.upperLeft = frame.getPoint();
        this.width = frame.getWidth();
        this.height = frame.getHeight();
        this.color = color;
    }
    /**
     * give random color.
     * @return random color.
     */
    private static Color getRandomColor() {
        Random randNum = new Random();
        int red = randNum.nextInt(256);
        int green = randNum.nextInt(256);
        int blue = randNum.nextInt(256);
        return new Color(red, green, blue);
    }
    /**
     * check intersection points with the specified line to the rectangle.
     * @param line -the upper left point of the rectangle.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersectPoints = new ArrayList<Point>();
        Line rectangleLine = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY());  //up
        if (rectangleLine.isIntersecting(line)) {
            intersectPoints.add(rectangleLine.intersectionWith(line));
        }
        rectangleLine = new Line(this.upperLeft.getX(), this.upperLeft.getY() + this.height,
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);  //down
        if (rectangleLine.isIntersecting(line)) {
            intersectPoints.add(rectangleLine.intersectionWith(line));
        }
        rectangleLine = new Line(this.upperLeft.getX(), this.upperLeft.getY(), this.upperLeft.getX(),
                this.upperLeft.getY() + this.height);
        if (rectangleLine.isIntersecting(line)) {
            intersectPoints.add(rectangleLine.intersectionWith(line));
        }
        rectangleLine = new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        if (rectangleLine.isIntersecting(line)) {
            intersectPoints.add(rectangleLine.intersectionWith(line));
        }
        return intersectPoints;
    }
    /**
     * give the width of the rectangle.
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * give the height of the rectangle.
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * give the color of the rectangle.
     * @return the color of the rectangle.
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * give the upper-left point of the rectangle.
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * change the upper-left point of the rectangle.
     * @param x the x upper-left point of the rectangle.
     * @param y the y upper-left point of the rectangle.
     */
    public void setUpperLeft(double x, double y) {
        this.upperLeft = new Point(x, y);
    }
    /**
     * draw rectangle in surface.
     * @param surface - the surface of rectangle.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), (int) this.width,
                (int) this.height);
        surface.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(), (int) this.width,
                (int) this.height);
    }
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    @Override
    public void timePassed() {
        return;
    }
}