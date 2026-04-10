//322631458 Nophar Glotman
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author      Nophar Glotman
 * @version     1
 * @since       8.4.22
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle shape;
    private List<HitListener> hitListeners = new ArrayList<>();
    /**
     * create new object type block.
     * @param shape - the shape of the object.
     */
    public Block(Rectangle shape) {
        this.shape = shape;
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;
        if ((collisionPoint.getX() == shape.getUpperLeft().getX())                        //left
                && (collisionPoint.getY() >= shape.getUpperLeft().getY())
                && (collisionPoint.getY() <= (shape.getUpperLeft().getY() + shape.getHeight()))) {
            newVelocity.setDx(-currentVelocity.getDx());
        }
        if ((collisionPoint.getY() == shape.getUpperLeft().getY())                        //up
                && (collisionPoint.getX() >= shape.getUpperLeft().getX())
                && (collisionPoint.getX() <= (shape.getUpperLeft().getX() + shape.getWidth()))) {
            newVelocity.setDy(-currentVelocity.getDy());
        }
        if ((collisionPoint.getY() == (shape.getUpperLeft().getY() + shape.getHeight())   //down
                && (collisionPoint.getX() >= shape.getUpperLeft().getX())
                && (collisionPoint.getX() <= (shape.getUpperLeft().getX() + shape.getWidth())))) {
            newVelocity.setDy(-currentVelocity.getDy());
        }
        if ((collisionPoint.getX() == (shape.getUpperLeft().getX() + shape.getWidth()))   //right
                && (collisionPoint.getY() >= shape.getUpperLeft().getY())
                && (collisionPoint.getY() <= (shape.getUpperLeft().getY() + shape.getHeight()))) {
            newVelocity.setDx(-currentVelocity.getDx());
        }
        this.notifyHit(hitter);
        return newVelocity;
    }
    // notify the sprite that time has passed
    @Override
    public void timePassed() {
        return;
    }
    // draw the sprite to the screen
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.shape.getColor());
        d.fillRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(),
                (int) this.shape.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.shape.getUpperLeft().getX(), (int) this.shape.getUpperLeft().getY(),
                (int) this.shape.getWidth(),
                (int) this.shape.getHeight());
    }
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * remove all hit listener from the block.
     */
    public void removeAllHitListener() {
        this.hitListeners.clear();
    }
}
