//322631458 Nophar Glotman
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 1.6.22
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    /**
     * create object KeyPressStoppableAnimation.
     * @param sensor - the keyboard.
     * @param key - the key to exit the section.
     * @param animation - the animation that needed to be stopped.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.sensor.isPressed(key) && isAlreadyPressed) {
            this.animation.shouldContinue();
            isAlreadyPressed = false;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.animation.shouldStop();
    }
    @Override
    public void shouldContinue() {
        this.animation.shouldContinue();
    }
    // ...
    // think about the implementations of doOneFrame and shouldStop.
}
