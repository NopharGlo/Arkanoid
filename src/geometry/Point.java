//322631458 Nophar Glotman
/**
 * @author      Nophar Glotman
 * @version     1
 * @since       8.4.22
 */
public class Point {
    private double x;
    private double y;
    /**
     * create object Point.
     * @param x -the x location.
     * @param y -the y location;
     */
    public Point(double x, double y) {
        setX(x);
        setY(y);
    }
    /**
     * create object Point with random value with default frame.
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }
    /**
     * calculate the distance between two point.
     * @param other -the point that from her calculate the distance.
     * @return the distance between two points.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }
    /**
     * Comparison if equal between two points.
     * @param other -The point with which one compares to the second point.
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (this.x != other.x) {
            return false;
        } else if (this.y == other.y) {
            return true;
        }
        return false;
    }
    /**
     * give the x value of the point.
     * @return the x value of the point.
     */
    public double getX() {
        return this.x;
    }
    /**
     * give the y value of the point.
     * @return the y value of the point.
     */
    public double getY() {
        return this.y;
    }
    /**
     * set the value x.
     * @param x - the new value of the x point.
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * set the value y.
     * @param y - the new value of the x point.
     */
    public void setY(double y) {
        this.y = y;
    }
}
