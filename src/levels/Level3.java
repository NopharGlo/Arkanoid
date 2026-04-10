//322631458 Nophar Glotman
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 1.6.22
 */
public class Level3 implements LevelInformation {
    static final Frame FRAME = new Frame(800, 600, 0, 0);
    static final java.awt.Color BACKGROUND = Color.WHITE;
    static final int NUMOFBALLS = 3;
    static final int PADDLESPEED = 10;
    static final int PADDLEWITDH = 200;
    static final Color COLORPADDLE = new Color(255, 102, 0);
    static final String LEVELNAME = "sunset";
    static final int SPEEDBALL = 5;
    static final int STARTANGEL = 300;
    static final int RANGEANGEL = 120;
    //block
    static final int DISTANCEFROMTHETOP = 100;
    static final int ROWBLOCK = 12;
    static final int COLBLOCK = 6;
    static final int BLOCKWHIDTH = 60;
    static final int BLOCKHEIDHT = 30;
    static final Color BLOCKCOLOR = Color.red;
    private int blockRemover;
    /**
     * create object Level3.
     */
    public Level3() {
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
            Color background = new Color(255, 255 - (6 * i), 00);
            Point startPoint = new Point(0, i * (FRAME.getHeight() / 50));
            Frame frame = new Frame(FRAME.getWidth(), FRAME.getHeight() / 50,
                    startPoint.getX(), startPoint.getY());
            Sprite rectangleBackground = new Rectangle(frame, background);
            design.addSprite(rectangleBackground);
        }
        for (int i = 35; i < 50; i++) {
            Color background = new Color(100, 153 + (7 * (i - 35)), 255);
            Point startPoint = new Point(0, i * (FRAME.getHeight() / 50));
            Frame frame = new Frame(FRAME.getWidth(), FRAME.getHeight() / 50, startPoint.getX(), startPoint.getY());
            Sprite rectangleBackground = new Rectangle(frame, background);
            design.addSprite(rectangleBackground);
        }
        //
        Color black = Color.BLACK;
        for (int i = 0; i < 400; i++) {
            int y = 300 + i;
            int width = 500 -  i;
            Point startPoint = new Point(0, y);
            Rectangle rectangle = new Rectangle(startPoint, width, 1, black);
            design.addSprite(rectangle);
        }
        this.createBird(design, 300, 100, 10);
        this.createBird(design, 360, 80, 10);
        this.createBird(design, 200, 80, 10);
        this.createBird(design, 150, 50, 10);
        this.createCactus(design, 200, 300, 40);
        this.createCactus(design, 150, 300, 60);
        this.createCactus(design, 100, 300, 20);
        return design;
    }
    /**
     * create cactus and  enter it to the SpriteCollection.
     * @param design SpriteCollection.
     * @param locX  - the location x.
     * @param locY - the location y.
     * @param size - the size of the bird.
     */
    private void createCactus(SpriteCollection design, int locX, int locY, int size) {
        for (int i = 0; i < size; i++) {
            Point center = new Point(locX, locY - i);
            Circle circle = new Circle(size / 4, center, Color.BLACK, true);
            design.addSprite(circle);
        }
        for (int i = 0; i < (size / 2); i++) {
            Point center2 = new Point(locX + i, locY - (size / 4));
            Circle circle2 = new Circle(size / 8, center2, Color.BLACK, true);
            design.addSprite(circle2);
        }
        for (int i = 0; i < (size / 2); i++) {
            Point center2 = new Point(locX + (size / 2), locY - ((size / 4) + i));
            Circle circle2 = new Circle(size / 8, center2, Color.BLACK, true);
            design.addSprite(circle2);
        }
        for (int i = 0; i < (size / 2); i++) {
            Point center2 = new Point(locX - i, locY - (size / 2));
            Circle circle2 = new Circle(size / 8, center2, Color.BLACK, true);
            design.addSprite(circle2);
        }
        for (int i = 0; i < (size / 2); i++) {
            Point center2 = new Point(locX - (size / 2), locY - (size / 2) - i);
            Circle circle2 = new Circle(size / 8, center2, Color.BLACK, true);
            design.addSprite(circle2);
        }
    }
    /**
     * create bird and  enter it to the SpriteCollection.
     * @param design SpriteCollection.
     * @param locX  - the location x.
     * @param locY - the location y.
     * @param size - the size of the bird.
     */
    private void createBird(SpriteCollection design, int locX, int locY, int size) {
        for (int i = 0; i < size; i++) {
            int y = locY - i;
            int x = (int) Math.sqrt(Math.pow(size, 2) - Math.pow(y - locY, 2)) + locX + size;
            int x2 = -(int) Math.sqrt(Math.pow(size, 2) - Math.pow(y - locY, 2)) + locX + size;
            Point startPoint = new Point(x, y);
            Point endPoint = new Point(x + 1, y);
            Line line = new Line(startPoint, endPoint, Color.black);
            Point startPoint2 = new Point(x2, y);
            Point endPoint2 = new Point(x2 + 1, y);
            Line line2 = new Line(startPoint2, endPoint2, Color.black);
            design.addSprite(line2);
            design.addSprite(line);
        }
        for (int i = 0; i < size; i++) {
            int y = locY - size + i;
            int x = (int) Math.sqrt(Math.pow(size, 2) - Math.pow(y - locY, 2)) + locX - size;
            int x2 = -(int) Math.sqrt(Math.pow(size, 2) - Math.pow(y - locY, 2)) + locX - size;
            Point startPoint = new Point(x, y);
            Point endPoint = new Point(x + 1, y);
            Line line = new Line(startPoint, endPoint, Color.black);
            Point startPoint2 = new Point(x2, y);
            Point endPoint2 = new Point(x2 + 1, y);
            Line line2 = new Line(startPoint2, endPoint2, Color.black);
            design.addSprite(line2);
            design.addSprite(line);
        }
    }
    @Override
    public List<Block> blocks() {
        this.blockRemover = 0;
        List<Block> blockList = new ArrayList<>();
        int counterCol = 0;
        for (int j = (DISTANCEFROMTHETOP - 10); j < FRAME.getHeight()
                && counterCol < COLBLOCK; j = j + BLOCKHEIDHT) {
            int counterRow = ROWBLOCK - counterCol;
            counterCol++;
            for (int i = (FRAME.getWidth() - 10 - BLOCKWHIDTH); (i > BLOCKWHIDTH)
                    && (counterRow > 0); i = i - BLOCKWHIDTH) {
                counterRow--;
                Rectangle rectangle = new Rectangle(new Point(i, j), BLOCKWHIDTH, BLOCKHEIDHT, BLOCKCOLOR);
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
        return Color.white;
    }
}
