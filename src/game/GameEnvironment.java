//322631458 Nophar Glotman
import java.util.ArrayList;
import java.util.List;
/**
 * @author      Nophar Glotman
 * @version     1
 * @since       8.4.22
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    /**
     * create new object for GameEnvironment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }
    /**
     * add the given collidable to the environment.
     * @param c  collidable object.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    /**
     * calculate if the moving object collide with any of the collidables.
     * @param trajectory  the trajectory of the moving object.
     * @return If this object will not collide with any of the collidables in this collection, return null.
     *         Else, return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double smallDistance = -1;
        Point collisionPoint;
        CollisionInfo collisionInfo = null;
        for (Collidable cld:collidables) {
            if (!cld.getCollisionRectangle().intersectionPoints(trajectory).isEmpty()) {
                collisionPoint = trajectory.closestIntersectionToStartOfLine(cld.getCollisionRectangle());
                double dstFromCollision = collisionPoint.distance(trajectory.start());
                if (smallDistance == -1 || smallDistance > dstFromCollision) {
                    smallDistance = dstFromCollision;
                    collisionInfo = new CollisionInfo(collisionPoint, cld);
                }
            }
        }
        return collisionInfo;
    }
    /**
     * gives list of collidables.
     * @return list of collidables.
     */
    public List<Collidable> getCollidables() {
        return collidables;
    }
    /**
     * remove collidable.
     * @param collidable the collidable need to remove.
     */
    public void removeCollidables(Collidable collidable) {
        this.collidables.remove(collidable);
    }
}