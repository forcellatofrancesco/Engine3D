package engine3d;

/**
 *
 * @author Francesco Forcellato
 */
public class Point3D {

    /**
     * These are the coordinates of the 3D point
     */
    private double x, y, z;

    /**
     * Constructor
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructor
     *
     * @param p array that contains x, y and z coordinates
     */
    public Point3D(double[] p) {
        if (p.length > 3) {
            throw new RuntimeException("p has more than 3 coordinates");
        } else if (p.length < 3) {
            throw new RuntimeException("p has less than 3 coordinates");
        }
        x = p[0];
        y = p[1];
        z = p[2];
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
     * Function to get the z coordinate
     *
     * @return z coordinate
     */
    public double getZ() {
        return z;
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

    /**
     * Function to set the z coordinate
     *
     * @param z coordinate to set
     */
    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Point3D{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }

}
