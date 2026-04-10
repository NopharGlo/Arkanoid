//322631458 Nophar Glotman
/**
 * @author      Nophar Glotman
 * @version     1
 * @since       22.5.22
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl - the listener.
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl - the listener.
     */
    void removeHitListener(HitListener hl);
}
