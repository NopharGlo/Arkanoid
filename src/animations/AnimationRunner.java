//322631458 Nophar Glotman
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 1.6.22
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * Creates an AnimationRunner instance.
     *
     * @param gui The GUI for displaying the animation.
     * @param framesPerSecond The number of frames per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = new Sleeper();
    }
    


    /**
     * Returns the GUI instance.
     *
     * @return The GUI.
     */
    public GUI getGui() {
        return gui;
    }


    /**
     * Runs the given animation.
     *
     * @param animation The animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}