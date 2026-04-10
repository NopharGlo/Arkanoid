//322631458 Nophar Glotman
import biuoop.KeyboardSensor;
import java.util.List;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 1.6.22
 */
public class GameFlow {
    static final int LIVESINTHEGAME = 2;
    static final String KEYFINISH = KeyboardSensor.SPACE_KEY;

    private final Counter score = new Counter();
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final Counter lives = new Counter();

    /**
     * Creates a GameFlow object.
     *
     * @param ar The animation runner for running the game.
     * @param ks The keyboard sensor for player input.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.lives.increase(LIVESINTHEGAME);
    }

    /**
     * Runs the game levels in the specified order.
     * Displays win/lose screens based on the player's performance.
     *
     * @param levels The list of levels to play in order.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                    this.score, this.lives);
            level.initialize();
            level.run();
            while ((level.getNumCollidable() != 0) && (lives.getValue() != 0)) {
                lives.decrease(1);
                level.run();
            }
            if ((lives.getValue() == 0) && (level.getNumCollidable() != 0)) {
                EndGame status = new EndGame(this.score, false);
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KEYFINISH, status));
                this.animationRunner.getGui().close();
                return;
            }
        }
        EndGame status = new EndGame(this.score, true);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KEYFINISH, status));
        this.animationRunner.getGui().close();
        return;
    }
}

