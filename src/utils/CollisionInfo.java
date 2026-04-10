//322631458 Nophar Glotman
/**
 * @author Nophar Glotman
 * @version 1
 * @since 8.4.22
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     * create object CollisionInfo.
     * @param collisionPnt -the collision point.
     * @param collisionObj -the collidable.
     */
    public CollisionInfo(Point collisionPnt, Collidable collisionObj) {
        this.collisionPoint = collisionPnt;
        this.collisionObject = collisionObj;
    }
    /**
     * the point at which the collision occurs.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * the collidable object involved in the collision.
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}