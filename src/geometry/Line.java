//322631458 Nophar Glotman
import biuoop.DrawSurface;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.util.List;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 8.4.22
 */
public class Line implements Sprite {
    private Point startPoint;
    private Point endPoint;
    private Color color;
    /**
     * create object Line from two points.
     * @param start -the start point of the line.
     * @param end   -the end point of the line.
     */
    public Line(Point start, Point end) {
        if (start.equals(end)) {
            JOptionPane.showMessageDialog(null,
                    " this is not line. the start Point and the end point of the line equal");
            System.exit(0);
        }
        this.endPoint = end;
        this.startPoint = start;
    }
    /**
     * create object Line from two points.
     * @param start -the start point of the line.
     * @param end   -the end point of the line.
     * @param colorLine - color line.
     */
    public Line(Point start, Point end, Color colorLine) {
        if (start.equals(end)) {
            JOptionPane.showMessageDialog(null,
                    " this is not line. the start Point and the end point of the line equal");
            System.exit(0);
        }
        this.endPoint = end;
        this.startPoint = start;
        this.color = colorLine;
    }
    /**
     * create object Line from value of points.
     * @param x1 - the x location of the first point.
     * @param y1 - the y location of the first point.
     * @param x2 - the x location of the second point.
     * @param y2 - the y location of the second point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.startPoint = new Point(x1, y1);
        this.endPoint = new Point(x2, y2);
        if (startPoint.equals(endPoint)) {
            JOptionPane.showMessageDialog(null,
                    " this is not line. the start Point and the end point of the line equal");
            System.exit(0);
        }
    }
    /**
     * the length of the line.
     * @return the length of the line.
     */
    public double length() {
        return this.startPoint.distance(endPoint);
    }
    /**
     * the middle point of the line.
     * @return the middle point of the line.
     */
    public Point middle() {
        double middleX = (this.startPoint.getX() + this.endPoint.getX()) / 2;
        double middleY = (this.startPoint.getY() + this.endPoint.getY()) / 2;
        return new Point(middleX, middleY);
    }
    /**
     * the middle point of the line.
     * @return the start point of the line.
     */
    public Point start() {
        return this.startPoint;
    }
    /**
     * the end point of the line.
     * @return the end point of the line.
     */
    public Point end() {
        return this.endPoint;
    }
    /**
     * check if the lines intersect.
     * @param other - the line which check if his intersect with the other line.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }
    /**
     * calculate the lines intersect.
     * @param other - the line which check if his intersect with the other line.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (this.equals(other)) {
            return null;
        }
        double xIntersection, yIntersection = 0, n1 = 0, n2 = 0;
        double inclineThisLine1, inclineOtherLine2;
        inclineThisLine1 = this.findIncline();
        inclineOtherLine2 = other.findIncline();
        if (inclineThisLine1 == inclineOtherLine2) {
            return this.ifConsolidate(other);
        }
        if (this.startPoint.getX() == this.endPoint.getX()) {
            xIntersection = this.startPoint.getX();
            n2 = other.endPoint.getY() - inclineOtherLine2 * other.endPoint.getX();
            yIntersection = xIntersection * inclineOtherLine2 + n2;
        } else if (other.startPoint.getX() == other.endPoint.getX()) {
            xIntersection = other.startPoint.getX();
            n1 = this.endPoint.getY() - inclineThisLine1 * this.endPoint.getX();
            yIntersection = xIntersection * inclineThisLine1 + n1;
        } else {
            if (this.checkEnds(other) != null) {
                return this.checkEnds(other);
            }
            n1 = this.endPoint.getY() - inclineThisLine1 * this.endPoint.getX();     //y=m1x+n1
            n2 = other.endPoint.getY() - inclineOtherLine2 * other.endPoint.getX();  //y=m2x+n2
            xIntersection = (n1 - n2) / (inclineOtherLine2 - inclineThisLine1);
            yIntersection = xIntersection * inclineThisLine1 + n1;
        }
        if (inRange(this.endPoint.getX(), this.startPoint.getX(), xIntersection)
                && inRange(other.endPoint.getX(), other.startPoint.getX(), xIntersection)
                && inRange(this.endPoint.getY(), this.startPoint.getY(), yIntersection)
                && inRange(other.endPoint.getY(), other.startPoint.getY(), yIntersection)) {
            return new Point(xIntersection, yIntersection);
        }
        return null;
    }
    /**
     * check if intersect in the edges of the straight line.
     * @param other - the line which check if his intersect with the other line.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    private Point checkEnds(Line other) {
        if (this.startPoint.getX() == this.endPoint.getX()) {
            if (inRange(this.startPoint.getY(), this.endPoint.getY(), other.endPoint.getY())
                    && this.startPoint.getX() == other.endPoint.getX()) {
                return other.endPoint;
            }
            if (inRange(this.startPoint.getY(), this.endPoint.getY(), other.startPoint.getY())
                    && this.startPoint.getX() == other.startPoint.getX()) {
                return other.startPoint;
            }
        }
        if (other.startPoint.getX() == other.endPoint.getX()) {
            if (inRange(other.startPoint.getY(), other.endPoint.getY(), this.endPoint.getY())
                    && other.startPoint.getX() == this.endPoint.getX()) {
                return this.endPoint;
            }
            if (inRange(other.startPoint.getY(), other.endPoint.getY(), this.startPoint.getY())
                    && other.startPoint.getX() == this.startPoint.getX()) {
                return this.startPoint;
            }
        }
        if (other.startPoint.getY() == other.endPoint.getY()) {
            if (inRange(other.startPoint.getX(), other.endPoint.getX(), this.endPoint.getX())
                    && other.startPoint.getY() == this.endPoint.getY()) {
                return this.endPoint;
            }
            if (inRange(other.startPoint.getX(), other.endPoint.getX(), this.startPoint.getX())
                    && other.startPoint.getY() == this.startPoint.getY()) {
                return this.startPoint;
            }
        }
        if (this.startPoint.getY() == this.endPoint.getY()) {
            if (inRange(this.startPoint.getX(), this.endPoint.getX(), other.endPoint.getX())
                    && this.startPoint.getY() == other.endPoint.getY()) {
                return other.endPoint;
            }
            if (inRange(this.startPoint.getX(), this.endPoint.getX(), other.startPoint.getX())
                    && this.startPoint.getY() == other.startPoint.getY()) {
                return other.startPoint;
            }
        }
        return null;
    }
    /**
     * calculate the lines parallel one point intersect.
     * @param other - the line which check if his intersect with the other line.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    private Point ifConsolidate(Line other) {
        if ((this.startPoint.equals(other.startPoint))) {
            if (inRange(other.endPoint.getX(), other.startPoint.getX(), this.endPoint.getX())
                    || inRange(this.endPoint.getX(), this.startPoint.getX(), other.endPoint.getX())) {
                return null;
            }
            return this.startPoint;
        } else if ((this.startPoint.equals(other.endPoint))) {
            if (inRange(other.endPoint.getX(), other.startPoint.getX(), this.endPoint.getX())
                    || inRange(this.endPoint.getX(), this.startPoint.getX(), other.startPoint.getX())) {
                return null;
            }
            return this.startPoint;
        } else if ((this.endPoint.equals(other.endPoint))) {
            if (inRange(other.endPoint.getX(), other.startPoint.getX(), this.startPoint.getX())
                    || inRange(this.endPoint.getX(), this.startPoint.getX(), other.startPoint.getX())) {
                return null;
            }
            return this.endPoint;
        } else if ((this.endPoint.equals(other.startPoint))) {
            if (inRange(other.endPoint.getX(), other.startPoint.getX(), this.startPoint.getX())
                    || inRange(this.endPoint.getX(), this.startPoint.getX(), other.endPoint.getX())) {
                return null;
            }
            return this.endPoint;
        }
        return null;
    }
    /**
     * check if the number in the range.
     * @param num       - the number we want to check.
     * @param numRange1 - number from one of the edge of the range.
     * @param numRange2 - number from another edge of the range.
     * @return true if the number in the range , otherwise false.
     */
    private static boolean inRange(double numRange1, double numRange2, double num) {
        double bottom = Math.min(numRange1, numRange2);
        double top = Math.max(numRange1, numRange2);
        if ((num <= top) && (num >= bottom)) {
            return true;
        }
        return false;
    }
    /**
     * find the incline of the line.
     * @return the incline of the line.
     */
    private double findIncline() {
        return (this.endPoint.getY() - this.startPoint.getY()) / (this.endPoint.getX() - this.startPoint.getX());
    }

    /**
     * check if the lines are equal.
     * @param other the line that compare if equal to the line.
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (this.startPoint.equals(other.startPoint) && this.endPoint.equals(other.endPoint)) {
            return true;
        }
        return false;
    }
    /**
     * draw line in the surface.
     * @param d - the surface that the line will draw.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) this.start().getX(), (int) this.start().getY(), (int) this.end().getX(),
                (int) this.end().getY());
    }
    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    /**
     * check the closest intersection point to the start of the line.
     * @param rect - the rectangle which the line intersection with.
     * @return If this line does not intersect with the rectangle, return null. Otherwise, return the closest
     *         intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        Point closestPoint = null;
        double minDistance = -1;
        for (Point point:intersectionPoints) {
            if ((this.startPoint.distance(point) < minDistance) || (minDistance == -1)) {
                closestPoint = point;
                minDistance = this.startPoint.distance(point);
            }
        }
        return closestPoint;
    }
    @Override
    public void timePassed() {
        return;
    }
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
