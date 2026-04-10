//322631458 Nophar Glotman
/**
 * @author Nophar Glotman
 * @version 1
 * @since 8.4.22
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx;
    private double dy;
    /**
     * create object Velocity.
     * @param dx -the alter of x.
     * @param dy -the alter of y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * give the dy velocity.
     * @return the dy velocity.
     */
    public double getDy() {
        return dy;
    }
    /**
     * give the dx velocity.
     * @return the dx velocity.
     */
    public double getDx() {
        return dx;
    }
    /**
     * change the dx velocity.
     * @param dx  - dx velocity.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * change the dy velocity.
     * @param dy  - dy velocity.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
    /**
     * give a new point after the change of the position.
     * @param p -the point.
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
    /**
     * get speed and angle and convert it to velocity dx,dy.
     * @param angle -the direction of the ball.
     * @param speed - the speed of the ball.
     * @return new object type velocity according the speed and angle.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radianAngle = Math.toRadians(angle);
        double dx = speed * Math.sin(radianAngle);
        double dy = -speed * Math.cos(radianAngle);
        return new Velocity(dx, dy);
    }
    /**
     * calculate the speed according dx, dy.
     * @return speed.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.getDx(), 2) + Math.pow(this.getDy(), 2));
    }
}
