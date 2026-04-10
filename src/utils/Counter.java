//322631458 Nophar Glotman
/**
 * @author      Nophar Glotman
 * @version     1
 * @since       22.5.22
 */
public class Counter {
    private int counter;
    /**
     * create object Counter.
     */
    public Counter() {
        this.counter = 0;
    }
    /**
     * add number to current count.
     * @param number - number that add to the counter.
     */
    void increase(int number) {
        this.counter = this.getValue() + number;
    }
    /**
     * subtract number from current count.
     * @param number - number that subtract from the counter.
     */
    void decrease(int number) {
        this.counter = this.getValue() - number;
    }
    /**
     * get current count.
     * @return the value of the counter.
     */
    public int getValue() {
        return counter;
    }
}
