//322631458 Nophar Glotman
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 1.6.22
 */
public class Level2 implements LevelInformation {
    static final Frame FRAME = new Frame(800, 600, 0, 0);
    static final java.awt.Color BACKGROUND = Color.WHITE;
    static final Color BROWN = new Color(153, 102, 0);;
    static final int NUMOFBALLS = 6;
    static final int PADDLESPEED = 10;
    static final int PADDLEWITDH = 400;
    static final Color COLORPADDLE = BROWN;
    static final String LEVELNAME = "vacation";
    static final int BLOCKTOREMOVE = 6;
    //balls
    static final int SPEEDBALL = 6;
    static final int STARTANGEL = 120;
    static final int RANGEANGEL = 120;
    //leaf
    static  final Color DARKGREEN = new Color(0, 160, 0);
    //block
    static final int BLOCKWHIDTH = 80;
    static final int BLOCKHEIDHT = 20;
    static final Color BLOCKCOLOR = Color.white;
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
        for (int i = 0; i < 50; i++) {
            Color background = new Color(51, 204 - 4 * i, 255);
            Point startPoint = new Point(0, i * (FRAME.getHeight() / 50));
            Frame frame = new Frame(FRAME.getWidth(), FRAME.getHeight() / 50, startPoint.getX(), startPoint.getY());
            Sprite rectangleBackground = new Rectangle(frame, background);
            design.addSprite(rectangleBackground);
        }
        Color mainlandColor = new Color(255, 204, 51);
        Circle mainland = new Circle(400, new Point(800, 800), mainlandColor, true);
        design.addSprite(mainland);
        Circle smallland = new Circle(300, new Point(500, 800), mainlandColor, true);
        design.addSprite(smallland);
        this.createBird(design, 300, 100, 10);
        this.createBird(design, 360, 80, 10);
        this.createBird(design, 200, 80, 10);
        this.createBird(design, 150, 50, 10);
        this.createBoat(design, 100, 300, 20);
        this.createBoat(design, 300, 400, 30);
        this.createTree(design, 700, 500, 300);
        this.createTree(design, 600, 500, 200);
        this.createTree(design, 650, 550, 200);
        this.createTree(design, 750, 540, 200);
        return design;
    }
    /**
     * create tree and  enter it to the SpriteCollection.
     * @param design SpriteCollection.
     * @param locX  - the location x.
     * @param locY - the location y.
     * @param size - the size of the bird.
     */
    private void createTree(SpriteCollection design, int locX, int locY, int size) {
        //down tree
        for (int i = 0; i <= (size / 12); i++) {
            Point startPoint = new Point(locX + i, locY - (size / 2));
            Point endPoint = new Point(locX - (size / 25) + (1.5 * i), locY);
            Line line = new Line(startPoint, endPoint, BROWN);
            design.addSprite(line);
        }
        //top tree
        for (int i = 0; i <= (size / 12); i++) {
            Point startPoint = new Point(locX + i, locY - (size / 2));
            Point endPoint = new Point(locX + (size / 25) + i, locY - size);
            Line line = new Line(startPoint, endPoint, BROWN);
            design.addSprite(line);
        }
        //leaf
        for (int i = 0; i <= (size / 35); i++) {
            Point startPoint = new Point(locX + (size / 12), locY - size);
            Point middlePoint = new Point(locX + (size / 12) - (size / 6) + (2 * i),
                    locY - size + (size / 6) + (2 * i));
            Point endPoint = new Point(locX + (size / 12) - (size / 3), locY - size + (size / 3));
            Line line = new Line(startPoint, middlePoint, Color.green);
            Line line2 = new Line(middlePoint, endPoint, Color.green);
            design.addSprite(line);
            design.addSprite(line2);
            Point startPoint1 = new Point(locX + (size / 12) + 1, locY - size + 1);
            Point middlePoint1 = new Point(locX + (size / 12) - (size / 6) + (2 * i) + 1,
                    locY - size + (size / 6) + (2 * i) + 1);
            Point endPoint1 = new Point(locX + (size / 12) - (size / 3) + 1, locY - size + (size / 3) + 1);
            Line line1 = new Line(startPoint1, middlePoint1, DARKGREEN);
            Line line21 = new Line(middlePoint1, endPoint1, DARKGREEN);
            design.addSprite(line1);
            design.addSprite(line21);
        }
        for (int i = 0; i <= 8; i++) {
            Point startPoint = new Point(locX + (size / 12), locY - size);
            Point middlePoint = new Point(locX - (size / 10) + (2 * i), locY - size + (2 * i));
            Point endPoint = new Point(locX - (size / 3.5), locY - size + (size / 5));
            Line line = new Line(startPoint, middlePoint, Color.green);
            Line line2 = new Line(middlePoint, endPoint, Color.green);
            design.addSprite(line);
            design.addSprite(line2);
        }
        for (int i = 0; i <= 8; i++) {
            Point startPoint = new Point(locX + (size / 12), locY - size);
            Point middlePoint = new Point(locX - (size / 7.5) + (2 * i), locY - size - (size / 15) + (2 * i));
            Point endPoint = new Point(locX - (size / 3), locY - size + (size / 15));
            Line line = new Line(startPoint, middlePoint, Color.green);
            Line line2 = new Line(middlePoint, endPoint, Color.green);
            design.addSprite(line);
            design.addSprite(line2);
        }
        for (int i = 0; i <= 8; i++) {
            Point startPoint = new Point(locX + (size / 12), locY - size);
            Point middlePoint = new Point(locX - (size / 10) - (3 * i), locY - size - (size / 5) + (3 * i));
            Point endPoint = new Point(locX - (size / 3), locY - size - (size / 7.5));
            Line line = new Line(startPoint, middlePoint, Color.green);
            Line line2 = new Line(middlePoint, endPoint, Color.green);
            design.addSprite(line);
            design.addSprite(line2);
        }
        for (int i = 0; i <= 8; i++) {
            Point startPoint = new Point(locX + (size / 12), locY - size);
            Point middlePoint = new Point(locX - (3 * i), locY - size - (size / 5) + (3 * i));
            Point endPoint = new Point(locX + (size / 15), locY - size - (size / 5));
            Line line = new Line(startPoint, middlePoint, Color.green);
            Line line2 = new Line(middlePoint, endPoint, Color.green);
            design.addSprite(line);
            design.addSprite(line2);
        }
        for (int i = 0; i <= 8; i++) {
            Point startPoint = new Point(locX + (size / 12), locY - size);
            Point middlePoint = new Point(locX + (size / 15) - (3 * i), locY - size - (size / 3) + (3 * i));
            Point endPoint = new Point(locX, locY - size - (size / 3.5));
            Line line = new Line(startPoint, middlePoint, Color.green);
            Line line2 = new Line(middlePoint, endPoint, Color.green);
            design.addSprite(line);
            design.addSprite(line2);
        }
        for (int i = 0; i <= 8; i++) {
            Point startPoint = new Point(locX + (size / 12), locY - size);
            Point middlePoint = new Point(locX + (size / 15) + (3 * i), locY - size - (size / 5) + (3 * i));
            Point endPoint = new Point(locX + (size / 5), locY - size - (size / 3));
            Line line = new Line(startPoint, middlePoint, Color.green);
            Line line2 = new Line(middlePoint, endPoint, Color.green);
            design.addSprite(line);
            design.addSprite(line2);
        }
        for (int i = 0; i <= 8; i++) {
            Point startPoint = new Point(locX + (size / 12), locY - size);
            Point middlePoint = new Point(locX + (size / 7.5) + (3 * i), locY - size - (size / 7.5) + (3 * i));
            Point endPoint = new Point(locX + (size / 3.5), locY - size - (size / 5));
            Line line = new Line(startPoint, middlePoint, Color.green);
            Line line2 = new Line(middlePoint, endPoint, Color.green);
            design.addSprite(line);
            design.addSprite(line2);
            Point startPoint1 = new Point(locX + (size / 12) + 1, locY - size + 1);
            Point middlePoint1 = new Point(locX + (size / 7.5) + (3 * i) + 1,
                    locY - size - (size / 7.5) + (3 * i) + 1);
            Point endPoint1 = new Point(locX + (size / 3.5) + 1, locY - size - (size / 5) + 1);
            Line line1 = new Line(startPoint1, middlePoint1, DARKGREEN);
            Line line21 = new Line(middlePoint1, endPoint1, DARKGREEN);
            design.addSprite(line1);
            design.addSprite(line21);
        }
        for (int i = 0; i <= 8; i++) {
            Point startPoint = new Point(locX + (size / 12), locY - size);
            Point middlePoint = new Point(locX + (size / 7.5) + (3 * i), locY - size + (size / 6) - (3 * i));
            Point endPoint = new Point(locX + (size / 3.5), locY - size);
            Line line = new Line(startPoint, middlePoint, Color.green);
            Line line2 = new Line(middlePoint, endPoint, Color.green);
            design.addSprite(line);
            design.addSprite(line2);
            Point startPoint1 = new Point(locX + (size / 12) + 1, locY - size + 1);
            Point middlePoint1 = new Point(locX + (size / 7.5) + (3 * i) + 1,
                    locY - size + (size / 6) - (3 * i) + 1);
            Point endPoint1 = new Point(locX + (size / 3.5) + 1, locY - size + 1);
            Line line1 = new Line(startPoint1, middlePoint1, DARKGREEN);
            Line line21 = new Line(middlePoint1, endPoint1, DARKGREEN);
            design.addSprite(line1);
            design.addSprite(line21);
        }
        for (int i = 0; i <= 8; i++) {
            Point startPoint = new Point(locX + (size / 12), locY - size);
            Point startPoint1 = new Point(locX + (size / 12) + 1, locY - size + 1);
            Point middlePoint = new Point(locX + (size / 15) + (3 * i), locY - size + (size / 6) - (3 * i));
            Point middlePoint1 = new Point(locX + (size / 15) + (3 * i) + 1, locY - size + (size / 6) - (3 * i) + 1);
            Point endPoint = new Point(locX + (size / 7.5), locY - size + (size / 3));
            Point endPoint1 = new Point(locX + (size / 7.5) + 1, locY - size + (size / 3) + 1);
            Line line = new Line(startPoint, middlePoint, Color.green);
            Line line2 = new Line(middlePoint, endPoint, Color.green);
            Line line1 = new Line(startPoint1, middlePoint1, DARKGREEN);
            Line line21 = new Line(middlePoint1, endPoint1, DARKGREEN);
            design.addSprite(line);
            design.addSprite(line2);
            design.addSprite(line1);
            design.addSprite(line21);
        }
    }
    /**
     * create boat and  enter it to the SpriteCollection.
     * @param design SpriteCollection.
     * @param locX  - the location x.
     * @param locY - the location y.
     * @param size - the size of the bird.
     */
    private void createBoat(SpriteCollection design, int locX, int locY, int size) {
        for (int i = 0; i <= size; i++) {
            Point startPoint = new Point(locX - i, locY - i);
            Point endPoint = new Point(locX + (2 * size) + i, locY - i);
            Line line = new Line(startPoint, endPoint, BROWN);
            design.addSprite(line);
        }
        for (int i = 0; i <= (size / 5); i++) {
            Point startPoint = new Point(locX + (2 * size / 3) - i, locY - size);
            Point endPoint = new Point(locX + (2 * size / 3) - i, locY - (3 * size));
            Line line = new Line(startPoint, endPoint, BROWN);
            design.addSprite(line);
        }
        for (int i = 0; i < (size * 2); i++) {
            Point startPoint = new Point(locX + (2 * size / 3) + i, locY - size - size / 3);
            Point endPoint = new Point(locX + (2 * size / 3), locY - (3 * size));
            Line line = new Line(startPoint, endPoint, Color.white);
            design.addSprite(line);
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
            Line line = new Line(startPoint, endPoint, Color.white);
            Point startPoint2 = new Point(x2, y);
            Point endPoint2 = new Point(x2 + 1, y);
            Line line2 = new Line(startPoint2, endPoint2, Color.white);
            design.addSprite(line2);
            design.addSprite(line);
        }
        for (int i = 0; i < size; i++) {
            int y = locY - size + i;
            int x = (int) Math.sqrt(Math.pow(size, 2) - Math.pow(y - locY, 2)) + locX - size;
            int x2 = -(int) Math.sqrt(Math.pow(size, 2) - Math.pow(y - locY, 2)) + locX - size;
            Point startPoint = new Point(x, y);
            Point endPoint = new Point(x + 1, y);
            Line line = new Line(startPoint, endPoint, Color.white);
            Point startPoint2 = new Point(x2, y);
            Point endPoint2 = new Point(x2 + 1, y);
            Line line2 = new Line(startPoint2, endPoint2, Color.white);
            design.addSprite(line2);
            design.addSprite(line);
        }
    }
    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        int distanceY = 40;
        for (int i = 1; i < 4; i++) {
            int locationX = i * FRAME.getWidth() / 7;
            int locationX2 = (7 - i) * FRAME.getWidth() / 7;
            int locationY = 100 + i * distanceY;
            Rectangle rectangle1 = new Rectangle(new Point(locationX, locationY), BLOCKWHIDTH, BLOCKHEIDHT, BLOCKCOLOR);
            Rectangle rectangle2 = new Rectangle(new Point(locationX2, locationY), BLOCKWHIDTH, BLOCKHEIDHT,
                    BLOCKCOLOR);
            Block block1 = new Block(rectangle1);
            Block block2 = new Block(rectangle2);
            blockList.add(block1);
            blockList.add(block2);
        }
        return blockList;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return BLOCKTOREMOVE;
    }
    @Override
    public Color getColorBalls() {
        return Color.white;
    }
}
