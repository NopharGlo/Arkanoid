//322631458 Nophar Glotman
import java.awt.Color;
import java.util.List;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 1.6.22
 */
public interface LevelInformation {
    /**
     * give the number of the balls.
     * @return the number of the ball.
     */
    int numberOfBalls();
    /**
     * create The initial velocity of each ball.
     * @return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();
    /**
     * give the speed of the paddle.
     * @return The speed of the paddle.
     */
    int paddleSpeed();
    /**
     * give the width of the paddle.
     * @return The width of the paddle.
     */
    int paddleWidth();
    /**
     * give the color of the paddle.
     * @return The color of the paddle.
     */
    Color paddleColor();
    /**
     * give the name of the game level.
     * @return the name of the game level.
     */
    String levelName();
    /**
     * give the Background of the game level.
     * @return the Background of the game level.
     */
    Sprite getBackground();
    /**
     * give the blocks of the game level.
     * @return the blocks of the game level.
     */
    List<Block> blocks();
    /**
     * give the number Of the blocks need to remove of the game level.
     * @return the number Of the blocks need to remove of the game level.
     */
    int numberOfBlocksToRemove();
    /**
     * give the design of background of the game level.
     * @return the design of background of the game level.
     */
    SpriteCollection getDesignBackground();
    /**
     * give the color balls of the game level.
     * @return the color balls of the game level.
     */
    Color getColorBalls();
}