//322631458 Nophar Glotman
import biuoop.DrawSurface;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 1.6.22
 */
public interface Animation {
    /**
     * create one frame.
     * @param d - the drawSurface that draw on.
     */
    void doOneFrame(DrawSurface d);
    /**
     * notify if the section need to be stopped.
     * @return if the section need to be stopped.
     */
    boolean shouldStop();
    /**
     * notify if the section need to be continued.
     */
    void shouldContinue();
}
