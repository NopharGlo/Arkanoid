//322631458 Nophar Glotman
import biuoop.DrawSurface;
import biuoop.Sleeper;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 1.6.22
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private boolean stop;
    private SpriteCollection gameScreen;
    /**
     * create object CountdownAnimation.
     * @param numOfSeconds - time the screen need to be preformed.
     * @param countFrom - start from this number the counting down.
     * @param gameScreen -the screen of the game.
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds * 1000 / countFrom;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        if (countFrom <= 0) {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "GO", 100);
        } else {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf(countFrom), 100);
        }
        Sleeper sleeper = new Sleeper();
        sleeper.sleepFor((long) numOfSeconds);
        if (countFrom == -1) {
            this.shouldContinue();
        }
        this.countFrom--;
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