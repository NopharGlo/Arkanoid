//322631458 Nophar Glotman
import biuoop.DrawSurface;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 8.4.22
 */
public class Frame {
    private int width;
    private int height;
    private double startX;
    private double startY;
    /**
     * create object Frame.
     * @param widthF -the width of the frame.
     * @param heightF -the height of the frame.
     * @param startX - the start point x of the frame.
     * @param startY - the start point x of the frame.
     */
    public Frame(int widthF, int heightF, double startX, double startY) {
        this.startX = startX;
        this.startY = startY;
        this.width = widthF;
        this.height = heightF;
    }
    /**
     * create object Frame.
     * @param widthF -the width of the frame.
     * @param heightF -the height of the frame.
     * @param startPointF - the start point of the frame.
     */
    public Frame(int widthF, int heightF, Point startPointF) {
        this.startX = startPointF.getX();
        this.startY = startPointF.getX();
        this.width = widthF;
        this.height = heightF;
    }
    /**
     * give the height of the frame.
     * @return the height of the frame.
     */
    public int getHeight() {
        return this.height;
    }
    /**
     * give the width of the frame.
     * @return the width of the frame.
     */
    public int getWidth() {
        return this.width;
    }
    /**
     * give the point start of the frame.
     * @return the point start of the frame.
     */
    public Point getPoint() {
        return new Point(this.startX, startY);
    }
    /**
     * give the x start point of the frame.
     * @return the x start point of the frame.
     */
    public double getStartX() {
        return startX;
    }
    /**
     * give the y start point of the frame.
     * @return the y start point of the frame.
     */
    public double getStartY() {
        return startY;
    }
    /**
     * set the height of the frame.
     * @param height - the height of the frame.
     */
    public void setHeight(int height) {
        this.height = height;
    }
    /**
     * set the width of the frame.
     * @param width - the width of the frame.
     */
    public void setWidth(int width) {
        this.width = width;
    }
    /**
     * set the x startPoint of the frame.
     * @param startX - the startPoint x of the frame.
     */
    public void setStartX(double startX) {
        this.startX = startX;
    }
    /**
     * set the y startPoint of the frame.
     * @param startY - the startPoint y of the frame.
     */
    public void setStartY(double startY) {
        this.startY = startY;
    }
    /**
     * draw frame in surface.
     * @param surface - the surface of frame.
     * @param color - the color of the frame.
     */
    public void drawFrame(DrawSurface surface, java.awt.Color color) {
        surface.setColor(color);
        surface.fillRectangle((int) this.startX, (int) this.startY, this.width, this.height);
    }

}
