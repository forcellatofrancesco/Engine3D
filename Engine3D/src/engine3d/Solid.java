package engine3d;

import java.util.ArrayList;

/**
 *
 * @author Francesco Forcellato
 */
public class Solid {

    ArrayList<Point3D> v; //vector that represent nodes in a 3D space
    Graph con; //Graph that contains all the connections

    /**
     * Constructor
     */
    public Solid() {
        v = new ArrayList();
        con = new Graph();
    }

    /**
     * Function to get a node by a given position
     *
     * @param pos position where the node will be taken
     * @return array which contains the three coordinates
     */
    public double[] getNode(int pos) {
        Point3D h = v.get(pos);
        return new double[]{
            h.getX(),
            h.getY(),
            h.getZ()
        };
    }

    /**
     * Method to add a node
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public void addNode(double x, double y, double z) {
        v.add(new Point3D(x, y, z));
    }

    /**
     * Function to remove a node of the solid
     *
     * @param pos position of the node
     */
    public void removeNode(int pos) {
        v.remove(pos);
    }

    /**
     * Function to remove a node of the solid
     *
     * @param p array that represent the node
     */
    public void removeNode(double[] p) {
        v.remove(new Point3D(p));
    }

    /**
     * Function to add a point
     *
     * @param i index of the first point
     * @param j index of the second point
     */
    public void addConnection(int i, int j) {
        con.addConnection(i, j);
    }

    /**
     * Function to get the Graph that represent the connections
     *
     * @return Graph representing all the connections
     */
    protected Graph getConnections() {
        return con;
    }

    /**
     * Function to get the vector that represent the solid
     *
     * @return the vector that represent the solid
     */
    protected ArrayList<Point3D> getVector() {
        return v;
    }

    /**
     * Function that set the vector that represent the solid
     *
     * @param v
     */
    protected void setVector(ArrayList<Point3D> v) {
        this.v = v;
    }

    /**
     * Function that reset the connections
     */
    public void resetConnections() {
        con = new Graph();
    }

    /**
     * Function that reset the nodes of the solid
     */
    public void resetNodes() {
        v = new ArrayList();
    }
    
    /**
     * Function used to make a cube
     *
     * @param size size of the cube
     * @return the matrix that represent the cube
     */
    public static Solid makeCube(double size) {
        double s = size / 2;
        Solid res = new Solid();
        res.addNode(s, -s, s);
        res.addNode(-s, -s, s);
        res.addNode(-s, s, s);
        res.addNode(s, s, s);
        res.addNode(s, -s, -s);
        res.addNode(-s, -s, -s);
        res.addNode(-s, s, -s);
        res.addNode(s, s, -s);

        res.addConnection(0, 1);
        res.addConnection(0, 3);
        res.addConnection(0, 4);
        res.addConnection(1, 2);
        res.addConnection(1, 5);
        res.addConnection(2, 3);
        res.addConnection(2, 6);
        res.addConnection(3, 7);
        res.addConnection(4, 5);
        res.addConnection(4, 7);
        res.addConnection(5, 6);
        res.addConnection(6, 7);
        return res;
    }
}
