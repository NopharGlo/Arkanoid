//322631458 Nophar Glotman
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 1.6.22
 */
public class PauseScreen implements Animation {
    static final int FONTPAUSE = 50;
    static final int FONTREST = 32;
    private SpriteCollection background;
    private Color colorFont;
    private boolean stop;
    /**
     * create object Circle.
     * @param design - the background of the page.
     * @param colorFont - the color of the font.
     */
    public PauseScreen(SpriteCollection design, Color colorFont) {
        this.stop = false;
        this.background = design;
        this.colorFont = colorFont;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.background.drawAllOn(d);
        d.setColor(colorFont);
        d.drawText(d.getWidth() / 3, d.getHeight() / 2, "paused", FONTPAUSE);
        d.drawText(d.getWidth() / 4, (d.getHeight() / 2) + FONTREST + FONTPAUSE, "press space to continue",
                FONTREST);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
    @Override
    public void shouldContinue() {
        this.stop = true;
    }
}