//322631458 Nophar Glotman
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 1.6.22
 */
public class Level4 implements LevelInformation {
    static final Frame FRAME = new Frame(800, 600, 0, 0);
    static final java.awt.Color BACKGROUND = Color.WHITE;
    static final int NUMOFBALLS = 2;
    static final int PADDLESPEED = 10;
    static final int PADDLEWITDH = 100;
    static final Color COLORPADDLE = new Color(255, 102, 0);
    static final Color COLORNOSE = new Color(255, 102, 0);
    static final Color HATCOLOR = new Color(102, 51, 0);
    static final String LEVELNAME = "snow man";
    static final int SPEEDBALL = 7;
    static final Color SNOWCOLOR = new Color(240, 240, 255);
    static final Color BLOCKCOLOR = new Color(204, 204, 204);
    static final int STARTANGEL = 300;
    static final int RANGEANGEL = 120;
    //block
    static final int DISTANCEFROMTHETOP = 100;
    static final int ROWBLOCK = 12;
    static final int COLBLOCK = 6;
    static final int BLOCKWHIDTH = 60;
    static final int BLOCKHEIDHT = 30;
    private int blockRemover;
    /**
     * create object Level3.
     */
    public Level4() {
        this.blockRemover = 0;
    }
    @Override
    public int numberOfBalls() {
        return NUMOFBALLS;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        for (int i = 0; i < NUMOFBALLS; i++) {
            int angel =  STARTANGEL + (i * (RANGEANGEL / (NUMOFBALLS - 1)));
            Velocity velocity = Velocity.fromAngleAndSpeed(angel, SPEEDBALL);
            velocityList.add(velocity);
        }
        return velocityList;
    }
    @Override
    public int paddleSpeed() {
        return PADDLESPEED;
    }
    @Override
    public int paddleWidth() {
        return PADDLEWITDH;
    }
    @Override
    public Color paddleColor() {
        return COLORPADDLE;
    }
    @Override
    public String levelName() {
        return LEVELNAME;
    }
    @Override
    public Sprite getBackground() {
        Sprite rectangleBackground = new Rectangle(FRAME, BACKGROUND);
        return rectangleBackground;
    }
    @Override
    public SpriteCollection getDesignBackground() {
        SpriteCollection design = new SpriteCollection();
        for (int i = 0; i < 35; i++) {
            Color background = new Color(51, 255 - 5 * i, 255);
            Point startPoint = new Point(0, i * (FRAME.getHeight() / 50));
            Frame frame = new Frame(FRAME.getWidth(), FRAME.getHeight() / 50, startPoint.getX(), startPoint.getY());
            Sprite rectangleBackground = new Rectangle(frame, background);
            design.addSprite(rectangleBackground);
        }
        for (int i = 35; i < 50; i++) {
            Point startPoint = new Point(0, i * (FRAME.getHeight() / 50));
            Frame frame = new Frame(FRAME.getWidth(), FRAME.getHeight() / 50, startPoint.getX(), startPoint.getY());
            Sprite rectangleBackground = new Rectangle(frame, SNOWCOLOR);
            design.addSprite(rectangleBackground);
        }
        for (int i = 0; i < 400; i++) {
            int y = 200 + i;
            int width = 2 * i;
            Point startPoint = new Point(0, y);
            Rectangle rectangle = new Rectangle(startPoint, width, 1, SNOWCOLOR);
            design.addSprite(rectangle);
        }
        this.createSnowman(design, 500, 500, 150);
        this.createSnowman(design, 50, 220, 40);
        this.createPeople(design, 200, 370, 20);
        this.createPeople(design, 150, 350, 20);
        this.createPeople(design, 100, 300, 20);
        this.createPeople(design, 50, 320, 20);
        this.createPeople(design, 150, 400, 20);
        this.createPeople(design, 100, 370, 20);
        return design;
    }
    /**
     * create snow man and  enter it to the SpriteCollection.
     * @param design SpriteCollection.
     * @param locX  - the location x.
     * @param locY - the location y.
     * @param size - the size of the bird.
     */
    private void createSnowman(SpriteCollection design, int locX, int locY, int size) {
        Point centerBody = new Point(locX, locY);
        Point centerHead = new Point(locX, locY - (size));
        Circle body = new Circle(2 * size / 3, centerBody, Color.white, true);
        Circle head = new Circle(size / 2, centerHead, Color.white, true);
        design.addSprite(body);
        design.addSprite(head);
        //nose
        for (int i = 0; i < size / 5; i++) {
            Point centerNose = new Point(locX - (2 * i), locY - (size) + (size / 10) + (2 * i));
            Circle nose = new Circle((size / 10) - i, centerNose, COLORNOSE, true);
            design.addSprite(nose);
        }
        Point centerEye1 = new Point(locX + (size / 5), locY - (size)  - (size / 7));
        Point centerEye2 = new Point(locX - (size / 5), locY - (size) - (size / 7));
        Circle eye1 = new Circle((size / 10), centerEye1, Color.black, true);
        Circle eye2 = new Circle((size / 10), centerEye2, Color.black, true);
        design.addSprite(eye1);
        design.addSprite(eye2);
        //hat
        Point bottomPoint = new Point(locX - (size / 2), locY - (size) - (size / 2));
        Rectangle bottom = new Rectangle(bottomPoint, size, size / 10, HATCOLOR);
        design.addSprite(bottom);
        //hat
        Point topPoint = new Point(locX - (size / 2) + (size / 10), locY - (size) - (size / 2) - (size / 3));
        Rectangle top = new Rectangle(topPoint, size - (size / 5), size / 3, HATCOLOR);
        design.addSprite(top);
        //button
        Point buttonPoint1 = new Point(locX, locY);
        Point buttonPoint2 = new Point(locX, locY - (size / 3));
        Point buttonPoint3 = new Point(locX, locY + (size / 3));
        Circle button1 = new Circle((size / 10), buttonPoint1, Color.black, true);
        Circle button2 = new Circle((size / 10), buttonPoint2, Color.black, true);
        Circle button3 = new Circle((size / 10), buttonPoint3, Color.black, true);
        design.addSprite(button1);
        design.addSprite(button2);
        design.addSprite(button3);
        //hands
        for (int i = 0; i < (size / 3); i++) {
            Point startPoint1 = new Point(locX - (size / 2) - i, locY - (size / 6) - i);
            Point startPoint2 = new Point(locX + (size / 2) + i, locY - (size / 6) - i);
            Circle hand1 = new Circle(size / 20, startPoint1, Color.black, true);
            Circle hand2 = new Circle(size / 20, startPoint2, Color.black, true);
            design.addSprite(hand1);
            design.addSprite(hand2);
        }

    }
    /**
     * create people and  enter it to the SpriteCollection.
     * @param design SpriteCollection.
     * @param locX  - the location x.
     * @param locY - the location y.
     * @param size - the size of the bird.
     */
    private void createPeople(SpriteCollection design, int locX, int locY, int size) {
        Point pointHead = new Point(locX, locY - size);
        Circle head = new Circle(size / 4, pointHead, Color.BLACK, true);
        design.addSprite(head);
        for (int i = 0; i < size; i++) {
            Point center = new Point(locX, locY - i);
            Circle circle = new Circle(size / 6, center, Color.BLACK, true);
            design.addSprite(circle);
        }
        for (int i = 0; i < (size / 4); i++) {
            Point center2 = new Point(locX + i, locY + (3 * i));
            Circle circle2 = new Circle(size / 8, center2, Color.BLACK, true);
            design.addSprite(circle2);
        }
        for (int i = 0; i < (size / 4); i++) {
            Point center2 = new Point(locX - i, locY + (3 * i));
            Circle circle2 = new Circle(size / 8, center2, Color.BLACK, true);
            design.addSprite(circle2);
        }
        for (int i = 0; i < size / 2; i++) {
            Point center2 = new Point(locX - i, locY - (3 * size / 4) + i);
            Circle circle2 = new Circle(size / 8, center2, Color.BLACK, true);
            design.addSprite(circle2);
        }
        for (int i = 0; i < size / 2; i++) {
            Point center2 = new Point(locX + i, locY - (3 * size / 4) + i);
            Circle circle2 = new Circle(size / 8, center2, Color.BLACK, true);
            design.addSprite(circle2);
        }
    }
    @Override
    public List<Block> blocks() {
        this.blockRemover = 0;
        List<Block> blockList = new ArrayList<>();
        int counterCol = 0;
        for (int j = (DISTANCEFROMTHETOP - 10); j < FRAME.getHeight() && counterCol < COLBLOCK;
             j = j + BLOCKHEIDHT) {
            counterCol++;
            int counterRow = ROWBLOCK;
            for (int i = (FRAME.getWidth() - 10 - BLOCKWHIDTH);
                 (i > BLOCKWHIDTH)  && (counterRow > 0); i = i - BLOCKWHIDTH) {
                counterRow--;
                Rectangle rectangle = new Rectangle(new Point(i, j), BLOCKWHIDTH, BLOCKHEIDHT,
                        BLOCKCOLOR);
                Block block = new Block(rectangle);
                blockList.add(block);
                this.blockRemover++;
            }
        }
        return blockList;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return blockRemover;
    }
    @Override
    public Color getColorBalls() {
        return Color.blue;
    }
}
