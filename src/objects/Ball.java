//322631458 Nophar Glotman
import java.awt.Color;
import biuoop.DrawSurface;
import javax.swing.JOptionPane;
import java.util.Random;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 8.4.22
 */
public class Ball implements Sprite {
    static final Velocity DEFAULTVELOCITY = new Velocity(0, 0);
    private int radius;
    private Point center;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    /**
     * create object Ball.
     * @param center -the center point of the ball.
     * @param r -the radius point of the ball.
     */
    public Ball(Point center, int r) {
        setRadius(r);
        this.color = getRandomColor();
        setCenter(center);
        this.velocity = DEFAULTVELOCITY;
        this.gameEnvironment = new GameEnvironment();
    }
    /**
     * create object Ball.
     * @param center -the center point of the ball.
     * @param r -the radius point of the ball.
     * @param color - the color point of the ball.
     */
    public Ball(Point center, int r, Color color) {
        setRadius(r);
        this.color = color;
        setCenter(center);
        this.velocity = DEFAULTVELOCITY;
        this.gameEnvironment = new GameEnvironment();
    }
    /**
     * create object Ball.
     * @param x -the x location of the center point of the ball.
     * @param y -the y location of the center point of the ball.
     * @param r -the radius point of the ball.
     * @param color - the color point of the ball.
     */
    public Ball(int x, int y, int r, Color color) {
        setRadius(r);
        this.color = color;
        setCenter(new Point(x, y));
        this.velocity = DEFAULTVELOCITY;
        this.gameEnvironment = new GameEnvironment();
    }
    /**
     * give the x location of the center of the ball.
     * @return the x location of the center of the ball.
     */
    public double getX() {
        return this.center.getX();
    }
    /**
     * give the Y location of the center of the ball.
     * @return the Y location of the center of the ball.
     */
    public double getY() {
        return this.center.getY();
    }
    /**
     * give the radius of the ball.
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * give Game Environment of the ball.
     * @return Game Environment of the ball.
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }
    /**
     * give the color of the ball.
     * @return the color of the ball.
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * draw the ball on the surface.
     * @param surface - the surface the ball will draw.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) center.getY(), this.radius);
    }
    /**
     * give random color.
     * @return random color.
     */
    private static Color getRandomColor() {
        Random randNum = new Random();
        int red = randNum.nextInt(256);
        int green = randNum.nextInt(256);
        int blue = randNum.nextInt(256);
        return new Color(red, green, blue);
    }
    /**
     * set the center of the ball.
     * @param center - the new center of the ball.
     */
    public void setCenter(Point center) {
        this.center = center;
    }
    /**
     * set the radius of the ball.
     * @param radius - the new radius of the ball.
     */
    public void setRadius(int radius) {
        if (radius <= 0) {
            JOptionPane.showMessageDialog(null, "error: radius balls need to bigger than 0");
            System.exit(0);
        }
        if (radius > 0) {
            this.radius = radius;
        }
    }
    /**
     * change the velocity of the ball.
     * @param v - the new value of the velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * set the velocity of the ball.
     * @param dx - the new value of the dx's velocity of the ball.
     * @param dy - the new value of the dy's velocity of the ball.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**
     * give the velocity of the ball.
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     * change the position the point.
     */
    public void moveOneStep() {
        if (this.velocity.getDx() == 0 && this.velocity.getDy() == 0) {
            return;
        }
        this.ifBallInPaddle();
        if ((this.gameEnvironment.getClosestCollision(this.trajectory()) != null)) {
            CollisionInfo block = this.gameEnvironment.getClosestCollision(this.trajectory());
            Point pointCollision = block.collisionPoint();
            this.velocity = block.collisionObject().hit(this, pointCollision, this.velocity);
            double newX = pointCollision.getX() + (this.velocity.getDx() / 100);
            double newY = pointCollision.getY() + (this.velocity.getDy() / 100);
            this.center = new Point(newX, newY);
            return;
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }
    /**
     * change the position of point if the ball in the paddle after paddle movement.
     * @return if the ball inside the paddle
     */
    private boolean ifBallInPaddle() {
        Rectangle paddle = this.gameEnvironment.getCollidables().get(0).getCollisionRectangle();
        if (this.center.getX() < (paddle.getWidth() + paddle.getUpperLeft().getX())
                && this.center.getX() > (paddle.getUpperLeft().getX())
                && this.center.getY() < (paddle.getHeight() + paddle.getUpperLeft().getY())
                && this.center.getY() > (paddle.getUpperLeft().getY())) {
            this.center.setX(closeTo(paddle.getUpperLeft().getX(),
                    paddle.getWidth() + paddle.getUpperLeft().getX(), this.center.getX()));
            this.center.setY(this.getY() + this.velocity.getDy());
            return true;
        }
        return false;
    }
    /**
     * change the position of ball if the ball is under the paddle.
     */
    private void isDownPaddle() {
        Rectangle paddle = this.gameEnvironment.getCollidables().get(1).getCollisionRectangle();
        if (this.center.getX() < (paddle.getWidth() + paddle.getUpperLeft().getX())
                && this.center.getX() > (paddle.getUpperLeft().getX())
                && this.center.getY() > (paddle.getHeight() + paddle.getUpperLeft().getY())) {
            this.center.setX(closeTo(paddle.getUpperLeft().getX(),
                    paddle.getWidth() + paddle.getUpperLeft().getX(), this.center.getX()));
        }
    }
    /**
     * check which number the check number close to.
     * @param check - the number that check where he is close to.
     * @param num1 - number 1.
     * @param num2 - number 2.
     * @return the number he is close to.
     */
    private double closeTo(double num1, double num2, double check) {
        if (Math.abs(num1 - check) > Math.abs(num2 - check)) {
            return num2;
        }
        return num1;
    }
    private boolean isFindInRectangle(Rectangle rectangle) {
        if ((this.center.getX() > rectangle.getUpperLeft().getX())
                && (this.center.getX() < (rectangle.getUpperLeft().getX() + rectangle.getWidth())
                && (this.center.getY() > rectangle.getUpperLeft().getY())
                && this.center.getY() < (rectangle.getUpperLeft().getY() + rectangle.getHeight()))) {
            return true;
        }
        return false;
    }
    /**
     * calculate the trajectory.
     * @return the trajectory.
     */
    private Line trajectory() {
        double xStart = this.center.getX();
        double yStart = this.center.getY();
        double xEnd = xStart + this.velocity.getDx();
        double yEnd = yStart + this.velocity.getDy();
        Line trajectory = new Line(xStart, yStart, xEnd, yEnd);
        return trajectory;
    }
    // notify the sprite that time has passed
    @Override
    public void timePassed() {
        this.moveOneStep();
    }
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        this.gameEnvironment = g.getEnvironment();
    }
}
