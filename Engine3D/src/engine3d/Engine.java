package engine3d;

import java.util.ArrayList;
import static engine3d.EngineConversions.arrayToMatrix;
import static engine3d.EngineConversions.matrixToPoint;
import static engine3d.EngineConversions.matrixToVector;
import static engine3d.EngineConversions.point3DToArray;
import static engine3d.EngineConversions.points3DToMatrix;
import static engine3d.Matrix.multiply;
import static engine3d.Matrix.projection;
import static engine3d.Matrix.rotationX;
import static engine3d.Matrix.rotationY;
import static engine3d.Matrix.rotationZ;

/**
 *
 * @author Francesco Forcellato
 */
public abstract class Engine {

    private double height; ///height of the canvas
    private double width; ///width of the canvas
    private Object drawer; ///object needed to draw on the canvas
    private boolean isMap; ///flag that indicates if the points have to be mapped to the center

    /**
     * Constructor. The points will be mapped by default.
     *
     * @param height height of the canvas
     * @param width width of the canvas
     * @param drawer object needed to draw on the canvas
     */
    public Engine(double height, double width, Object drawer) {
        this.height = height;
        this.width = width;
        this.drawer = drawer;
        isMap = true;
    }

    /**
     * Constructor. The points will not be mapped by default.
     *
     * @param drawer
     */
    public Engine(Object drawer) {
        this.drawer = drawer;
        isMap = false;
    }

    /**
     * Function to allow the map of the points to the center
     *
     * @param height
     * @param width
     */
    public void mapToCentre(double height, double width) {
        this.height = height;
        this.width = width;
        isMap = true;
    }

    /**
     * Function that remove the mapping to the center feature.
     */
    public void noMap() {
        isMap = false;
    }

    /**
     * Function to get the height of the canvas
     *
     * @return height of the canvas
     */
    public double getHeight() {
        return height;
    }

    /**
     * Function to get the width of the canvas
     *
     * @return width of the canvas
     */
    public double getWidth() {
        return width;
    }

    /**
     * Function to set the height
     *
     * @param height height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Function to set the width
     *
     * @param width width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Function to map a point to the actual coordinate on the canvas
     *
     * @param x x position
     * @param y y position
     * @return the mapped point
     */
    private Point map(double x, double y) {
        Point res;
        if (isMap) {
            res = new Point(x + (width / 2), y + (height / 2));
        } else {
            res = new Point(x, y);
        }
        return res;
    }

    /**
     * Function to map a point to the actual coordinate on the canvas
     *
     * @param p point to be mapped
     * @return the point mapped
     */
    private Point map(Point p) {
        Point res;
        if (isMap) {
            res = new Point(p.getX() + (width / 2.0), p.getY() + (height / 2.0));
        } else {
            res = new Point(p.getX(), p.getY());
        }
        return res;
    }

    /**
     * Function that transform a giver 3D matrix into a 2D matrix
     *
     * @param solid matrix with 3 dimensional coordinates
     * @return matrix with 2 dimensional coordinates
     */
    private ArrayList<Point> transform3Dto2D(ArrayList<Point3D> solid) {
        ArrayList<Point> result = new ArrayList();
        for (int i = 0; i < solid.size(); i++) {
            result.add(matrixToPoint(multiply(projection, arrayToMatrix(point3DToArray(solid.get(i))))));
        }
        return result;
    }

    /**
     * Function that draw the figure on the canvas
     *
     * @param figure figure that must be drawn on the canvas
     * @param connections matrix that has all the connections from point to
     * point
     */
    private void draw2DFigure(ArrayList<Point> figure, Graph connections) {
        beginPath(drawer);
        for (int i = 0; i < connections.getRowsSize(); i++) {
            for (int j = 0; j < connections.getColumnSize(i); j++) {
                Point a = map(figure.get(i));
                Point b = map(figure.get(connections.getNext(i, j)));
                drawLine(a.getX(), a.getY(), b.getX(), b.getY(), drawer);
            }
        }
        closePath(drawer);
    }

    /**
     * Function to draw a solid on a canvas
     *
     * @param s solid to draw
     */
    public void drawSolid(Solid s) {
        draw2DFigure(transform3Dto2D(s.getVector()), s.getConnections());
    }

    /**
     * Function to get the drawer
     * @return object that draws
     */
    public Object getDrawer() {
        return drawer;
    }

    /**
     * Function to set the drawer
     * @param drawer object that draws
     */
    public void setDrawer(Object drawer) {
        this.drawer = drawer;
    }
    
    /**
     * Function that translate a solid by a given vector
     * 
     * @param s solid to translate
     * @param vector Point3D object representing a vector pointing in origin of the axes
     */
    public static void translate(Solid s, Point3D vector) {
        s.setVector(matrixToVector(Matrix.translate(points3DToMatrix(s.getVector()), point3DToArray(vector))));
    }
    
    /**
     * Function that checks if a given solid is drawn outside the canvas
     * 
     * @param s solid to check
     * @return boolean value (result of the test)
     */
    public boolean isOutsideCanvas(Solid s) {
        boolean ris = false;
        ArrayList<Point> pointsList = transform3Dto2D(s.getVector());
        int cursor = 0;
        while (!ris && cursor < pointsList.size()) {
            Point point = pointsList.get(cursor);
            System.out.println("x: "+point.getX() +" Y: "+point.getY());
            ris = point.getX() < -width/2 || point.getX() > width/2 || point.getY() < -height/2 || point.getY() > height/2;
            cursor++;
        }
        return ris;
    }
    
    /**
     * Function that scale a given solid
     * @param s solid to scale
     * @param size size to scale the solid
     */
    public void scale(Solid s, double size){
        s.setVector(matrixToVector(Matrix.scale(points3DToMatrix(s.getVector()), size)));
    }

    /**
     * Function that rotates a given 3D matrix in the X axes
     *
     * @param m matrix to be converted
     * @param angle angle of the final matrix
     */
    public static void rotateX(Solid m, double angle) {
        m.setVector(matrixToVector(multiply(points3DToMatrix(m.getVector()), rotationX(angle))));
    }

    /**
     * Function that rotates a given 3D matrix in the Y axes
     *
     * @param m matrix to be converted
     * @param angle angle of the final matrix
     */
    public static void rotateY(Solid m, double angle) {
        m.setVector(matrixToVector(multiply(points3DToMatrix(m.getVector()), rotationY(angle))));
    }

    /**
     * Function that rotates a given 3D matrix in the Z axes
     *
     * @param m matrix to be converted
     * @param angle angle of the final matrix
     */
    public static void rotateZ(Solid m, double angle) {
        m.setVector(matrixToVector(multiply(points3DToMatrix(m.getVector()), rotationZ(angle))));
    }

    /**
     * Function to draw a line between two points
     *
     * @param x0 x coordinate of the first point
     * @param y0 y coordinate of the first point
     * @param x1 x coordinate of the second point
     * @param y1 t coordinate of the second point
     * @param drawer object that draws
     */
    public abstract void drawLine(double x0, double y0, double x1, double y1, Object drawer);

    /**
     * Function needed for some drawers
     *
     * @param drawer object that draws
     */
    public abstract void beginPath(Object drawer);

    /**
     * Function needed for some drawers
     *
     * @param drawer object that draws
     */
    public abstract void closePath(Object drawer);

}

class EngineConversions {

    /**
     * Function that converts a vector into a matrix
     *
     * @param v vector to be converted
     * @return matrix n*1
     */
    protected static double[] point3DToArray(Point3D p) {
        double[] res = new double[3];
        res[0] = p.getX();
        res[1] = p.getY();
        res[2] = p.getZ();
        return res;
    }

    /**
     * Function that converts an array into a two dimensional array
     *
     * @param v array to be converted
     * @return matrix
     */
    protected static double[][] arrayToMatrix(double[] v) {
        double[][] res = new double[v.length][1];
        for (int i = 0; i < v.length; i++) {
            res[i][0] = v[i];
        }
        return res;
    }

    /**
     * Function that converts a matrix into an array
     *
     * @param m matrix to be converted
     * @return vector that represent the given matrix
     */
    protected static ArrayList<Point3D> matrixToVector(double[][] m) {
        ArrayList<Point3D> res = new ArrayList();
        for (double[] m1 : m) {
            res.add(new Point3D(m1[0], m1[1], m1[2]));
        }
        return res;
    }

    /**
     * Function that converts a 3D points into a matrix
     *
     * @param v vector that contains multiple point which represent a solid
     * @return matrix
     */
    protected static double[][] points3DToMatrix(ArrayList<Point3D> v) {
        double[][] res = new double[v.size()][3];
        for (int i = 0; i < v.size(); i++) {
            res[i] = point3DToArray(v.get(i));
        }
        return res;
    }

    /**
     * Function that converts a n*1 matrix into a vector
     *
     * @param m matrix to be converted
     * @return vector
     */
    protected static Point matrixToPoint(double[][] m) {
        return new Point(m[0][0], m[1][0]);
    }
}
