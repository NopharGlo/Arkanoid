//322631458 Nophar Glotman
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 1.6.22
 */
public class Level1 implements LevelInformation {
    static final Frame FRAME = new Frame(800, 600, 0, 0);
    static final java.awt.Color BACKGROUND = new Color(0, 204, 0);
    static final int NUMOFBALLS = 1;
    static final int PADDLESPEED = 10;
    static final int PADDLEWITDH = 90;
    static final Color COLORPADDLE = Color.black;
    static final String LEVELNAME = "shooting";
    static final int BLOCKTOREMOVE = 1;
    static final Velocity VELOCITYBALLS = Velocity.fromAngleAndSpeed(0, 8);
    //block
    static final int BLOCKWHIDTH = 30;
    static final int BLOCKHEIDHT = 30;
    static final int LOCATIONX = FRAME.getWidth() / 2 - BLOCKWHIDTH / 2;
    static final int LOCATIONY = FRAME.getHeight() / 4 - BLOCKHEIDHT / 2;
    static final Color BLOCKCOLOR = Color.black;
    //design
    static final Point CENTER = new Point(FRAME.getWidth() / 2, FRAME.getHeight() / 4);
    static final int RADIUSCIRCLE1 = 60;
    static final int RADIUSCIRCLE2 = 80;
    static final int RADIUSCIRCLE3 = 100;
    static final Color COLORCIRCLE = Color.black;
    static final int SIZELINE = 100;
    static final Point STARTTOPLINE = new Point((int) CENTER.getX(), (int) (CENTER.getY() - BLOCKHEIDHT / 2));
    static final Point STARTDOWNLINE = new Point((int) CENTER.getX(), (int) (CENTER.getY() + BLOCKHEIDHT / 2));
    static final Point STARTLEFTLINE = new Point((int) CENTER.getX() - BLOCKWHIDTH / 2, (int) (CENTER.getY()));
    static final Point STARTRIGHTLINE = new Point((int) CENTER.getX() + BLOCKWHIDTH / 2, (int) (CENTER.getY()));
    @Override
    public int numberOfBalls() {
        return NUMOFBALLS;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<>();
        velocityList.add(VELOCITYBALLS);
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
            Color background = new Color(51, 153, 255 - (2 * i));
            Point startPoint = new Point(0, i * (FRAME.getHeight() / 50));
            Frame frame = new Frame(FRAME.getWidth(), FRAME.getHeight() / 50, startPoint.getX(), startPoint.getY());
            Sprite rectangleBackground = new Rectangle(frame, background);
            design.addSprite(rectangleBackground);
        }
        Circle circle1 = new Circle(RADIUSCIRCLE1, CENTER, COLORCIRCLE, false);
        Circle circle2 = new Circle(RADIUSCIRCLE2, CENTER, COLORCIRCLE, false);
        Circle circle3 = new Circle(RADIUSCIRCLE3, CENTER, COLORCIRCLE, false);
        Line topLine = new Line(STARTTOPLINE, new Point(STARTTOPLINE.getX(), STARTTOPLINE.getY() - SIZELINE),
                COLORCIRCLE);
        Line downLine = new Line(STARTTOPLINE, new Point(STARTDOWNLINE.getX(), STARTDOWNLINE.getY() + SIZELINE),
                COLORCIRCLE);
        Line leftLine = new Line(STARTLEFTLINE, new Point(STARTLEFTLINE.getX() - SIZELINE, STARTLEFTLINE.getY()),
                COLORCIRCLE);
        Line rightLine = new Line(STARTRIGHTLINE, new Point(STARTRIGHTLINE.getX() + SIZELINE, STARTRIGHTLINE.getY()),
                COLORCIRCLE);
        design.addSprite(circle1);
        design.addSprite(circle2);
        design.addSprite(circle3);
        design.addSprite(topLine);
        design.addSprite(downLine);
        design.addSprite(leftLine);
        design.addSprite(rightLine);
        return design;
    }
    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        Rectangle rectangle = new Rectangle(new Point(LOCATIONX, LOCATIONY), BLOCKWHIDTH, BLOCKHEIDHT, BLOCKCOLOR);
        Block block = new Block(rectangle);
        blockList.add(block);
        return blockList;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return BLOCKTOREMOVE;
    }
    @Override
    public Color getColorBalls() {
        return Color.red;
    }
}
