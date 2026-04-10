//322631458 Nophar Glotman
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;
/**
 * @author Nophar Glotman
 * @version 1
 * @since 1.6.22
 */
public class GameLevel implements Animation {
    static final Frame FRAME = new Frame(800, 600, 0, 0);
    static final java.awt.Color BACKGROUND = Color.WHITE;
    //borders
    static final java.awt.Color COLORBORDERS = Color.GRAY;
    static final int THICKBORDERS = 10;
    //paddle
    static final int PADDLEHEIDTH = 20;
    static final double YPADDLE = FRAME.getHeight() - THICKBORDERS - PADDLEHEIDTH - 0.1;
    //font text
    static final int FONTTEXT = 20;
    //balls
    static final int RADIUS = 5;
    static final String KEYFORWARD = KeyboardSensor.SPACE_KEY;
    private static Boolean stopGame = false;
    private biuoop.KeyboardSensor keyboard;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter numCollidable;
    private Counter numBalls;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation level;
    /**
     * set object game.
     * @param animationRunner - the animation that run.
     * @param level - the level that run.
     * @param keyboardSensor - the keyboard of the game
     * @param lives - lives stay in the game.
     * @param score - the score of the game.
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     Counter score, Counter lives) {
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.level = level;
        this.score = score;
        this.lives = lives;
        this.numCollidable = new Counter();
        this.numBalls = new Counter();
    }
    /**
     * give Game Environment.
     * @return Game Environment.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }
    /**
     * remove collidable from list environment.
     * @param collidable the collidable needed to be removed.
     */
    public void removeCollidableFromList(Collidable collidable) {
        this.environment.removeCollidables(collidable);
    }
    /**
     * remove sprites from list sprites.
     * @param sprites the sprites needed to be removed.
     */
    public void removeSpritesFromList(Sprite sprites) {
        this.sprites.removeSprite(sprites);
    }
    /**
     * add the given collidable to the environment.
     *
     * @param c collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * add to the list object that impliments Sprite.
     * @param s - object that impliments Sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * Initialize a new game.
     *
     * <p>create the Blocks and Ball (and Paddle) and add them to the game.
     */
    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        this.level.getBackground().addToGame(this);
        for (Sprite backgroundDesign:this.level.getDesignBackground().getSprites()) {
            backgroundDesign.addToGame(this);
        }
        double xPaddle = (FRAME.getWidth() / 2) - this.level.paddleWidth() / 2 + 0.5;
        Point paddlePoint = new Point(xPaddle, YPADDLE);
        Rectangle paddleRectangle = new Rectangle(paddlePoint, level.paddleWidth(), PADDLEHEIDTH,
                this.level.paddleColor());
        Paddle paddle = new Paddle(paddleRectangle, FRAME.getWidth() - THICKBORDERS, THICKBORDERS,
                this.level.paddleSpeed());
        paddle.setGui(this.runner.getGui());
        paddle.addToGame(this);
        BlockRemover listenerCollidable = new BlockRemover(this, numCollidable);
        ScoreTrackingListener listenerScore = new ScoreTrackingListener(score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(score, FONTTEXT, FRAME, this.level.levelName(), lives);
        scoreIndicator.addToGame(this);
        for (Block block:this.level.blocks()) {
            block.addToGame(this);
            block.addHitListener(listenerCollidable);
            block.addHitListener(listenerScore);
        }
        numCollidable.increase(this.level.numberOfBlocksToRemove());
        this.createBorders();
    }
    /**
     * add collidable border to the game.
     */
    private void createBorders() {
        Point topBorder = new Point(FRAME.getStartX(), FRAME.getStartY() + FONTTEXT);
        Rectangle top = new Rectangle(topBorder, FRAME.getWidth(), THICKBORDERS, COLORBORDERS);
        Point downBorder = new Point(FRAME.getStartX(), FRAME.getStartY() + FRAME.getHeight());
        Rectangle down = new Rectangle(downBorder, FRAME.getWidth(), THICKBORDERS, COLORBORDERS);
        Point leftBorder = new Point(FRAME.getStartX(), FRAME.getStartY() + FONTTEXT);
        Rectangle left = new Rectangle(leftBorder, THICKBORDERS, FRAME.getHeight(), COLORBORDERS);
        Point rightPoint = new Point(FRAME.getStartX() + FRAME.getWidth() - THICKBORDERS,
                FRAME.getStartY() + FONTTEXT);
        Rectangle right = new Rectangle(rightPoint, THICKBORDERS, FRAME.getHeight(), COLORBORDERS);
        Block topBlock = new Block(top);
        Block downBlock = new Block(down);
        BallRemover listenerBall = new BallRemover(this, numBalls);
        downBlock.addHitListener(listenerBall);
        Block leftBlock = new Block(left);
        Block rightBlock = new Block(right);
        topBlock.addToGame(this);
        downBlock.addToGame(this);
        leftBlock.addToGame(this);
        rightBlock.addToGame(this);
    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
    @Override
    public void shouldContinue() {
        this.running = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KEYFORWARD,
                    new PauseScreen(this.level.getDesignBackground(), this.level.getColorBalls())));
        }
        d.setColor(BACKGROUND);
        d.fillRectangle(0, 0, FRAME.getWidth(), FRAME.getHeight());
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.numCollidable.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        if (this.numBalls.getValue() == 0) {
            this.running = false;
        }
        // the logic from the previous run method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
    }
    /**
     * run the animation.
     */
    public void run() {
        this.createBallsOnTopOfPaddle(); // or a similar method
        Paddle paddle = (Paddle) (this.getEnvironment().getCollidables().get(0));
        double xPaddle = (FRAME.getWidth() / 2) - this.level.paddleWidth() / 2 + 0.5;
        paddle.setLocationPaddle(new Point(xPaddle, YPADDLE));
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }
    private void createBallsOnTopOfPaddle() {
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Point pointStart = new Point(FRAME.getWidth() / 2, YPADDLE - 0.1);
            Ball ball = new Ball(pointStart, RADIUS, this.level.getColorBalls());
            ball.setVelocity(this.level.initialBallVelocities().get(i));
            ball.addToGame(this);
        }
        numBalls.increase(level.numberOfBalls());
    }
    /**
     * give the number of blocks that remained.
     * @return the number of blocks that remained.
     */
    public int getNumCollidable() {
        return numCollidable.getValue();
    }
}