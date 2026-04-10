//322631458 Nophar Glotman
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 1.6.22
 */
public class Circle implements Sprite {
    private int radius;
    private Point center;
    private Color color;
    private boolean isFill;
    /**
     * create object Circle.
     * @param radius - the radius of the circle.
     * @param center - the location of the circle.
     * @param color -the color of the circle.
     * @param isFill - if the circle need to be filled.
     */
    public Circle(int radius, Point center, Color color, boolean isFill) {
        this.radius = radius;
        this.center = center;
        this.color = color;
        this.isFill = isFill;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        if (!isFill) {
            d.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        } else {
            d.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        }
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
