//322631458 Nophar Glotman
/**
 * @author      Nophar Glotman
 * @version     1
 * @since       4.8.22
 */
public interface Collidable {
    /**
     * give the "collision shape" of the object.
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint - the point collision.
     * @param currentVelocity - the current velocity of the object that we collided with.
     * @param hitter - the ball that hit.
     * @return the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}