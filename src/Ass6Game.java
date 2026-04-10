//322631458 Nophar Glotman
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author      Nophar Glotman
 * @version     1
 * @since       1.6.22
 */
public class Ass6Game {
    static final Frame FRAME = new Frame(800, 600, 0, 0);
    static final GUI GUIGAME = new GUI("Arkanoid", FRAME.getWidth(), FRAME.getHeight());
    static final LevelInformation[] LEVEL_GAME = {new Level1(), new Level2(), new Level3(), new Level4()};

    /**
     * Start the game.
     * @param args User input (order of the game). If no order is given, levels run in default order.
     */
    public static void main(String[] args) {
        AnimationRunner runner = new AnimationRunner(GUIGAME, 60);
        KeyboardSensor ks = GUIGAME.getKeyboardSensor();
        GameFlow game = new GameFlow(runner, ks);

        // set orders levels
        List<LevelInformation> levels = setLevel(args, LEVEL_GAME);
        if (levels.isEmpty()) { // If no args are given
            levels = Arrays.asList(LEVEL_GAME); // Use the default order
        }
        game.runLevels(levels);
    }
    /**
     * Initialize the order of the game based on user input.
     * @param numbers User input as level indices.
     * @param levelGame The available levels in the game.
     * @return List of levels in the desired order.
     */
    private static List<LevelInformation> setLevel(String[] numbers, LevelInformation[] levelGame) {
        List<LevelInformation> levels = new ArrayList<>();
        int value;
        for (int i = 0; i < numbers.length; i++) {
            try {
                value = Integer.parseInt(numbers[i]);
            } catch (Exception e) {
                continue;
            }
            if ((value <= 4) && (value >= 1)) {
                levels.add(levelGame[Integer.parseInt(numbers[i]) - 1]);
            }
        }
        return levels;
    }
    /**
     * create array type int in order to array type string.
     * @param  numbers is array of numbers type string.
     * @return array type int that converted array type string.
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] intArray = new int[(numbers.length) - 1];
        for (int i = 1; i < numbers.length; i++) {
            intArray[i - 1] = Integer.parseInt(numbers[i]);
        }
        return intArray;
    }
}
