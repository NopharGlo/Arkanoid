//322631458 Nophar Glotman
/**
 * @author      Nophar Glotman
 * @version     1
 * @since       22.5.22
 */
public interface HitListener {
    /**
     * remove the ball or the block according to the listener.
     * @param beingHit - the block he hit.
     * @param hitter  - the ball that hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
