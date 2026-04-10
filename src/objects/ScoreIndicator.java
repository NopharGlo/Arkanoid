//322631458 Nophar Glotman
import biuoop.DrawSurface;
/**
 * @author      Nophar Glotman
 * @version     1
 * @since       22.5.22
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private int font;
    private Frame frame;
    private String levelName;
    private Counter lives;
    /**
     * create new object for ScoreIndicator.
     * @param score - the score of the game.
     * @param frame  - the size of the frame it needed to be written.
     * @param font  - of the writing.
     * @param levelName - the level name the game is.
     * @param lives - the lives of the game.
     */
    public ScoreIndicator(Counter score, int font, Frame frame, String levelName, Counter lives) {
        this.score = score;
        this.font = font;
        this.frame = frame;
        this.levelName = levelName;
        this.lives = lives;
    }
    // notify the sprite that time has passed
    @Override
    public void timePassed() {
        return;
    }
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(frame.getWidth() / 2, font, "score: " + score.getValue(), font);
        d.drawText(frame.getWidth() / 5, font, "level name: " + levelName, font);
        d.drawText(3 * frame.getWidth() / 4, font, "lives: " + lives.getValue(), font);
    }
}
