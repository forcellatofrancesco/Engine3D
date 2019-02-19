package engine3d;

/**
 *
 * @author Francesco Forcellato
 */
public class Point {

    private double x; //x coordinate
    private double y; //y coordinate

    /**
     * Constructor
     *
     * @param x coordinate
     * @param y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor
     * @param p array which contains x and y coordinates
     */
    public Point(double[] p) {
        if (p.length > 2) {
            throw new RuntimeException("p has more than 2 coordinates");
        } else if (p.length < 2) {
            throw new RuntimeException("p has less than 2 coordinates");
        }
        x = p[0];
        y = p[1];
    }

    /**
     * Function to get the x coordinate
     *
     * @return x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Function to get the y coordinate
     *
     * @return y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Function to set the x coordinate
     *
     * @param x coordinate to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Function to set the y coordinate
     *
     * @param y coordinate to set
     */
    public void setY(double y) {
        this.y = y;
    }

}
