//322631458 Nophar Glotman
/**
 * @author      Nophar Glotman
 * @version     1
 * @since       22.5.22
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;
    /**
     * create new object for BallRemover.
     * @param gameLevel - the game the ballRemover listen.
     * @param remainingBalls  - num of balls in the game.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }
    //remove ball
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.gameLevel.removeSpritesFromList(hitter);
        this.remainingBalls.decrease(1);
    }
}
