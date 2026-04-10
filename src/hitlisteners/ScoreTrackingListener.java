//322631458 Nophar Glotman
/**
 * @author      Nophar Glotman
 * @version     1
 * @since       22.5.22
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * create new object for ScoreTrackingListener.
     * @param scoreCounter - the score of the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    //increase score every hit in 5 points
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }
}
