//322631458 Nophar Glotman
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 8.4.22
 */
public class SpriteCollection {
    private List<Sprite> sprites;
    /**
     * create new object for SpriteCollection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }
    /**
     * give list of sprite that have.
     * @return list of sprite that have.
     */
    public List<Sprite> getSprites() {
        return sprites;
    }
    /**
     * add to the list object that impliments Sprite.
     * @param s - object that impliments Sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * remove sprite from the list.
     * @param s - object that impliments Sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }
    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copySprites = new ArrayList<Sprite>(this.sprites);
        for (Sprite spr:copySprites) {
            spr.timePassed();
        }
    }
    /**
     * call drawOn(d) on all sprites.
     * @param d - the surface that the object will draw.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite spr:sprites) {
            spr.drawOn(d);
        }
    }
}