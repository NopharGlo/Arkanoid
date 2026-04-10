//322631458 Nophar Glotman
/**
 * @author      Nophar Glotman
 * @version     1
 * @since       8.4.22
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;
    /**
     * create new object for BlockRemover.
     * @param gameLevel - the game the ballRemover listen.
     * @param removedBlocks  - the block that remain.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }
    //remove block
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeAllHitListener();
        this.gameLevel.removeCollidableFromList(beingHit);
        this.gameLevel.removeSpritesFromList(beingHit);
        this.remainingBlocks.decrease(1);
    }
}
