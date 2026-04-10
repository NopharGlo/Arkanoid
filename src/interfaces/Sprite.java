//322631458 Nophar Glotman
import biuoop.DrawSurface;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 8.4.22
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d - the surface the sprite will draw.
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
    /**
     * charge of adding the ball and the block to the game.
     * @param g - the game the object join.
     */
    void addToGame(GameLevel g);
}
