package engine3d;

import java.util.ArrayList;

/**
 *
 * @author Francesco Forcellato
 */
class Graph {

    ArrayList<ArrayList<Integer>> g; ///ArrayList of ArrayList that represent all the connections

    /**
     * Constructor
     */
    protected Graph() {
        g = new ArrayList();
    }

    /**
     * Function that increase the size of the Graph
     *
     * @param i index that it needs to reach
     */
    protected void refresh(int i) {
        for (int j = g.size() - 1; j < i; j++) {
            g.add(new ArrayList());
        }
    }

    /**
     * Function to add a connection between two points
     *
     * @param i position of the first coordinate in the vector of coordinates
     * @param j position of the second coordinate in the vector of coordinates
     */
    protected void addConnection(int i, int j) {
        if (i >= g.size()) {
            refresh(i);
        }
        g.get(i).add(j);
    }

    /**
     * Function that remove a connection
     *
     * @param i position of the first coordinate
     * @param j position of the second coordinate
     */
    protected void removeConnection(int i, int j) {
        g.get(i).remove(j);
    }

    /**
     * Function that get the number of the rows
     *
     * @return number of rows
     */
    protected int getRowsSize() {
        return g.size();
    }

    /**
     * Function that get the number of the columns of a given row
     *
     * @param pos number that identifies a row
     * @return the number of columns that has that row
     */
    protected int getColumnSize(int pos) {
        return g.get(pos).size();
    }

    /**
     * Function to get the next node to be connected
     *
     * @param i first node
     * @param j node to connect with the first node
     * @return position of the next node in the vector of nodes
     */
    protected int getNext(int i, int j) {
        return g.get(i).get(j);
    }
}
