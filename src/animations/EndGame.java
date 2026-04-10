//322631458 Nophar Glotman
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 1.6.22
 */
public class EndGame implements Animation {
    static final int FONTRESULT = 50;
    static final int FONTREST = 32;
    private boolean stop;
    private boolean ifWin;
    private Counter score;
    /**
     * create object EndGame.
     * @param score - the final score.
     * @param ifWin - if the player win.
     */
    public EndGame(Counter score, boolean ifWin) {
        this.stop = false;
        this.ifWin = ifWin;
        this.score = score;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (ifWin) {
            for (int i = 0; i < 6; i = i + 2) {
                d.setColor(Color.black);
                if (i == 4) {
                    d.setColor(Color.red);
                }
                d.drawText((d.getWidth() / 3) + i, (d.getHeight() / 2) + i - FONTRESULT, "You Win!", FONTRESULT);
                d.drawText((d.getWidth() / 3) + i, (d.getHeight() / 2) + i + FONTREST,
                        "Your score is " + this.score.getValue(), FONTREST);
                d.drawText((d.getWidth() / 3) + i, (d.getHeight() / 2) + i + FONTREST + FONTREST,
                        "press space to exit", FONTREST);
            }
        } else {
            for (int i = 0; i < 6; i = i + 2) {
                d.setColor(Color.black);
                if (i == 4) {
                    d.setColor(Color.red);
                }
                d.drawText((d.getWidth() / 3) + i, (d.getHeight() / 2) + i - FONTRESULT, "You lose!", FONTRESULT);
                d.drawText((d.getWidth() / 3) + i, (d.getHeight() / 2) + i + FONTREST,
                        "Your score is " + this.score.getValue(), FONTREST);
                d.drawText((d.getWidth() / 3) + i, (d.getHeight() / 2) + i + FONTREST + FONTREST,
                        "press space to exit", FONTREST);
            }
        }
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