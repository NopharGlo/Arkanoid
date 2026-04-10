//322631458 Nophar Glotman
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/**
 * @author      Nophar Glotman
 * @version     1
 * @since       4.8.22
 */
public class Paddle implements Sprite, Collidable, KeyboardSensor {
    static final int PACE = 10;
    private int speed;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private int borderLeft;
    private int borderRight;
    private Set<String> pressedButtons = Collections.synchronizedSet(new HashSet());
    /**
     * create new object Paddle.
     * @param paddle - the shape of the paddle.
     * @param borderRight - the right border.
     * @param borderLeft - the left border.
     */
    public Paddle(Rectangle paddle, int borderRight, int borderLeft) {
        this.paddle = paddle;
        this.borderLeft = borderLeft;
        this.borderRight = borderRight;
    }
    /**
     * create new object Paddle.
     * @param paddle - the shape of the paddle.
     * @param borderRight - the right border.
     * @param borderLeft - the left border.
     * @param speed - the speed of the paddle.
     */
    public Paddle(Rectangle paddle, int borderRight, int borderLeft, int speed) {
        this.paddle = paddle;
        this.borderLeft = borderLeft;
        this.borderRight = borderRight;
        this.speed = speed;
    }
    /**
     * set the keyboard.
     * @param gui  - get the gui to init the keyboard.
     */
    public void setGui(GUI gui) {
        this.keyboard = gui.getKeyboardSensor();
    }
    /**
     * move the paddle left.
     */
    public void moveLeft() {
        double newY = this.paddle.getUpperLeft().getY();
        if (this.paddle.getUpperLeft().getX() < this.borderLeft + speed) {
            this.paddle.setUpperLeft(this.borderLeft + 0.5, newY);
            return;
        }
        double newX = this.paddle.getUpperLeft().getX() - speed;
        this.paddle.setUpperLeft(newX, newY);
    }
    /**
     * move the paddle right.
     */
    public void moveRight() {
        double newY = this.paddle.getUpperLeft().getY();
        if (this.paddle.getUpperLeft().getX() > (this.borderRight - this.paddle.getWidth() - speed)) {
            this.paddle.setUpperLeft(this.borderRight - this.paddle.getWidth() - 0.5, newY);
            return;
        }
        double newX = this.paddle.getUpperLeft().getX() + speed;
        this.paddle.setUpperLeft(newX, newY);
    }
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.paddle.getColor());
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;
        double speed = newVelocity.getSpeed();
        double part = this.paddle.getWidth() / 5;
        double x = this.paddle.getUpperLeft().getX();
        double y = this.paddle.getUpperLeft().getY();
        int anglePart1 = 300;
        int anglePart2 = 330;
        int anglePart4 = 30;
        int anglePart5 = 60;
        if ((collisionPoint.getX() == (this.paddle.getUpperLeft().getX() + this.paddle.getWidth()))   //right
                && (collisionPoint.getY() >= this.paddle.getUpperLeft().getY())
                && (collisionPoint.getY() <= (this.paddle.getUpperLeft().getY() + this.paddle.getHeight()))) {
            newVelocity.setDx(-currentVelocity.getDx());
        } else if ((collisionPoint.getX() == this.paddle.getUpperLeft().getX())                        //left
                && (collisionPoint.getY() >= this.paddle.getUpperLeft().getY())
                && (collisionPoint.getY() <= (this.paddle.getUpperLeft().getY() + this.paddle.getHeight()))) {
            newVelocity.setDx(-currentVelocity.getDx());
        } else if ((x <= collisionPoint.getX()) && (collisionPoint.getX() < (x + part))
                && (y == collisionPoint.getY())) {
            newVelocity = Velocity.fromAngleAndSpeed(anglePart1, speed);
        } else if (((x + part) <= collisionPoint.getX()) && (collisionPoint.getX() < (x + (2 * part)))
                && (y == collisionPoint.getY())) {
            newVelocity = Velocity.fromAngleAndSpeed(anglePart2, speed);
        } else if (((x + (2 * part)) <= collisionPoint.getX()) && (collisionPoint.getX() < (x + (3 * part)))
                && (y == collisionPoint.getY())) {
            newVelocity.setDy(-currentVelocity.getDy());
        } else if (((x + (3 * part)) <= collisionPoint.getX()) && (collisionPoint.getX() < (x + (4 * part)))
                && (y == collisionPoint.getY())) {
            newVelocity = Velocity.fromAngleAndSpeed(anglePart4, speed);
        } else if (((x + (4 * part)) <= collisionPoint.getX()) && (collisionPoint.getX() <= (x + (5 * part)))
                && (y == collisionPoint.getY())) {
            newVelocity = Velocity.fromAngleAndSpeed(anglePart5, speed);
        }
        return newVelocity;
    }
    // Add this paddle to the game.
    @Override
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    @Override
    public boolean isPressed(String s) {
        return this.pressedButtons.contains(s);
    }
    /**
     * set location of the paddle.
     * @param locationPaddle - the change point.
     */
    public void setLocationPaddle(Point locationPaddle) {
        this.paddle.setUpperLeft(locationPaddle.getX(), locationPaddle.getY());
    }
}